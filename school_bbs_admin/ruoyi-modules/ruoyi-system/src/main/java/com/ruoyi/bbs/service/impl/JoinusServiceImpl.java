package com.ruoyi.bbs.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.JoinusMapper;
import com.ruoyi.bbs.domain.Joinus;
import com.ruoyi.bbs.service.IJoinusService;

/**
 * 入驻申请Service业务层处理
 *
 * @author ckl
 * @date 2024-04-05
 */
@Service
public class JoinusServiceImpl implements IJoinusService {
    @Autowired
    private JoinusMapper joinusMapper;

    /**
     * 查询入驻申请
     *
     * @param id 入驻申请主键
     * @return 入驻申请
     */
    @Override
    public Joinus selectJoinusById(Long id) {
        return joinusMapper.selectJoinusById(id);
    }

    /**
     * 查询入驻申请列表
     *
     * @param joinus 入驻申请
     * @return 入驻申请
     */
    @Override
    public List<Joinus> selectJoinusList(Joinus joinus) {
        List<Joinus> joinuses = joinusMapper.selectJoinusList(joinus);
        joinuses.forEach(item -> {
            String fileUrl = item.getFileUrl();
            if (fileUrl != null && !fileUrl.isEmpty()) {
                String[] split = fileUrl.split(";;");
                String urlStr = Arrays.stream(split).map(url -> "<p style='line-height: 12px;'><a style='color: blue' href='" + url + "'>" + url.substring(url.indexOf("filename=")+9) + "</a></p>").collect(Collectors.joining());
                item.setFileUrl(urlStr);
            }
        });
        return joinuses;
    }

    /**
     * 新增入驻申请
     *
     * @param joinus 入驻申请
     * @return 结果
     */
    @Override
    public int insertJoinus(Joinus joinus) {
        joinus.setCreateTime(DateUtils.getNowDate());
        joinus.setUpdateTime(DateUtils.getNowDate());
        return joinusMapper.insertJoinus(joinus);
    }

    /**
     * 修改入驻申请
     *
     * @param joinus 入驻申请
     * @return 结果
     */
    @Override
    public int updateJoinus(Joinus joinus) {
        joinus.setUpdateTime(DateUtils.getNowDate());
        return joinusMapper.updateJoinus(joinus);
    }

    /**
     * 批量删除入驻申请
     *
     * @param ids 需要删除的入驻申请主键
     * @return 结果
     */
    @Override
    public int deleteJoinusByIds(Long[] ids) {
        return joinusMapper.deleteJoinusByIds(ids);
    }

    /**
     * 删除入驻申请信息
     *
     * @param id 入驻申请主键
     * @return 结果
     */
    @Override
    public int deleteJoinusById(Long id) {
        return joinusMapper.deleteJoinusById(id);
    }
}
