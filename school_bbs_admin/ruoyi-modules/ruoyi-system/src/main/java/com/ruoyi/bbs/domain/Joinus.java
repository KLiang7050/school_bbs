package com.ruoyi.bbs.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 入驻申请对象 joinus
 * 
 * @author ckl
 * @date 2024-04-05
 */
public class Joinus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键")
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 学校名 */
    @Excel(name = "学校名")
    private String school;

    /** 文件url */
    @Excel(name = "文件url")
    private String fileUrl;

    /** 申请明细 */
    @Excel(name = "申请明细")
    private String description;

    /** 负责人 */
    @Excel(name = "负责人")
    private String auditor;

    /** 结果详情 */
    @Excel(name = "结果详情")
    private String descriptionRes;

    /** 状态(0：申请，1：审核，2：详谈，3：归档) */
    @Excel(name = "状态(0：申请，1：审核，2：详谈，3：归档)")
    private String status;

    /** 回退状态(1：正常，2：回退) */
    @Excel(name = "回退状态(1：正常，2：回退)")
    private String backFlag;

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
    public void setSchool(String school) 
    {
        this.school = school;
    }

    public String getSchool() 
    {
        return school;
    }
    public void setFileUrl(String fileUrl) 
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() 
    {
        return fileUrl;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setAuditor(String auditor) 
    {
        this.auditor = auditor;
    }

    public String getAuditor() 
    {
        return auditor;
    }
    public void setDescriptionRes(String descriptionRes) 
    {
        this.descriptionRes = descriptionRes;
    }

    public String getDescriptionRes() 
    {
        return descriptionRes;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setBackFlag(String backFlag) 
    {
        this.backFlag = backFlag;
    }

    public String getBackFlag() 
    {
        return backFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("school", getSchool())
            .append("fileUrl", getFileUrl())
            .append("description", getDescription())
            .append("createTime", getCreateTime())
            .append("auditor", getAuditor())
            .append("descriptionRes", getDescriptionRes())
            .append("status", getStatus())
            .append("backFlag", getBackFlag())
            .toString();
    }
}
