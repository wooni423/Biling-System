package com.jiwoong.billingsystem.streaming.repository;

import com.jiwoong.billingsystem.streaming.entity.VideoAdList;
import org.springframework.data.jpa.repository.JpaRepository;



public interface VideoAdsRepository extends JpaRepository<VideoAdList,Long> {

}
