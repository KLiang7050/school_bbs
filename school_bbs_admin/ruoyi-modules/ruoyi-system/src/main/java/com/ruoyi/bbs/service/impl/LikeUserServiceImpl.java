package com.ruoyi.bbs.service.impl;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.LikeUserMapper;
import com.ruoyi.bbs.domain.LikeUser;
import com.ruoyi.bbs.service.ILikeUserService;

/**
 * 点赞用户关联Service业务层处理
 * 
 * @author ckl
 * @date 2024-04-05
 */
@Service
public class LikeUserServiceImpl implements ILikeUserService
{
    @Autowired
    private LikeUserMapper likeUserMapper;

    /**
     * 查询点赞用户关联
     * 
     * @param id 点赞用户关联主键
     * @return 点赞用户关联
     */
    @Override
    public LikeUser selectLikeUserById(Long id)
    {
        return likeUserMapper.selectLikeUserById(id);
    }

    /**
     * 查询点赞用户关联列表
     * 
     * @param likeUser 点赞用户关联
     * @return 点赞用户关联
     */
    @Override
    public List<LikeUser> selectLikeUserList(LikeUser likeUser)
    {
        return likeUserMapper.selectLikeUserList(likeUser);
    }

    /**
     * 新增点赞用户关联
     * 
     * @param likeUser 点赞用户关联
     * @return 结果
     */
    @Override
    public int insertLikeUser(LikeUser likeUser)
    {
        likeUser.setCreateTime(DateUtils.getNowDate());
        return likeUserMapper.insertLikeUser(likeUser);
    }

    /**
     * 修改点赞用户关联
     * 
     * @param likeUser 点赞用户关联
     * @return 结果
     */
    @Override
    public int updateLikeUser(LikeUser likeUser)
    {
        return likeUserMapper.updateLikeUser(likeUser);
    }

    /**
     * 批量删除点赞用户关联
     * 
     * @param ids 需要删除的点赞用户关联主键
     * @return 结果
     */
    @Override
    public int deleteLikeUserByIds(Long[] ids)
    {
        return likeUserMapper.deleteLikeUserByIds(ids);
    }

    /**
     * 删除点赞用户关联信息
     * 
     * @param id 点赞用户关联主键
     * @return 结果
     */
    @Override
    public int deleteLikeUserById(Long id)
    {
        return likeUserMapper.deleteLikeUserById(id);
    }

    @Override
    public Integer selectTodayCount() {
        return likeUserMapper.selectTodayCount();
    }
}
