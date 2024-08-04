package com.bbs.thirdparty.component.oss;

import com.aliyun.oss.*;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyun.oss.model.PutObjectResult;
import com.bbs.common.core.constant.HttpStatus;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class OSSComponent {

    // 阿里云AccessKey ID
    @Value("${spring.cloud.alicloud.access-key}")
    private String accessId;

    // 阿里云AccessKey Secret
    @Value("${spring.cloud.alicloud.secret-key}")
    private String secretKey;

    // 阿里云OSS Endpoint
    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;

    // 存储空间名称
    @Value("${spring.cloud.alicloud.oss.bucket}")
    private String bucket;

    public OSS getOssClient() {
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endpoint, accessId, secretKey);
        } catch (ClientException | OSSException e) {
            e.printStackTrace();
        }
        return ossClient;
    }

    public Map<String, String> policy() {

        // https://md-ossbucket.oss-cn-beijing.aliyuncs.com/QQ%E6%88%AA%E5%9B%BE20210609114525.png  host的格式为 bucketname.endpoint
        String host = "http://" + bucket + "." + endpoint;

        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // 格式化一个当前的服务器时间

        String dir = format + "/"; // 用户上传文件时指定的前缀,我们希望以日期作为一个目录

        Map<String, String> respMap = null; // 返回结果

        OSS ossClient = getOssClient();

        try {
            Long expireTime = 30000L;
            Long expireEndTime = System.currentTimeMillis() + expireTime;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_SUCCESS_ACTION_STATUS, String.valueOf(HttpStatus.SUCCESS));
            // policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);

            String policy = ossClient.generatePostPolicy(expiration, policyConds);
            String encodePolicy = new String(Base64.encodeBase64(policy.getBytes()));
            String signaturecom = computeSignature(secretKey, encodePolicy);

            respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessId);
            respMap.put("policy", encodePolicy);
            respMap.put("signature", signaturecom);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expiration.getTime()));
            // respMap.put("expire", formatISO8601Date(expiration));

        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            ossClient.shutdown();
        }

        return respMap;
    }

    /**
     * 为文件签名（不返回视频封面）
     *
     * @param key
     * @return
     */
    public URL generateUrl(String key) {
        URL signedUrl = null;
        // 创建OSSClient实例。
        OSS ossClient = getOssClient();

        try {
            // 指定生成的签名URL过期时间，单位为毫秒。本示例以设置过期时间为1小时为例。
            Date expiration = new Date(new Date().getTime() + 3600 * 1000L);

            // 生成签名URL。
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, key, HttpMethod.GET);
            // 设置过期时间。
            request.setExpiration(expiration);

            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            signedUrl = ossClient.generatePresignedUrl(request);
            return signedUrl;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        }

        return signedUrl;
    }

    /**
     * 为文件签名（返回视频封面）
     *
     * @param key
     * @return
     */
    public URL generateRootUrl(OSS ossClient, String key) {
        URL signedUrl = null;

        try {
            // 指定生成的签名URL过期时间，单位为毫秒。本示例以设置过期时间为1小时为例。
            Date expiration = new Date(new Date().getTime() + 3600 * 1000L);
            // 生成签名URL。
            GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucket, key, HttpMethod.GET);
            req.setExpiration(expiration);

            String type = key.substring(key.indexOf("type=") + 5);
            if (type.equals("video")) {
                // 使用精确时间模式截取视频17s处的内容，输出为JPG格式的图片，宽度为800，高度为600。
                String style = "video/snapshot,t_1000,f_jpg,w_800,h_600";
                req.setProcess(style);
            }
            return ossClient.generatePresignedUrl(req);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        }
        return signedUrl;
    }

    public Map<String, List<String>> getSignedUrls(Map<String, List<String>> urlMap) {
        Map<String, List<String>> map = new HashMap<>();
        OSS ossClient = getOssClient();
        urlMap.forEach((k, v) -> {
            ArrayList<String> urls = new ArrayList<>();
            for (String url : v) {
                urls.add(generateRootUrl(ossClient, url).toString());
            }
            map.put(k, urls);
        });

        return map;
    }

    public String uploadFile(MultipartFile file, String fileName) {
        OSS ossClient = getOssClient();
        try {
            PutObjectResult putObjectResult = ossClient.putObject(bucket, fileName, file.getInputStream());
            return "https://" + bucket + "." + endpoint + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return null;
    }

    private static String computeSignature(String accessKeySecret, String encodePolicy)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        // convert to UTF-8
        byte[] key = accessKeySecret.getBytes("UTF-8");
        byte[] data = encodePolicy.getBytes("UTF-8");

        // hmac-sha1
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(key, "HmacSHA1"));
        byte[] sha = mac.doFinal(data);

        // base64
        return new String(Base64.encodeBase64(sha));
    }
}
