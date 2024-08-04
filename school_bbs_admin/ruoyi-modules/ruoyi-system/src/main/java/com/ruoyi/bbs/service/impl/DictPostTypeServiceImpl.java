package com.ruoyi.bbs.service.impl;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ruoyi.bbs.domain.DictPostType;
import com.ruoyi.bbs.mapper.DictPostTypeMapper;
import com.ruoyi.bbs.service.IDictPostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 贴子类型字典Service业务层处理
 * 
 * @author ckl
 * @date 2024-03-26
 */
@Service
public class DictPostTypeServiceImpl implements IDictPostTypeService
{
    @Autowired
    private DictPostTypeMapper dictPostTypeMapper;

    /**
     * 查询贴子类型字典
     * 
     * @param id 贴子类型字典主键
     * @return 贴子类型字典
     */
    @Override
    public DictPostType selectDictPostTypeById(Long id)
    {
        return dictPostTypeMapper.selectDictPostTypeById(id);
    }

    /**
     * 查询贴子类型字典列表
     * 
     * @param dictPostType 贴子类型字典
     * @return 贴子类型字典
     */
    @Override
    public List<DictPostType> selectDictPostTypeList(DictPostType dictPostType)
    {
        return dictPostTypeMapper.selectDictPostTypeList(dictPostType);
    }

    /**
     * 新增贴子类型字典
     * 
     * @param dictPostType 贴子类型字典
     * @return 结果
     */
    @Override
    public int insertDictPostType(DictPostType dictPostType)
    {
        return dictPostTypeMapper.insertDictPostType(dictPostType);
    }

    /**
     * 修改贴子类型字典
     * 
     * @param dictPostType 贴子类型字典
     * @return 结果
     */
    @Override
    public int updateDictPostType(DictPostType dictPostType)
    {
        return dictPostTypeMapper.updateDictPostType(dictPostType);
    }

    /**
     * 批量删除贴子类型字典
     * 
     * @param ids 需要删除的贴子类型字典主键
     * @return 结果
     */
    @Override
    public int deleteDictPostTypeByIds(Long[] ids)
    {
        return dictPostTypeMapper.deleteDictPostTypeByIds(ids);
    }

    /**
     * 删除贴子类型字典信息
     * 
     * @param id 贴子类型字典主键
     * @return 结果
     */
    @Override
    public int deleteDictPostTypeById(Long id)
    {
        return dictPostTypeMapper.deleteDictPostTypeById(id);
    }
}
