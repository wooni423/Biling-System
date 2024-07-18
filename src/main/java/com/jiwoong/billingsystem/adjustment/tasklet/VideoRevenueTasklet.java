package com.jiwoong.billingsystem.adjustment.tasklet;

import com.jiwoong.billingsystem.adjustment.entity.VideoStat;
import com.jiwoong.billingsystem.adjustment.repository.VideoStatRepository;
import com.jiwoong.billingsystem.streaming.entity.VideoAdList;
import com.jiwoong.billingsystem.streaming.repository.VideoAdsRepository;
import com.jiwoong.billingsystem.streaming.repository.VideoWatchHistoryRepository;
import com.jiwoong.billingsystem.streaming.repository.VideosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class VideoRevenueTasklet implements Tasklet {

    private final VideoWatchHistoryRepository videoWatchHistoryRepository;
    private final VideoStatRepository videoStatRepository;
    private final VideosRepository videosRepository;


    // 조회수 구간별 단가 (영상)
    private static final double[] VIDEO_UNIT_PRICES = {1.0, 1.1, 1.3, 1.5};

    // 조회수 구간별 단가 (광고)
    private static final double[] AD_UNIT_PRICES = {10.0, 12.0, 15.0, 20.0};

    // 조회수 구간
    private static final long[] VIEW_COUNT_THRESHOLDS = {100000, 500000, 1000000};

    @Override
    @Transactional
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("정산 작업 시작...");
        long previousTotalViewCount = 100000L; // 전날까지의 누적 조회수
        long todayViewCount = 500000L; // 오늘의 조회수

        // 영상 정산 금액 계산
        double videoRevenue = calculationDailyRevenue(todayViewCount, previousTotalViewCount, VIDEO_UNIT_PRICES);
        System.out.println("오늘의 영상 정산 금액: " + Math.floor(videoRevenue) + " 원");  // 1원 단위 절사

        // 광고 정산 금액 계산
        double adRevenue = calculationDailyRevenue(todayViewCount, previousTotalViewCount, AD_UNIT_PRICES);
        System.out.println("오늘의 광고 정산 금액: " + Math.floor(adRevenue) + " 원");  // 1원 단위 절사

        return RepeatStatus.FINISHED;
    }

    // 일별 조회수에 따른 정산 금액 계산
    private static double calculationDailyRevenue(long todayViewCount, long previousTotalViewCount, double[] unitPrices) {
        double revenue = 0;

        // 오늘 조회수와 누적 조회수를 합친 값을 누적 조회수로 설정
        long cumulativeViewCount = previousTotalViewCount + todayViewCount;

        // 각 구간별로 계산
        for (int i = VIEW_COUNT_THRESHOLDS.length - 1; i >= 0; i--) {
            if (cumulativeViewCount > VIEW_COUNT_THRESHOLDS[i]) {
                long countInThisRange = cumulativeViewCount - VIEW_COUNT_THRESHOLDS[i];
                if (previousTotalViewCount > VIEW_COUNT_THRESHOLDS[i]) {
                    // 이전 누적 조회수가 현재 구간 상한값보다 크면, 오늘 조회수만을 구간 단가로 계산
                    revenue += todayViewCount * unitPrices[i + 1];
                    break;
                } else {
                    // 오늘 조회수 중 해당 구간에 속하는 부분만 계산
                    long todayCountInThisRange = Math.min(todayViewCount, countInThisRange);
                    revenue += todayCountInThisRange * unitPrices[i + 1];
                    todayViewCount -= todayCountInThisRange;
                }
                cumulativeViewCount = VIEW_COUNT_THRESHOLDS[i];
            }
        }
        // 최하위 구간 처리
        if (todayViewCount > 0) {
            revenue += todayViewCount * unitPrices[0];
        }

        return revenue;
    }
}

