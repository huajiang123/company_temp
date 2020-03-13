package com.hondee.service_plan.dao;


import com.hondee.service_plan.domain.QuarterPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuarterPlanDao extends JpaRepository<QuarterPlan, String> {
    Page<QuarterPlan> findByProjectCode(String projectCode, Pageable pageable);

    QuarterPlan findFirstByProjectCode(String projectCode, Sort sort);

    @Query(value = "SELECT * FROM plan_quarterplan WHERE ProjectCode = ?1 AND (`Year` > ?2 OR (`Year` = ?2 AND Quarter > ?3))", nativeQuery = true)
    List<QuarterPlan> findByProjectCodeAndYearAndQuarterAfter(String projectCode, int year, int quarter);

    QuarterPlan findByProjectCodeAndYearAndQuarterAndReleased(String projectCode, int year, int quarter, boolean released);

    QuarterPlan findByProjectCodeAndYearAndQuarter(String projectCode, Integer year, Integer quarter);
}
