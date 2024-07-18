package com.jiwoong.billingsystem.streaming.entity;

import com.jiwoong.billingsystem.streaming.entity.key.VideoWatchHistoryId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "video_watch_history")
@IdClass(VideoWatchHistoryId.class)
public class VideoWatchHistory {  // 유저 영상 시청 기록
    @Id
    private LocalDate histDt; // 시청 기록 Date Id

    @Id
    private Long videoId; // 시청 기록 ID

    @Id
    private Long userId; // 유저 ID

    @Column(nullable = false)
    private Long playbackTimeline;

    @Column(nullable = false)
    private LocalDateTime createDt;

    @Column(nullable = false)
    private String ipAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @MapsId("userId")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    @MapsId("videoId")
    private Videos video;

    @Builder
    public VideoWatchHistory(Users user, Videos video, Long playbackTimeline,String ipAddress) {
        this.histDt = LocalDate.now();
        this.videoId = video.getVideoId();
        this.userId = user.getUserId();
        this.playbackTimeline = playbackTimeline;
        this.createDt = LocalDateTime.now();
        this.ipAddress = ipAddress;
        this.video = video;
        this.user = user;
    }

    // 시청 시간 업데이트 메서드
    public void updateLastWatchedTime(Long lastWatchedTimestamp, LocalDateTime lastWatchedDateTime) {
        this.playbackTimeline = lastWatchedTimestamp;
        this.createDt = lastWatchedDateTime;
    }
}
