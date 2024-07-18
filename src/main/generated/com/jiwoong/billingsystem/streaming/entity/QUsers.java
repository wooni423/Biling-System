package com.jiwoong.billingsystem.streaming.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUsers is a Querydsl query type for Users
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsers extends EntityPathBase<Users> {

    private static final long serialVersionUID = 2084448407L;

    public static final QUsers users = new QUsers("users");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<AdWatchHistory, QAdWatchHistory> adWatchHistoryList = this.<AdWatchHistory, QAdWatchHistory>createList("adWatchHistoryList", AdWatchHistory.class, QAdWatchHistory.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.sql.Timestamp> create_at = _super.create_at;

    public final StringPath email = createString("email");

    public final StringPath name = createString("name");

    public final EnumPath<com.jiwoong.billingsystem.streaming.dto.ROLE> role = createEnum("role", com.jiwoong.billingsystem.streaming.dto.ROLE.class);

    //inherited
    public final DateTimePath<java.sql.Timestamp> update_at = _super.update_at;

    public final ListPath<Videos, QVideos> uploadVideosList = this.<Videos, QVideos>createList("uploadVideosList", Videos.class, QVideos.class, PathInits.DIRECT2);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath username = createString("username");

    public final ListPath<VideoWatchHistory, QVideoWatchHistory> videoWatchHistoryList = this.<VideoWatchHistory, QVideoWatchHistory>createList("videoWatchHistoryList", VideoWatchHistory.class, QVideoWatchHistory.class, PathInits.DIRECT2);

    public QUsers(String variable) {
        super(Users.class, forVariable(variable));
    }

    public QUsers(Path<? extends Users> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsers(PathMetadata metadata) {
        super(Users.class, metadata);
    }

}

