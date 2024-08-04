package com.bbs.post.job;

import com.bbs.api.thirdparty.RemoteThirdPartyService;
import com.bbs.common.core.domain.post.dto.DetectionLogDTO;
import com.bbs.common.core.domain.post.entity.DetectionLog;
import com.bbs.common.core.domain.post.entity.DetectionRes;
import com.bbs.common.core.exception.BizException;
import com.bbs.post.service.DetectionLogService;
import com.bbs.post.service.IPostContentService;
import com.bbs.post.service.IPostMainService;
import com.bbs.post.service.IPostReplyService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TDJob {
    private static final Logger log = LoggerFactory.getLogger(TDJob.class);

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
    @Autowired
    ThreadPoolExecutor executor;

    // @Scheduled(cron = "0 */1 * * * ?")
    // public void textDetectionJob() {
    //     List<DetectionLog> list = detectionLogService.unHandle();
    //     if (list.isEmpty()) return;
    //     List<DetectionLogDTO> contents = contentService.selectDetectionLogDTOByIdList(list);
    //
    //     List<DetectionLog> newList = new ArrayList<>();
    //     List<Long> okMainContentIdList = new ArrayList<>();
    //     List<Long> okReplyContentIdList = new ArrayList<>();
    //     for (DetectionLogDTO log : contents) {
    //         DetectionRes detectionRes = remoteThirdPartyService.textDetection(log.getContent());
    //         if (detectionRes.getCode() == 200) {
    //             DetectionRes.Data data = detectionRes.getData();
    //             DetectionLog newLog = new DetectionLog();
    //             newLog.setId(log.getId());
    //             newLog.setContentId(log.getId());
    //             int result = data.getResult();
    //             newLog.setIsHandle(String.valueOf(result));
    //             newLog.setMsg(data.getResultMsg());
    //             StringBuilder wordsSb = new StringBuilder();
    //             if (result == 1) { // 合规
    //                 if(log.getReplyFlag().equals("1") || log.getReplyFlag().equals("0")){
    //                     okReplyContentIdList.add(log.getContentId());
    //                 }else {
    //                     okMainContentIdList.add(log.getContentId());
    //                 }
    //             } else { // 不合规
    //                 List<DetectionRes.Data.ResultItem> resultItems = data.getResultItems();
    //                 for (DetectionRes.Data.ResultItem resultItem : resultItems) {
    //                     List<DetectionRes.Data.ResultItem.Hit> hits = resultItem.getHits();
    //                     for (DetectionRes.Data.ResultItem.Hit hit : hits) {
    //                         List<String> words = hit.getWords();
    //                         for (String word : words) {
    //                             wordsSb.append(word);
    //                         }
    //                         if(hit.getWordHitPositions().isEmpty()){
    //                             wordsSb.append("全文");
    //                         }
    //                     }
    //                 }
    //             }
    //             newLog.setWords(wordsSb.toString());
    //             newList.add(newLog);
    //         }
    //     }
    //     Integer res = 0;
    //     if (!newList.isEmpty()) {
    //         res = detectionLogService.handle(newList);
    //     }
    //     if (!okMainContentIdList.isEmpty()) {
    //         mainService.updatePostFlagByContentIdList(okMainContentIdList);
    //     }
    //     if(!okReplyContentIdList.isEmpty()){
    //         replyService.updatePostFlagByContentIdList(okReplyContentIdList);
    //     }
    //
    //     log.info("预处理{}条日志,成功{}条日志,通过{}{}条贴子", list.size(), res, okMainContentIdList.size(), okReplyContentIdList.size());
    // }

    @Scheduled(cron = "0 */1 * * * ?")
    public void textDetectionJob() {
        List<DetectionLog> logs = detectionLogService.unHandle();
        if (logs.isEmpty()) return;

        List<DetectionLogDTO> contents = contentService.selectDetectionLogDTOByIdList(logs);
        List<DetectionLogDTO> updatedLogs = new ArrayList<>();
        int updateCount = 0;
        try {
            for (DetectionLogDTO content : contents) {
                // 发送文本审核请求，获取审核结果
                DetectionLogDTO detect = detect(content);
                updatedLogs.add(detect);
            }
            // 更新贴子状态
            updatePostStatus(contents);
            // 更新审核日志
            updateCount += detectionLogService.handle(updatedLogs);
        } catch (BizException e) {
            log.error("文本审核失败", e);
        }

        log.info("预处理{}条日志,成功{}条日志,通过{}条贴子", logs.size(), updateCount, contents.size());
    }

    /**
     * 发送文本审核请求
     */
    private DetectionLogDTO detect(DetectionLogDTO log) throws BizException {
        DetectionRes detectionRes = remoteThirdPartyService.textDetection(log.getContent());
        if (detectionRes.getCode() != 200) {
            throw new BizException("文本审核失败");
        }

        DetectionLogDTO detectionLog = new DetectionLogDTO();
        DetectionRes.Data data = detectionRes.getData();

        log.setIsHandle(String.valueOf(data.getResult()));
        log.setMsg(data.getResultMsg());
        log.setWords(extractWords(data));
        return detectionLog;
    }

    /**
     * 处理文本审核结果
     */
    private String extractWords(DetectionRes.Data data) {
        if (data.getResult() == 1) { // 合规
            return "";
        }

        return data.getResultItems().stream()
                .flatMap(item -> item.getHits().stream())
                .flatMap(hit -> hit.getWords().stream())
                .collect(Collectors.joining(","));
    }

    /**
     * 更新贴子状态
     */
    private void updatePostStatus(List<DetectionLogDTO> contents) {
        List<Long> compliantMainContentIds = contents.stream()
                .filter(log -> !log.getReplyFlag().equals("1"))
                .map(DetectionLogDTO::getContentId)
                .collect(Collectors.toList());

        List<Long> compliantReplyContentIds = contents.stream()
                .filter(log -> log.getReplyFlag().equals("1"))
                .map(DetectionLogDTO::getContentId)
                .collect(Collectors.toList());

        if (!compliantMainContentIds.isEmpty()) {
            mainService.updatePostFlagByContentIdList(compliantMainContentIds);
        }

        if (!compliantReplyContentIds.isEmpty()) {
            replyService.updatePostFlagByContentIdList(compliantReplyContentIds);
        }
    }

}
