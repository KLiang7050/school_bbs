package com.bbs.like.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbs.api.post.RemotePostService;
import com.bbs.api.user.RemoteUserService;
import com.bbs.common.core.domain.like.entity.FavoritesUser;
import com.bbs.common.core.domain.post.dto.MainDTO;
import com.bbs.like.mapper.FavoritesUserMapper;
import com.bbs.like.service.IFavoritesUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritesUserServiceImpl extends ServiceImpl<FavoritesUserMapper, FavoritesUser> implements IFavoritesUserService {

    @Autowired
    private FavoritesUserMapper favoritesUserMapper;

    @Autowired
    private RemotePostService remotePostService;

    @Autowired
    private RemoteUserService remoteUserService;

    @Transactional
    @Override
    public boolean favorite(Long userId, Long postId, Long favoriteUserId) {
        FavoritesUser favorite = favoritesUserMapper.selectOne(new QueryWrapper<FavoritesUser>()
                .eq("post_id", postId)
                .eq("user_id", userId));
        if (favorite != null) {
            baseMapper.deleteById(favorite.getId());
            remotePostService.minusFavoritesCount(postId);
            remoteUserService.minusFavoriteCount(favoriteUserId);
            return false;
        } else {
            FavoritesUser favoritesUser = new FavoritesUser();
            favoritesUser.setUserId(userId);
            favoritesUser.setPostId(postId);
            baseMapper.insert(favoritesUser);
            remotePostService.addMainFavoritesCount(postId);
            remoteUserService.addFavoriteCount(favoriteUserId);
            return true;
        }
    }

    @Override
    public List<MainDTO> infoList(String userId) {
        List<FavoritesUser> list = baseMapper.selectList(
                new QueryWrapper<FavoritesUser>()
                        .eq("user_id", userId)
                        .orderByDesc("create_time"));
        if(list == null || list.size() == 0){
            return new ArrayList<>();
        }
        List<Long> idList = list.stream().map(FavoritesUser::getPostId).collect(Collectors.toList());
        return remotePostService.listbyid(idList).getData();
    }

    @Override
    public List<FavoritesUser> list(String userId) {
        return baseMapper.selectList(new QueryWrapper<FavoritesUser>().eq("user_id",userId));
    }
}
