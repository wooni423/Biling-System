package com.jiwoong.billingsystem.streaming.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVideos is a Querydsl query type for Videos
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVideos extends EntityPathBase<Videos> {

    private static final long serialVersionUID = 212742825L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVideos videos = new QVideos("videos");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.sql.Timestamp> create_at = _super.create_at;

    public final StringPath desc = createString("desc");

    public final BooleanPath isActive = createBoolean("isActive");

    public final DateTimePath<java.util.Date> nxtCalcDate = createDateTime("nxtCalcDate", java.util.Date.class);

    public final NumberPath<Long> runningTime = createNumber("runningTime", Long.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> totalViewCnt = createNumber("totalViewCnt", Long.class);

    //inherited
    public final DateTimePath<java.sql.Timestamp> update_at = _super.update_at;

    public final QUsers uploader;

    public final StringPath url = createString("url");

    public final ListPath<VideoAdList, QVideoAdList> videoAdList = this.<VideoAdList, QVideoAdList>createList("videoAdList", VideoAdList.class, QVideoAdList.class, PathInits.DIRECT2);

    public final NumberPath<Long> videoId = createNumber("videoId", Long.class);

    public final ListPath<VideoWatchHistory, QVideoWatchHistory> videoWatchHistoryList = this.<VideoWatchHistory, QVideoWatchHistory>createList("videoWatchHistoryList", VideoWatchHistory.class, QVideoWatchHistory.class, PathInits.DIRECT2);

    public QVideos(String variable) {
        this(Videos.class, forVariable(variable), INITS);
    }

    public QVideos(Path<? extends Videos> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVideos(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVideos(PathMetadata metadata, PathInits inits) {
        this(Videos.class, metadata, inits);
    }

    public QVideos(Class<? extends Videos> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.uploader = inits.isInitialized("uploader") ? new QUsers(forProperty("uploader")) : null;
    }

}

