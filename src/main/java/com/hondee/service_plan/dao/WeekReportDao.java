package com.hondee.service_plan.dao;

import com.hondee.service_plan.domain.WeekReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeekReportDao extends JpaRepository<WeekReport, String> {
    Page<WeekReport> findByProjectCode(String projectCode, Pageable pageable);

    WeekReport findFirstByProjectCode(String projectCode, Sort sort);

    @Query(value = "SELECT * FROM plan_weekreport WHERE ProjectCode = ?1 AND (`Year` > ?2 OR (`Year` = ?2 AND `Week` > ?3))", nativeQuery = true)
    List<WeekReport> findByProjectCodeAndYearAndWeekAfter(String projectCode, int year, int week);

    WeekReport findByProjectCodeAndYearAndWeek(String projectCode, int year, int week);
}
