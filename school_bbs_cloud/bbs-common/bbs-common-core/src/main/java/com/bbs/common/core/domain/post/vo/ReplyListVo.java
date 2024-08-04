package com.bbs.common.core.domain.post.vo;


import com.bbs.common.core.domain.post.dto.ReplyListDTO;

import java.util.List;

public class ReplyListVo {
    private List<ReplyListDTO> replyList;

    public ReplyListVo() {
    }

    public ReplyListVo(List<ReplyListDTO> replyList) {
        this.replyList = replyList;
    }

    public List<ReplyListDTO> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyListDTO> replyList) {
        this.replyList = replyList;
    }
}
