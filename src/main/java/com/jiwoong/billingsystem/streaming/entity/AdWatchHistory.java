package com.jiwoong.billingsystem.streaming.entity;


import com.jiwoong.billingsystem.streaming.entity.key.AdWatchHistoryId;
import com.jiwoong.billingsystem.streaming.entity.key.VideoWatchHistoryId;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "ad_watch_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@IdClass(AdWatchHistoryId.class)
public class AdWatchHistory { // 광고 시청 기록
    @Id
    private LocalDate histDt; // 히스토리 날짜

    @Id
    private Long adListId;

    @Id
    private Long userId; // 유저 ID

    @Column(nullable = false)
    private Timestamp createDt; // 생성 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_list_id", insertable = false, updatable = false)
    @MapsId("adListId")
    private VideoAdList videoAdList; // 광고 리스트와의 관계 매핑

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @MapsId("userId")
    private Users user; // 사용자와의 관계 매핑

    @Builder
    public AdWatchHistory(Users user, VideoAdList videoAdList) {
        this.histDt = LocalDate.now();
        this.adListId = videoAdList.getAdListId();
        this.userId = user.getUserId();
        this.createDt = Timestamp.valueOf(LocalDateTime.now());
        this.videoAdList = videoAdList;
        this.user = user;
    }
}
