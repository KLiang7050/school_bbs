package com.bbs.thirdparty.component.sms;

import com.bbs.common.core.exception.BizException;
import com.bbs.common.core.utils.uuid.IdUtils;
import com.bbs.common.core.utils.web.AjaxResult;
import com.bbs.common.core.utils.web.HttpUtils;
import com.bbs.common.redis.CacheConstants.UserCacheKey;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@ConfigurationProperties(prefix = "spring.cloud.alicloud.sms")
@Component
public class SMSComponent {

    @Autowired
    StringRedisTemplate redisTemplate;

    // 短信服务商域名
    private String host;
    // 短信服务商路径
    private String path;
    // 短信模板id
    private String template_id;
    // 密钥
    private String appcode;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getAppcode() {
        return appcode;
    }

    public void setAppcode(String appcode) {
        this.appcode = appcode;
    }

    public boolean sendCode(String code, String phone) throws BizException {
        Map<String, String> headers = new HashMap<String, String>();
        // 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        // 根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:" + code + ",expire_at:1");
        bodys.put("template_id", template_id);
        bodys.put("phone_number", phone);

        try {
            HttpResponse response = HttpUtils.doPost(host, path, "POST", headers, querys, bodys);
            return response.getStatusLine().getStatusCode() == 200;
        } catch (Exception e) {
            throw new BizException("短信发送失败");
        }
    }

    public AjaxResult createCaptchaToPhone(String phone) throws BizException {

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = UserCacheKey.captchaCodeKey(uuid);

        // 创建一个Random实例
        Random random = new Random();
        // 生成一个随机四位数
        String code = String.valueOf(random.nextInt(10000) + 1000);

        this.sendCode(code, phone);
        // 存入缓存，并设置过期时间为1分组
        redisTemplate.opsForValue().set(verifyKey, code, 1, TimeUnit.MINUTES);

        AjaxResult ajax = AjaxResult.success();
        ajax.put("uuid", uuid);
        return ajax;
    }
}