package com.hondee.service_plan.dao;

import com.hondee.service_plan.domain.WeekPlanDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeekPlanDetailDao extends JpaRepository<WeekPlanDetail, String> {
    void deleteByProjectCodeAndYearAndWeek(String projectCode, int year, int week);

    List<WeekPlanDetail> findByProjectCodeAndYearAndWeek(String projectCode, int year, int week);

    WeekPlanDetail findByYearAndWeekAndItemCode(int year, int week, String itemCode);
}
