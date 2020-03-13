package com.hondee.service_plan.dao;

import com.hondee.service_plan.domain.WeekPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeekPlanDao extends JpaRepository<WeekPlan, String> {
    Page<WeekPlan> findByProjectCode(String projectCode, Pageable pageable);

    WeekPlan findFirstByProjectCode(String projectCode, Sort sort);

    @Query(value = "SELECT * FROM plan_weekplan WHERE ProjectCode = ?1 AND (`Year` > ?2 OR (`Year` = ?2 AND `Week` > ?3))", nativeQuery = true)
    List<WeekPlan> findByProjectCodeAndYearAndWeekAfter(String projectCode, int year, int week);

    WeekPlan findByProjectCodeAndYearAndWeekAndReleased(String projectCode, int year, int week, boolean released);

    WeekPlan findByProjectCodeAndYearAndWeek(String projectCode, int year, int week);
}
