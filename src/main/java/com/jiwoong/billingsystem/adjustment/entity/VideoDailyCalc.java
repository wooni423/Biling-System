package com.jiwoong.billingsystem.adjustment.entity;

import com.jiwoong.billingsystem.adjustment.entity.key.VideoDailyCalcId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "video_daily_calc")
@IdClass(VideoDailyCalcId.class)
public class VideoDailyCalc {
    @Id
    private LocalDate calcDt;

    @Id
    private Long videoId;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Timestamp createDt;

}
