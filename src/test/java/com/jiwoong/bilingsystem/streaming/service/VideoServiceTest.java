package com.jiwoong.bilingsystem.video.service;

import com.jiwoong.bilingsystem.auth.entity.Users;
import com.jiwoong.bilingsystem.auth.repository.UserRepository;
import com.jiwoong.bilingsystem.video.entity.UserWatchHistory;
import com.jiwoong.bilingsystem.video.entity.Videos;
import com.jiwoong.bilingsystem.video.repository.UserWatchHistoryRepository;
import com.jiwoong.bilingsystem.video.repository.VideosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

    // @Mock -> 가짜 객체 주입
    @InjectMocks
    private VideoService streamingService;
    @Mock 
    private VideosRepository videosRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserWatchHistoryRepository userWatchHistoryRepository;

    // 연관 Entity 객체
    static Users user;
    static Videos videos;
    static UserWatchHistory userWatchHistory;

    //연관 Entity 객체 사전 생성
    @BeforeEach
    public void setup() {
        user = Users.toEntity("kakao 1234", "최지웅", "test@naver.com",
                com.jiwoong.bilingsystem.auth.dto.ROLE.USER);
        videos = Videos.builder()
                .id(1L)
                .user(user)
                .title("동영상 제목")
                .length(LocalTime.of(0, 10, 0))
                .view_count(0L)
                .is_active(true)
                .build();
        userWatchHistory = UserWatchHistory.builder()
                .id(1L)
                .user(user)
                .video(videos)
                .watch_history(LocalTime.of(0, 10, 0))
                .build();
        userRepository.saveAndFlush(user);
        videosRepository.saveAndFlush(videos);

    }

    @Test
    @DisplayName("동영상 조회수 증가 서비스")
    public void 동영상조회수증가() {
        // given

        // when

        // then
    }
}
