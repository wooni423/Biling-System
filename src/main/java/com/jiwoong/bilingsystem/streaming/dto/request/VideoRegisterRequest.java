package com.jiwoong.bilingsystem.streaming.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoRegisterRequest {

    private String title; // 비디오 제목

    private String url; // 비디오 url

    private Long duration; // 비디오 길이(초 단위)
}
