package com.jiwoong.billingsystem.adjustment.entity.key;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class AdDailyCalcId implements Serializable {
    private LocalDate calcDt;
    private Long adId;
}
