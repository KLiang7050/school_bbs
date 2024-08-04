package com.bbs.post.controller;

import com.bbs.common.core.domain.post.entity.DetectionLog;
import com.bbs.post.service.DetectionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detectionlog")
public class DetectionLogController {

    @Autowired
    private DetectionLogService detectionLogService;

}