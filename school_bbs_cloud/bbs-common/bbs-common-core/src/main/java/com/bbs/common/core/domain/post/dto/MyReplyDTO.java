package com.bbs.common.core.domain.post.dto;

public class MyReplyDTO extends ReplyDTO{
    private ReplyDTO parent;

    public ReplyDTO getParent() {
        return parent;
    }

    public void setParent(ReplyDTO parent) {
        this.parent = parent;
    }
}
