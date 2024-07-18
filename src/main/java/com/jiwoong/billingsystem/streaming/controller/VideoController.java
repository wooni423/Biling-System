package com.jiwoong.billingsystem.streaming.controller;

import com.jiwoong.billingsystem.streaming.service.CustomOAuth2User;
import com.jiwoong.billingsystem.streaming.dto.request.VideoRegisterRequest;
import com.jiwoong.billingsystem.streaming.dto.response.VideoPlayBackResponse;
import com.jiwoong.billingsystem.streaming.service.VideoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/videos")
@Log4j2
public class VideoController {

    private final VideoService videoService;

    // 동영상 재생 api
    @GetMapping("/{videoId}/play")
    public ResponseEntity<?> playVideo(@PathVariable("videoId") Long videoId,
                                       @AuthenticationPrincipal CustomOAuth2User loginUser,
                                       HttpServletRequest request) {
        String ipAddress = getClientIp(request);
        log.info(ipAddress);
        VideoPlayBackResponse response = videoService.videoPlay(videoId, loginUser.getUsername(), ipAddress);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/{videoId}/stop")
    public ResponseEntity<?> stopVideo(@PathVariable("videoId") Long videoId,
                                        @AuthenticationPrincipal CustomOAuth2User loginUser,
                                        @RequestParam("currentTimestamp") Long currentTimestamp
            , HttpServletRequest request) {
        String ipAddress = getClientIp(request);
        videoService.videoStop(videoId, loginUser.getUsername(),currentTimestamp, ipAddress);
        return ResponseEntity.ok("동영상 stop");
    }

    // 동영상 등록 api
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<?> registerVideo(@RequestBody VideoRegisterRequest request,
                                           @AuthenticationPrincipal CustomOAuth2User loginUser) {
        log.info(loginUser.getUsername());
        videoService.videoRegister(request, loginUser.getUsername());
        return ResponseEntity.ok("동영상 등록 ok");
    }


    // 접속 ip 판단
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // Handle localhost address
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }

        return ip;
    }


}
