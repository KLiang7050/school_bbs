package com.bbs.common.core.domain.post.dto;


import com.bbs.common.core.domain.post.entity.DetectionLog;

public class DetectionLogDTO extends DetectionLog {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
