package com.jiwoong.billingsystem.streaming.repository;

import com.jiwoong.billingsystem.streaming.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideosRepository extends JpaRepository<Videos,Long> {

    @Query("SELECT v FROM Videos v LEFT JOIN FETCH v.videoAdList WHERE v.videoId = :videoId")
    Optional<Videos> findByIdWithAds(@Param("videoId") Long videoId);
}
