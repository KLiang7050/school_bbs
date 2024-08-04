package com.bbs.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bbs.common.core.domain.R;
import com.bbs.common.core.domain.user.entity.User;
import com.bbs.user.domain.UpdateBody;
import com.bbs.common.core.exception.BizException;
import com.bbs.user.domain.LoginBody;
import com.bbs.user.domain.RegisterBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 用户Service接口
 * 
 * @author ckl
 * @date 2023-10-03
 */
public interface IUserService extends IService<User>
{
    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    User selectUserByUserName(String username);

    /**
     * 查询用户列表
     *
     * @param user 用户
     * @return 用户集合
     */
    List<User> selectUserList(User user);

    HashMap<String, Object> login(LoginBody loginBody) throws BizException;

    User selectUserById(String id);

    R register(RegisterBody registerBody) throws BizException;

    R updatePWD(UpdateBody body) throws BizException;

    R updateTel(UpdateBody body) throws BizException;

    List<User> selectUserListByIds(Set<Long> ids);

    R<String> updateAvatar(String id, MultipartFile avatar);

    int studentVerification(String code, Long id) throws ParseException;

    boolean addPostCount(Long userId);

    boolean addLikeCount(Long userId);

    boolean minusLikeCount(Long userId);

    boolean addFavoriteCount(Long userId);

    boolean minusFavoriteCount(Long userId);

    boolean minusPostCount(Long userId);
}
