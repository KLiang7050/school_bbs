package com.ruoyi.bbs.config;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.bbs.domain.DetectionRes;
import com.ruoyi.common.core.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TDComponent {

    @Value("${spring.cloud.alicloud.td.host}")
    private String host;

    @Value("${spring.cloud.alicloud.td.path}")
    private String path;

    @Value("${spring.cloud.alicloud.td.app-code}")
    private String appCode;

    public DetectionRes textDetection(String text) {
        String method = "POST";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appCode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("text", text);

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            String json = EntityUtils.toString(response.getEntity());
            return JSONObject.parseObject(json, DetectionRes.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
