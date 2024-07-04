package com.jiwoong.bilingsystem.streaming.entity;

import com.jiwoong.bilingsystem.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "advertisements")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Advertisements extends BaseEntity { // 광고

    @Id
    @Column(name = "advertisment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", nullable = false)
    private String url; // 광고 URL

    @Column(name = "view_count",nullable = false)
    private Long viewCount =0L; // 광고 조회 수

    @Column(name = "ad_position", nullable = false)
    private Long adPosition; // 광고가 재생되는 시점 (초 단위)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Videos video; // 광고가 속한 동영상

    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL)
    private List<UserVideoAdsHistory> videoAdsHistoryList = new ArrayList<>(); // 광고 시청 기록들

    // 생성자
    private Advertisements(String url, Long adPosition, Videos video) {
        this.url = url;
        this.adPosition = adPosition;
        this.video = video;
    }

    // Builder 메서드
    public static Advertisements createAd(String url, Long adPosition, Videos video) {
        return new Advertisements(url, adPosition, video);
    }

    // 조회수 증가 메서드
    public void incrementViewCount() {
        this.viewCount++;
    }
}
