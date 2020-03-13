package com.hondee.service_plan.dao;

import com.hondee.service_plan.domain.MonthPlanDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonthPlanDetailDao extends JpaRepository<MonthPlanDetail, String> {
    void deleteByProjectCodeAndYearAndMonth(String projectCode, int year, int month);

    List<MonthPlanDetail> findByProjectCodeAndYearAndMonth(String projectCode, int year, int month);

    MonthPlanDetail findByYearAndMonthAndItemCode(int year, int month, String itemCode);
}
