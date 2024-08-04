package com.bbs.post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbs.common.core.domain.post.entity.DictPostType;
import com.bbs.post.mapper.DictPostTypeMapper;
import com.bbs.post.service.IDictPostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictPostTypeServiceImpl extends ServiceImpl<DictPostTypeMapper, DictPostType> implements IDictPostTypeService{
    @Autowired
    DictPostTypeMapper dictPostTypeMapper;

    @Override
    public List<DictPostType> selectAll() {
        return dictPostTypeMapper.selectList(new QueryWrapper<DictPostType>());
    }
}
