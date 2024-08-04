package com.bbs.thirdparty.controller;

import com.bbs.common.core.domain.post.entity.DetectionRes;
import com.bbs.thirdparty.component.td.TDComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/td/")
public class TDController {

    @Autowired
    private TDComponent tdComponent;

    @PostMapping("/textDetection")
    DetectionRes textDetection(@RequestParam("content")String content) {
        return tdComponent.textDetection(content);
    }

}
