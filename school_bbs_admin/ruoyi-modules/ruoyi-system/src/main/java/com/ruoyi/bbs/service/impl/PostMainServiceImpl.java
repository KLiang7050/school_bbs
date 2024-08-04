package com.ruoyi.bbs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ruoyi.bbs.domain.dto.MainDTO;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.PostMainMapper;
import com.ruoyi.bbs.domain.PostMain;
import com.ruoyi.bbs.service.IPostMainService;

/**
 * 贴子主贴Service业务层处理
 * 
 * @author ckl
 * @date 2024-03-29
 */
@Service
public class PostMainServiceImpl implements IPostMainService
{
    @Autowired
    private PostMainMapper postMainMapper;

    /**
     * 查询贴子主贴
     * 
     * @param id 贴子主贴主键
     * @return 贴子主贴
     */
    @Override
    public PostMain selectPostMainById(Long id)
    {
        return postMainMapper.selectPostMainById(id);
    }

    /**
     * 查询贴子主贴列表
     * 
     * @param postMain 贴子主贴
     * @return 贴子主贴
     */
    @Override
    public List<PostMain> selectPostMainList(PostMain postMain)
    {
        return postMainMapper.selectPostMainList(postMain);
    }

    /**
     * 新增贴子主贴
     * 
     * @param postMain 贴子主贴
     * @return 结果
     */
    @Override
    public int insertPostMain(PostMain postMain)
    {
        postMain.setCreateTime(DateUtils.getNowDate());
        postMain.setUpdateTime(DateUtils.getNowDate());
        return postMainMapper.insertPostMain(postMain);
    }

    /**
     * 修改贴子主贴
     * 
     * @param postMain 贴子主贴
     * @return 结果
     */
    @Override
    public int updatePostMain(PostMain postMain)
    {
        postMain.setUpdateTime(DateUtils.getNowDate());
        postMain.setUpdateTime(DateUtils.getNowDate());
        return postMainMapper.updatePostMain(postMain);
    }

    /**
     * 批量删除贴子主贴
     * 
     * @param ids 需要删除的贴子主贴主键
     * @return 结果
     */
    @Override
    public int deletePostMainByIds(Long[] ids)
    {
        return postMainMapper.deletePostMainByIds(ids);
    }

    /**
     * 删除贴子主贴信息
     * 
     * @param id 贴子主贴主键
     * @return 结果
     */
    @Override
    public int deletePostMainById(Long id)
    {
        return postMainMapper.deletePostMainById(id);
    }

    @Override
    public Integer selectTodayNewPost() {
        return postMainMapper.selectTodayNewPost();
    }

    @Override
    public List<MainDTO> unaudited(Integer startNum, Integer pageSize) {
        return postMainMapper.selectUnaudited(startNum,pageSize);
    }

    @Override
    public Integer updatePostFlagByContentIdList(List<Long> okContentIdList) {
        return postMainMapper.updatePostFlagByContentIdList(okContentIdList);
    }

}
