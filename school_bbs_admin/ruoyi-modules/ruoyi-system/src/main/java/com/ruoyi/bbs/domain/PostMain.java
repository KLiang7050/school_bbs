package com.ruoyi.bbs.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 贴子主贴对象 post_main
 * 
 * @author ckl
 * @date 2024-03-29
 */
public class PostMain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 类型 */
    private String type;

    /** 类型id */
    @Excel(name = "类型id")
    private Long typeId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 回复id */
    @Excel(name = "回复id")
    private Long contentId;

    /** 总回复数 */
    @Excel(name = "总回复数")
    private Long replyCount;

    /** 点赞总数 */
    @Excel(name = "点赞总数")
    private Long likes;

    /** 收藏数 */
    @Excel(name = "收藏数")
    private Long favorites;

    /** 状态（1:显示,0:隐藏） */
    @Excel(name = "状态", readConverterExp = "1=:显示,0:隐藏")
    private String isEnable;

    /** 逻辑删除（0:未删除；1:已删除） */
    private String delFlag;

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
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContentId(Long contentId) 
    {
        this.contentId = contentId;
    }

    public Long getContentId() 
    {
        return contentId;
    }
    public void setReplyCount(Long replyCount) 
    {
        this.replyCount = replyCount;
    }

    public Long getReplyCount() 
    {
        return replyCount;
    }
    public void setLikes(Long likes) 
    {
        this.likes = likes;
    }

    public Long getLikes() 
    {
        return likes;
    }
    public void setFavorites(Long favorites) 
    {
        this.favorites = favorites;
    }

    public Long getFavorites() 
    {
        return favorites;
    }
    public void setIsEnable(String isEnable) 
    {
        this.isEnable = isEnable;
    }

    public String getIsEnable() 
    {
        return isEnable;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("type", getType())
            .append("typeId", getTypeId())
            .append("title", getTitle())
            .append("contentId", getContentId())
            .append("replyCount", getReplyCount())
            .append("likes", getLikes())
            .append("favorites", getFavorites())
            .append("isEnable", getIsEnable())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
