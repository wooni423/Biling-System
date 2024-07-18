package com.jiwoong.billingsystem.adjustment.entity.key;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class VideoStatId implements Serializable { // VideoStat 테이블 복합키

    private LocalDate statDt;
    private Long videoId;
}
