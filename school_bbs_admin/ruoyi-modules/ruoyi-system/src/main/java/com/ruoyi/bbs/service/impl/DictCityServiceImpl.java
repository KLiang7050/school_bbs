package com.ruoyi.bbs.service.impl;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.DictCityMapper;
import com.ruoyi.bbs.domain.DictCity;
import com.ruoyi.bbs.service.IDictCityService;

/**
 * 城市字典表Service业务层处理
 * 
 * @author ckl
 * @date 2024-04-05
 */
@Service
public class DictCityServiceImpl implements IDictCityService
{
    @Autowired
    private DictCityMapper dictCityMapper;

    /**
     * 查询城市字典表
     * 
     * @param id 城市字典表主键
     * @return 城市字典表
     */
    @Override
    public DictCity selectDictCityById(Long id)
    {
        return dictCityMapper.selectDictCityById(id);
    }

    /**
     * 查询城市字典表列表
     * 
     * @param dictCity 城市字典表
     * @return 城市字典表
     */
    @Override
    public List<DictCity> selectDictCityList(DictCity dictCity)
    {
        return dictCityMapper.selectDictCityList(dictCity);
    }

    /**
     * 新增城市字典表
     * 
     * @param dictCity 城市字典表
     * @return 结果
     */
    @Override
    public int insertDictCity(DictCity dictCity)
    {
        return dictCityMapper.insertDictCity(dictCity);
    }

    /**
     * 修改城市字典表
     * 
     * @param dictCity 城市字典表
     * @return 结果
     */
    @Override
    public int updateDictCity(DictCity dictCity)
    {
        return dictCityMapper.updateDictCity(dictCity);
    }

    /**
     * 批量删除城市字典表
     * 
     * @param ids 需要删除的城市字典表主键
     * @return 结果
     */
    @Override
    public int deleteDictCityByIds(Long[] ids)
    {
        return dictCityMapper.deleteDictCityByIds(ids);
    }

    /**
     * 删除城市字典表信息
     * 
     * @param id 城市字典表主键
     * @return 结果
     */
    @Override
    public int deleteDictCityById(Long id)
    {
        return dictCityMapper.deleteDictCityById(id);
    }
}
