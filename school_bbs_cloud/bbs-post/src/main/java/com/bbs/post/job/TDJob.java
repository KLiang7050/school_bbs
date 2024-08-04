package com.bbs.post.job;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.bbs.api.thirdparty.RemoteThirdPartyService;
import com.bbs.common.core.domain.post.dto.DetectionLogDTO;
import com.bbs.common.core.domain.post.entity.DetectionLog;
import com.bbs.common.core.domain.post.entity.DetectionRes;
import com.bbs.post.service.DetectionLogService;
import com.bbs.post.service.IPostContentService;
import com.bbs.post.service.IPostMainService;
import com.bbs.post.service.IPostReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TDJob {

    @Autowired
    private RemoteThirdPartyService remoteThirdPartyService;
    @Autowired
    private DetectionLogService detectionLogService;
    @Autowired
    private IPostMainService mainService;
    @Autowired
    private IPostReplyService replyService;
    @Autowired
    private IPostContentService contentService;

    @Scheduled(cron = "0 */1 * * * ?")
    public void textDetectionJob() {
        List<DetectionLog> list = detectionLogService.unHandle();
        if (list.isEmpty()) return;
        List<DetectionLogDTO> contents = contentService.selectDetectionLogDTOByIdList(list);

        List<DetectionLog> newList = new ArrayList<>();
        List<Long> okMainContentIdList = new ArrayList<>();
        List<Long> okReplyContentIdList = new ArrayList<>();
        for (DetectionLogDTO log : contents) {
            DetectionRes detectionRes = remoteThirdPartyService.textDetection(log.getContent());
            if (detectionRes.getCode() == 200) {
                DetectionRes.Data data = detectionRes.getData();
                DetectionLog newLog = new DetectionLog();
                newLog.setId(log.getId());
                newLog.setContentId(log.getId());
                int result = data.getResult();
                newLog.setIsHandle(String.valueOf(result));
                newLog.setMsg(data.getResultMsg());
                StringBuilder wordsSb = new StringBuilder();
                if (result == 1) { // 合规
                    if(log.getReplyFlag().equals("1") || log.getReplyFlag().equals("0")){
                        okReplyContentIdList.add(log.getContentId());
                    }else {
                        okMainContentIdList.add(log.getContentId());
                    }
                } else { // 不合规
                    List<DetectionRes.Data.ResultItem> resultItems = data.getResultItems();
                    for (DetectionRes.Data.ResultItem resultItem : resultItems) {
                        List<DetectionRes.Data.ResultItem.Hit> hits = resultItem.getHits();
                        for (DetectionRes.Data.ResultItem.Hit hit : hits) {
                            List<String> words = hit.getWords();
                            for (String word : words) {
                                wordsSb.append(word);
                            }
                            if(hit.getWordHitPositions().isEmpty()){
                                wordsSb.append("全文");
                            }
                        }
                    }
                }
                newLog.setWords(wordsSb.toString());
                newList.add(newLog);
            }
        }
        Integer res = 0;
        if (!newList.isEmpty()) {
            res = detectionLogService.handle(newList);
        }
        if (!okMainContentIdList.isEmpty()) {
            mainService.updatePostFlagByContentIdList(okMainContentIdList);
        }
        if(!okReplyContentIdList.isEmpty()){
            replyService.updatePostFlagByContentIdList(okReplyContentIdList);
        }

        log.info("预处理" + list.size() + "条日志,成功" + res + "条日志,通过" + okMainContentIdList.size()+okReplyContentIdList.size() + "条贴子");
    }

}
