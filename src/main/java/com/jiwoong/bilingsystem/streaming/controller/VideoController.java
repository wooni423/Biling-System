package com.jiwoong.bilingsystem.streaming.controller;

import com.jiwoong.bilingsystem.auth.service.CustomOAuth2User;
import com.jiwoong.bilingsystem.streaming.dto.request.VideoRegisterRequest;
import com.jiwoong.bilingsystem.streaming.dto.response.VideoPlayBackResponse;
import com.jiwoong.bilingsystem.streaming.service.VideoService;
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
                                       @RequestParam("currentTimestamp") Long currentTimestamp,
                                       HttpServletRequest request) {
        String ipAddress = getClientIp(request);
        VideoPlayBackResponse response = videoService.videoPlay(videoId, loginUser, ipAddress, currentTimestamp);
        return ResponseEntity.ok(response);
    }


    // 동영상 등록 api
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<?> registerVideo(@RequestBody VideoRegisterRequest request,
                                           @AuthenticationPrincipal CustomOAuth2User loginUser) {
        videoService.videoRegister(request,loginUser);
        return null;
    }

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
