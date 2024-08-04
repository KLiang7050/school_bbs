package com.bbs.like.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bbs.common.core.domain.like.entity.LikeUser;

import java.util.List;

public interface ILikeUserService extends IService<LikeUser> {

    boolean like(Long userId, Long postId, String type, Long mainId, Long likeUserId);

    List<LikeUser> list(String userId, Long mainId);
}
