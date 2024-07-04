package com.jiwoong.bilingsystem.streaming.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAdvertisements is a Querydsl query type for Advertisements
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdvertisements extends EntityPathBase<Advertisements> {

    private static final long serialVersionUID = 33542581L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAdvertisements advertisements = new QAdvertisements("advertisements");

    public final com.jiwoong.bilingsystem.global.entity.QBaseEntity _super = new com.jiwoong.bilingsystem.global.entity.QBaseEntity(this);

    public final NumberPath<Long> adPosition = createNumber("adPosition", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> create_at = _super.create_at;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> update_at = _super.update_at;

    public final StringPath url = createString("url");

    public final QVideos video;

    public final ListPath<UserVideoAdsHistory, QUserVideoAdsHistory> videoAdsHistoryList = this.<UserVideoAdsHistory, QUserVideoAdsHistory>createList("videoAdsHistoryList", UserVideoAdsHistory.class, QUserVideoAdsHistory.class, PathInits.DIRECT2);

    public final NumberPath<Long> viewCount = createNumber("viewCount", Long.class);

    public QAdvertisements(String variable) {
        this(Advertisements.class, forVariable(variable), INITS);
    }

    public QAdvertisements(Path<? extends Advertisements> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAdvertisements(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAdvertisements(PathMetadata metadata, PathInits inits) {
        this(Advertisements.class, metadata, inits);
    }

    public QAdvertisements(Class<? extends Advertisements> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.video = inits.isInitialized("video") ? new QVideos(forProperty("video"), inits.get("video")) : null;
    }

}

