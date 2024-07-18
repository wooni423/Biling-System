package com.jiwoong.billingsystem.streaming.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVideoWatchHistory is a Querydsl query type for VideoWatchHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVideoWatchHistory extends EntityPathBase<VideoWatchHistory> {

    private static final long serialVersionUID = 168772847L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVideoWatchHistory videoWatchHistory = new QVideoWatchHistory("videoWatchHistory");

    public final DateTimePath<java.time.LocalDateTime> createDt = createDateTime("createDt", java.time.LocalDateTime.class);

    public final DatePath<java.time.LocalDate> histDt = createDate("histDt", java.time.LocalDate.class);

    public final StringPath ipAddress = createString("ipAddress");

    public final NumberPath<Long> playbackTimeline = createNumber("playbackTimeline", Long.class);

    public final QUsers user;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final QVideos video;

    public final NumberPath<Long> videoId = createNumber("videoId", Long.class);

    public QVideoWatchHistory(String variable) {
        this(VideoWatchHistory.class, forVariable(variable), INITS);
    }

    public QVideoWatchHistory(Path<? extends VideoWatchHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVideoWatchHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVideoWatchHistory(PathMetadata metadata, PathInits inits) {
        this(VideoWatchHistory.class, metadata, inits);
    }

    public QVideoWatchHistory(Class<? extends VideoWatchHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUsers(forProperty("user")) : null;
        this.video = inits.isInitialized("video") ? new QVideos(forProperty("video"), inits.get("video")) : null;
    }

}

