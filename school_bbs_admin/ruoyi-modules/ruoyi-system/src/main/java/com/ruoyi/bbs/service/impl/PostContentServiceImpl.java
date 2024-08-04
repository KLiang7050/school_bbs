package com.ruoyi.bbs.service.impl;

import java.util.List;

import com.ruoyi.bbs.domain.DetectionLog;
import com.ruoyi.bbs.domain.dto.DetectionLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.PostContentMapper;
import com.ruoyi.bbs.domain.PostContent;
import com.ruoyi.bbs.service.IPostContentService;

/**
 * 贴子内容Service业务层处理
 * 
 * @author ckl
 * @date 2024-03-29
 */
@Service
public class PostContentServiceImpl implements IPostContentService
{
    @Autowired
    private PostContentMapper postContentMapper;

    /**
     * 查询贴子内容
     * 
     * @param id 贴子内容主键
     * @return 贴子内容
     */
    @Override
    public PostContent selectPostContentById(Long id)
    {
        return postContentMapper.selectPostContentById(id);
    }

    /**
     * 查询贴子内容列表
     * 
     * @param postContent 贴子内容
     * @return 贴子内容
     */
    @Override
    public List<PostContent> selectPostContentList(PostContent postContent)
    {
        return postContentMapper.selectPostContentList(postContent);
    }

    /**
     * 新增贴子内容
     * 
     * @param postContent 贴子内容
     * @return 结果
     */
    @Override
    public int insertPostContent(PostContent postContent)
    {
        return postContentMapper.insertPostContent(postContent);
    }

    /**
     * 修改贴子内容
     * 
     * @param postContent 贴子内容
     * @return 结果
     */
    @Override
    public int updatePostContent(PostContent postContent)
    {
        return postContentMapper.updatePostContent(postContent);
    }

    /**
     * 批量删除贴子内容
     * 
     * @param ids 需要删除的贴子内容主键
     * @return 结果
     */
    @Override
    public int deletePostContentByIds(Long[] ids)
    {
        return postContentMapper.deletePostContentByIds(ids);
    }

    /**
     * 删除贴子内容信息
     * 
     * @param id 贴子内容主键
     * @return 结果
     */
    @Override
    public int deletePostContentById(Long id)
    {
        return postContentMapper.deletePostContentById(id);
    }

    @Override
    public List<DetectionLogDTO> selectByIdList(List<DetectionLog> list) {
        return postContentMapper.selectByIdList(list);
    }
}
