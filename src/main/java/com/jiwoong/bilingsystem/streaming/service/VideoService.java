package com.jiwoong.bilingsystem.streaming.service;

import com.jiwoong.bilingsystem.auth.entity.Users;
import com.jiwoong.bilingsystem.auth.service.CustomOAuth2User;
import com.jiwoong.bilingsystem.global.exception.RestApiException;
import com.jiwoong.bilingsystem.global.exception.errorCode.StreamingErrorCode;
import com.jiwoong.bilingsystem.global.exception.errorCode.UsersErrorCode;
import com.jiwoong.bilingsystem.streaming.dto.request.VideoRegisterRequest;
import com.jiwoong.bilingsystem.streaming.dto.response.VideoPlayBackResponse;
import com.jiwoong.bilingsystem.streaming.entity.Advertisements;
import com.jiwoong.bilingsystem.streaming.entity.UserVideoAdsHistory;
import com.jiwoong.bilingsystem.streaming.entity.UserVideoHistory;
import com.jiwoong.bilingsystem.streaming.entity.Videos;
import com.jiwoong.bilingsystem.streaming.repository.UserVideoAdsHistoryRepository;
import com.jiwoong.bilingsystem.streaming.repository.UserVideoHistoryRepository;
import com.jiwoong.bilingsystem.streaming.repository.UsersRepository;
import com.jiwoong.bilingsystem.streaming.repository.VideosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Log4j2
public class VideoService {

    private final VideosRepository videosRepository;
    private final UsersRepository usersRepository;
    private final UserVideoHistoryRepository userVideoHistoryRepository;
    private final UserVideoAdsHistoryRepository userVideoAdsHistoryRepository;


    @Transactional
    public VideoPlayBackResponse videoPlay(Long videoId, CustomOAuth2User loginUser, String ipAddress, Long currentTimestamp) {

        Users user = usersRepository.findByUsername(loginUser.getUsername())
                .orElseThrow(() -> new RestApiException(UsersErrorCode.NOT_FOUND_USER));
        Videos video = videosRepository.findById(videoId)
                .orElseThrow(() -> new RestApiException(StreamingErrorCode.NOT_FOUND_VIDEO));

        // 어뷰징 체크 -> 30초 이내 재 클릭 시에는 조회수 증가 X
        LocalDateTime thirtySecondsAgo = LocalDateTime.now().minusSeconds(30);
        Optional<UserVideoHistory> recentHistory = userVideoHistoryRepository.findRecentHistory(loginUser.getUsername(), videoId, ipAddress, thirtySecondsAgo);
        if (recentHistory.isPresent()) {
            return new VideoPlayBackResponse(video.getUrl(), recentHistory.get().getLastWatchedTimestamp());
        }

        // 동영상 조회수 증가(업로더가 아닐 경우)
        if (!video.getUploader().getUsername().equals(loginUser.getUsername())) {
            video.incrementViewCount();
        }

        // 시청 기록 저장
        UserVideoHistory history = userVideoHistoryRepository.findByUsernameAndVideoId(loginUser.getUsername(), videoId)
                .orElse(UserVideoHistory.createVideoHistory(user, video, currentTimestamp, LocalDateTime.now()));
        history.updateLastWatchedTime(currentTimestamp, LocalDateTime.now());
        userVideoHistoryRepository.save(history);

        // 광고 시청 기록 업데이트
        updateAdViews(video, user, currentTimestamp);

        userVideoHistoryRepository.save(history);
        return new VideoPlayBackResponse(video.getUrl(), history.getLastWatchedTimestamp());
    }

    // 광고 시청 기록 업데이트
    @Transactional
    public void updateAdViews(Videos video, Users user, Long lastWatchedTimestamp) {
        if (!video.getUploader().getId().equals(user.getId())) { // 업로더 가 아닐 때만 광고 시청 횟수 증가(어뷰징 방지)
            for (Advertisements advertisement : video.getAdvertisementList()) { // 비디오에 연결된 광고 리스트 순회
                if (lastWatchedTimestamp >= advertisement.getAdPosition()) { // 사용자가 마지막으로 시청한 위치가 광고가 재생되는 시점보다 크다면
                    advertisement.incrementViewCount();
                    UserVideoAdsHistory userVideoAdsHistory = UserVideoAdsHistory.createAdHistory(user, advertisement, LocalDateTime.now());
                    userVideoAdsHistoryRepository.save(userVideoAdsHistory);
                }
            }
        }
    }


    @Transactional
    public void videoRegister(VideoRegisterRequest request, CustomOAuth2User loginUser) {
        Users uploader = usersRepository.findByUsername(loginUser.getUsername())
                .orElseThrow(() -> new RestApiException(UsersErrorCode.NOT_FOUND_USER));
        Videos video = Videos.createVideo(request.getTitle(), request.getUrl(), request.getDuration(), uploader);
        registerAdsForVideo(video);
    }


    @Transactional
    public void registerAdsForVideo(Videos video) {
        List<Advertisements> advertisementsList = new ArrayList<>();
        Long duration = video.getDuration();
        for (long i = 300; i <= duration; i += 300) {
            Advertisements advertisement = Advertisements.createAd("http://example.com/ad/" + i, i, video);
            advertisementsList.add(advertisement);
        }

        video.getAdvertisementList().addAll(advertisementsList);
        videosRepository.save(video);
    }

}

