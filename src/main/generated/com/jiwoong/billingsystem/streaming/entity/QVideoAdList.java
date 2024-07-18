package com.jiwoong.billingsystem.streaming.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVideoAdList is a Querydsl query type for VideoAdList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVideoAdList extends EntityPathBase<VideoAdList> {

    private static final long serialVersionUID = -753462517L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVideoAdList videoAdList = new QVideoAdList("videoAdList");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QAds ad;

    public final NumberPath<Long> adListId = createNumber("adListId", Long.class);

    public final NumberPath<Long> adPosition = createNumber("adPosition", Long.class);

    public final ListPath<AdWatchHistory, QAdWatchHistory> adWatchHistoryList = this.<AdWatchHistory, QAdWatchHistory>createList("adWatchHistoryList", AdWatchHistory.class, QAdWatchHistory.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.sql.Timestamp> create_at = _super.create_at;

    public final NumberPath<Long> totalViewCnt = createNumber("totalViewCnt", Long.class);

    //inherited
    public final DateTimePath<java.sql.Timestamp> update_at = _super.update_at;

    public final QVideos video;

    public QVideoAdList(String variable) {
        this(VideoAdList.class, forVariable(variable), INITS);
    }

    public QVideoAdList(Path<? extends VideoAdList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVideoAdList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVideoAdList(PathMetadata metadata, PathInits inits) {
        this(VideoAdList.class, metadata, inits);
    }

    public QVideoAdList(Class<? extends VideoAdList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ad = inits.isInitialized("ad") ? new QAds(forProperty("ad")) : null;
        this.video = inits.isInitialized("video") ? new QVideos(forProperty("video"), inits.get("video")) : null;
    }

}

