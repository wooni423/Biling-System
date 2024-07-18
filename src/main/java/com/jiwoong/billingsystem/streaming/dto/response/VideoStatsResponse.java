package com.jiwoong.billingsystem.streaming.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoStatsResponse {

    private LocalDate histDt; // 통계 날짜
    private Long videoId; // 동영상 ID
    private Long viewCount; // 조회 수
    private Long totalPlaybackTime; // 누적 재생시간
}
