package com.bbs.like.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bbs.common.core.domain.like.entity.FavoritesUser;
import com.bbs.common.core.domain.post.dto.MainDTO;

import java.util.List;

public interface IFavoritesUserService extends IService<FavoritesUser> {

    List<FavoritesUser> list(String userId);

    boolean favorite(Long userId, Long postId, Long favoriteUserId);

    List<MainDTO> infoList(String userId);
}
