package com.jiwoong.billingsystem.streaming.repository;

import com.jiwoong.billingsystem.streaming.dto.response.VideoStatsResponse;
import com.jiwoong.billingsystem.streaming.entity.VideoWatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface VideoWatchHistoryRepository extends JpaRepository<VideoWatchHistory, Long> {

    @Query("SELECT h FROM VideoWatchHistory h WHERE h.user.username = :username AND h.video.videoId = :videoId AND h.createDt > :thirtySecondsAgo AND h.ipAddress = :ipAddress ORDER BY h.createDt DESC")
    Optional<VideoWatchHistory> findRecentHistory(@Param("username") String username, @Param("videoId") Long videoId, @Param("thirtySecondsAgo") LocalDateTime thirtySecondsAgo, @Param("ipAddress") String ipAddress);

    @Query("SELECT h FROM VideoWatchHistory h WHERE h.user.username = :username AND h.video.videoId = :videoId ORDER BY h.createDt DESC")
    Optional<VideoWatchHistory> findByUsernameAndVideoId(@Param("username") String username, @Param("videoId") Long videoId);

    // 특정 날짜의 비디오 시청 기록을 videoId 별로 그룹화하여 가져오는 메서드
    @Query("SELECT new com.jiwoong.billingsystem.streaming.dto.response.VideoStatsResponse(v.histDt,v.video.videoId, COUNT(v), SUM(v.playbackTimeline)) " +
            "FROM VideoWatchHistory v WHERE v.histDt = :watchDate GROUP BY v.video.videoId")
    List<VideoStatsResponse> findVideoStatsByWatchDate(@Param("watchDate") LocalDate watchDate);

    // 모든 날짜의 비디오 시청 기록을 videoId 별로 그룹화하여 가져오는 메서드
    @Query("SELECT new com.jiwoong.billingsystem.streaming.dto.response.VideoStatsResponse(v.histDt,v.video.videoId, COUNT(v), SUM(v.playbackTimeline)) " +
            "FROM VideoWatchHistory v GROUP BY v.video.videoId, v.histDt")
    List<VideoStatsResponse> findAllVideoStats();
}

