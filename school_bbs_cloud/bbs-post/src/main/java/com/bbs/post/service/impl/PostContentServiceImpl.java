package com.bbs.post.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbs.common.core.domain.post.dto.DetectionLogDTO;
import com.bbs.common.core.domain.post.entity.DetectionLog;
import com.bbs.common.core.domain.post.entity.PostContent;
import com.bbs.post.mapper.PostContentMapper;
import com.bbs.post.service.IPostContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 贴子Service业务层处理
 *
 * @author ckl
 * @date 2023-11-11
 */
@Service
public class PostContentServiceImpl extends ServiceImpl<PostContentMapper, PostContent> implements IPostContentService {
    @Autowired
    private PostContentMapper postContentMapper;

    @Override
    public List<DetectionLogDTO> selectDetectionLogDTOByIdList(List<DetectionLog> list) {
        List<DetectionLogDTO> reslist = new ArrayList<>();
        for (DetectionLog log : list) {
            DetectionLogDTO detectionLogDTO = postContentMapper.selectDetectionLogDTOByIdList(log);
            reslist.add(detectionLogDTO);
        }
        return reslist;
    }
}
