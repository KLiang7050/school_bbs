package com.ruoyi.bbs.service.impl;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.DictSchoolMapper;
import com.ruoyi.bbs.domain.DictSchool;
import com.ruoyi.bbs.service.IDictSchoolService;

/**
 * 学校字典表Service业务层处理
 * 
 * @author ckl
 * @date 2024-04-05
 */
@Service
public class DictSchoolServiceImpl implements IDictSchoolService
{
    @Autowired
    private DictSchoolMapper dictSchoolMapper;

    /**
     * 查询学校字典表
     * 
     * @param id 学校字典表主键
     * @return 学校字典表
     */
    @Override
    public DictSchool selectDictSchoolById(Long id)
    {
        return dictSchoolMapper.selectDictSchoolById(id);
    }

    /**
     * 查询学校字典表列表
     * 
     * @param dictSchool 学校字典表
     * @return 学校字典表
     */
    @Override
    public List<DictSchool> selectDictSchoolList(DictSchool dictSchool)
    {
        return dictSchoolMapper.selectDictSchoolList(dictSchool);
    }

    /**
     * 新增学校字典表
     * 
     * @param dictSchool 学校字典表
     * @return 结果
     */
    @Override
    public int insertDictSchool(DictSchool dictSchool)
    {
        return dictSchoolMapper.insertDictSchool(dictSchool);
    }

    /**
     * 修改学校字典表
     * 
     * @param dictSchool 学校字典表
     * @return 结果
     */
    @Override
    public int updateDictSchool(DictSchool dictSchool)
    {
        return dictSchoolMapper.updateDictSchool(dictSchool);
    }

    /**
     * 批量删除学校字典表
     * 
     * @param ids 需要删除的学校字典表主键
     * @return 结果
     */
    @Override
    public int deleteDictSchoolByIds(Long[] ids)
    {
        return dictSchoolMapper.deleteDictSchoolByIds(ids);
    }

    /**
     * 删除学校字典表信息
     * 
     * @param id 学校字典表主键
     * @return 结果
     */
    @Override
    public int deleteDictSchoolById(Long id)
    {
        return dictSchoolMapper.deleteDictSchoolById(id);
    }
}
