package com.jiwoong.billingsystem.adjustment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdStat is a Querydsl query type for AdStat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdStat extends EntityPathBase<AdStat> {

    private static final long serialVersionUID = 1983366607L;

    public static final QAdStat adStat = new QAdStat("adStat");

    public final NumberPath<Long> adId = createNumber("adId", Long.class);

    public final DateTimePath<java.sql.Timestamp> createDt = createDateTime("createDt", java.sql.Timestamp.class);

    public final NumberPath<Long> dailyViewCnt = createNumber("dailyViewCnt", Long.class);

    public final DatePath<java.time.LocalDate> statDt = createDate("statDt", java.time.LocalDate.class);

    public QAdStat(String variable) {
        super(AdStat.class, forVariable(variable));
    }

    public QAdStat(Path<? extends AdStat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdStat(PathMetadata metadata) {
        super(AdStat.class, metadata);
    }

}

