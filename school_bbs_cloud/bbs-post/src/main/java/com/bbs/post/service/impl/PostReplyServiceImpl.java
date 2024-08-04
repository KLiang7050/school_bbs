package com.bbs.post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbs.api.user.RemoteUserService;
import com.bbs.common.core.constant.HttpStatus;
import com.bbs.common.core.domain.TableR;
import com.bbs.common.core.domain.post.dto.MyReplyDTO;
import com.bbs.common.core.domain.post.dto.ReplyDTO;
import com.bbs.common.core.domain.post.dto.ReplyListDTO;
import com.bbs.common.core.domain.post.entity.DetectionLog;
import com.bbs.common.core.domain.post.entity.PostContent;
import com.bbs.common.core.domain.post.entity.PostReply;
import com.bbs.common.core.domain.post.vo.PostAddVO;
import com.bbs.common.core.domain.user.entity.User;
import com.bbs.common.core.utils.post.PostUtils;
import com.bbs.common.core.utils.user.UserUtils;
import com.bbs.post.mapper.DetectionLogMapper;
import com.bbs.post.mapper.PostContentMapper;
import com.bbs.post.mapper.PostMainMapper;
import com.bbs.post.mapper.PostReplyMapper;
import com.bbs.post.service.IPostReplyService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostReplyServiceImpl extends ServiceImpl<PostReplyMapper, PostReply> implements IPostReplyService {
    @Autowired
    private PostContentMapper contentMapper;

    @Autowired
    private PostReplyMapper replyMapper;

    @Autowired
    private PostMainMapper mainMapper;

    @Autowired
    private DetectionLogMapper detectionLogMapper;

    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    public TableR getList(Long mainId, String orderByColumn) {
        TableR r = new TableR();
        r.setCode(HttpStatus.SUCCESS);
        r.setMsg("查询成功");
        List<ReplyListDTO> resultList = new ArrayList<>();
        List<ReplyDTO> reply2List = new ArrayList<>();
        List<ReplyDTO> reply1List = replyMapper.selectRootByMainId(mainId, orderByColumn);
        if (reply1List.isEmpty()) return r;
        List<Long> rootIdList = reply1List.stream().map(ReplyDTO::getId).collect(Collectors.toList());
        reply2List = replyMapper.selectByRootIdList(rootIdList);
        Set<Long> userIdList = new HashSet<>();
        // 获取对应的user
        Iterator<ReplyDTO> reply1I = reply1List.iterator();
        Iterator<ReplyDTO> reply2I = reply2List.iterator();
        for (int i = 0; i < Math.max(reply1List.size(), reply2List.size()); i++) {
            if (reply1I.hasNext()) {
                userIdList.add(reply1I.next().getUserId());
            }
            if (reply2I.hasNext()) {
                userIdList.add(reply2I.next().getUserId());
            }
        }
        if (userIdList.isEmpty()) return r;
        List<User> userList = remoteUserService.listByIds(userIdList).getData();

        // 处理回复间的逻辑关系
        for (ReplyDTO reply1Item : reply1List) {
            ReplyListDTO reply1 = new ReplyListDTO();
            BeanUtils.copyProperties(reply1Item, reply1);
            // 获取reply1Item对应的user
            reply1.setUser(UserUtils.getUser(userList, reply1.getUserId()));
            // 获取reply1Item对应的二级回复
            if (reply1Item.getReplyCount() > 0) {
                reply1.setReplyList(PostUtils.getReplyList(reply2List, reply1Item.getId(), userList));
            }
            resultList.add(reply1);
        }

        r.setTotal(new PageInfo(reply1List).getTotal());
        r.setRows(resultList);
        return r;
    }

    @Transactional
    @Override
    public int addReply(PostAddVO postAddVO, Long userId, Long mainId, Long parentUserId) {
        int row1, row2 = 0;
        PostContent content = new PostContent();
        PostReply reply = new PostReply();
        try {
            // 插入content
            content.setContent(postAddVO.getContent());
            content.setMediaUrl(postAddVO.getMediaUrls());
            row2 = contentMapper.insert(content);

            Integer floor = Math.toIntExact(count(new QueryWrapper<PostReply>().eq("main_id", mainId).eq("root_id", 0L)) + 2);
            User user = remoteUserService.infoById(parentUserId).getData();
            reply.setUserId(userId);
            reply.setParentId(mainId);
            reply.setMainId(mainId);
            reply.setParentUserId(parentUserId);
            reply.setParentUserName(user.getNickName());
            reply.setContentId(content.getId());
            reply.setFloor(floor);
            row1 = replyMapper.insert(reply);
            mainMapper.addReplyCount(mainId);
            remoteUserService.addPostCount(userId);
        } catch (Exception e) {
            throw new RuntimeException("发布失败 --- " + e.getMessage());
        }

        // 写入日志
        DetectionLog log = new DetectionLog();
        log.setContentId(reply.getContentId());
        log.setCreateTime(new Date());
        log.setIsHandle("0");
        log.setReplyFlag("1");
        detectionLogMapper.insert(log);
        return row1;
    }

    @Transactional
    @Override
    public int addReply2(PostReply reply, String content, Long userId) {
        int row1, row2, row3 = 0;
        try {
            // 插入content
            PostContent c = new PostContent();
            c.setContent(content);
            row2 = contentMapper.insert(c);

            Integer floor = Math.toIntExact(count(new QueryWrapper<PostReply>().eq("main_id", reply.getMainId()).eq("root_id", 0L)) + 2);
            reply.setContentId(c.getId());
            reply.setFloor(floor);
            reply.setUserId(userId);
            row1 = replyMapper.insert(reply);
            replyMapper.updateReplyCount(reply.getRootId());
            remoteUserService.addPostCount(userId);
        } catch (Exception e) {
            throw new RuntimeException("发布失败 --- " + e.getMessage());
        }
        // 写入日志
        DetectionLog log = new DetectionLog();
        log.setContentId(reply.getContentId());
        log.setCreateTime(new Date());
        log.setIsHandle("0");
        log.setReplyFlag("1");
        detectionLogMapper.insert(log);
        return row1;
    }

    @Override
    public List<ReplyDTO> getReply2List(Long rootId, String orderByColumn) {
        List<ReplyDTO> list = replyMapper.selectByRootId(rootId, orderByColumn);
        Set<Long> userIds = list.stream().map(ReplyDTO::getUserId).collect(Collectors.toSet());
        if (userIds.isEmpty()) return list;
        List<User> userList = remoteUserService.listByIds(userIds).getData();
        list.forEach(item -> item.setUser(UserUtils.getUser(userList, item.getUserId())));
        return list;
    }

    @Override
    public TableR getMyReply(Long userId) {
        TableR r = new TableR();
        r.setCode(HttpStatus.SUCCESS);
        r.setMsg("查询成功");
        List<MyReplyDTO> resultList = new ArrayList<>();
        // 根据用户id获取二级回复
        List<ReplyDTO> reply2 = replyMapper.selectReply2ByUserId(userId);
        if (reply2.isEmpty()) return r;
        // 获取二级回复id
        Set<Long> postIdList = reply2.stream().map(ReplyDTO::getParentId).collect(Collectors.toSet());
        // 获取父贴
        List<ReplyDTO> parent = replyMapper.selectByIds(postIdList);

        // 获取对应的user
        Set<Long> userIdList = new HashSet<>();
        Iterator<ReplyDTO> parentI = parent.iterator();
        for (int i = 0; i < reply2.size(); i++) {
            userIdList.add(reply2.get(i).getUserId());
            if (parentI.hasNext()) userIdList.add(parentI.next().getUserId());
        }
        if (userIdList.isEmpty()) return r;
        List<User> userList = remoteUserService.listByIds(userIdList).getData();

        // 处理回复间的逻辑关系
        for (ReplyDTO reply : reply2) {
            MyReplyDTO myReply = new MyReplyDTO();
            BeanUtils.copyProperties(reply, myReply);
            User user = UserUtils.getUser(userList, myReply.getUserId());
            int index = PostUtils.getParent(parent, myReply.getParentId());
            ReplyDTO p = parent.get(index);
            User pUser = UserUtils.getUser(userList, p.getUserId());
            p.setUser(pUser);
            myReply.setParent(p);
            myReply.setUser(user);
            resultList.add(myReply);
        }
        r.setTotal(new PageInfo(reply2).getTotal());
        r.setRows(resultList);
        return r;
    }

    @Override
    public boolean addLikeById(Long id) {
        return replyMapper.addLikeById(id);
    }

    @Override
    public Integer updatePostFlagByContentIdList(List<Long> okReplyContentIdList) {
        return replyMapper.updatePostFlagByContentIdList(okReplyContentIdList);
    }

    @Override
    public boolean minusLikeCount(Long postId) {
        return replyMapper.minusLikeBatchById(postId);
    }
}
