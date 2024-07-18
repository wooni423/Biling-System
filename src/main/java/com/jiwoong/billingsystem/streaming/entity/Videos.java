package com.jiwoong.billingsystem.streaming.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "videos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Videos extends BaseEntity { // 동영상

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId; // 비디오 ID

    @Column(nullable = false)
    private String title; // 동영상 제목

    @Column(nullable = false)
    private Long runningTime; // 재생 시간

    @Column(nullable = false)
    private String url; // 비디오 URL

    @Column(name = "`desc`", nullable = false)
    private String desc; // 비디오 설명

    @Column(nullable = false)
    private Long totalViewCnt = 0L; // 총 조회수

    @Column(nullable = true)
    private Date nxtCalcDate; // 다음 계산 날짜

    @Column(nullable = false)
    private Boolean isActive = true; // 동영상 공개 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader_id",nullable = false)
    private Users uploader; // 비디오 업로더

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<VideoWatchHistory> videoWatchHistoryList = new ArrayList<>(); // 비디오 시청 기록 리스트

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<VideoAdList> videoAdList = new ArrayList<>(); // 비디오 광고 리스트

    @Builder
    public Videos(String title, Long runningTime, String url, String desc, Users uploader) {
        this.title = title;
        this.runningTime = runningTime;
        this.url = url;
        this.desc = desc;
        this.totalViewCnt = 0L;
        this.nxtCalcDate = Timestamp.valueOf(LocalDateTime.now());
        this.uploader = uploader;
    }

    // 광고 추가 메서드
    public void addAd(Ads ad,Long adPosition) {
        VideoAdList videoAdList = VideoAdList.builder()
                .video(this)
                .ad(ad)
                .adPosition(adPosition)
                .build();
        this.videoAdList.add(videoAdList);
    }

    // 조회수 증가 메서드
    public void incrementViewCount() {
        System.out.println("비디오 조회 수 증가");
        this.totalViewCnt += 1L;
    }
}
