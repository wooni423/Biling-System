package com.jiwoong.billingsystem.adjustment.repository;

import com.jiwoong.billingsystem.adjustment.entity.VideoDailyCalc;
import com.jiwoong.billingsystem.adjustment.entity.key.VideoDailyCalcId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VideoDailyCalcRepository extends JpaRepository<VideoDailyCalc, VideoDailyCalcId> {
    List<VideoDailyCalc> findByCalcDt(LocalDate calcDt);
}
