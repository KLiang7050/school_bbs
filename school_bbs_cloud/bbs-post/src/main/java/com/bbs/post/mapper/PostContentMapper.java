package com.bbs.post.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bbs.common.core.domain.post.dto.DetectionLogDTO;
import com.bbs.common.core.domain.post.entity.DetectionLog;
import com.bbs.common.core.domain.post.entity.PostContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 贴子Mapper接口
 * 
 * @author ckl
 * @date 2023-11-11
 */
@Mapper
public interface PostContentMapper extends BaseMapper<PostContent>
{

    List<PostContent> selectByIdList(@Param("idList") List<Long> contentIdList);

    DetectionLogDTO selectDetectionLogDTOByIdList(DetectionLog log);
}
