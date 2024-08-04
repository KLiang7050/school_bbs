package com.ruoyi.bbs.service.impl;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.DictProvinceMapper;
import com.ruoyi.bbs.domain.DictProvince;
import com.ruoyi.bbs.service.IDictProvinceService;

/**
 * 省份字典表Service业务层处理
 * 
 * @author ckl
 * @date 2024-04-05
 */
@Service
public class DictProvinceServiceImpl implements IDictProvinceService
{
    @Autowired
    private DictProvinceMapper dictProvinceMapper;

    /**
     * 查询省份字典表
     * 
     * @param id 省份字典表主键
     * @return 省份字典表
     */
    @Override
    public DictProvince selectDictProvinceById(Long id)
    {
        return dictProvinceMapper.selectDictProvinceById(id);
    }

    /**
     * 查询省份字典表列表
     * 
     * @param dictProvince 省份字典表
     * @return 省份字典表
     */
    @Override
    public List<DictProvince> selectDictProvinceList(DictProvince dictProvince)
    {
        return dictProvinceMapper.selectDictProvinceList(dictProvince);
    }

    /**
     * 新增省份字典表
     * 
     * @param dictProvince 省份字典表
     * @return 结果
     */
    @Override
    public int insertDictProvince(DictProvince dictProvince)
    {
        return dictProvinceMapper.insertDictProvince(dictProvince);
    }

    /**
     * 修改省份字典表
     * 
     * @param dictProvince 省份字典表
     * @return 结果
     */
    @Override
    public int updateDictProvince(DictProvince dictProvince)
    {
        return dictProvinceMapper.updateDictProvince(dictProvince);
    }

    /**
     * 批量删除省份字典表
     * 
     * @param ids 需要删除的省份字典表主键
     * @return 结果
     */
    @Override
    public int deleteDictProvinceByIds(Long[] ids)
    {
        return dictProvinceMapper.deleteDictProvinceByIds(ids);
    }

    /**
     * 删除省份字典表信息
     * 
     * @param id 省份字典表主键
     * @return 结果
     */
    @Override
    public int deleteDictProvinceById(Long id)
    {
        return dictProvinceMapper.deleteDictProvinceById(id);
    }
}
