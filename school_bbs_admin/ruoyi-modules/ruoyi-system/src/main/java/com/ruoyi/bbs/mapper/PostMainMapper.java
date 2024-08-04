package com.ruoyi.bbs.mapper;

import java.util.List;
import com.ruoyi.bbs.domain.PostMain;
import com.ruoyi.bbs.domain.dto.MainDTO;
import org.apache.ibatis.annotations.Param;

/**
 * 贴子主贴Mapper接口
 * 
 * @author ckl
 * @date 2024-03-29
 */
public interface PostMainMapper
{
    /**
     * 查询贴子主贴
     * 
     * @param id 贴子主贴主键
     * @return 贴子主贴
     */
    public PostMain selectPostMainById(Long id);

    /**
     * 查询贴子主贴列表
     * 
     * @param postMain 贴子主贴
     * @return 贴子主贴集合
     */
    public List<PostMain> selectPostMainList(PostMain postMain);

    /**
     * 新增贴子主贴
     * 
     * @param postMain 贴子主贴
     * @return 结果
     */
    public int insertPostMain(PostMain postMain);

    /**
     * 修改贴子主贴
     * 
     * @param postMain 贴子主贴
     * @return 结果
     */
    public int updatePostMain(PostMain postMain);

    /**
     * 删除贴子主贴
     * 
     * @param id 贴子主贴主键
     * @return 结果
     */
    public int deletePostMainById(Long id);

    /**
     * 批量删除贴子主贴
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePostMainByIds(Long[] ids);

    Integer selectTodayNewPost();

    List<MainDTO> selectUnaudited(@Param("startNum") Integer startNum,
                                  @Param("pageSize") Integer pageSize);

    Integer updatePostFlagByContentIdList(@Param("list") List<Long> okContentIdList);
}
