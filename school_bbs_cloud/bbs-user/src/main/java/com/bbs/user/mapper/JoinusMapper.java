package com.bbs.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bbs.common.core.domain.user.entity.Joinus;
import com.bbs.common.core.domain.user.vo.JoinusVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JoinusMapper extends BaseMapper<Joinus> {


    List<JoinusVo> listByUserId(String userId);

    JoinusVo selectOneById(Long id);
}
