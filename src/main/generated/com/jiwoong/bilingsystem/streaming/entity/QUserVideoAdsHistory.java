package com.jiwoong.bilingsystem.streaming.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserVideoAdsHistory is a Querydsl query type for UserVideoAdsHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserVideoAdsHistory extends EntityPathBase<UserVideoAdsHistory> {

    private static final long serialVersionUID = 1990212365L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserVideoAdsHistory userVideoAdsHistory = new QUserVideoAdsHistory("userVideoAdsHistory");

    public final com.jiwoong.bilingsystem.global.entity.QBaseEntity _super = new com.jiwoong.bilingsystem.global.entity.QBaseEntity(this);

    public final QAdvertisements advertisement;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> create_at = _super.create_at;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> update_at = _super.update_at;

    public final com.jiwoong.bilingsystem.auth.entity.QUsers user;

    public final DateTimePath<java.time.LocalDateTime> watchedDateTime = createDateTime("watchedDateTime", java.time.LocalDateTime.class);

    public QUserVideoAdsHistory(String variable) {
        this(UserVideoAdsHistory.class, forVariable(variable), INITS);
    }

    public QUserVideoAdsHistory(Path<? extends UserVideoAdsHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserVideoAdsHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserVideoAdsHistory(PathMetadata metadata, PathInits inits) {
        this(UserVideoAdsHistory.class, metadata, inits);
    }

    public QUserVideoAdsHistory(Class<? extends UserVideoAdsHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.advertisement = inits.isInitialized("advertisement") ? new QAdvertisements(forProperty("advertisement"), inits.get("advertisement")) : null;
        this.user = inits.isInitialized("user") ? new com.jiwoong.bilingsystem.auth.entity.QUsers(forProperty("user")) : null;
    }

}

