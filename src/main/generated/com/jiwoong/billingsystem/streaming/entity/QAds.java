package com.jiwoong.billingsystem.streaming.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAds is a Querydsl query type for Ads
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAds extends EntityPathBase<Ads> {

    private static final long serialVersionUID = 1821141759L;

    public static final QAds ads = new QAds("ads");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> adId = createNumber("adId", Long.class);

    //inherited
    public final DateTimePath<java.sql.Timestamp> create_at = _super.create_at;

    public final StringPath desc = createString("desc");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.sql.Timestamp> update_at = _super.update_at;

    public final StringPath url = createString("url");

    public final ListPath<VideoAdList, QVideoAdList> videoAdList = this.<VideoAdList, QVideoAdList>createList("videoAdList", VideoAdList.class, QVideoAdList.class, PathInits.DIRECT2);

    public QAds(String variable) {
        super(Ads.class, forVariable(variable));
    }

    public QAds(Path<? extends Ads> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAds(PathMetadata metadata) {
        super(Ads.class, metadata);
    }

}

