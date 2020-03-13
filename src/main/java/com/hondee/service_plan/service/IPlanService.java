package com.hondee.service_plan.service;


import com.hondee.service_plan.domain.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IPlanService {
    YearPlan newYearPlan(String projectCode, int year);

    QuarterPlan newQuarterPlan(String projectCode, int year, int quarter);

    MonthPlan newMonthPlan(String projectCode, int year, int quarter, int month);

    WeekPlan newWeekPlan(String projectCode, int year, int quarter, int month, int week, Date startTime);

    BasePlan getBasePlan(String projectCode);

    BasePlanDetail getBasePlanDetail(String itemCode);

    void saveBasePlanDetailDraft(BasePlanDetailDraft basePlanDetailDraft);

    void initBasePlanDetailDraftSequence(BasePlanDetailDraft basePlanDetailDraft);

    BasePlanDetailDraft getBasePlanDetailDraft(String itemCode);

    void releaseBasePlan(String projectCode);

    void updateBasePlanDetailDraftPercent(List<BasePlanDetailDraft> drafts, BasePlanDetailDraft parent);

    YearPlan getYearPlan(String yearPlanCode);

    QuarterPlan getQuarterPlan(String quarterPlanCode);

    MonthPlan getMonthPlan(String monthPlanCode);

    WeekPlan getWeekPlan(String weekPlanCode);

    WeekReport newWeekReport(String projectCode, int year, int quarter, int month, int week, Date startTime);

    WeekReport getWeekReport(String weekReportCode);

    WeekReportTracking newWeekReportTracking(String weekReportCode, String weekReportDetailCode, Date periodDay);

    void updateWeekReportTrackingQtyAndManHour(String weekReportCode, String weekReportDetailCode, Date periodDay);

    Map<String, Object> progressOverview(String projectCode);

    Map<String, Object> progressMap(String projectCode);

    Object progressExecution(String projectCode);

    void newVersionData(String content, String businessNo, int version, int type);

    List<BasePlanDetail> exportBasePlan(String projectCode);
}
