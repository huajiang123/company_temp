package com.hondee.service_plan.dao;


import com.hondee.service_plan.domain.WeekReportDetail;
import com.hondee.service_plan.domain.WeekReportTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface WeekReportTrackingDao extends JpaRepository<WeekReportTracking, String> {
    void deleteByWeekReportCode(String weekReportCode);

    WeekReportTracking findFirstByWeekReportCodeAndWeekReportDetailAndPeriodDayBeforeOrderByPeriodDayDesc(String weekReportCode, WeekReportDetail weekReportDetail, Date periodDay);

    List<WeekReportTracking> findByWeekReportCodeAndWeekReportDetailAndPeriodDayGreaterThanEqualOrderByPeriodDayAsc(String weekReportCode, WeekReportDetail weekReportDetail, Date periodDay);

    WeekReportTracking findFirstByItemCodeAndPeriodDayLessThanEqualOrderByPeriodDayDesc(String itemCode, Date periodDay);

    List<WeekReportTracking> findByItemCodeAndPeriodDayIsAfterAndPeriodDayIsBefore(String itemCode, Date periodDay1, Date periodDay2);

    WeekReportTracking findFirstByItemCodeAndPeriodDayBeforeOrderByPeriodDayDesc(String itemCode, Date periodDay);

    List<WeekReportTracking> findByItemCodeAndPeriodDayIsAfterAndPeriodDayBefore(String itemCode, Date periodDay1, Date periodDay2);
}
