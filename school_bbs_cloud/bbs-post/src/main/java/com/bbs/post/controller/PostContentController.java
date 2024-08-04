package com.bbs.post.controller;

import com.bbs.common.core.domain.post.dto.DetectionLogDTO;
import com.bbs.post.service.IPostContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 贴子Controller
 * 
 * @author ckl
 * @date 2023-11-11
 */
@RestController
@RequestMapping("/content")
public class PostContentController
{
    @Autowired
    private IPostContentService postContentService;

    @GetMapping("/content/getDetectionLogDTO")
    public List<DetectionLogDTO> selectDetectionLogDTOByIdList(List<Long> idList){
        return selectDetectionLogDTOByIdList(idList);
    }
}
