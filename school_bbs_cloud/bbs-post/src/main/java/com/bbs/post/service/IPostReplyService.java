package com.bbs.post.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bbs.common.core.domain.TableR;
import com.bbs.common.core.domain.post.dto.ReplyDTO;
import com.bbs.common.core.domain.post.entity.PostReply;
import com.bbs.common.core.domain.post.vo.PostAddVO;

import java.util.List;

/**
 * 贴子Service接口
 * 
 * @author ckl
 * @date 2023-11-11
 */
public interface IPostReplyService extends IService<PostReply>
{
    TableR getList(Long mainId, String orderByColumn);

    int addReply(PostAddVO postAddVO, Long userId, Long mainId, Long parentUserId);

    int addReply2(PostReply reply, String content, Long userId);

    List<ReplyDTO> getReply2List(Long rootId, String orderByColumn);

    TableR getMyReply(Long userId);

    boolean minusLikeCount(Long postId);

    // boolean addLikeBatchById(List<Map<String, Object>> list);

    boolean addLikeById(Long id);

    Integer updatePostFlagByContentIdList(List<Long> okReplyContentIdList);
}
