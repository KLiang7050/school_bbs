package com.bbs.thirdparty.controller;

import com.aliyun.oss.OSS;
import com.bbs.common.core.domain.R;
import com.bbs.common.core.utils.uuid.UUID;
import com.bbs.thirdparty.component.oss.OSSComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/oss/")
public class OSSController {

    @Autowired
    private OSSComponent ossComponent;

    @PostMapping("policy")
    public R policy() {
        Map<String, String> policy = ossComponent.policy();
        return R.ok(policy);
    }

    @GetMapping("signedUrl/{dir}/{objectName}")
    public R getSignedUrl(@PathVariable("dir") String dir, @PathVariable("objectName") String objectName) {
        String key = dir + "/" + objectName;
        URL signedUrl = ossComponent.generateUrl(key);
        return R.ok(signedUrl);
    }

    @PostMapping("/getOSSClient")
    public R<OSS> getOSSClient() {
        return R.ok(ossComponent.getOssClient());
    }

    @PostMapping("/batchSigned")
    public R<Map<String, List<String>>> batchSigned(@RequestBody Map<String, List<String>> urlMap) {
        Map<String, List<String>> signedUrls = ossComponent.getSignedUrls(urlMap);
        return R.ok(signedUrls);
    }

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<String> uploadFile(@RequestPart("avatar") MultipartFile file,@RequestParam("filename")String filename) throws IOException {
        if(filename == null || filename.equals("")){
            String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // 格式化一个当前的服务器时间
            String dir = format + "/";
            filename = dir + UUID.fastUUID();
        }
        String url = ossComponent.uploadFile(file, filename);
        return R.ok(url);
    }
}
