package com.jiwoong.bilingsystem.streaming.entity;

import com.jiwoong.bilingsystem.auth.entity.Users;
import com.jiwoong.bilingsystem.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "videos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Videos extends BaseEntity { // 동영상

    @Id
    @Column(name = "video_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "uploader_id")
    private Users uploader; // 동영상 업로더

    @Column(name = "title", nullable = false)
    private String title; // 동영상 제목

    @Column(name = "url", nullable = false)
    private String url; // 비디오 URL

    @Column(name = "view_count",nullable = false)
    private Long viewCount = 0L; // 동영상 조회 수

    @OneToMany(mappedBy = "video",fetch = FetchType.LAZY,orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Advertisements> advertisementList = new ArrayList<>(); // 동영상 에 포함된 광고 목록

    @OneToMany(mappedBy = "video",fetch = FetchType.LAZY,orphanRemoval = true, cascade = CascadeType.ALL)
    private List<UserVideoHistory> userVideoHistoryList = new ArrayList<>(); // 동영상 에 추가된 광고 시청 목록

    @Column(name = "duration",nullable = false)
    private Long duration; // 동영상 길이

    @Column(name = "is_active",nullable = false)
    private Boolean isActive = true; // 동영상 공개 여부


    // 생성자
    private Videos(String title, String url, Long duration, Users uploader) {
        this.title = title;
        this.url = url;
        this.duration = duration;
        this.uploader = uploader;
    }

    // Builder 메서드
    public static Videos createVideo(String title, String url, Long duration, Users uploader) {
        return new Videos(title, url, duration, uploader);
    }

    // 조회수 증가 메서드
    public void incrementViewCount() {
        this.viewCount++;
    }

    // 광고 추가 메서드
    public void addAd(Advertisements advertisement) {
        this.advertisementList.add(advertisement);
    }

}
