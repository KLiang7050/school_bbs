package com.bbs.api.thirdparty;

import com.bbs.common.core.domain.R;
import com.bbs.common.core.domain.post.entity.DetectionRes;
import com.bbs.common.core.exception.BizException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@FeignClient(contextId = "remoteThirdPartyService", name = "bbs-thirdparty-${school-name}")
public interface RemoteThirdPartyService {
    @GetMapping("/sms/sendCode")
    R sendCode(String phone) throws BizException;

    @GetMapping("policy")
    R policy();

    @PostMapping("/oss/batchSigned")
    R<Map<String, List<String>>> batchSigned(@RequestBody Map<String, List<String>> urlMap);

    @PostMapping(value = "/oss/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    R<String> uploadFile(@RequestPart("avatar") MultipartFile file, @RequestParam("filename") String filename);

    @GetMapping("/ssv/getInfo")
    R<Map<String, Object>> getInfo(@RequestParam("code") String code);

    @PostMapping("/td/textDetection")
    DetectionRes textDetection(@RequestParam("content")String content);
}
