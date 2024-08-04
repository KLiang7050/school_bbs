package com.ruoyi.bbs.service;

import java.util.List;
import com.ruoyi.bbs.domain.DictCity;

/**
 * 城市字典表Service接口
 * 
 * @author ckl
 * @date 2024-04-05
 */
public interface IDictCityService 
{
    /**
     * 查询城市字典表
     * 
     * @param id 城市字典表主键
     * @return 城市字典表
     */
    public DictCity selectDictCityById(Long id);

    /**
     * 查询城市字典表列表
     * 
     * @param dictCity 城市字典表
     * @return 城市字典表集合
     */
    public List<DictCity> selectDictCityList(DictCity dictCity);

    /**
     * 新增城市字典表
     * 
     * @param dictCity 城市字典表
     * @return 结果
     */
    public int insertDictCity(DictCity dictCity);

    /**
     * 修改城市字典表
     * 
     * @param dictCity 城市字典表
     * @return 结果
     */
    public int updateDictCity(DictCity dictCity);

    /**
     * 批量删除城市字典表
     * 
     * @param ids 需要删除的城市字典表主键集合
     * @return 结果
     */
    public int deleteDictCityByIds(Long[] ids);

    /**
     * 删除城市字典表信息
     * 
     * @param id 城市字典表主键
     * @return 结果
     */
    public int deleteDictCityById(Long id);
}
