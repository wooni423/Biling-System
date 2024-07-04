package com.jiwoong.bilingsystem.video.entity;

import com.jiwoong.bilingsystem.auth.entity.Users;
import com.jiwoong.bilingsystem.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "videos")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Videos extends BaseEntity { // 동영상

    @Id
    @Column(name = "video_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToMany(mappedBy = "video", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<AdsWatchHistory> adsWatchHistoryList = new ArrayList<>(); // 동영상 에 추가된 광고 리스트

    @OneToMany(mappedBy = "video", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<UserWatchHistory> userWatchHistoryList = new ArrayList<>(); // 동영상 시청 기록 리스트

    @Column
    private String title; // 동영상 제목

    @Column
    private Long length; // 동영상 길이

    @Column
    private Long view_count; // 동영상 조회 수

    @Column
    private Boolean is_active; // 동영상 공개 여부


    // 조회 수 증가 메서드
    public void updateCount() {
        this.view_count += 1;
    }
}
