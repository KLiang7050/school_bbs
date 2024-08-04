package com.bbs.like.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bbs.common.core.domain.like.entity.FavoritesUser;
import com.bbs.common.core.domain.like.entity.LikeUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeUserMapper extends BaseMapper<LikeUser> {
}
