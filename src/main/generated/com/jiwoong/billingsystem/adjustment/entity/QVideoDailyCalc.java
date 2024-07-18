package com.jiwoong.billingsystem.adjustment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVideoDailyCalc is a Querydsl query type for VideoDailyCalc
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVideoDailyCalc extends EntityPathBase<VideoDailyCalc> {

    private static final long serialVersionUID = 841141899L;

    public static final QVideoDailyCalc videoDailyCalc = new QVideoDailyCalc("videoDailyCalc");

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final DatePath<java.time.LocalDate> calcDt = createDate("calcDt", java.time.LocalDate.class);

    public final DateTimePath<java.sql.Timestamp> createDt = createDateTime("createDt", java.sql.Timestamp.class);

    public final NumberPath<Long> videoId = createNumber("videoId", Long.class);

    public QVideoDailyCalc(String variable) {
        super(VideoDailyCalc.class, forVariable(variable));
    }

    public QVideoDailyCalc(Path<? extends VideoDailyCalc> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVideoDailyCalc(PathMetadata metadata) {
        super(VideoDailyCalc.class, metadata);
    }

}

