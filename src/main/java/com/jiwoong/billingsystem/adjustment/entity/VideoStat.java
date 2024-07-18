package com.jiwoong.billingsystem.adjustment.entity;

import com.jiwoong.billingsystem.adjustment.entity.key.VideoStatId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "video_stat")
@IdClass(VideoStatId.class)
public class VideoStat {

    @Id
    private LocalDate statDt;

    @Id
    private Long videoId;

    @Column(nullable = false)
    private Long dailyViewCnt; // 일간 조회 수

    @Column(nullable = false)
    private Long dailyPlayTime; // 일간 총 재생 시간

    @Column(nullable = false)
    private Timestamp createDt;

}
