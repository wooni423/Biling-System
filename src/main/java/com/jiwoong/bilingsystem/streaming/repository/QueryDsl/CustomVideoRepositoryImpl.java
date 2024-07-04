package com.jiwoong.bilingsystem.streaming.repository.QueryDsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;



@Repository
@RequiredArgsConstructor
public class CustomVideoRepositoryImpl implements CustomVideoRepository {

    private final JPAQueryFactory jpaQueryFactory;
/*

    @Override
    public Videos findVideoByVideoIdAndUsername(Long videoId, String username) {
        QVideos videos = QVideos.videos;
        QUsers users = QUsers.users;
        QAdsWatchHistory adsWatchHistory = QAdsWatchHistory.adsWatchHistory;

        Videos videoWithAdsWatchHistory = jpaQueryFactory
                .select(videos)
                .distinct()
                .from(videos)
                .innerJoin(videos.user, users).fetchJoin()
                .leftJoin(videos.adsWatchHistoryList, adsWatchHistory).fetchJoin()
                .where(videos.id.eq(videoId).and(users.username.eq(username)))
                .fetchOne();

        return Optional.ofNullable(videoWithAdsWatchHistory)
                .orElseThrow(() -> new RestApiException(StreamingErrorCode.NOT_FOUND_VIDEO));
    }
*/

  /*  @Override
    public UserWatchHistory findVideoUserWatchHistory(Long videoId, String username) {
        QUserWatchHistory userWatchHistory = QUserWatchHistory.userWatchHistory;
        return jpaQueryFactory.select(userWatchHistory)
                .distinct()
                .from(userWatchHistory)
                .where(userWatchHistory.video.id.eq(videoId).and(userWatchHistory.user.username.eq(username)))
                .fetchOne();
    }*/
}
