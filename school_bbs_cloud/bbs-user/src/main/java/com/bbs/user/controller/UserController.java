package com.bbs.user.controller;

import com.bbs.common.core.constant.SecurityConstants;
import com.bbs.common.core.constant.TokenConstants;
import com.bbs.common.core.domain.R;
import com.bbs.common.core.domain.user.entity.User;
import com.bbs.common.core.utils.KaptchaUtil;
import com.bbs.common.redis.CacheConstants.UserCacheKey;
import com.bbs.user.domain.UpdateBody;
import com.bbs.common.core.exception.BizException;
import com.bbs.common.core.utils.web.AjaxResult;
import com.bbs.common.core.utils.JwtUtils;
import com.bbs.common.core.utils.uuid.IdUtils;
import com.bbs.user.domain.LoginBody;
import com.bbs.user.domain.RegisterBody;
import com.bbs.user.service.IUserService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.bbs.common.core.utils.web.AjaxResult.success;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("listbyids")
    public R<List<User>> listByIds(@RequestParam("ids") Set<Long> ids) {
        List<User> list = userService.selectUserListByIds(ids);
        return R.ok(list);
    }

    @PostMapping("changetel")
    public R changeTel(@RequestBody UpdateBody body) throws BizException {
        if (StringUtils.isEmpty(body.getCode()) ||
                StringUtils.isEmpty(body.getUuid()) ||
                StringUtils.isEmpty(body.getNewValue()) ||
                StringUtils.isEmpty(body.getOldValue())) {
            throw new BizException("参数不能为空");
        }
        return userService.updateTel(body);
    }

    @PostMapping("changepwd")
    public R changePWD(@RequestBody UpdateBody body) throws BizException {
        if (StringUtils.isEmpty(body.getCode()) ||
                StringUtils.isEmpty(body.getUuid()) ||
                StringUtils.isEmpty(body.getNewValue()) ||
                StringUtils.isEmpty(body.getOldValue())) {
            throw new BizException("参数不能为空");
        }
        return userService.updatePWD(body);
    }

    @PostMapping("logout")
    public R logout(HttpServletRequest request) {
        String token = request.getHeader(TokenConstants.AUTHENTICATION).replaceFirst(TokenConstants.PREFIX, "");
        Claims claims = JwtUtils.parseToken(token);
        redisTemplate.delete(UserCacheKey.userInfoKey(claims.get(SecurityConstants.USER_KEY).toString()));
        return R.ok();
    }

    @GetMapping("info")
    public R info(HttpServletRequest request) {
        String token = request.getHeader(TokenConstants.AUTHENTICATION);
        String t = token.replaceFirst(TokenConstants.PREFIX, "");
        String id = JwtUtils.getUserId(t);
        User user = userService.selectUserById(id);
        return R.ok(user);
    }

    @GetMapping("infobyid")
    public R<User> infoById(@RequestParam("id") Long id) {
        User user = userService.selectUserById(String.valueOf(id));
        return R.ok(user);
    }

    @PostMapping("login")
    public R login(@RequestBody @Validated LoginBody loginBody) throws BizException {
        HashMap<String, Object> map = userService.login(loginBody);
        return R.ok(map);
    }

    @GetMapping("codeimg")
    public AjaxResult codeImg() throws IOException {
        DefaultKaptcha kaptcha = KaptchaUtil.getDefaultKaptcha();
        // 生成验证码图片
        String code = kaptcha.createText();
        ByteArrayOutputStream out = null;
        BufferedImage image = kaptcha.createImage(code);
        out = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", out);

        // 生成uuid
        String uuid = IdUtils.simpleUUID();

        // 存入缓存
        redisTemplate.opsForValue().set(UserCacheKey.captchaCodeKey(uuid), code, 1, TimeUnit.MINUTES);

        AjaxResult ajax = new AjaxResult();
        ajax.put("img", Base64.getEncoder().encodeToString(out.toByteArray()));
        ajax.put("uuid", uuid);
        return ajax;
    }

    @PostMapping("register")
    public R register(@RequestBody @Validated RegisterBody registerBody) throws BizException {
        return userService.register(registerBody);
    }

    @PostMapping("update")
    public R update(@RequestBody User user) {
        boolean res = userService.updateById(user);
        return res ? R.ok() : R.fail();
    }

    @PostMapping("updateavatar")
    public R<String> updateAvatar(@RequestPart("avatar") MultipartFile avatar) {
        String id = JwtUtils.getUserIdInHeader();
        return userService.updateAvatar(id, avatar);
    }

    @PostMapping("studentVerification")
    public AjaxResult studentVerification(@RequestParam("code") String code) throws ParseException {
        String id = JwtUtils.getUserIdInHeader();
        int i = userService.studentVerification(code, Long.valueOf(id));
        return i > 0 ? AjaxResult.success("认证成功") : AjaxResult.error("认证失败");
    }


    @PostMapping("/addPostCount")
    R addPostCount(@RequestParam("id") Long userId) {
        boolean res = userService.addPostCount(userId);
        return res ? R.ok() : R.fail();
    }

    @PostMapping("/minusPostCount")
    R minusPostCount(@RequestParam("id") Long userId) {
        boolean res = userService.minusPostCount(userId);
        return res ? R.ok() : R.fail();
    }

    @PostMapping("/addLikeCount")
    R addLikeCount(@RequestParam("id") Long userId) {
        boolean res = userService.addLikeCount(userId);
        return res ? R.ok() : R.fail();
    }

    @PostMapping("/minusLikeCount")
    R minusLikeCount(@RequestParam("id") Long userId) {
        boolean res = userService.minusLikeCount(userId);
        return res ? R.ok() : R.fail();
    }

    @PostMapping("addFavoriteCount")
    R addFavoriteCount(@RequestParam("id") Long userId) {
        boolean res = userService.addFavoriteCount(userId);
        return res ? R.ok() : R.fail();
    }

    @PostMapping("minusFavoriteCount")
    R minusFavoriteCount(@RequestParam("id") Long userId) {
        boolean res = userService.minusFavoriteCount(userId);
        return res ? R.ok() : R.fail();
    }
}
