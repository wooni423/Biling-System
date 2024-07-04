package com.jiwoong.bilingsystem.streaming.repository.QueryDsl;

import com.jiwoong.bilingsystem.auth.entity.QUsers;
import com.jiwoong.bilingsystem.global.exception.RestApiException;
import com.jiwoong.bilingsystem.global.exception.errorCode.StreamingErrorCode;
import com.jiwoong.bilingsystem.streaming.entity.QAdsWatchHistory;
import com.jiwoong.bilingsystem.streaming.entity.QUserWatchHistory;
import com.jiwoong.bilingsystem.streaming.entity.QVideos;
import com.jiwoong.bilingsystem.streaming.entity.Videos;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomVideoRepositoryImpl implements CustomVideoRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Videos customFindVideoByVideoIdAndUsername(Long videoId, String username) {
        QVideos videos = QVideos.videos;
        QUsers users = QUsers.users;
        QAdsWatchHistory adsWatchHistory = QAdsWatchHistory.adsWatchHistory;
        QUserWatchHistory userWatchHistory = QUserWatchHistory.userWatchHistory;

        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(videos)
                .leftJoin(videos.user, users).fetchJoin()
                .leftJoin(videos.adsWatchHistoryList, adsWatchHistory).fetchJoin()
                .leftJoin(videos.userWatchHistoryList,userWatchHistory).fetchJoin()
                .where(videos.id.eq(videoId).and(users.username.eq(username)))
                .fetchOne())
                .orElseThrow(() -> new RestApiException(StreamingErrorCode.NOT_FOUND_VIDEO));
    }
}
