package com.ruoyi.bbs.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 点赞用户关联对象 like_user
 * 
 * @author ckl
 * @date 2024-04-05
 */
public class LikeUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键")
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 贴子id */
    @Excel(name = "贴子id")
    private Long postId;

    /** 主贴id */
    @Excel(name = "主贴id")
    private Long mainId;

    /** 类型（0：main；1：reply） */
    @Excel(name = "类型", readConverterExp = "0=：main；1：reply")
    private String type;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setPostId(Long postId) 
    {
        this.postId = postId;
    }

    public Long getPostId() 
    {
        return postId;
    }
    public void setMainId(Long mainId) 
    {
        this.mainId = mainId;
    }

    public Long getMainId() 
    {
        return mainId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("postId", getPostId())
            .append("mainId", getMainId())
            .append("type", getType())
            .append("createTime", getCreateTime())
            .toString();
    }
}
