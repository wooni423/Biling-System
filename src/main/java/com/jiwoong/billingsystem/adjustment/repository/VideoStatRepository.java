package com.jiwoong.billingsystem.adjustment.repository;

import com.jiwoong.billingsystem.adjustment.entity.VideoStat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface VideoStatRepository extends JpaRepository<VideoStat, Long> {

    // 특정 날짜의 비디오 통계를 조회
    List<VideoStat> findByStatDt(LocalDate statDt);

    // 특정 기간의 비디오 통계를 조회
    List<VideoStat> findByStatDtBetween(LocalDate startDate,LocalDate endDate);

/*    @Query("SELECT new com.jiwoong.billingsystem.streaming.dto.response.VideoStatsResponse(v.videoId, SUM(v.dailyViewCnt)) " +
            "FROM VideoStat v WHERE v.statDt BETWEEN :startDate AND :endDate GROUP BY v.videoId ORDER BY SUM(v.dailyViewCnt) DESC")
    List<VideoStatsResponse> findTop5ByViewCount(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT new com.jiwoong.billingsystem.streaming.dto.response.VideoStatsResponse(v.videoId, v.title, SUM(v.dailyPlayTime)) " +
            "FROM VideoStat v WHERE v.statDt BETWEEN :startDate AND :endDate GROUP BY v.videoId, v.title ORDER BY SUM(v.dailyPlayTime) DESC")
    List<VideoStatsResponse> findTop5ByPlayTime(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);*/
}
