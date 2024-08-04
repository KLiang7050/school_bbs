package com.ruoyi.bbs.mapper;

import java.util.List;

import com.ruoyi.bbs.domain.DetectionLog;
import com.ruoyi.bbs.domain.PostContent;
import com.ruoyi.bbs.domain.dto.DetectionLogDTO;
import org.apache.ibatis.annotations.Param;

/**
 * 贴子内容Mapper接口
 * 
 * @author ckl
 * @date 2024-03-29
 */
public interface PostContentMapper 
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
     * 删除贴子内容
     * 
     * @param id 贴子内容主键
     * @return 结果
     */
    public int deletePostContentById(Long id);

    /**
     * 批量删除贴子内容
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePostContentByIds(Long[] ids);

    List<DetectionLogDTO> selectByIdList(@Param("list") List<DetectionLog> log);
}
