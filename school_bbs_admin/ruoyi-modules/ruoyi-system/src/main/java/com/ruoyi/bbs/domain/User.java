package com.ruoyi.bbs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 用户对象 user
 * 
 * @author ckl
 * @date 2024-04-05
 */
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickName;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 手机号 */
    @Excel(name = "手机号")
    private String tel;

    /** 性别（1：男；2：女） */
    @Excel(name = "性别", readConverterExp = "1=：男；2：女")
    private String sex;

    /** 头像 */
    @Excel(name = "头像")
    private String avatar;

    /** 简介 */
    @Excel(name = "简介")
    private String introduce;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 学号 */
    @Excel(name = "学号")
    private Long studentId;

    /** 学校 */
    @Excel(name = "学校")
    private String school;

    /** 学院 */
    @Excel(name = "学院")
    private String college;

    /** 专业 */
    @Excel(name = "专业")
    private String major;

    /** 贴子数量 */
    @Excel(name = "贴子数量")
    private Long postCount;

    /** 获收藏数 */
    @Excel(name = "获收藏数")
    private Long favoritesCount;

    /** 获赞数 */
    @Excel(name = "获赞数")
    private Long likeCount;

    /** 账号状态（1：已禁用；0：未禁用） */
    @Excel(name = "账号状态", readConverterExp = "1=：已禁用；0：未禁用")
    private String isEnable;

    /** 逻辑删除标识（1：已删除；0：未删除） */
    @Excel(name = "逻辑删除标识", readConverterExp = "1=：已删除；0：未删除")
    private String delFlag;

    /** 入学时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入学时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enrollmentTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRealName(String realName) 
    {
        this.realName = realName;
    }

    public String getRealName() 
    {
        return realName;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setIntroduce(String introduce) 
    {
        this.introduce = introduce;
    }

    public String getIntroduce() 
    {
        return introduce;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setSchool(String school) 
    {
        this.school = school;
    }

    public String getSchool() 
    {
        return school;
    }

    public void setCollege(String college)
    {
        this.college = college;
    }

    public String getCollege() 
    {
        return college;
    }

    public void setMajor(String major)
    {
        this.major = major;
    }

    public String getMajor() 
    {
        return major;
    }

    public void setPostCount(Long postCount)
    {
        this.postCount = postCount;
    }

    public Long getPostCount() 
    {
        return postCount;
    }
    public void setFavoritesCount(Long favoritesCount) 
    {
        this.favoritesCount = favoritesCount;
    }

    public Long getFavoritesCount() 
    {
        return favoritesCount;
    }
    public void setLikeCount(Long likeCount) 
    {
        this.likeCount = likeCount;
    }

    public Long getLikeCount() 
    {
        return likeCount;
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
    public void setEnrollmentTime(Date enrollmentTime) 
    {
        this.enrollmentTime = enrollmentTime;
    }

    public Date getEnrollmentTime() 
    {
        return enrollmentTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("realName", getRealName())
            .append("nickName", getNickName())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("tel", getTel())
            .append("sex", getSex())
            .append("avatar", getAvatar())
            .append("introduce", getIntroduce())
            .append("birthday", getBirthday())
            .append("studentId", getStudentId())
            .append("school", getSchool())
            .append("college", getCollege())
            .append("major", getMajor())
            .append("postCount", getPostCount())
            .append("favoritesCount", getFavoritesCount())
            .append("likeCount", getLikeCount())
            .append("isEnable", getIsEnable())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("enrollmentTime", getEnrollmentTime())
            .toString();
    }
}
