package com.ruoyi.bbs.service.impl;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.FavoritesUserMapper;
import com.ruoyi.bbs.domain.FavoritesUser;
import com.ruoyi.bbs.service.IFavoritesUserService;

/**
 * 收藏用户关联Service业务层处理
 * 
 * @author ckl
 * @date 2024-04-05
 */
@Service
public class FavoritesUserServiceImpl implements IFavoritesUserService
{
    @Autowired
    private FavoritesUserMapper favoritesUserMapper;

    /**
     * 查询收藏用户关联
     * 
     * @param id 收藏用户关联主键
     * @return 收藏用户关联
     */
    @Override
    public FavoritesUser selectFavoritesUserById(Long id)
    {
        return favoritesUserMapper.selectFavoritesUserById(id);
    }

    /**
     * 查询收藏用户关联列表
     * 
     * @param favoritesUser 收藏用户关联
     * @return 收藏用户关联
     */
    @Override
    public List<FavoritesUser> selectFavoritesUserList(FavoritesUser favoritesUser)
    {
        return favoritesUserMapper.selectFavoritesUserList(favoritesUser);
    }

    /**
     * 新增收藏用户关联
     * 
     * @param favoritesUser 收藏用户关联
     * @return 结果
     */
    @Override
    public int insertFavoritesUser(FavoritesUser favoritesUser)
    {
        favoritesUser.setCreateTime(DateUtils.getNowDate());
        return favoritesUserMapper.insertFavoritesUser(favoritesUser);
    }

    /**
     * 修改收藏用户关联
     * 
     * @param favoritesUser 收藏用户关联
     * @return 结果
     */
    @Override
    public int updateFavoritesUser(FavoritesUser favoritesUser)
    {
        return favoritesUserMapper.updateFavoritesUser(favoritesUser);
    }

    /**
     * 批量删除收藏用户关联
     * 
     * @param ids 需要删除的收藏用户关联主键
     * @return 结果
     */
    @Override
    public int deleteFavoritesUserByIds(Long[] ids)
    {
        return favoritesUserMapper.deleteFavoritesUserByIds(ids);
    }

    /**
     * 删除收藏用户关联信息
     * 
     * @param id 收藏用户关联主键
     * @return 结果
     */
    @Override
    public int deleteFavoritesUserById(Long id)
    {
        return favoritesUserMapper.deleteFavoritesUserById(id);
    }

    @Override
    public Integer selectTodayCount() {
        return favoritesUserMapper.selectTodayCount();
    }
}
