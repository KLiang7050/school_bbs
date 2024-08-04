package com.bbs.post.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bbs.common.core.domain.post.dto.DetectionLogDTO;
import com.bbs.common.core.domain.post.entity.DetectionLog;
import com.bbs.common.core.domain.post.entity.PostContent;

import java.util.List;

/**
 * 贴子Service接口
 * 
 * @author ckl
 * @date 2023-11-11
 */
public interface IPostContentService extends IService<PostContent>
{
    List<DetectionLogDTO> selectDetectionLogDTOByIdList(List<DetectionLog> list);
}
