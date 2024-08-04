package com.bbs.post.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbs.common.core.domain.post.entity.DetectionLog;
import com.bbs.post.mapper.DetectionLogMapper;
import com.bbs.post.service.DetectionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetectionLogServiceImpl extends ServiceImpl<DetectionLogMapper, DetectionLog> implements DetectionLogService {

    @Autowired
    private DetectionLogMapper detectionLogMapper;

    @Override
    public List<DetectionLog> unHandle() {
        return detectionLogMapper.unHandle();
    }

    @Override
    public Integer handle(List<DetectionLog> newList) {
        Integer res = 0;
        for (DetectionLog detectionLog : newList) {
            res += detectionLogMapper.handle(detectionLog);
        }
        return res;
    }
}