package com.hondee.service_plan.dao;

import com.hondee.service_plan.domain.YearPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YearPlanDao extends JpaRepository<YearPlan, String> {
    YearPlan findFirstByProjectCodeOrderByYearDesc(String projectCode);

    Page<YearPlan> findByProjectCodeOrderByYearDesc(String projectCode, Pageable pageable);

    List<YearPlan> findByProjectCodeAndYearAfter(String projectCode, int year);

    YearPlan findByProjectCodeAndYearAndReleased(String projectCode, int year, boolean released);

    YearPlan findByProjectCodeAndYear(String projectCode, int year);
}
