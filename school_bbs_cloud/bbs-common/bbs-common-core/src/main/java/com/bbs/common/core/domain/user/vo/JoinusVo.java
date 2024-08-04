package com.bbs.common.core.domain.user.vo;

import com.bbs.common.core.domain.user.entity.Joinus;

public class JoinusVo extends Joinus {
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
