package com.ruoyi.bbs.mapper;

import java.util.List;
import com.ruoyi.bbs.domain.PostReply;
import com.ruoyi.bbs.domain.dto.ReplyDTO;

/**
 * 贴子回复Mapper接口
 * 
 * @author ckl
 * @date 2024-04-05
 */
public interface PostReplyMapper 
{
    /**
     * 查询贴子回复
     * 
     * @param id 贴子回复主键
     * @return 贴子回复
     */
    public PostReply selectPostReplyById(Long id);

    /**
     * 查询贴子回复列表
     * 
     * @param postReply 贴子回复
     * @return 贴子回复集合
     */
    public List<PostReply> selectPostReplyList(PostReply postReply);

    /**
     * 新增贴子回复
     * 
     * @param postReply 贴子回复
     * @return 结果
     */
    public int insertPostReply(PostReply postReply);

    /**
     * 修改贴子回复
     * 
     * @param postReply 贴子回复
     * @return 结果
     */
    public int updatePostReply(PostReply postReply);

    /**
     * 删除贴子回复
     * 
     * @param id 贴子回复主键
     * @return 结果
     */
    public int deletePostReplyById(Long id);

    /**
     * 批量删除贴子回复
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePostReplyByIds(Long[] ids);

}
