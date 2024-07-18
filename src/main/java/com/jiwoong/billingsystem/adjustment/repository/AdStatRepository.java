package com.jiwoong.billingsystem.adjustment.repository;

import com.jiwoong.billingsystem.adjustment.entity.AdStat;
import com.jiwoong.billingsystem.adjustment.entity.key.AdStatId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AdStatRepository extends JpaRepository<AdStat, AdStatId> {
    List<AdStat> findByStatDt(LocalDate statDt);
    List<AdStat> findByStatDtBetween(LocalDate startDate,LocalDate endDate);
}
