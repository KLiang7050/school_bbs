package com.ruoyi.bbs.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 贴子回复对象 post_reply
 * 
 * @author ckl
 * @date 2024-04-05
 */
public class PostReply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 父评论id */
    @Excel(name = "父评论id")
    private Long parentId;

    /** 父评论的userid */
    @Excel(name = "父评论的userid")
    private Long parentUserId;

    /** 父评论的username */
    @Excel(name = "父评论的username")
    private String parentUserName;

    /** 回复id */
    @Excel(name = "回复id")
    private Long contentId;

    /** 根贴子id (0：自身为根贴) */
    @Excel(name = "根贴子id (0：自身为根贴)")
    private Long rootId;

    /** 主贴子id(0：自身为主贴) */
    @Excel(name = "主贴子id(0：自身为主贴)")
    private Long mainId;

    /** 总回复数 */
    @Excel(name = "总回复数")
    private Long replyCount;

    /** 楼层 */
    @Excel(name = "楼层")
    private Long floor;

    /** 点赞总数 */
    @Excel(name = "点赞总数")
    private Long likes;

    /** 状态（1:显示,0:隐藏） */
    @Excel(name = "状态", readConverterExp = "1=:显示,0:隐藏")
    private String isEnable;

    /** 逻辑删除（0:未删除；1:已删除） */
    @Excel(name = "逻辑删除", readConverterExp = "0=:未删除；1:已删除")
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
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setParentUserId(Long parentUserId) 
    {
        this.parentUserId = parentUserId;
    }

    public Long getParentUserId() 
    {
        return parentUserId;
    }
    public void setParentUserName(String parentUserName) 
    {
        this.parentUserName = parentUserName;
    }

    public String getParentUserName() 
    {
        return parentUserName;
    }
    public void setContentId(Long contentId) 
    {
        this.contentId = contentId;
    }

    public Long getContentId() 
    {
        return contentId;
    }
    public void setRootId(Long rootId) 
    {
        this.rootId = rootId;
    }

    public Long getRootId() 
    {
        return rootId;
    }
    public void setMainId(Long mainId) 
    {
        this.mainId = mainId;
    }

    public Long getMainId() 
    {
        return mainId;
    }
    public void setReplyCount(Long replyCount) 
    {
        this.replyCount = replyCount;
    }

    public Long getReplyCount() 
    {
        return replyCount;
    }
    public void setFloor(Long floor) 
    {
        this.floor = floor;
    }

    public Long getFloor() 
    {
        return floor;
    }
    public void setLikes(Long likes) 
    {
        this.likes = likes;
    }

    public Long getLikes() 
    {
        return likes;
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
            .append("parentId", getParentId())
            .append("parentUserId", getParentUserId())
            .append("parentUserName", getParentUserName())
            .append("contentId", getContentId())
            .append("rootId", getRootId())
            .append("mainId", getMainId())
            .append("replyCount", getReplyCount())
            .append("floor", getFloor())
            .append("likes", getLikes())
            .append("isEnable", getIsEnable())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
