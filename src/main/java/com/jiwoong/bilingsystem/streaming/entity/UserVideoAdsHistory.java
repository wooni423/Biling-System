package com.jiwoong.bilingsystem.streaming.entity;


import com.jiwoong.bilingsystem.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "ads_watch_history")
public class UserVideoAdsHistory extends BaseEntity { // 광고 시청 기록

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ads_watch_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id")
    private Advertisements advertisement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Videos video;

    @Column
    private Long location; // 광고 재생 위치
}
