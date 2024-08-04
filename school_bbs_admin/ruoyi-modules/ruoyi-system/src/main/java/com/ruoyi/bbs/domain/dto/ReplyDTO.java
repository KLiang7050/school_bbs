package com.ruoyi.bbs.domain.dto;

import com.ruoyi.bbs.domain.PostReply;
import com.ruoyi.bbs.domain.User;

public class ReplyDTO extends PostReply {
    /**
     * 所属贴子内容
     */
    private String content;

    /**
     * 所属用户
     */
    private User user;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
