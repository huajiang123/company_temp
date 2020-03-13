package com.hondee.service_plan.dao;

import com.hondee.service_plan.domain.MonthPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MonthPlanDao extends JpaRepository<MonthPlan, String> {
    Page<MonthPlan> findByProjectCode(String projectCode, Pageable pageable);

    MonthPlan findFirstByProjectCode(String projectCode, Sort sort);

    MonthPlan findByProjectCodeAndYearAndMonthAndReleased(String projectCode, int year, int month, boolean isReleased);

    MonthPlan findByProjectCodeAndYearAndMonth(String projectCode, int year, int month);
}
