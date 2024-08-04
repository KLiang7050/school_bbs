package com.bbs.post.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bbs.common.core.domain.post.dto.DetectionLogDTO;
import com.bbs.common.core.domain.post.entity.DetectionLog;

import java.util.List;

public interface DetectionLogService extends IService<DetectionLog> {

    List<DetectionLog> unHandle();

    Integer handle(List<DetectionLog> newList);
}