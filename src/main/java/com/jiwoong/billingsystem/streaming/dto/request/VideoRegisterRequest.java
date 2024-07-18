package com.jiwoong.billingsystem.streaming.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoRegisterRequest {

    private String title; // 비디오 제목

    private Long runningTime; // 동영상 재생 시간

    private String url; // 동영상 url

    private String desc; // 설명
}
