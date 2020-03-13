package com.hondee.service_plan.dao;

import com.hondee.service_plan.domain.WeekReportDetail;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekReportDetailDao extends JpaRepository<WeekReportDetail, String> {
    void deleteByProjectCodeAndYearAndWeek(String projectCode, int year, int week);

    WeekReportDetail findFirstByItemCode(String itemCode, Sort sort);
}
