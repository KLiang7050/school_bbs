package com.bbs.user.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbs.api.thirdparty.RemoteThirdPartyService;
import com.bbs.common.core.constant.SecurityConstants;
import com.bbs.common.core.domain.R;
import com.bbs.common.core.domain.user.entity.User;
import com.bbs.common.core.utils.user.StudentInfoUtils;
import com.bbs.common.core.utils.uuid.UUID;
import com.bbs.common.redis.CacheConstants.UserCacheKey;
import com.bbs.user.domain.UpdateBody;
import com.bbs.common.core.exception.BizException;
import com.bbs.common.core.utils.JwtUtils;
import com.bbs.common.core.utils.MD5;
import com.bbs.common.core.utils.uuid.IdUtils;
import com.bbs.user.domain.LoginBody;
import com.bbs.user.domain.RegisterBody;
import com.bbs.user.mapper.UserMapper;
import com.bbs.user.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 用户Service业务层处理
 *
 * @author ckl
 * @date 2023-10-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RemoteThirdPartyService remoteThirdPartyService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean checkUserExists(String userName) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", userName)) == null;
    }

    @Override
    public User selectUserByUserName(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    /**
     * 查询用户列表
     *
     * @param user 用户
     * @return 用户
     */
    @Override
    public List<User> selectUserList(User user) {
        return userMapper.selectUserList(user);
    }

    @Override
    public HashMap<String, Object> login(LoginBody loginBody) throws BizException {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", loginBody.getUsername()));

        if (user == null) {
            throw new BizException("该用户不存在");
        }
        String md5PWD = MD5.encrypt(loginBody.getPassword());
        if (!md5PWD.equals(user.getPassword())) {
            throw new BizException("密码错误");
        }
        if (user.getIsEnable().equals("1")) {
            throw new BizException("账户已禁用");
        }

        String token = IdUtils.fastUUID();

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstants.USER_KEY, token);
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, user.getId());
        claimsMap.put(SecurityConstants.DETAILS_USERNAME, user.getUsername());

        // 存入缓存
        String json = JSON.toJSONString(user, JSONWriter.Feature.WriteMapNullValue); // arg2：序列化配置WriteMapNullValue——输出值为null的字段
        redisTemplate.opsForValue().set(UserCacheKey.userInfoKey(token), json, 1, TimeUnit.DAYS);

        HashMap<String, Object> map = new HashMap<>();
        map.put("token", JwtUtils.createToken(claimsMap));
        map.put("user", user);
        return map;
    }

    @Override
    public User selectUserById(String id) {
        User user = baseMapper.selectUserById(Long.valueOf(id));
        return user;
    }

    @Override
    public R register(RegisterBody registerBody) throws BizException {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", registerBody.getUsername()));
        if (user != null) {
            throw new BizException("用户名已存在");
        }
        user = new User();
        String password = MD5.encrypt(registerBody.getPassword());
        BeanUtils.copyProperties(registerBody, user);
        user.setNickName(registerBody.getUsername());
        user.setPassword(password);
        int res = userMapper.insert(user);
        return res > 0 ? R.ok() : R.fail();
    }

    @Override
    public R updatePWD(UpdateBody body) throws BizException {
        String id = JwtUtils.getUserIdInHeader();
        User user = userMapper.selectUserById(Long.valueOf(id));
        String realPWD = user.getPassword();
        String oldPWD = MD5.encrypt(body.getOldValue());
        if (!realPWD.equals(oldPWD)) {
            throw new BizException("旧密码错误");
        }
        user.setUpdateTime(null);// 让mybatis自动填充更新时间
        user.setPassword(MD5.encrypt(body.getNewValue()));
        int result = userMapper.updateById(user);
        return result > 0 ? R.ok() : R.fail();
    }

    @Override
    public R updateTel(UpdateBody body) throws BizException {
        String realCode = redisTemplate.opsForValue().get(UserCacheKey.captchaCodeKey(body.getUuid()));
        if (!body.getCode().equals(realCode)) {
            throw new BizException("验证码错误");
        }
        String id = JwtUtils.getUserIdInHeader();
        User user = userMapper.selectUserById(Long.valueOf(id));
        String realTel = user.getTel();
        String oldTel = body.getOldValue();
        if (!realTel.equals(oldTel)) {
            throw new BizException("旧手机号错误");
        }
        user.setUpdateTime(null);// 让mybatis自动填充更新时间
        user.setTel(body.getNewValue());
        int result = userMapper.updateById(user);
        return result > 0 ? R.ok() : R.fail();
    }

    @Override
    public List<User> selectUserListByIds(Set<Long> ids) {
        return baseMapper.selectUserListByIds(ids);
    }

    @Override
    public R<String> updateAvatar(String id, MultipartFile avatar) {
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // 格式化一个当前的服务器时间
        String dir = format + "/";
        String filename = dir + UUID.fastUUID();
        String url = remoteThirdPartyService.uploadFile(avatar, filename).getData();
        if (StringUtils.isBlank(url)) {
            return R.fail();
        }
        int i = userMapper.updateAvatar(id, url);
        return R.ok(url);
    }

    @Override
    public int studentVerification(String code, Long id) throws ParseException {
        Map<String, Object> map = remoteThirdPartyService.getInfo(code).getData();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date birthdate = simpleDateFormat.parse((String) map.get(StudentInfoUtils.BIRTH_DATE));
        Date enrollmentTime = simpleDateFormat.parse((String) map.get(StudentInfoUtils.ENROLLMENT_DATE));
        String major = "" + map.get(StudentInfoUtils.MAJOR) + map.get(StudentInfoUtils.CLASS_NAME);
        String school = (String) map.get(StudentInfoUtils.SCHOOL);
        String college = (String) map.get(StudentInfoUtils.DEPARTMENT_CODE);
        String sex = (map.get(StudentInfoUtils.GENDER)).equals("男") ? "1" : "0";
        String realName = (String) map.get(StudentInfoUtils.STUDENT_NAME);
        Long studentId = Long.parseLong((String) map.get(StudentInfoUtils.STUDENT_ID));

        User newUser = new User();
        newUser.setCollege(college);
        newUser.setBirthday(birthdate);
        newUser.setMajor(major);
        newUser.setSchool(school);
        newUser.setSex(sex);
        newUser.setRealName(realName);
        newUser.setEnrollmentTime(enrollmentTime);
        newUser.setStudentId(studentId);
        newUser.setId(id);

        int i = userMapper.updateById(newUser);
        return i;
    }

    @Override
    public boolean addPostCount(Long userId) {
        int res = userMapper.addPostCountById(userId);
        return res > 0;
    }

    @Override
    public boolean addLikeCount(Long userId) {
        int res = userMapper.addLikeCount(userId);
        return res > 0;
    }

    @Override
    public boolean minusLikeCount(Long userId) {
        int res = userMapper.minusLikeCount(userId);
        return res > 0;
    }

    @Override
    public boolean addFavoriteCount(Long userId) {
        int res = userMapper.addFavoriteCount(userId);
        return res > 0;
    }

    @Override
    public boolean minusFavoriteCount(Long userId) {
        int res = userMapper.minusFavoriteCount(userId);
        return res > 0;
    }

    @Override
    public boolean minusPostCount(Long userId) {
        int res = userMapper.minusPostCount(userId);
        return res > 0;
    }
}
