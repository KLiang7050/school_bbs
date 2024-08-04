package com.ruoyi.bbs.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.RequisitionMapper;
import com.ruoyi.bbs.domain.Requisition;
import com.ruoyi.bbs.service.IRequisitionService;

/**
 * 需求管理Service业务层处理
 * 
 * @author ckl
 * @date 2024-04-20
 */
@Service
public class RequisitionServiceImpl implements IRequisitionService 
{
    @Autowired
    private RequisitionMapper requisitionMapper;

    /**
     * 查询需求管理
     * 
     * @param id 需求管理主键
     * @return 需求管理
     */
    @Override
    public Requisition selectRequisitionById(Long id)
    {
        return requisitionMapper.selectRequisitionById(id);
    }

    /**
     * 查询需求管理列表
     * 
     * @param requisition 需求管理
     * @return 需求管理
     */
    @Override
    public List<Requisition> selectRequisitionList(Requisition requisition)
    {
        return requisitionMapper.selectRequisitionList(requisition);
    }

    /**
     * 新增需求管理
     * 
     * @param requisition 需求管理
     * @return 结果
     */
    @Override
    public int insertRequisition(Requisition requisition)
    {
        requisition.setCreateTime(DateUtils.getNowDate());
        requisition.setUpdateTime(DateUtils.getNowDate());
        return requisitionMapper.insertRequisition(requisition);
    }

    /**
     * 修改需求管理
     * 
     * @param requisition 需求管理
     * @return 结果
     */
    @Override
    public int updateRequisition(Requisition requisition)
    {
        requisition.setUpdateTime(DateUtils.getNowDate());
        return requisitionMapper.updateRequisition(requisition);
    }

    /**
     * 批量删除需求管理
     * 
     * @param ids 需要删除的需求管理主键
     * @return 结果
     */
    @Override
    public int deleteRequisitionByIds(Long[] ids)
    {
        return requisitionMapper.deleteRequisitionByIds(ids);
    }

    /**
     * 删除需求管理信息
     * 
     * @param id 需求管理主键
     * @return 结果
     */
    @Override
    public int deleteRequisitionById(Long id)
    {
        return requisitionMapper.deleteRequisitionById(id);
    }
}
