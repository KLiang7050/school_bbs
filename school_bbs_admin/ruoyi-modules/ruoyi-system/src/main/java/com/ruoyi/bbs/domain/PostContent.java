package com.ruoyi.bbs.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 贴子内容对象 post_content
 * 
 * @author ckl
 * @date 2024-03-29
 */
public class PostContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 媒体url集合，用【;;】分割url */
    @Excel(name = "媒体url集合，用【;;】分割url")
    private String mediaUrl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setMediaUrl(String mediaUrl) 
    {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaUrl() 
    {
        return mediaUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("content", getContent())
            .append("mediaUrl", getMediaUrl())
            .toString();
    }
}
