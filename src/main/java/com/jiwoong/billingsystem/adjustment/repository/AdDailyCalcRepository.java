package com.jiwoong.billingsystem.adjustment.repository;

import com.jiwoong.billingsystem.adjustment.entity.AdDailyCalc;
import com.jiwoong.billingsystem.adjustment.entity.key.AdDailyCalcId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AdDailyCalcRepository extends JpaRepository<AdDailyCalc, AdDailyCalcId> {
    List<AdDailyCalc> findByCalcDt(LocalDate calcDt);
}
