package com.ruoyi.bbs.service;

import java.util.List;
import com.ruoyi.bbs.domain.Requisition;

/**
 * 需求管理Service接口
 * 
 * @author ckl
 * @date 2024-04-20
 */
public interface IRequisitionService 
{
    /**
     * 查询需求管理
     * 
     * @param id 需求管理主键
     * @return 需求管理
     */
    public Requisition selectRequisitionById(Long id);

    /**
     * 查询需求管理列表
     * 
     * @param requisition 需求管理
     * @return 需求管理集合
     */
    public List<Requisition> selectRequisitionList(Requisition requisition);

    /**
     * 新增需求管理
     * 
     * @param requisition 需求管理
     * @return 结果
     */
    public int insertRequisition(Requisition requisition);

    /**
     * 修改需求管理
     * 
     * @param requisition 需求管理
     * @return 结果
     */
    public int updateRequisition(Requisition requisition);

    /**
     * 批量删除需求管理
     * 
     * @param ids 需要删除的需求管理主键集合
     * @return 结果
     */
    public int deleteRequisitionByIds(Long[] ids);

    /**
     * 删除需求管理信息
     * 
     * @param id 需求管理主键
     * @return 结果
     */
    public int deleteRequisitionById(Long id);
}
