package com.jiwoong.bilingsystem.video.repository;

import com.jiwoong.bilingsystem.auth.entity.Users;
import com.jiwoong.bilingsystem.auth.repository.UserRepository;
import com.jiwoong.bilingsystem.video.entity.Videos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // Repository 테스트 annotation
public class VideoRepositoryTest {

    @Autowired
    private VideosRepository videosRepository;

    @Autowired
    private UserRepository userRepository;

    // TODO : 재생 시 기존에 조회 했던 영상의 경우 최근 재생 시점부터 재생되고,
    //  최초 조회의 경우 처음 부터 조회 된다. 재생 시 해당 영상의 조회수가 증가 한다.

    // 연관 Entity 객체
    static Users user;
    static Videos videos;

    //연관 Entity 객체 사전 생성
    @BeforeEach
    public void setup() {
        user = Users.toEntity("kakao 1234", "최지웅", "test@naver.com",
                com.jiwoong.bilingsystem.auth.dto.ROLE.USER);
        videos = Videos.builder()
                .id(1L)
                .user(user)
                .title("동영상 제목")
                .length(20000L)
                .view_count(0L)
                .is_active(true)
                .build();
        userRepository.saveAndFlush(user);
        videosRepository.saveAndFlush(videos);
    }

    @Test
    @DisplayName("동영상조회수증가")
    public void 동영상조회수증가() {
        // given
        videos.updateCount();
        // when
        Videos result = videosRepository.save(videos);
        // then
        System.out.println(result.getView_count());
        assertThat(result.getView_count()).isEqualTo(1L);
    }
}


