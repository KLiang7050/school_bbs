package com.bbs.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbs.common.core.domain.user.entity.Joinus;
import com.bbs.common.core.domain.user.vo.JoinusVo;
import com.bbs.user.mapper.JoinusMapper;
import com.bbs.user.service.IJoinusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户Service业务层处理
 *
 * @author ckl
 * @date 2023-10-03
 */
@Service
public class JoinusServiceImpl extends ServiceImpl<JoinusMapper, Joinus> implements IJoinusService {

    @Autowired
    private JoinusMapper joinusMapper;


    @Override
    public List<JoinusVo> list(String userId) {
        return joinusMapper.listByUserId(userId);
    }

    @Override
    public void saveJoinus(Joinus joinus) {
        Long id = joinus.getId();
        boolean isNull = true;

        if (id != null) {
            Joinus temp = baseMapper.selectById(joinus.getId());
            if (temp != null) {
                isNull = false;
            }
        }

        if (isNull) {
            joinus.setStatus("0");
            baseMapper.insert(joinus);
        } else {
            baseMapper.updateById(joinus);
        }
    }

    @Override
    public JoinusVo one(Long id) {
        return baseMapper.selectOneById(id);
    }

    @Override
    public boolean commitJoinus(Joinus joinus) {
        Long id = joinus.getId();
        boolean isNull = true;
        Integer res = 0;

        if (id != null) {
            Joinus temp = baseMapper.selectById(joinus.getId());
            if (temp != null) {
                isNull = false;
            }
        }

        joinus.setBackFlag("0");
        if (isNull) {
            joinus.setStatus("1");
            res = baseMapper.insert(joinus);
        } else {
            if(joinus.getStatus().equals("4")) throw new RuntimeException("已归档");
            joinus.setStatus((Integer.parseInt(joinus.getStatus()) + 1) + "");
            res = baseMapper.updateById(joinus);
        }
        return res > 0;
    }
}
