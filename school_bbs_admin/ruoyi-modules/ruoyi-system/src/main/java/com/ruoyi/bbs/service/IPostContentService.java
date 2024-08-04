package com.ruoyi.bbs.service;

import java.util.List;

import com.ruoyi.bbs.domain.DetectionLog;
import com.ruoyi.bbs.domain.PostContent;
import com.ruoyi.bbs.domain.dto.DetectionLogDTO;

/**
 * 贴子内容Service接口
 * 
 * @author ckl
 * @date 2024-03-29
 */
public interface IPostContentService 
{
    /**
     * 查询贴子内容
     * 
     * @param id 贴子内容主键
     * @return 贴子内容
     */
    public PostContent selectPostContentById(Long id);

    /**
     * 查询贴子内容列表
     * 
     * @param postContent 贴子内容
     * @return 贴子内容集合
     */
    public List<PostContent> selectPostContentList(PostContent postContent);

    /**
     * 新增贴子内容
     * 
     * @param postContent 贴子内容
     * @return 结果
     */
    public int insertPostContent(PostContent postContent);

    /**
     * 修改贴子内容
     * 
     * @param postContent 贴子内容
     * @return 结果
     */
    public int updatePostContent(PostContent postContent);

    /**
     * 批量删除贴子内容
     * 
     * @param ids 需要删除的贴子内容主键集合
     * @return 结果
     */
    public int deletePostContentByIds(Long[] ids);

    /**
     * 删除贴子内容信息
     * 
     * @param id 贴子内容主键
     * @return 结果
     */
    public int deletePostContentById(Long id);

    List<DetectionLogDTO> selectByIdList(List<DetectionLog> list);
}
