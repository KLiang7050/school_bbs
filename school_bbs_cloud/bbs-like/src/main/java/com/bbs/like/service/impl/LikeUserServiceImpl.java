package com.bbs.like.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbs.api.post.RemotePostService;
import com.bbs.api.user.RemoteUserService;
import com.bbs.common.core.domain.like.entity.LikeUser;
import com.bbs.like.mapper.LikeUserMapper;
import com.bbs.like.service.ILikeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class LikeUserServiceImpl extends ServiceImpl<LikeUserMapper, LikeUser> implements ILikeUserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private LikeUserMapper likeUserMapper;

    @Autowired
    private RemotePostService remotePostService;
    @Autowired
    private RemoteUserService remoteUserService;

    @Transactional
    @Override
    public boolean like(Long userId, Long postId, String type, Long mainId, Long likeUserId) {
        // 查看是否已点赞
        LikeUser likeUser = baseMapper.selectOne(
                new QueryWrapper<LikeUser>()
                        .eq("user_id", userId)
                        .eq("post_id", postId)
                        .eq("type", type));
        if (likeUser == null) { // 未点赞
            LikeUser temp = new LikeUser();
            temp.setUserId(userId);
            temp.setPostId(postId);
            temp.setType(type);
            temp.setMainId(mainId);
            // 插入点赞记录
            baseMapper.insert(temp);
            if (type.equals("0")) { // 判断被点赞的是主贴还是回复贴
                int code = remotePostService.addMainLikeCount(postId).getCode(); // 更新主贴被点赞数
                if (code == 200) {
                    remoteUserService.addLikeCount(likeUserId); // 更新用户被点赞数
                }
            } else {
                remotePostService.addReplyLikeCount(postId); // 更新回复贴被点赞数
            }
            return true;
        } else { // 已点赞
            // 取消点赞，删除点赞记录
            baseMapper.delete(new QueryWrapper<LikeUser>().eq("user_id", userId).eq("post_id", postId));
            if (type.equals("0")) { // 判断被点赞的是主贴还是回复贴
                int code = remotePostService.minusMainLikeCount(postId).getCode(); // 更新主贴被点赞数
                if (code == 200) {
                    remoteUserService.minusLikeCount(likeUserId); // 更新用户被点赞数
                }
            } else {
                remotePostService.minusReplyLikeCount(postId);  // 更新回复贴被点赞数
            }
            return false;
        }
    }

    @Override
    public List<LikeUser> list(String userId, Long mainId) {
        return baseMapper.selectList(new QueryWrapper<LikeUser>().eq("main_id", mainId));
    }

    // @Scheduled(fixedRate = 30000)
    // public void likeMainCount2DB() {
    //     String time = LocalDateTime.now().minusSeconds(30).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    //     List<Map<String, Object>> list = likeUserMapper.selectMaps(
    //             new QueryWrapper<LikeUser>()
    //                     .select("post_id", "count(*) as count")
    //                     .eq("type", "0")
    //                     .ge("create_time", time)
    //                     .groupBy("post_id"));
    //     if (list != null && list.size() != 0) {
    //         R r = remotePostService.addMainLikeCount(list);
    //     }
    // }
    //
    // @Scheduled(fixedRate = 30000)
    // public void likeReplyCount2DB() {
    //     String time = LocalDateTime.now().minusSeconds(30).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    //     List<Map<String, Object>> list = likeUserMapper.selectMaps(
    //             new QueryWrapper<LikeUser>()
    //                     .select("post_id", "count(*) as count")
    //                     .eq("type", "1")
    //                     .ge("create_time", time)
    //                     .groupBy("post_id"));
    //     if (list != null && list.size() != 0) {
    //         R r = remotePostService.addReplyLikeCount(list);
    //     }
    // }

    // @Override
    // public boolean like(String userId, String postId) {
    //     boolean isLiked = isLikedValidate(userId, postId);
    //     if (isLiked) {
    //         // 已点赞，删除点赞记录
    //         redisTemplate.opsForHash().delete("like:user:" + postId, userId);
    //         return false;
    //     } else {
    //         // 未点赞，添加点赞记录
    //         redisTemplate.opsForHash().put("like:user:" + postId, userId, "1");
    //         return true;
    //     }
    // }

    private boolean isLikedValidate(String userId, String postId) {
        Boolean hasKey = redisTemplate.opsForHash().hasKey("like:user:" + postId, userId);
        if (hasKey) {
            return true;
        } else {
            return baseMapper.exists(new QueryWrapper<LikeUser>()
                    .eq("user_id", userId)
                    .eq("post_id", postId));
        }
    }


}
