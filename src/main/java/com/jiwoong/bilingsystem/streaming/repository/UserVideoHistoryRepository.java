package com.jiwoong.bilingsystem.streaming.repository;

import com.jiwoong.bilingsystem.streaming.entity.UserVideoHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserVideoHistoryRepository extends JpaRepository<UserVideoHistory, Long> {

    UserVideoHistory findByVideoIdAndUserId(Long videoId, Long userId);

    @Query("SELECT uwh FROM UserVideoHistory uwh WHERE uwh.user.username= :username AND uwh.video.id = :videoId")
    UserVideoHistory findUserWatchHistoryByUserName(@Param("username") String username, @Param("videoId") Long videoId);

}
