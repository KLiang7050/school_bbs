package com.bbs.thirdparty.controller;

import com.bbs.common.core.domain.R;
import com.bbs.thirdparty.component.ssv.SSVComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ssv/")
public class SSVController {

    @Autowired
    SSVComponent ssvComponent;

    @GetMapping("getInfo")
    public R<Map<String, Object>> getInfo(@RequestParam("code") String code){
        Map<String, Object> map = ssvComponent.getStudent(code);
        return R.ok(map);
    }

}
