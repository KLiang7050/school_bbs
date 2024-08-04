package com.ruoyi.bbs.service;

import com.ruoyi.bbs.domain.DetectionLog;
import com.ruoyi.bbs.domain.dto.DetectionLogDTO;

import java.util.List;

public interface DetectionLogService {

    List<DetectionLog> unHandle();

    Integer handle(List<DetectionLog> newList);

    List<DetectionLogDTO> getOutList(Integer startNum, Integer pageSize);

    boolean pass(Long id);

    boolean ban(Long id);
}