package com.ruoyi.bbs.service.impl;

import com.ruoyi.bbs.domain.DetectionLog;
import com.ruoyi.bbs.domain.PostMain;
import com.ruoyi.bbs.domain.PostReply;
import com.ruoyi.bbs.domain.dto.DetectionLogDTO;
import com.ruoyi.bbs.mapper.DetectionLogMapper;
import com.ruoyi.bbs.mapper.PostMainMapper;
import com.ruoyi.bbs.mapper.PostReplyMapper;
import com.ruoyi.bbs.service.DetectionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DetectionLogServiceImpl implements DetectionLogService {
    @Autowired
    private PostMainMapper postMainMapper;
    @Autowired
    private PostReplyMapper postReplyMapper;

    @Autowired
    private DetectionLogMapper detectionLogMapper;

    @Override
    public List<DetectionLog> unHandle() {
        return detectionLogMapper.unHandle();
    }

    @Override
    public Integer handle(List<DetectionLog> newList) {
        return detectionLogMapper.handle(newList);
    }

    @Override
    public List<DetectionLogDTO> getOutList(Integer startNum, Integer pageSize) {
        return detectionLogMapper.getOutList(startNum,pageSize);
    }

    @Override
    public boolean pass(Long id) {
        DetectionLog detectionLog = detectionLogMapper.getById(id);
        String replyFlag = detectionLog.getReplyFlag();
        if(replyFlag.equals("1")){
            PostReply postReply = new PostReply();
            postReply.setIsEnable("1");
            postReplyMapper.updatePostReply(postReply);
        }else{
            PostMain postMain = new PostMain();
            postMain.setIsEnable("1");
            postMainMapper.updatePostMain(postMain);
        }
        return detectionLogMapper.pass(id) > 0;
    }

    @Override
    public boolean ban(Long id) {
        DetectionLog detectionLog = detectionLogMapper.getById(id);
        String replyFlag = detectionLog.getReplyFlag();
        if(replyFlag.equals("1")){
            PostReply postReply = new PostReply();
            postReply.setIsEnable("1");
            postReplyMapper.updatePostReply(postReply);
        }else{
            PostMain postMain = new PostMain();
            postMain.setIsEnable("1");
            postMainMapper.updatePostMain(postMain);
        }
        return detectionLogMapper.ban(id) > 0;
    }
}