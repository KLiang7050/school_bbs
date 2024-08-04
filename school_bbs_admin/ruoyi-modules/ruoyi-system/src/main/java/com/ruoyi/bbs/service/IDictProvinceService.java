package com.ruoyi.bbs.service;

import java.util.List;
import com.ruoyi.bbs.domain.DictProvince;

/**
 * 省份字典表Service接口
 * 
 * @author ckl
 * @date 2024-04-05
 */
public interface IDictProvinceService 
{
    /**
     * 查询省份字典表
     * 
     * @param id 省份字典表主键
     * @return 省份字典表
     */
    public DictProvince selectDictProvinceById(Long id);

    /**
     * 查询省份字典表列表
     * 
     * @param dictProvince 省份字典表
     * @return 省份字典表集合
     */
    public List<DictProvince> selectDictProvinceList(DictProvince dictProvince);

    /**
     * 新增省份字典表
     * 
     * @param dictProvince 省份字典表
     * @return 结果
     */
    public int insertDictProvince(DictProvince dictProvince);

    /**
     * 修改省份字典表
     * 
     * @param dictProvince 省份字典表
     * @return 结果
     */
    public int updateDictProvince(DictProvince dictProvince);

    /**
     * 批量删除省份字典表
     * 
     * @param ids 需要删除的省份字典表主键集合
     * @return 结果
     */
    public int deleteDictProvinceByIds(Long[] ids);

    /**
     * 删除省份字典表信息
     * 
     * @param id 省份字典表主键
     * @return 结果
     */
    public int deleteDictProvinceById(Long id);
}
