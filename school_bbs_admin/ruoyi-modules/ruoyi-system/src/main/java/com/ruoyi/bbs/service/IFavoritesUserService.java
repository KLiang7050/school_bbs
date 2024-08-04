package com.ruoyi.bbs.service;

import java.util.List;
import com.ruoyi.bbs.domain.FavoritesUser;

/**
 * 收藏用户关联Service接口
 * 
 * @author ckl
 * @date 2024-04-05
 */
public interface IFavoritesUserService 
{
    /**
     * 查询收藏用户关联
     * 
     * @param id 收藏用户关联主键
     * @return 收藏用户关联
     */
    public FavoritesUser selectFavoritesUserById(Long id);

    /**
     * 查询收藏用户关联列表
     * 
     * @param favoritesUser 收藏用户关联
     * @return 收藏用户关联集合
     */
    public List<FavoritesUser> selectFavoritesUserList(FavoritesUser favoritesUser);

    /**
     * 新增收藏用户关联
     * 
     * @param favoritesUser 收藏用户关联
     * @return 结果
     */
    public int insertFavoritesUser(FavoritesUser favoritesUser);

    /**
     * 修改收藏用户关联
     * 
     * @param favoritesUser 收藏用户关联
     * @return 结果
     */
    public int updateFavoritesUser(FavoritesUser favoritesUser);

    /**
     * 批量删除收藏用户关联
     * 
     * @param ids 需要删除的收藏用户关联主键集合
     * @return 结果
     */
    public int deleteFavoritesUserByIds(Long[] ids);

    /**
     * 删除收藏用户关联信息
     * 
     * @param id 收藏用户关联主键
     * @return 结果
     */
    public int deleteFavoritesUserById(Long id);

    Integer selectTodayCount();
}
