package com.jiwoong.billingsystem.streaming.service;


import com.jiwoong.billingsystem.streaming.entity.Users;
import com.jiwoong.billingsystem.global.exception.RestApiException;
import com.jiwoong.billingsystem.global.exception.errorCode.StreamingErrorCode;
import com.jiwoong.billingsystem.global.exception.errorCode.UsersErrorCode;
import com.jiwoong.billingsystem.streaming.dto.request.VideoRegisterRequest;
import com.jiwoong.billingsystem.streaming.dto.response.VideoPlayBackResponse;
import com.jiwoong.billingsystem.streaming.entity.*;
import com.jiwoong.billingsystem.streaming.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Log4j2
public class VideoService {

    private final UsersRepository userRepository;

    private final VideosRepository videoRepository;

    private final VideoWatchHistoryRepository videoWatchHistoryRepository;

    private final AdsRepository adsRepository;

    private final VideoAdsRepository videoAdsRepository;

    private final AdWatchHistoryRepository adWatchHistoryRepository;

    @Transactional
    public VideoPlayBackResponse videoPlay(Long videoId, String username, String ipAddress) {
        // 사용자 조회
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RestApiException(UsersErrorCode.NOT_FOUND_USER));

        // 비디오 조회
        Videos video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RestApiException(StreamingErrorCode.NOT_FOUND_VIDEO));

        // 시청 기록 조회 및 어뷰징 체크
        LocalDateTime thirtySecondsAgo = LocalDateTime.now().minusSeconds(30);
        Optional<VideoWatchHistory> recentHistoryOpt = videoWatchHistoryRepository.findRecentHistory(username, videoId, thirtySecondsAgo, ipAddress);
        Optional<VideoWatchHistory> historyOpt = videoWatchHistoryRepository.findByUsernameAndVideoId(username, videoId);

        // 동영상 게시자가 동영상을 시청하는 경우 조회수 및 시청 횟수 증가 X
        if (video.getUploader().getUserId().equals(user.getUserId())) {
            if (historyOpt.isPresent()) {
                VideoWatchHistory history = historyOpt.get();
                return new VideoPlayBackResponse(video.getUrl(), history.getPlaybackTimeline());
            } else {
                return new VideoPlayBackResponse(video.getUrl(), 0L);
            }
        }

        // 어뷰징 체크
        if (recentHistoryOpt.isPresent()) {
            VideoWatchHistory recentHistory = recentHistoryOpt.get();
            log.info("어뷰징 감지");
            return new VideoPlayBackResponse(video.getUrl(), recentHistory.getPlaybackTimeline());
        }

        // 조회수 증가
        video.incrementViewCount();
        videoRepository.save(video);

        // 시청 기록이 있는 경우 해당 시점부터 재생, 없는 경우 처음부터 재생
        if (historyOpt.isPresent()) {
            VideoWatchHistory history = historyOpt.get();
            return new VideoPlayBackResponse(video.getUrl(), history.getPlaybackTimeline());
        } else {
            return new VideoPlayBackResponse(video.getUrl(), 0L);
        }
    }

    // 동영상 멈춤 시 현재 재생 시점 저장
    @Transactional
    public void videoStop(Long videoId, String username, Long currentTimestamp, String ipAddress) {
        // 사용자 조회
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RestApiException(UsersErrorCode.NOT_FOUND_USER));

        // 비디오 조회 (광고 리스트 초기화 포함)
        Videos video = videoRepository.findByIdWithAds(videoId)
                .orElseThrow(() -> new RestApiException(StreamingErrorCode.NOT_FOUND_VIDEO));

        // 시청 기록 조회 및 업데이트
        VideoWatchHistory history = videoWatchHistoryRepository.findByUsernameAndVideoId(username, videoId)
                .orElse(VideoWatchHistory.builder()
                        .user(user)
                        .video(video)
                        .playbackTimeline(currentTimestamp)
                        .ipAddress(ipAddress)
                        .build());
        history.updateLastWatchedTime(currentTimestamp, LocalDateTime.now());
        videoWatchHistoryRepository.save(history);

        // 광고 시청 기록 업데이트
        updateAdViews(video, user, currentTimestamp);
    }

    // 광고 시청 기록 업데이트
    @Transactional
    public void updateAdViews(Videos video, Users user, Long currentTimestamp) {
        long adInterval = 300; // 5분 (300초)마다 광고
        List<VideoAdList> ads = video.getVideoAdList();
        for (VideoAdList adList : ads) {
            long adTimestamp = adInterval * (ads.indexOf(adList) + 1);
            if (currentTimestamp >= adList.getAdPosition()) {
                adList.incrementViewCount();
                videoAdsRepository.save(adList);

                // 유저 광고 시청 기록 저장
                AdWatchHistory adViewHistory = AdWatchHistory.builder()
                        .user(user)
                        .videoAdList(adList)
                        .build();
                adWatchHistoryRepository.save(adViewHistory);
            }
        }
    }

    // 비디오 등록 및 광고 추가
    @Transactional
    public void videoRegister(VideoRegisterRequest request, String uploaderUsername) {
        // 업로더 조회
        Users uploader = userRepository.findByUsername(uploaderUsername)
                .orElseThrow(() -> new RestApiException(UsersErrorCode.NOT_FOUND_USER));
        // 비디오 생성
        Videos video = Videos.builder()
                .title(request.getTitle())
                .runningTime(request.getRunningTime())
                .url(request.getUrl())
                .desc(request.getDesc())
                .uploader(uploader)
                .build();

        // 비디오 저장
        videoRepository.save(video);

        // 광고 추가 (5분마다 광고 추가)
        long adCount = request.getRunningTime() / 300; // 5분 = 300초마다 광고
        List<Ads> adsList = adsRepository.findAll();
        for (int i = 1; i <= adCount; i++) {
            Ads ad = adsList.get(ThreadLocalRandom.current().nextInt(adsList.size()));
            video.addAd(ad, i * 300L);
        }
        videoRepository.save(video);
    }
}

