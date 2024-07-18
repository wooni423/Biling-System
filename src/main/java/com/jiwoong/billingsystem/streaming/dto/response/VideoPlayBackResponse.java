package com.jiwoong.billingsystem.streaming.dto.response;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@NoArgsConstructor
public class VideoPlayBackResponse {

    private String url; // 비디오 url
    private Long lastWatchedTimeStamp; // 마지막 시청 시간(초단위)


    public VideoPlayBackResponse(String url, Long lastWatchedTimestamp) {
        this.url = url;
        this.lastWatchedTimeStamp = lastWatchedTimestamp;
    }
}
