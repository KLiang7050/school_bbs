package com.bbs.thirdparty.component.ssv;

import com.alibaba.fastjson2.JSON;
import com.bbs.common.core.utils.MD5;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class SSVComponent {

    @Value("${mengyuan.access-key}")
    private String accessId;

    @Value("${mengyuan.secret-key}")
    private String secretKey;

    // 存储空间名称
    @Value("${mengyuan.student.host}")
    private String host;

    public Map<String, Object> getStudent(String code) {
        Map<String, Object> map = new HashMap<>();
        try {
            URL url = new URL(host + "?" + "key=" + accessId +"&vcode="+code);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            String sgin = MD5.encrypt("key=" + accessId + "&vcode=" + code + secretKey);

            // 设置请求方式
            connection.setRequestMethod("GET");
            connection.setRequestProperty("sign", sgin);
            connection.connect();

            // 获取响应码
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    // 读取到的内容给line变量
                    map = JSON.parseObject(line);
                    Object obj = map.get("data");
                    map = JSON.parseObject(obj.toString());
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
