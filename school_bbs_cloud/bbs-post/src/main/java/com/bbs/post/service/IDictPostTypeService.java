package com.bbs.post.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bbs.common.core.domain.post.entity.DictPostType;

import java.util.List;

public interface IDictPostTypeService extends IService<DictPostType> {

    List<DictPostType> selectAll();
}
