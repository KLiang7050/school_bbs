package com.bbs.common.core.domain.post.dto;

import com.bbs.common.core.domain.post.dto.ReplyDTO;

import java.util.List;

public class ReplyListDTO extends ReplyDTO {
    private List<ReplyDTO> replyList;

    public List<ReplyDTO> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyDTO> replyList) {
        this.replyList = replyList;
    }
}
