package com.bbs.post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbs.api.user.RemoteUserService;
import com.bbs.common.core.constant.HttpStatus;
import com.bbs.common.core.domain.TableR;
import com.bbs.common.core.domain.post.dto.MainDTO;
import com.bbs.common.core.domain.post.entity.DetectionLog;
import com.bbs.common.core.domain.post.entity.PostContent;
import com.bbs.common.core.domain.post.entity.PostMain;
import com.bbs.common.core.domain.post.entity.PostReply;
import com.bbs.common.core.domain.post.vo.PostAddVO;
import com.bbs.common.core.domain.user.entity.User;
import com.bbs.common.core.utils.user.UserUtils;
import com.bbs.post.mapper.DetectionLogMapper;
import com.bbs.post.mapper.PostContentMapper;
import com.bbs.post.mapper.PostMainMapper;
import com.bbs.post.mapper.PostReplyMapper;
import com.bbs.post.service.IPostMainService;
import com.github.pagehelper.PageInfo;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostMainServiceImpl extends ServiceImpl<PostMainMapper, PostMain> implements IPostMainService {

    @Autowired
    private PostMainMapper postMainMapper;
    @Autowired
    private PostReplyMapper replyMapper;
    @Autowired
    private DetectionLogMapper detectionLogMapper;
    @Autowired
    private PostContentMapper postContentMapper;
    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    public TableR getList(String title, Integer typeId, String orderByColumn) {
        TableR r = new TableR();
        r.setCode(HttpStatus.SUCCESS);
        r.setMsg("查询成功");

        // 获取根节点集合
        List<MainDTO> result = postMainMapper.selectMainList(title, typeId, orderByColumn);
        if (result.size() < 1) {
            r.setRows(result);
            return r;
        }

        setUserAndURL(result);
        r.setTotal(new PageInfo(result).getTotal());
        r.setRows(result);
        return r;
    }

    private void setUserAndURL(List<MainDTO> postList) {
        if (postList.isEmpty()) return;
        List<User> userList = new ArrayList<>();

        // 根据根节点userid获取user集合
        HashSet<Long> userIds = postList.stream().map(MainDTO::getUserId).collect(Collectors.toCollection(HashSet::new));
        if(userIds.isEmpty()) return;
        userList = remoteUserService.listByIds(userIds).getData();

        for (MainDTO item : postList) {
            // set贴子对应的user
            User user = UserUtils.getUser(userList, item.getUserId());
            item.setUser(user);

            // 整理urlDto
            List<String> urlList = new ArrayList<>();
            String mediaUrl = item.getMediaUrl();
            if (!StringUtils.isBlank(mediaUrl)) {
                urlList = Arrays.stream(mediaUrl.split(";;")).collect(Collectors.toList());
            }
            item.setUrlList(urlList);
        }
    }

    @Override
    public List<MainDTO> getHots() {
        List<MainDTO> mainDTOs = postMainMapper.selectHots();
        setUserAndURL(mainDTOs);
        return mainDTOs;
    }

    @Override
    public boolean addLikeById(Long id) {
        return postMainMapper.addLikeById(id);
    }

    @Override
    public boolean minusLikeCount(Long postId) {
        return postMainMapper.minusLikeBatchById(postId);
    }

    @Override
    public boolean addFavoritesById(Long postId) {
        return postMainMapper.addFavoritesById(postId);
    }

    @Override
    public boolean minusFavoritesById(Long postId) {
        return postMainMapper.minusFavoritesById(postId);
    }

    @Override
    public List<MainDTO> list(List<Long> idList) {
        List<MainDTO> list = baseMapper.selectByIdList(idList);
        setUserAndURL(list);
        return list;
    }

    @Override
    public TableR getMyPostList(Long userId) {
        TableR r = new TableR();
        r.setCode(HttpStatus.SUCCESS);
        r.setMsg("查询成功");

        // 获取根节点集合
        List<MainDTO> result = postMainMapper.selectMyList(userId);
        if (result.size() < 1) {
            r.setRows(result);
            return r;
        }
        r.setTotal(new PageInfo(result).getTotal());
        r.setRows(result);
        return r;
    }

    @Transactional
    @Override
    public boolean update(PostAddVO postAddVO) {
        boolean res = false;
        // new UpdateWrapper<PostMain>().set("type_id",postAddVO.getTypeId()).set("title",postAddVO.getTitle())
        PostMain postMain = new PostMain();
        BeanUtils.copyProperties(postAddVO, postMain);
        res = baseMapper.updateById(postMain) > 0;
        PostContent content = new PostContent();
        content.setContent(postAddVO.getContent());
        content.setId(postMain.getContentId());
        content.setMediaUrl(postAddVO.getMediaUrls());
        postContentMapper.updateById(content);
        DetectionLog log = new DetectionLog();
        log.setContentId(postMain.getContentId());
        log.setCreateTime(new Date());
        log.setIsHandle("0");
        log.setReplyFlag("0");
        detectionLogMapper.insert(log);
        return res;
    }

    @Transactional
    @Override
    public boolean delById(Long id, String userId) {
        boolean res = false;
        res = this.removeById(id);
        res = replyMapper.delete(new QueryWrapper<PostReply>().eq("main_id", id)) >= 0;
        if (res) {
            res = remoteUserService.minusPostCount(Long.valueOf(userId)).getCode() > 0;
        }
        if (!res) throw new RuntimeException("删除失败");
        return res;
    }


    @Transactional
    @Override
    public Long addMain(PostAddVO postAddVO, Long userId) {
        PostContent postContent = new PostContent();
        PostMain postMain = new PostMain();
        try {
            // 插入content
            postContent.setContent(postAddVO.getContent());
            postContent.setMediaUrl(postAddVO.getMediaUrls());
            postContentMapper.insert(postContent);

            // 插入main
            postMain.setContentId(postContent.getId());
            postMain.setType(postAddVO.getType());
            postMain.setTypeId(postAddVO.getTypeId());
            postMain.setUserId(userId);
            postMain.setTitle(postAddVO.getTitle());
            postMain.setIsEnable("2"); // 待审核状态
            postMainMapper.insertOutId(postMain);

            // 用户贴子数+1
            remoteUserService.addPostCount(userId);

        } catch (Exception e) {
            throw new RuntimeException("发布失败 --- " + e.getMessage());
        }
        // 写入日志
        DetectionLog log = new DetectionLog();
        log.setContentId(postMain.getContentId());
        log.setCreateTime(new Date());
        log.setIsHandle("0");
        log.setReplyFlag("0");
        detectionLogMapper.insert(log);
        return postMain.getId();
    }

    @Override
    public MainDTO mainInfo(Long id) {
        // 获取主贴
        MainDTO main = postMainMapper.selectById(id);
        // 获取用户
        User user = remoteUserService.infoById(main.getUserId()).getData();
        main.setUser(user);

        return main;
    }

    @Override
    public Integer updatePostFlagByContentIdList(List<Long> okContentIdList) {
        return postMainMapper.updatePostFlagByContentIdList(okContentIdList);
    }

}
