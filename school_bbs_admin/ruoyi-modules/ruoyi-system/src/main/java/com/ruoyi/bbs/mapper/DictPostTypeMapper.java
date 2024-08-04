package com.ruoyi.bbs.mapper;

import com.ruoyi.bbs.domain.DictPostType;

import java.util.List;

/**
 * 贴子类型字典Mapper接口
 * 
 * @author ckl
 * @date 2024-03-26
 */
public interface DictPostTypeMapper 
{
    /**
     * 查询贴子类型字典
     * 
     * @param id 贴子类型字典主键
     * @return 贴子类型字典
     */
    public DictPostType selectDictPostTypeById(Long id);

    /**
     * 查询贴子类型字典列表
     * 
     * @param dictPostType 贴子类型字典
     * @return 贴子类型字典集合
     */
    public List<DictPostType> selectDictPostTypeList(DictPostType dictPostType);

    /**
     * 新增贴子类型字典
     * 
     * @param dictPostType 贴子类型字典
     * @return 结果
     */
    public int insertDictPostType(DictPostType dictPostType);

    /**
     * 修改贴子类型字典
     * 
     * @param dictPostType 贴子类型字典
     * @return 结果
     */
    public int updateDictPostType(DictPostType dictPostType);

    /**
     * 删除贴子类型字典
     * 
     * @param id 贴子类型字典主键
     * @return 结果
     */
    public int deleteDictPostTypeById(Long id);

    /**
     * 批量删除贴子类型字典
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDictPostTypeByIds(Long[] ids);
}
