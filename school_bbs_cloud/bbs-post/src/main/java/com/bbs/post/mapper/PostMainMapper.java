package com.bbs.post.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bbs.common.core.domain.post.dto.MainDTO;
import com.bbs.common.core.domain.post.entity.PostMain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMainMapper extends BaseMapper<PostMain> {

    int insertOutId(PostMain postMain);

    MainDTO selectById(@Param("id") Long id);

    List<MainDTO> selectMainList(@Param("title") String title,
                                 @Param("typeId") Integer typeId,
                                 @Param("orderByColumn") String orderByColumn);

    List<MainDTO> selectHots();

    boolean minusLikeBatchById(@Param("id") Long id);

    boolean addLikeById(@Param("id")Long id);

    boolean addFavoritesById(@Param("id")Long id);

    boolean minusFavoritesById(@Param("id")Long id);

    List<MainDTO> selectByIdList(@Param("idList") List<Long> idList);

    List<MainDTO> selectMyList(@Param("userId") Long userId);

    Integer updatePostFlagByContentIdList(@Param("list") List<Long> okContentIdList);

    void addReplyCount(@Param("id") Long id);
}
