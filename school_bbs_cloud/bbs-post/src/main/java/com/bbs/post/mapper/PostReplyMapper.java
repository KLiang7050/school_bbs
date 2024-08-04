package com.bbs.post.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bbs.common.core.domain.post.dto.ReplyDTO;
import com.bbs.common.core.domain.post.entity.PostMain;
import com.bbs.common.core.domain.post.entity.PostReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface PostReplyMapper extends BaseMapper<PostReply> {

    List<ReplyDTO> selectRootByMainId(@Param("mainId") Long mainId,
                                      @Param("orderByColumn") String orderByColumn);

    List<ReplyDTO> selectByRootIdList(@Param("rootIdList") List<Long> rootIdList);

    int updateReplyCount(@Param("id") Long id);

    List<ReplyDTO> selectByRootId(@Param("rootId") Long rootId, @Param("orderByColumn") String orderByColumn);

    List<ReplyDTO> selectReply2ByUserId(@Param("userId") Long userId);

    List<ReplyDTO> selectByIds(@Param("postIdList")Set<Long> postIdList);

    boolean addLikeBatchById(List<PostMain> postList);

    boolean addLikeById(@Param("id") Long id);

    boolean minusLikeBatchById(Long postId);

    Integer updatePostFlagByContentIdList(List<Long> okReplyContentIdList);
}
