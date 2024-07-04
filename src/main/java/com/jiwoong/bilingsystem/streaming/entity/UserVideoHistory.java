package com.jiwoong.bilingsystem.streaming.entity;

import com.jiwoong.bilingsystem.auth.entity.Users;
import com.jiwoong.bilingsystem.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_Video_history")
@ToString(exclude = {"user", "video"})
public class UserVideoHistory extends BaseEntity {  // 유저 영상 시청 기록

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_video_history_id")
    private Long id; // 시청 기록 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user; // 시청한 사용자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Videos video; // 시청한 비디오

    @Column(name = "last_watched_timestamp", nullable = false)
    private Long lastWatchedTimestamp = 0L; // 마지막 시청 시간 (초 단위)

    @Column(name = "last_watched_date_time", nullable = false)
    private LocalDateTime lastWatchedDateTime; // 마지막 시청 일시

    // 생성자
    private UserVideoHistory(Users user, Videos video, Long lastWatchedTimestamp, LocalDateTime lastWatchedDateTime) {
        this.user = user;
        this.video = video;
        this.lastWatchedTimestamp = lastWatchedTimestamp;
        this.lastWatchedDateTime = lastWatchedDateTime;
    }

    // Builder 메서드
    public static UserVideoHistory createVideoHistory(Users user, Videos video, Long lastWatchedTimestamp, LocalDateTime lastWatchedDateTime) {
        return new UserVideoHistory(user, video, lastWatchedTimestamp, lastWatchedDateTime);
    }

    // 시청 시간 업데이트 메서드
    public void updateLastWatchedTime(Long lastWatchedTimestamp, LocalDateTime lastWatchedDateTime) {
        this.lastWatchedTimestamp = lastWatchedTimestamp;
        this.lastWatchedDateTime = lastWatchedDateTime;
    }

}
