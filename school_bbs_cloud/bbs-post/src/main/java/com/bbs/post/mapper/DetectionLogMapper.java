package com.bbs.post.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bbs.common.core.domain.post.dto.DetectionLogDTO;
import com.bbs.common.core.domain.post.entity.DetectionLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DetectionLogMapper extends BaseMapper<DetectionLog> {

    List<DetectionLog> unHandle();

    Integer handle(DetectionLog log);

    List<DetectionLogDTO> getOutList(@Param("startNum") Integer startNum,
                                     @Param("pageSize") Integer pageSize);

    int pass(@Param("id") Long id);

    int ban(@Param("id")Long id);
}