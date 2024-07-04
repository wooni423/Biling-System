package com.jiwoong.bilingsystem.auth.entity;

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

    private static final long serialVersionUID = 183219787L;

    public static final QUsers users = new QUsers("users");

    public final com.jiwoong.bilingsystem.global.entity.QBaseEntity _super = new com.jiwoong.bilingsystem.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> create_at = _super.create_at;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ipAddress = createString("ipAddress");

    public final StringPath name = createString("name");

    public final EnumPath<com.jiwoong.bilingsystem.auth.dto.ROLE> role = createEnum("role", com.jiwoong.bilingsystem.auth.dto.ROLE.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> update_at = _super.update_at;

    public final ListPath<com.jiwoong.bilingsystem.streaming.entity.Videos, com.jiwoong.bilingsystem.streaming.entity.QVideos> uploadVideoList = this.<com.jiwoong.bilingsystem.streaming.entity.Videos, com.jiwoong.bilingsystem.streaming.entity.QVideos>createList("uploadVideoList", com.jiwoong.bilingsystem.streaming.entity.Videos.class, com.jiwoong.bilingsystem.streaming.entity.QVideos.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public final ListPath<com.jiwoong.bilingsystem.streaming.entity.UserVideoAdsHistory, com.jiwoong.bilingsystem.streaming.entity.QUserVideoAdsHistory> userVideoAdsHistoryList = this.<com.jiwoong.bilingsystem.streaming.entity.UserVideoAdsHistory, com.jiwoong.bilingsystem.streaming.entity.QUserVideoAdsHistory>createList("userVideoAdsHistoryList", com.jiwoong.bilingsystem.streaming.entity.UserVideoAdsHistory.class, com.jiwoong.bilingsystem.streaming.entity.QUserVideoAdsHistory.class, PathInits.DIRECT2);

    public final ListPath<com.jiwoong.bilingsystem.streaming.entity.UserVideoHistory, com.jiwoong.bilingsystem.streaming.entity.QUserVideoHistory> userWatchHistoryList = this.<com.jiwoong.bilingsystem.streaming.entity.UserVideoHistory, com.jiwoong.bilingsystem.streaming.entity.QUserVideoHistory>createList("userWatchHistoryList", com.jiwoong.bilingsystem.streaming.entity.UserVideoHistory.class, com.jiwoong.bilingsystem.streaming.entity.QUserVideoHistory.class, PathInits.DIRECT2);

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

