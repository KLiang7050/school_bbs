package com.ruoyi.bbs.service;

import java.util.List;
import com.ruoyi.bbs.domain.LikeUser;

/**
 * 点赞用户关联Service接口
 * 
 * @author ckl
 * @date 2024-04-05
 */
public interface ILikeUserService 
{
    /**
     * 查询点赞用户关联
     * 
     * @param id 点赞用户关联主键
     * @return 点赞用户关联
     */
    public LikeUser selectLikeUserById(Long id);

    /**
     * 查询点赞用户关联列表
     * 
     * @param likeUser 点赞用户关联
     * @return 点赞用户关联集合
     */
    public List<LikeUser> selectLikeUserList(LikeUser likeUser);

    /**
     * 新增点赞用户关联
     * 
     * @param likeUser 点赞用户关联
     * @return 结果
     */
    public int insertLikeUser(LikeUser likeUser);

    /**
     * 修改点赞用户关联
     * 
     * @param likeUser 点赞用户关联
     * @return 结果
     */
    public int updateLikeUser(LikeUser likeUser);

    /**
     * 批量删除点赞用户关联
     * 
     * @param ids 需要删除的点赞用户关联主键集合
     * @return 结果
     */
    public int deleteLikeUserByIds(Long[] ids);

    /**
     * 删除点赞用户关联信息
     * 
     * @param id 点赞用户关联主键
     * @return 结果
     */
    public int deleteLikeUserById(Long id);

    Integer selectTodayCount();
}
