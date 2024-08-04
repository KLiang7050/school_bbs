package com.ruoyi.bbs.service.impl;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ruoyi.bbs.domain.dto.ReplyDTO;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.PostReplyMapper;
import com.ruoyi.bbs.domain.PostReply;
import com.ruoyi.bbs.service.IPostReplyService;

/**
 * 贴子回复Service业务层处理
 * 
 * @author ckl
 * @date 2024-04-05
 */
@Service
public class PostReplyServiceImpl implements IPostReplyService
{
    @Autowired
    private PostReplyMapper postReplyMapper;

    /**
     * 查询贴子回复
     * 
     * @param id 贴子回复主键
     * @return 贴子回复
     */
    @Override
    public PostReply selectPostReplyById(Long id)
    {
        return postReplyMapper.selectPostReplyById(id);
    }

    /**
     * 查询贴子回复列表
     * 
     * @param postReply 贴子回复
     * @return 贴子回复
     */
    @Override
    public List<PostReply> selectPostReplyList(PostReply postReply)
    {
        return postReplyMapper.selectPostReplyList(postReply);
    }

    /**
     * 新增贴子回复
     * 
     * @param postReply 贴子回复
     * @return 结果
     */
    @Override
    public int insertPostReply(PostReply postReply)
    {
        postReply.setCreateTime(DateUtils.getNowDate());
        return postReplyMapper.insertPostReply(postReply);
    }

    /**
     * 修改贴子回复
     * 
     * @param postReply 贴子回复
     * @return 结果
     */
    @Override
    public int updatePostReply(PostReply postReply)
    {
        postReply.setUpdateTime(DateUtils.getNowDate());
        return postReplyMapper.updatePostReply(postReply);
    }

    /**
     * 批量删除贴子回复
     * 
     * @param ids 需要删除的贴子回复主键
     * @return 结果
     */
    @Override
    public int deletePostReplyByIds(Long[] ids)
    {
        return postReplyMapper.deletePostReplyByIds(ids);
    }

    /**
     * 删除贴子回复信息
     * 
     * @param id 贴子回复主键
     * @return 结果
     */
    @Override
    public int deletePostReplyById(Long id)
    {
        return postReplyMapper.deletePostReplyById(id);
    }

}
