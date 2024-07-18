package com.jiwoong.billingsystem.streaming.service;

import com.jiwoong.billingsystem.adjustment.repository.VideoStatRepository;
import com.jiwoong.billingsystem.streaming.dto.response.VideoStatsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoStatsService {

    private final VideoStatRepository videoStatRepository;
/*
    public List<VideoStatsResponse> getTop5ByViewCount(LocalDate startDate, LocalDate endDate) {
        return videoStatRepository.findTop5ByViewCount(startDate, endDate);
    }

    public List<VideoStatsResponse> getTop5ByPlayTime(LocalDate startDate, LocalDate endDate) {
        return videoStatRepository.findTop5ByPlayTime(startDate, endDate);
    }*/
}
