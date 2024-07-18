package com.jiwoong.billingsystem.streaming.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "video_ad_list")
public class VideoAdList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adListId; // 광고 리스트 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Videos video; // 비디오와의 관계 매핑

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id")
    private Ads ad; // 광고와의 관계 매핑

    @Column(nullable = false)
    private Long totalViewCnt; // 광고 시청 수

    @Column(nullable = false)
    private Long adPosition; // 광고 재생 위치 (초 단위)

    @OneToMany(mappedBy = "videoAdList",fetch = FetchType.LAZY)
    private List<AdWatchHistory> adWatchHistoryList;

    @Builder
    public VideoAdList(Videos video, Ads ad,Long adPosition) {
        this.video = video;
        this.ad = ad;
        this.totalViewCnt = 0L;
        this.adPosition =  adPosition;
    }

    // 조회수 증가 메서드
    public void incrementViewCount() {
        this.totalViewCnt += 1L;
    }

    public boolean hasUserWatched(Users user) {
        // 광고 시청 기록이 있는지 확인하는 로직
        if (adWatchHistoryList != null) {
            for (AdWatchHistory history : adWatchHistoryList) {
                if (history.getUser().equals(user)) {
                    return false;
                }
            }
        }
        return true;
    }
}
