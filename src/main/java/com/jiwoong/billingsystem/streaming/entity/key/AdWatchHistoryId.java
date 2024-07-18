package com.jiwoong.billingsystem.streaming.entity.key;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class AdWatchHistoryId implements Serializable {
    private LocalDate histDt; // 히스 토리 날짜
    private Long adListId; // 비디오 ID
    private Long userId; // 사용자 번호
}
