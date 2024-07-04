package com.jiwoong.bilingsystem.streaming.repository;

import com.jiwoong.bilingsystem.auth.entity.Users;
import com.jiwoong.bilingsystem.streaming.entity.Advertisements;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jiwoong.bilingsystem.streaming.entity.UserVideoAdsHistory;

public interface UserVideoAdsHistoryRepository extends JpaRepository<UserVideoAdsHistory,Long> {

    boolean existsByUserAndAdvertisement(Users user, Advertisements advertisement);
}
