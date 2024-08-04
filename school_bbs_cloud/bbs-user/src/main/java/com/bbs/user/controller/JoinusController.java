package com.bbs.user.controller;

import com.bbs.api.req.RemoteJoinUsService;
import com.bbs.api.thirdparty.RemoteThirdPartyService;
import com.bbs.common.core.domain.user.entity.Joinus;
import com.bbs.common.core.domain.user.vo.JoinusVo;
import com.bbs.common.core.utils.JwtUtils;
import com.bbs.common.core.utils.web.AjaxResult;
import com.bbs.common.core.utils.web.HttpUtils;
import com.bbs.common.core.web.BaseController;
import com.bbs.user.service.IJoinusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;

@RestController
@RequestMapping("/joinus/")
public class JoinusController extends BaseController {

    @Autowired
    private IJoinusService joinusService;
    @Autowired
    private RemoteThirdPartyService remoteThirdPartyService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("list")
    public AjaxResult list() {
        String userId = JwtUtils.getUserIdInHeader();
        List<JoinusVo> vo = joinusService.list(userId);
        return success(vo);
    }

    @GetMapping("one/{id}")
    public AjaxResult one(@PathVariable("id") Long id) {
        JoinusVo vo = joinusService.one(id);
        return success(vo);
    }

    @PostMapping("save")
    public AjaxResult save(@RequestBody Joinus joinus) {
        String userId = JwtUtils.getUserIdInHeader();
        joinus.setUserId(Long.parseLong(userId));
        joinusService.saveJoinus(joinus);
        return success();
    }

    @PostMapping("upload")
    public AjaxResult upload(@RequestPart("file") MultipartFile file, @RequestParam("filename") String filename) {
        int code = remoteThirdPartyService.uploadFile(file, filename).getCode();
        return code == 200 ? success() : fail();
    }

    @PostMapping("commit")
    public AjaxResult commitJoinus(@RequestBody Joinus joinus) throws JsonProcessingException {
        String userId = JwtUtils.getUserIdInHeader();
        joinus.setUserId(Long.parseLong(userId));
        joinus.setStatus("1");
        // 将Joinus对象转换为JSON字符串
        ObjectMapper mapper = new ObjectMapper(); // 或者使用Gson的Gson.toJson方法
        String jsonBody = mapper.writeValueAsString(joinus);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 构建请求实体
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://www.schollbbs-manager.com/prod-api/system/app/joinus", entity, String.class);
        return response.getStatusCode().is2xxSuccessful() ? success() : fail();
    }
}
