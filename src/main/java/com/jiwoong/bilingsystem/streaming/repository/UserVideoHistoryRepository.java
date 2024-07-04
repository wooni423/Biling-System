package com.jiwoong.bilingsystem.streaming.repository;

import com.jiwoong.bilingsystem.streaming.entity.UserVideoHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.Optional;


public interface UserVideoHistoryRepository extends JpaRepository<UserVideoHistory, Long> {

    @Query("SELECT uvh FROM UserVideoHistory uvh WHERE uvh.user.username = :username AND uvh.video.id = :videoId")
    Optional<UserVideoHistory> findByUsernameAndVideoId(@Param("username") String username, @Param("videoId") Long videoId);

    @Query("SELECT uvh FROM UserVideoHistory uvh WHERE uvh.user.username = :username AND uvh.video.id = :videoId AND uvh.user.ipAddress = :ipAddress AND uvh.lastWatchedDateTime > :thirtySecondsAgo")
    Optional<UserVideoHistory> findRecentHistory(@Param("username") String username,
                                                 @Param("videoId") Long videoId,
                                                 @Param("ipAddress") String ipAddress,
                                                 @Param("thirtySecondsAgo") LocalDateTime thirtySecondsAgo);

}
