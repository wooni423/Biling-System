package com.jiwoong.bilingsystem.streaming.entity;

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

    private static final long serialVersionUID = 1817867519L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVideos videos = new QVideos("videos");

    public final com.jiwoong.bilingsystem.global.entity.QBaseEntity _super = new com.jiwoong.bilingsystem.global.entity.QBaseEntity(this);

    public final ListPath<Advertisements, QAdvertisements> advertisementList = this.<Advertisements, QAdvertisements>createList("advertisementList", Advertisements.class, QAdvertisements.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> create_at = _super.create_at;

    public final NumberPath<Long> duration = createNumber("duration", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isActive = createBoolean("isActive");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> update_at = _super.update_at;

    public final com.jiwoong.bilingsystem.auth.entity.QUsers uploader;

    public final StringPath url = createString("url");

    public final ListPath<UserVideoHistory, QUserVideoHistory> userVideoHistoryList = this.<UserVideoHistory, QUserVideoHistory>createList("userVideoHistoryList", UserVideoHistory.class, QUserVideoHistory.class, PathInits.DIRECT2);

    public final NumberPath<Long> viewCount = createNumber("viewCount", Long.class);

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
        this.uploader = inits.isInitialized("uploader") ? new com.jiwoong.bilingsystem.auth.entity.QUsers(forProperty("uploader")) : null;
    }

}

