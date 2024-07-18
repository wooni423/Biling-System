package com.jiwoong.billingsystem.adjustment.entity;

import com.jiwoong.billingsystem.adjustment.entity.key.AdDailyCalcId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ad_daily_calc")
@IdClass(AdDailyCalcId.class)
public class AdDailyCalc {

    @Id
    private LocalDate calcDt;

    @Id
    private Long adId;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Timestamp createDt;
}
