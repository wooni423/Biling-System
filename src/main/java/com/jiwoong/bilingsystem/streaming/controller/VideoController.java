package com.jiwoong.bilingsystem.video.controller;

import com.jiwoong.bilingsystem.video.entity.UserWatchHistory;
import com.jiwoong.bilingsystem.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoService videoService;
    private final UserWatchHistory userWatchHistory;

    @GetMapping("/{videoId}")
    public Response

}
