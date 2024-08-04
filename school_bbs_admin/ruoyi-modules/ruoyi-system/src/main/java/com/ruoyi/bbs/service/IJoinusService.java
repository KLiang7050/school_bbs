package com.ruoyi.bbs.service;

import java.util.List;
import com.ruoyi.bbs.domain.Joinus;

/**
 * 入驻申请Service接口
 * 
 * @author ckl
 * @date 2024-04-05
 */
public interface IJoinusService 
{
    /**
     * 查询入驻申请
     * 
     * @param id 入驻申请主键
     * @return 入驻申请
     */
    public Joinus selectJoinusById(Long id);

    /**
     * 查询入驻申请列表
     * 
     * @param joinus 入驻申请
     * @return 入驻申请集合
     */
    public List<Joinus> selectJoinusList(Joinus joinus);

    /**
     * 新增入驻申请
     * 
     * @param joinus 入驻申请
     * @return 结果
     */
    public int insertJoinus(Joinus joinus);

    /**
     * 修改入驻申请
     * 
     * @param joinus 入驻申请
     * @return 结果
     */
    public int updateJoinus(Joinus joinus);

    /**
     * 批量删除入驻申请
     * 
     * @param ids 需要删除的入驻申请主键集合
     * @return 结果
     */
    public int deleteJoinusByIds(Long[] ids);

    /**
     * 删除入驻申请信息
     * 
     * @param id 入驻申请主键
     * @return 结果
     */
    public int deleteJoinusById(Long id);
}
