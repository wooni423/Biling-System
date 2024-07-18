package com.jiwoong.billingsystem.streaming.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAdWatchHistory is a Querydsl query type for AdWatchHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdWatchHistory extends EntityPathBase<AdWatchHistory> {

    private static final long serialVersionUID = 625711833L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAdWatchHistory adWatchHistory = new QAdWatchHistory("adWatchHistory");

    public final NumberPath<Long> adListId = createNumber("adListId", Long.class);

    public final DateTimePath<java.sql.Timestamp> createDt = createDateTime("createDt", java.sql.Timestamp.class);

    public final DatePath<java.time.LocalDate> histDt = createDate("histDt", java.time.LocalDate.class);

    public final QUsers user;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final QVideoAdList videoAdList;

    public QAdWatchHistory(String variable) {
        this(AdWatchHistory.class, forVariable(variable), INITS);
    }

    public QAdWatchHistory(Path<? extends AdWatchHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAdWatchHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAdWatchHistory(PathMetadata metadata, PathInits inits) {
        this(AdWatchHistory.class, metadata, inits);
    }

    public QAdWatchHistory(Class<? extends AdWatchHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUsers(forProperty("user")) : null;
        this.videoAdList = inits.isInitialized("videoAdList") ? new QVideoAdList(forProperty("videoAdList"), inits.get("videoAdList")) : null;
    }

}

