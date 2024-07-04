package com.jiwoong.bilingsystem.video.entity;

import com.jiwoong.bilingsystem.global.entity.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "advertisements")

public class Advertisements extends BaseEntity { // 광고

    @Id
    @Column(name = "advertisment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Videos video; // Videos fk 설정

    @OneToMany(mappedBy = "advertisement", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<AdsWatchHistory> adsWatchHistoryList = new ArrayList<>(); // 동영상 에 추가된 광고 리스트

    @Column
    private String title; // 광고 제목

    @Column
    private Long length; // 광고 길이

    @Column
    private Long view_count; // 광고 조회 수

    @Column
    private Boolean is_active; // 동영상 공개 여부
}
