package com.ruoyi.bbs.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.UserMapper;
import com.ruoyi.bbs.domain.User;
import com.ruoyi.bbs.service.IUserService;

/**
 * 用户Service业务层处理
 * 
 * @author ckl
 * @date 2024-04-05
 */
@Service
public class UserServiceImpl implements IUserService
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public User selectUserById(Long id)
    {
        return userMapper.selectUserById(id);
    }

    /**
     * 查询用户列表
     * 
     * @param user 用户
     * @return 用户
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 新增用户
     * 
     * @param user 用户
     * @return 结果
     */
    @Override
    public int insertUser(User user)
    {
        user.setCreateTime(DateUtils.getNowDate());
        user.setUpdateTime(DateUtils.getNowDate());
        return userMapper.insertUser(user);
    }

    /**
     * 修改用户
     * 
     * @param user 用户
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        user.setUpdateTime(DateUtils.getNowDate());
        return userMapper.updateUser(user);
    }

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteUserByIds(Long[] ids)
    {
        return userMapper.deleteUserByIds(ids);
    }

    /**
     * 删除用户信息
     * 
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deleteUserById(Long id)
    {
        return userMapper.deleteUserById(id);
    }

    @Override
    public Integer selectCount() {
        return userMapper.selectCount();
    }

    @Override
    public Integer selectonlineUserCount() {
        Set<String> keys = redisTemplate.keys("user:user_info:*");
        return keys == null?0:keys.size();
    }
}
