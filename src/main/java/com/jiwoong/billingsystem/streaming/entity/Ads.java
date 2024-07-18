package com.jiwoong.billingsystem.streaming.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "ads")
public class Ads extends BaseEntity { // 광고

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adId; // 광고 ID

    @Column(nullable = false)
    private String title; // 광고 제목

    @Column(name = "`desc`", nullable = false)
    private String desc; // 설명

    @Column(nullable = false)
    private String url; // 광고 URL

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoAdList> videoAdList = new ArrayList<>(); // 동영상 광고 목록

    @Builder
    public Ads(String title, String desc, String url) {
        this.title = title;
        this.desc = desc;
        this.url = url;
    }
}
