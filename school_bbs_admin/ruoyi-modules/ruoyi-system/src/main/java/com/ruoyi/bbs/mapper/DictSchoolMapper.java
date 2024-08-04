package com.ruoyi.bbs.mapper;

import java.util.List;
import com.ruoyi.bbs.domain.DictSchool;

/**
 * 学校字典表Mapper接口
 * 
 * @author ckl
 * @date 2024-04-05
 */
public interface DictSchoolMapper 
{
    /**
     * 查询学校字典表
     * 
     * @param id 学校字典表主键
     * @return 学校字典表
     */
    public DictSchool selectDictSchoolById(Long id);

    /**
     * 查询学校字典表列表
     * 
     * @param dictSchool 学校字典表
     * @return 学校字典表集合
     */
    public List<DictSchool> selectDictSchoolList(DictSchool dictSchool);

    /**
     * 新增学校字典表
     * 
     * @param dictSchool 学校字典表
     * @return 结果
     */
    public int insertDictSchool(DictSchool dictSchool);

    /**
     * 修改学校字典表
     * 
     * @param dictSchool 学校字典表
     * @return 结果
     */
    public int updateDictSchool(DictSchool dictSchool);

    /**
     * 删除学校字典表
     * 
     * @param id 学校字典表主键
     * @return 结果
     */
    public int deleteDictSchoolById(Long id);

    /**
     * 批量删除学校字典表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDictSchoolByIds(Long[] ids);
}
