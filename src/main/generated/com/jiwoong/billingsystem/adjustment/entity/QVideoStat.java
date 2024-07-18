package com.jiwoong.billingsystem.adjustment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVideoStat is a Querydsl query type for VideoStat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVideoStat extends EntityPathBase<VideoStat> {

    private static final long serialVersionUID = -1077905993L;

    public static final QVideoStat videoStat = new QVideoStat("videoStat");

    public final DateTimePath<java.sql.Timestamp> createDt = createDateTime("createDt", java.sql.Timestamp.class);

    public final NumberPath<Long> dailyPlayTime = createNumber("dailyPlayTime", Long.class);

    public final NumberPath<Long> dailyViewCnt = createNumber("dailyViewCnt", Long.class);

    public final DatePath<java.time.LocalDate> statDt = createDate("statDt", java.time.LocalDate.class);

    public final NumberPath<Long> videoId = createNumber("videoId", Long.class);

    public QVideoStat(String variable) {
        super(VideoStat.class, forVariable(variable));
    }

    public QVideoStat(Path<? extends VideoStat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVideoStat(PathMetadata metadata) {
        super(VideoStat.class, metadata);
    }

}

