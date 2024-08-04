package com.ruoyi.bbs.domain.dto;


import com.ruoyi.bbs.domain.PostMain;
import com.ruoyi.bbs.domain.User;

import java.util.List;

public class MainDTO extends PostMain {
    /**
     * 所属贴子内容
     */
    private String content;

    /**
     * 所属贴子图片url
     */
    private String mediaUrl;

    /**
     * 整理成list后的url(目前仅首页用到)
     */
    private List<String> urlList;

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

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
