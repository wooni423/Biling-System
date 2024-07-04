package com.jiwoong.bilingsystem.streaming.entity;


import com.jiwoong.bilingsystem.auth.entity.Users;
import com.jiwoong.bilingsystem.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "user_video_ads_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserVideoAdsHistory extends BaseEntity { // 광고 시청 기록

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ads_watch_id")
    private Long id; // 광고 시청 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user; // 광고를 시청한 사용자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id")
    private Advertisements advertisement;

    @Column(name = "watched_date_time", nullable = false)
    private LocalDateTime watchedDateTime; // 광고 시청 일시

    // 생성자
    private UserVideoAdsHistory(Users user, Advertisements advertisement, LocalDateTime watchedDateTime) {
        this.user = user;
        this.advertisement = advertisement;
        this.watchedDateTime = watchedDateTime;
    }

    // Builder 메서드
    public static UserVideoAdsHistory createAdHistory(Users user, Advertisements advertisement, LocalDateTime watchedDateTime) {
        return new UserVideoAdsHistory(user, advertisement, watchedDateTime);
    }
}
