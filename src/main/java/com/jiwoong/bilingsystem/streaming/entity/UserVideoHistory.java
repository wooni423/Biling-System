package com.jiwoong.bilingsystem.streaming.entity;

import com.jiwoong.bilingsystem.auth.entity.Users;
import com.jiwoong.bilingsystem.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_watch_history")
@ToString(exclude = {"user", "video"})
public class UserVideoHistory extends BaseEntity {  // 유저 영상 시청 기록

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_watch_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Videos video;

    @Column(name = "watch_history")
    private Long watch_history; // 마지막 재생 시점

    @Builder
    public UserVideoHistory(Users user, Videos video, Long watch_history) {
        this.user = user;
        this.video = video;
        this.watch_history = watch_history;
    }

    // 시청 기록 update 메서드
    public void updateEntity(Long watch_history) {
        this.watch_history = watch_history;
    }
}
