package com.jiwoong.billingsystem.adjustment.entity;

import com.jiwoong.billingsystem.adjustment.entity.key.AdStatId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ad_stat")
@IdClass(AdStatId.class)
public class AdStat {
    @Id
    private LocalDate statDt;

    @Id
    private Long adId;

    @Column(nullable = false)
    private Long dailyViewCnt; //  일간 조회수

    @Column(nullable = false)
    private Timestamp createDt;
}
