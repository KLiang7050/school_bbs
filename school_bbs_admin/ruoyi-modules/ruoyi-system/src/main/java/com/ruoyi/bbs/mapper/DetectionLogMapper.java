package com.ruoyi.bbs.mapper;

import com.ruoyi.bbs.domain.DetectionLog;
import com.ruoyi.bbs.domain.dto.DetectionLogDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DetectionLogMapper{

    List<DetectionLog> unHandle();

    Integer handle(@Param("list") List<DetectionLog> newList);

    List<DetectionLogDTO> getOutList(@Param("startNum") Integer startNum,
                                     @Param("pageSize") Integer pageSize);

    DetectionLog getById(@Param("id") Long id);

    int pass(@Param("id") Long id);

    int ban(@Param("id")Long id);
}