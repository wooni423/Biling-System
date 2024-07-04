package com.jiwoong.bilingsystem.streaming.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserVideoHistory is a Querydsl query type for UserVideoHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserVideoHistory extends EntityPathBase<UserVideoHistory> {

    private static final long serialVersionUID = -743459861L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserVideoHistory userVideoHistory = new QUserVideoHistory("userVideoHistory");

    public final com.jiwoong.bilingsystem.global.entity.QBaseEntity _super = new com.jiwoong.bilingsystem.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> create_at = _super.create_at;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> lastWatchedDateTime = createDateTime("lastWatchedDateTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> lastWatchedTimestamp = createNumber("lastWatchedTimestamp", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> update_at = _super.update_at;

    public final com.jiwoong.bilingsystem.auth.entity.QUsers user;

    public final QVideos video;

    public QUserVideoHistory(String variable) {
        this(UserVideoHistory.class, forVariable(variable), INITS);
    }

    public QUserVideoHistory(Path<? extends UserVideoHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserVideoHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserVideoHistory(PathMetadata metadata, PathInits inits) {
        this(UserVideoHistory.class, metadata, inits);
    }

    public QUserVideoHistory(Class<? extends UserVideoHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.jiwoong.bilingsystem.auth.entity.QUsers(forProperty("user")) : null;
        this.video = inits.isInitialized("video") ? new QVideos(forProperty("video"), inits.get("video")) : null;
    }

}

