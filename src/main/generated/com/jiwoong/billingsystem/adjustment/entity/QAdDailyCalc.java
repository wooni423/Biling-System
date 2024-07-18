package com.jiwoong.billingsystem.adjustment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdDailyCalc is a Querydsl query type for AdDailyCalc
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdDailyCalc extends EntityPathBase<AdDailyCalc> {

    private static final long serialVersionUID = -1699623565L;

    public static final QAdDailyCalc adDailyCalc = new QAdDailyCalc("adDailyCalc");

    public final NumberPath<Long> adId = createNumber("adId", Long.class);

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final DatePath<java.time.LocalDate> calcDt = createDate("calcDt", java.time.LocalDate.class);

    public final DateTimePath<java.sql.Timestamp> createDt = createDateTime("createDt", java.sql.Timestamp.class);

    public QAdDailyCalc(String variable) {
        super(AdDailyCalc.class, forVariable(variable));
    }

    public QAdDailyCalc(Path<? extends AdDailyCalc> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdDailyCalc(PathMetadata metadata) {
        super(AdDailyCalc.class, metadata);
    }

}

