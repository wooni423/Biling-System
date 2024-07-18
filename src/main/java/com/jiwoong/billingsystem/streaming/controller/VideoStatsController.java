package com.jiwoong.billingsystem.streaming.controller;

import com.jiwoong.billingsystem.streaming.dto.response.VideoStatsResponse;
import com.jiwoong.billingsystem.streaming.service.VideoStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/videos")
public class VideoStatsController {

    private final VideoStatsService videoStatsService;

    /*@GetMapping("/top5/viewCount/daily")
    public List<VideoStatsResponse> getTop5ByViewCountDaily(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return videoStatsService.getTop5ByViewCount(date, date);
    }

    @GetMapping("/top5/viewCount/weekly")
    public List<VideoStatsResponse> getTop5ByViewCountWeekly(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDate startDate = date.minusDays(6); // 일주일 전
        return videoStatsService.getTop5ByViewCount(startDate, date);
    }

    @GetMapping("/top5/viewCount/monthly")
    public List<VideoStatsResponse> getTop5ByViewCountMonthly(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDate startDate = date.withDayOfMonth(1); // 해당 월의 첫 날
        return videoStatsService.getTop5ByViewCount(startDate, date);
    }

    @GetMapping("/top5/playTime/daily")
    public List<VideoStatsResponse> getTop5ByPlayTimeDaily(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return videoStatsService.getTop5ByPlayTime(date, date);
    }

    @GetMapping("/top5/playTime/weekly")
    public List<VideoStatsResponse> getTop5ByPlayTimeWeekly(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDate startDate = date.minusDays(6); // 일주일 전
        return videoStatsService.getTop5ByPlayTime(startDate, date);
    }

    @GetMapping("/top5/playTime/monthly")
    public List<VideoStatsResponse> getTop5ByPlayTimeMonthly(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDate startDate = date.withDayOfMonth(1); // 해당 월의 첫 날
        return videoStatsService.getTop5ByPlayTime(startDate, date);
    }*/

}
