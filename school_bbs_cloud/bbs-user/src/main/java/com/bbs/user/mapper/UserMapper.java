package com.bbs.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bbs.common.core.domain.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 用户Mapper接口
 *
 * @author ckl
 * @date 2023-10-03
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    public User selectUserById(Long id);

    /**
     * 查询用户列表
     *
     * @param user 用户
     * @return 用户集合
     */
    public List<User> selectUserList(User user);

    public int updatePWD(User user);

    List<User> selectUserListByIds(@Param("ids") Set<Long> ids);

    int updateAvatar(@Param("id") String id, @Param("avatar") String avatar);

    int addPostCountById(@Param("id") Long userId);

    int addLikeCount(@Param("id")Long userId);

    int minusLikeCount(@Param("id")Long id);

    int addFavoriteCount(@Param("id")Long id);

    int minusFavoriteCount(@Param("id")Long id);

    int minusPostCount(@Param("id") Long id);
}
