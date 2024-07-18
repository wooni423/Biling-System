package com.jiwoong.billingsystem.adjustment.tasklet;


import com.jiwoong.billingsystem.adjustment.entity.VideoStat;
import com.jiwoong.billingsystem.adjustment.repository.VideoStatRepository;
import com.jiwoong.billingsystem.streaming.dto.response.VideoStatsResponse;
import com.jiwoong.billingsystem.streaming.repository.VideoWatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Component
@StepScope
@RequiredArgsConstructor
public class VideoStatTasklet implements Tasklet {

    private final VideoWatchHistoryRepository videoWatchHistoryRepository;
    private final VideoStatRepository videoStatRepository;

    @Override
    @Transactional
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("비디오 통계 start...");
        LocalDate today = LocalDate.now();
        System.out.println("오늘 날짜 : " + today);

        try {
            // 오늘 날짜의 비디오 시청 기록을 비디오 ID별로 그룹화하여 조회
            List<VideoStatsResponse> stats = videoWatchHistoryRepository.findVideoStatsByWatchDate(today);

            /*List<VideoStatsResponse> stats = videoWatchHistoryRepository.findAllVideoStats();
            * */

            if (stats.isEmpty()) {
                System.out.println("오늘 날짜의 비디오 시청 기록이 없습니다.");
            }

            for (VideoStatsResponse stat : stats) {
                LocalDate statDate = stat.getHistDt();
                Long videoId = stat.getVideoId();
                Long viewCount = stat.getViewCount();
                Long totalPlayTime = stat.getTotalPlaybackTime();

                VideoStat videoStat = new VideoStat(statDate, videoId, viewCount, totalPlayTime, new Timestamp(System.currentTimeMillis()));
                videoStatRepository.save(videoStat);
                System.out.println("videoStat에 저장됨");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // 예외를 던져서 스프링 배치가 예외를 처리할 수 있도록 함
        }
        return RepeatStatus.FINISHED;
    }
}
