package com.hondee.service_plan.serviceImpl;

import com.hondee.common.exception.HdException;
import com.hondee.service_plan.dao.*;
import com.hondee.service_plan.domain.*;
import com.hondee.service_plan.dto.WeekReportDetailDto;
import com.hondee.service_plan.dto.WeekReportDto;
import com.hondee.service_plan.mapper.WeekReportDetailMapper;
import com.hondee.service_plan.mapper.WeekReportMapper;
import com.hondee.service_plan.service.IPlanService;
import com.hondee.service_plan.service.IPlanVersionService;
import com.hondee.service_plan.utils.CalendarUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PlanServiceImpl implements IPlanService {
    private final BasePlanDao basePlanDao;
    private final BasePlanDetailDao basePlanDetailDao;
    private final BasePlanDetailDraftDao basePlanDetailDraftDao;
    private final MonthPlanDao monthPlanDao;
    private final MonthPlanDetailDao monthPlanDetailDao;
    //todo
    //private final ProjectDao projectDao;
    private final QuarterPlanDao quarterPlanDao;
    private final QuarterPlanDetailDao quarterPlanDetailDao;
    private final WeekPlanDao weekPlanDao;
    private final WeekPlanDetailDao weekPlanDetailDao;
    private final WeekReportDao weekReportDao;
    private final WeekReportDetailDao weekReportDetailDao;
    private final WeekReportTrackingDao weekReportTrackingDao;
    private final YearPlanDao yearPlanDao;
    private final YearPlanDetailDao yearPlanDetailDao;
    private final IPlanVersionService versionService;

    //todo
    @Autowired
    public PlanServiceImpl(BasePlanDao basePlanDao, BasePlanDetailDao basePlanDetailDao, BasePlanDetailDraftDao basePlanDetailDraftDao, MonthPlanDao monthPlanDao, MonthPlanDetailDao monthPlanDetailDao, ProjectDao projectDao, QuarterPlanDao quarterPlanDao, QuarterPlanDetailDao quarterPlanDetailDao, WeekPlanDao weekPlanDao, WeekPlanDetailDao weekPlanDetailDao, WeekReportDao weekReportDao, WeekReportDetailDao weekReportDetailDao, WeekReportTrackingDao weekReportTrackingDao, YearPlanDao yearPlanDao, YearPlanDetailDao yearPlanDetailDao, IPlanVersionService versionService) {
        this.basePlanDao = basePlanDao;
        this.basePlanDetailDao = basePlanDetailDao;
        this.basePlanDetailDraftDao = basePlanDetailDraftDao;
        this.monthPlanDao = monthPlanDao;
        this.monthPlanDetailDao = monthPlanDetailDao;
        //todo
        //this.projectDao = projectDao;
        this.quarterPlanDao = quarterPlanDao;
        this.quarterPlanDetailDao = quarterPlanDetailDao;
        this.weekPlanDao = weekPlanDao;
        this.weekPlanDetailDao = weekPlanDetailDao;
        this.weekReportDao = weekReportDao;
        this.weekReportDetailDao = weekReportDetailDao;
        this.weekReportTrackingDao = weekReportTrackingDao;
        this.yearPlanDao = yearPlanDao;
        this.yearPlanDetailDao = yearPlanDetailDao;
        this.versionService = versionService;
    }

    @PostConstruct
    public void init() {
    }

    @Override
    public YearPlan newYearPlan(String projectCode, int year) {
        BasePlan basePlan = getBasePlan(projectCode);
        YearPlan yearPlan = new YearPlan();
        yearPlan.setYearPlanCode(UUID.randomUUID().toString());
        yearPlan.setBasePlanCode(basePlan.getPlanCode());
        yearPlan.setProjectCode(projectCode);
        yearPlan.setYear(year);
        yearPlan.setReleased(false);
        yearPlan.setValidity(true);
        //todo
        //EntityUtils.addCreateInfo(yearPlan);
        yearPlanDao.save(yearPlan);
        if (basePlan.getDetails().size() > 0) {
            newYearPlanDetailFromBasePlanDetail(year, basePlan.getDetails(), yearPlan, null);
        }
        return yearPlan;
    }

    private void newYearPlanDetailFromBasePlanDetail(int year, List<BasePlanDetail> basePlanDetailList, YearPlan yearPlan, YearPlanDetail parent) {
        basePlanDetailList.forEach(basePlanDetail -> {
            YearPlanDetail yearPlanDetail = new YearPlanDetail();
            BeanUtils.copyProperties(basePlanDetail, yearPlanDetail);
            yearPlanDetail.setYearPlanDetailCode(UUID.randomUUID().toString());
            yearPlanDetail.setYear(year);
            yearPlanDetail.setYearPlan(yearPlan);
            yearPlanDetail.setParent(parent);
            yearPlanDetail.setValidity(true);
            //todo
            //EntityUtils.addCreateInfo(yearPlanDetail);
            yearPlanDetailDao.save(yearPlanDetail);
            if (basePlanDetail.getChildren().size() > 0) {
                newYearPlanDetailFromBasePlanDetail(year, basePlanDetail.getChildren(), null, yearPlanDetail);
            }
        });
    }

    @Override
    public QuarterPlan newQuarterPlan(String projectCode, int year, int quarter) {
        BasePlan basePlan = getBasePlan(projectCode);
        QuarterPlan quarterPlan = new QuarterPlan();
        quarterPlan.setQuarterPlanCode(UUID.randomUUID().toString());
        quarterPlan.setBasePlanCode(basePlan.getPlanCode());
        quarterPlan.setProjectCode(projectCode);
        quarterPlan.setYear(year);
        quarterPlan.setQuarter(quarter);
        quarterPlan.setReleased(false);
        quarterPlan.setValidity(true);
        //todo
        //EntityUtils.addCreateInfo(quarterPlan);
        quarterPlanDao.save(quarterPlan);
        if (basePlan.getDetails().size() > 0) {
            YearPlan yearPlan = yearPlanDao.findByProjectCodeAndYear(projectCode, year);
            newQuarterPlanDetailFromBasePlanDetail(year, quarter, basePlan.getDetails(), yearPlan, quarterPlan, null);
        }
        return quarterPlan;
    }

    private void newQuarterPlanDetailFromBasePlanDetail(int year, int quarter, List<BasePlanDetail> basePlanDetailList, YearPlan yearPlan, QuarterPlan quarterPlan, QuarterPlanDetail parent) {
        basePlanDetailList.forEach(basePlanDetail -> {
            QuarterPlanDetail quarterPlanDetail = new QuarterPlanDetail();
            BeanUtils.copyProperties(basePlanDetail, quarterPlanDetail);
            quarterPlanDetail.setQuarterPlanDetailCode(UUID.randomUUID().toString());
            quarterPlanDetail.setYear(year);
            quarterPlanDetail.setQuarter(quarter);
            quarterPlanDetail.setQuarterPlan(quarterPlan);
            quarterPlanDetail.setParent(parent);
            if (yearPlan != null && yearPlan.getReleased()) {
                YearPlanDetail yearPlanDetail = yearPlanDetailDao.findByYearAndItemCode(year, basePlanDetail.getItemCode());
                quarterPlanDetail.setYearPlanQty(yearPlanDetail.getYearPlanQty());
                quarterPlanDetail.setYearPlanManHour(yearPlanDetail.getYearPlanManHour());
            }
            quarterPlanDetail.setValidity(true);
            //todo
            //EntityUtils.addCreateInfo(quarterPlanDetail);
            quarterPlanDetailDao.save(quarterPlanDetail);
            if (basePlanDetail.getChildren().size() > 0) {
                newQuarterPlanDetailFromBasePlanDetail(year, quarter, basePlanDetail.getChildren(), yearPlan, null, quarterPlanDetail);
            }
        });
    }

    @Override
    public MonthPlan newMonthPlan(String projectCode, int year, int quarter, int month) {
        BasePlan basePlan = getBasePlan(projectCode);
        MonthPlan monthPlan = new MonthPlan();
        monthPlan.setMonthPlanCode(UUID.randomUUID().toString());
        monthPlan.setBasePlanCode(basePlan.getPlanCode());
        monthPlan.setProjectCode(projectCode);
        monthPlan.setYear(year);
        monthPlan.setQuarter(quarter);
        monthPlan.setMonth(month);
        monthPlan.setReleased(false);
        monthPlan.setValidity(true);
        //todo
        //EntityUtils.addCreateInfo(monthPlan);
        monthPlanDao.save(monthPlan);
        if (basePlan.getDetails().size() > 0) {
            YearPlan yearPlan = yearPlanDao.findByProjectCodeAndYear(projectCode, year);
            QuarterPlan quarterPlan = quarterPlanDao.findByProjectCodeAndYearAndQuarter(projectCode, year, quarter);
            newMonthPlanDetailFromBasePlanDetail(year, quarter, month, basePlan.getDetails(), yearPlan, quarterPlan, monthPlan, null);
        }
        return monthPlan;
    }

    private void newMonthPlanDetailFromBasePlanDetail(int year, int quarter, int month, List<BasePlanDetail> basePlanDetailList, YearPlan yearPlan, QuarterPlan quarterPlan, MonthPlan monthPlan, MonthPlanDetail parent) {
        basePlanDetailList.forEach(basePlanDetail -> {
            MonthPlanDetail monthPlanDetail = new MonthPlanDetail();
            BeanUtils.copyProperties(basePlanDetail, monthPlanDetail);
            monthPlanDetail.setMonthPlanDetailCode(UUID.randomUUID().toString());
            monthPlanDetail.setYear(year);
            monthPlanDetail.setQuarter(quarter);
            monthPlanDetail.setMonth(month);
            monthPlanDetail.setMonthPlan(monthPlan);
            monthPlanDetail.setParent(parent);
            if (yearPlan != null && yearPlan.getReleased()) {
                YearPlanDetail yearPlanDetail = yearPlanDetailDao.findByYearAndItemCode(year, basePlanDetail.getItemCode());
                monthPlanDetail.setYearPlanQty(yearPlanDetail.getYearPlanQty());
                monthPlanDetail.setYearPlanManHour(yearPlanDetail.getYearPlanManHour());
            }
            if (quarterPlan != null && quarterPlan.getReleased()) {
                QuarterPlanDetail quarterPlanDetail = quarterPlanDetailDao.findByYearAndQuarterAndItemCode(year, quarter, basePlanDetail.getItemCode());
                monthPlanDetail.setQuarterPlanQty(quarterPlanDetail.getQuarterPlanQty());
                monthPlanDetail.setQuarterPlanManHour(quarterPlanDetail.getQuarterPlanManHour());
            }
            monthPlanDetail.setValidity(true);
            //todo
            //EntityUtils.addCreateInfo(monthPlanDetail);
            monthPlanDetailDao.save(monthPlanDetail);
            if (basePlanDetail.getChildren().size() > 0) {
                newMonthPlanDetailFromBasePlanDetail(year, quarter, month, basePlanDetail.getChildren(), yearPlan, quarterPlan, null, monthPlanDetail);
            }
        });
    }

    @Override
    public WeekPlan newWeekPlan(String projectCode, int year, int quarter, int month, int week, Date startTime) {
        BasePlan basePlan = getBasePlan(projectCode);
        WeekPlan weekPlan = new WeekPlan();
        weekPlan.setWeekPlanCode(UUID.randomUUID().toString());
        weekPlan.setBasePlanCode(basePlan.getPlanCode());
        weekPlan.setProjectCode(projectCode);
        weekPlan.setYear(year);
        weekPlan.setQuarter(quarter);
        weekPlan.setMonth(month);
        weekPlan.setWeek(week);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        weekPlan.setStartTime(calendar.getTime());
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        weekPlan.setEndTime(calendar.getTime());
        weekPlan.setReleased(false);
        weekPlan.setValidity(true);
        //todo
        //EntityUtils.addCreateInfo(weekPlan);
        weekPlanDao.save(weekPlan);
        if (basePlan.getDetails().size() > 0) {
            YearPlan yearPlan = yearPlanDao.findByProjectCodeAndYear(projectCode, year);
            QuarterPlan quarterPlan = quarterPlanDao.findByProjectCodeAndYearAndQuarter(projectCode, year, quarter);
            MonthPlan monthPlan = monthPlanDao.findByProjectCodeAndYearAndMonth(projectCode, year, month);
            newWeekPlanDetailFromBasePlanDetail(year, quarter, month, week, basePlan.getDetails(), yearPlan, quarterPlan, monthPlan, weekPlan, null);
        }
        return weekPlan;
    }

    private void newWeekPlanDetailFromBasePlanDetail(int year, int quarter, int month, int week, List<BasePlanDetail> basePlanDetailList, YearPlan yearPlan, QuarterPlan quarterPlan, MonthPlan monthPlan, WeekPlan weekPlan, WeekPlanDetail parent) {
        basePlanDetailList.forEach(basePlanDetail -> {
            WeekPlanDetail weekPlanDetail = new WeekPlanDetail();
            BeanUtils.copyProperties(basePlanDetail, weekPlanDetail);
            weekPlanDetail.setWeekPlanDetailCode(UUID.randomUUID().toString());
            weekPlanDetail.setYear(year);
            weekPlanDetail.setQuarter(quarter);
            weekPlanDetail.setMonth(month);
            weekPlanDetail.setWeek(week);
            weekPlanDetail.setWeekPlan(weekPlan);
            weekPlanDetail.setParent(parent);
            if (yearPlan != null && yearPlan.getReleased()) {
                YearPlanDetail yearPlanDetail = yearPlanDetailDao.findByYearAndItemCode(year, basePlanDetail.getItemCode());
                weekPlanDetail.setYearPlanQty(yearPlanDetail.getYearPlanQty());
                weekPlanDetail.setYearPlanManHour(yearPlanDetail.getYearPlanManHour());
            }
            if (quarterPlan != null && quarterPlan.getReleased()) {
                QuarterPlanDetail quarterPlanDetail = quarterPlanDetailDao.findByYearAndQuarterAndItemCode(year, quarter, basePlanDetail.getItemCode());
                weekPlanDetail.setQuarterPlanQty(quarterPlanDetail.getQuarterPlanQty());
                weekPlanDetail.setQuarterPlanManHour(quarterPlanDetail.getQuarterPlanManHour());
            }
            if (monthPlan != null && monthPlan.getReleased()) {
                MonthPlanDetail monthPlanDetail = monthPlanDetailDao.findByYearAndMonthAndItemCode(year, month, basePlanDetail.getItemCode());
                weekPlanDetail.setMonthPlanQty(monthPlanDetail.getMonthPlanQty());
                weekPlanDetail.setMonthPlanManHour(monthPlanDetail.getMonthPlanManHour());
            }
            weekPlanDetail.setValidity(true);
            //todo
            //EntityUtils.addCreateInfo(weekPlanDetail);
            weekPlanDetailDao.save(weekPlanDetail);
            if (basePlanDetail.getChildren().size() > 0) {
                newWeekPlanDetailFromBasePlanDetail(year, quarter, month, week, basePlanDetail.getChildren(), yearPlan, quarterPlan, monthPlan, null, weekPlanDetail);
            }
        });
    }

    @Override
    public BasePlan getBasePlan(String projectCode) {
        BasePlan basePlan = basePlanDao.findByProjectCode(projectCode);
        return basePlan == null ? initBasePlan(projectCode) : basePlan;
    }

    private BasePlan initBasePlan(String projectCode) {
        BasePlan basePlan = new BasePlan();
        basePlan.setPlanCode(UUID.randomUUID().toString());
        //todo
        //basePlan.setProject(projectDao.findByProjectCode(projectCode));
        basePlan.setReleased(false);
        basePlan.setValidity(true);
        //todo
        //EntityUtils.addCreateInfo(basePlan);
        return basePlanDao.save(basePlan);
    }

    @Override
    public BasePlanDetail getBasePlanDetail(String itemCode) {
        return basePlanDetailDao.getOne(itemCode);
    }

    @Override
    public void saveBasePlanDetailDraft(BasePlanDetailDraft basePlanDetailDraft) {
        basePlanDetailDraftDao.save(basePlanDetailDraft);
    }

    @Override
    public void initBasePlanDetailDraftSequence(BasePlanDetailDraft basePlanDetailDraft) {
        if (basePlanDetailDraft.getParent() == null) {
            BasePlanDetailDraft basePlanDetailDraftLast = basePlanDetailDraftDao.findFirstByBasePlanAndParentIsNullOrderBySequenceDesc(basePlanDetailDraft.getBasePlan());
            if (basePlanDetailDraftLast == null) {
                basePlanDetailDraft.setSequence("10001");
            } else {
                basePlanDetailDraft.setSequence(String.valueOf(Long.parseLong(basePlanDetailDraftLast.getSequence()) + 1));
            }
        } else {
            BasePlanDetailDraft basePlanDetailDraftParent = basePlanDetailDraft.getParent();
            BasePlanDetailDraft basePlanDetailDraftLast = basePlanDetailDraftDao.findFirstByParentOrderBySequenceDesc(basePlanDetailDraftParent);
            if (basePlanDetailDraftLast == null) {
                basePlanDetailDraft.setSequence(basePlanDetailDraftParent.getSequence() + "0001");
            } else {
                String sequenceLast = basePlanDetailDraftLast.getSequence();
                basePlanDetailDraft.setSequence(sequenceLast.substring(0, sequenceLast.length() - 4) +
                        String.format("%04d", Long.parseLong(sequenceLast.substring(sequenceLast.length() - 4)) + 1));
            }
        }
    }

    @Override
    public BasePlanDetailDraft getBasePlanDetailDraft(String itemCode) {
        return basePlanDetailDraftDao.getOne(itemCode);
    }

    @Override
    public void releaseBasePlan(String projectCode) {
        List<BasePlanDetailDraft> basePlanDetailDraftList = basePlanDetailDraftDao.findByProjectCodeAndReleasedOrderBySequenceAsc(projectCode, false);
        basePlanDetailDraftList.forEach(draft -> {
            if ((draft.getPlanBDate() == null || draft.getPlanEDate() == null) && draft.getValidity()) {
                throw new HdException("999", "起止时间不能为空！");
            }
            if (draft.getWeight().equals(0) && draft.getValidity()) {
                throw new HdException("999", "权重不能为空！");
            }
            List<BasePlanDetailDraft> children = basePlanDetailDraftDao.findByParent(draft);
            if (children.size() > 0 && children.stream().anyMatch(BaseEntity::getValidity)) {
                draft.setUom(null);
                draft.setQty(null);
                draft.setPlanManHour(null);
            } else {
                if (draft.getUom() == null && draft.getValidity()) {
                    throw new HdException("999", "子项单位不能为空！");
                }
                if ((draft.getQty() == null || draft.getQty() == 0) && draft.getValidity()) {
                    throw new HdException("999", "子项数量不能为空！");
                }
            }
            Optional<BasePlanDetail> optional = basePlanDetailDao.findById(draft.getItemCode());
            if (draft.getValidity()) {
                BasePlanDetail detail = optional.orElseGet(BasePlanDetail::new);
                detail.setBasePlan(draft.getBasePlan() == null ? null : basePlanDao.getOne(draft.getBasePlan().getPlanCode()));
                detail.setParent(draft.getParent() == null ? null : basePlanDetailDao.getOne(draft.getParent().getItemCode()));
                BeanUtils.copyProperties(draft, detail, "basePlan", "parent", "children");
                basePlanDetailDao.save(detail);
                draft.setReleased(true);
                basePlanDetailDraftDao.save(draft);
            } else {
                optional.ifPresent(basePlanDetailDao::delete);
                basePlanDetailDraftDao.delete(draft);
            }
        });
        BasePlan basePlan = basePlanDao.findByProjectCode(projectCode);
        basePlan.setVersion(basePlan.getVersion() == null ? 1 : basePlan.getVersion() + 1);
        basePlan.setReleased(true);
        basePlan.setReleasedTime(new Date());
        //todo
        //EntityUtils.addUpdateInfo(basePlan);
        basePlanDao.save(basePlan);
        updateBasePlanDetailDraftPercentWhenRelease(basePlan.getDrafts(), null);
        updateBasePlanDetailPercent(basePlan.getDetails(), null);
    }

    private void updateBasePlanDetailPercent(List<BasePlanDetail> details, BasePlanDetail parent) {
        if (details == null) {
            return;
        }
        details.removeIf(detail -> !detail.getValidity());
        AtomicReference<Double> totalWeights = new AtomicReference<>(0.00d);
        details.forEach(detail -> totalWeights.updateAndGet(v -> v + detail.getWeight()));
        if (totalWeights.get().equals(0d)) {
            details.forEach(detail -> {
                detail.setWeightPercent(0d);
                detail.setWeightTotalPercent(0d);
            });
        } else {
            details.forEach(detail -> {
                Double wp = detail.getWeight() / totalWeights.get();
                detail.setWeightPercent(wp);
                Double wtp = parent == null ? detail.getWeightPercent() : detail.getWeightPercent() * parent.getWeightTotalPercent();
                detail.setWeightTotalPercent(wtp);
            });
        }
        basePlanDetailDao.saveAll(details);
        details.forEach(detail -> {
            if (detail.getChildren() != null && detail.getChildren().size() > 0) {
                updateBasePlanDetailPercent(detail.getChildren(), detail);
            }
        });
    }


    private void updateBasePlanDetailDraftPercentWhenRelease(List<BasePlanDetailDraft> drafts, BasePlanDetailDraft parent) {
        if (drafts == null) {
            return;
        }
        AtomicReference<Double> totalWeights = new AtomicReference<>(0.00d);
        drafts.forEach(draft -> {
            if (!draft.getValidity()) {
                return;
            }
            totalWeights.updateAndGet(v -> v + draft.getWeight());
        });
        if (totalWeights.get().equals(0d)) {
            drafts.forEach(draft -> {
                if (!draft.getValidity()) {
                    return;
                }
                draft.setWeightPercent(0d);
                draft.setWeightTotalPercent(0d);
                basePlanDetailDraftDao.save(draft);
            });
        } else {
            drafts.forEach(draft -> {
                if (!draft.getValidity()) {
                    return;
                }
                Double wp = draft.getWeight() / totalWeights.get();
                draft.setWeightPercent(wp);
                Double wtp = parent == null ? draft.getWeightPercent() : draft.getWeightPercent() * parent.getWeightTotalPercent();
                draft.setWeightTotalPercent(wtp);
                basePlanDetailDraftDao.save(draft);
            });
        }
        drafts.forEach(draft -> {
            if (!draft.getValidity()) {
                return;
            }
            if (draft.getChildren().size() > 0) {
                updateBasePlanDetailDraftPercentWhenRelease(draft.getChildren(), draft);
            }
        });
    }

    @Override
    public void updateBasePlanDetailDraftPercent(List<BasePlanDetailDraft> drafts, BasePlanDetailDraft parent) {
        if (drafts == null) {
            return;
        }
        AtomicReference<Double> totalWeights = new AtomicReference<>(0.00d);
        drafts.forEach(draft -> {
            if (!draft.getValidity()) {
                return;
            }
            totalWeights.updateAndGet(v -> v + draft.getWeight());
        });
        if (totalWeights.get().equals(0d)) {
            drafts.forEach(draft -> {
                if (!draft.getValidity()) {
                    return;
                }
                if (!draft.getWeightPercent().equals(0d)) {
                    draft.setReleased(false);
                }
                draft.setWeightPercent(0d);
                if (!draft.getWeightTotalPercent().equals(0d)) {
                    draft.setReleased(false);
                }
                draft.setWeightTotalPercent(0d);
                basePlanDetailDraftDao.save(draft);
            });
        } else {
            drafts.forEach(draft -> {
                if (!draft.getValidity()) {
                    return;
                }
                Double wp = draft.getWeight() / totalWeights.get();
                if (!draft.getWeightPercent().equals(wp)) {
                    draft.setReleased(false);
                }
                draft.setWeightPercent(wp);
                Double wtp = parent == null ? draft.getWeightPercent() : draft.getWeightPercent() * parent.getWeightTotalPercent();
                if (!draft.getWeightTotalPercent().equals(wtp)) {
                    draft.setReleased(false);
                }
                draft.setWeightTotalPercent(wtp);
                basePlanDetailDraftDao.save(draft);
            });
        }
        drafts.forEach(draft -> {
            if (!draft.getValidity()) {
                return;
            }
            if (draft.getChildren().size() > 0) {
                updateBasePlanDetailDraftPercent(draft.getChildren(), draft);
            }
        });
    }

    @Override
    public YearPlan getYearPlan(String yearPlanCode) {
        return yearPlanDao.getOne(yearPlanCode);
    }

    @Override
    public QuarterPlan getQuarterPlan(String quarterPlanCode) {
        return quarterPlanDao.getOne(quarterPlanCode);
    }

    @Override
    public MonthPlan getMonthPlan(String monthPlanCode) {
        return monthPlanDao.getOne(monthPlanCode);
    }

    @Override
    public WeekPlan getWeekPlan(String weekPlanCode) {
        return weekPlanDao.getOne(weekPlanCode);
    }

    @Override
    public WeekReport newWeekReport(String projectCode, int year, int quarter, int month, int week, Date startTime) {
        BasePlan basePlan = getBasePlan(projectCode);
        WeekReport weekReport = new WeekReport();
        weekReport.setWeekReportCode(UUID.randomUUID().toString());
        weekReport.setBasePlanCode(basePlan.getPlanCode());
        weekReport.setProjectCode(projectCode);
        weekReport.setYear(year);
        weekReport.setQuarter(quarter);
        weekReport.setMonth(month);
        weekReport.setWeek(week);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        weekReport.setStartTime(calendar.getTime());
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        weekReport.setEndTime(calendar.getTime());
        weekReport.setReleased(false);
        weekReport.setValidity(true);
        //todo
        //EntityUtils.addCreateInfo(weekReport);
        weekReportDao.save(weekReport);
        if (basePlan.getDetails().size() > 0) {
            YearPlan yearPlan = yearPlanDao.findByProjectCodeAndYear(projectCode, year);
            QuarterPlan quarterPlan = quarterPlanDao.findByProjectCodeAndYearAndQuarter(projectCode, year, quarter);
            MonthPlan monthPlan = monthPlanDao.findByProjectCodeAndYearAndMonth(projectCode, year, month);
            WeekPlan weekPlan = weekPlanDao.findByProjectCodeAndYearAndWeek(projectCode, year, week);
            newWeekReportDetailFromBasePlanDetail(year, quarter, month, week, basePlan.getDetails(), yearPlan, quarterPlan, monthPlan, weekPlan, weekReport, null);
        }
        return weekReport;
    }

    private void newWeekReportDetailFromBasePlanDetail(int year, int quarter, int month, int week, List<BasePlanDetail> basePlanDetailList, YearPlan yearPlan, QuarterPlan quarterPlan, MonthPlan monthPlan, WeekPlan weekPlan, WeekReport weekReport, WeekReportDetail parent) {
        basePlanDetailList.forEach(basePlanDetail -> {
            WeekReportDetail weekReportDetail = new WeekReportDetail();
            BeanUtils.copyProperties(basePlanDetail, weekReportDetail);
            weekReportDetail.setWeekReportDetailCode(UUID.randomUUID().toString());
            weekReportDetail.setYear(year);
            weekReportDetail.setQuarter(quarter);
            weekReportDetail.setMonth(month);
            weekReportDetail.setWeek(week);
            weekReportDetail.setWeekReport(weekReport);
            weekReportDetail.setParent(parent);
            if (yearPlan != null && yearPlan.getReleased()) {
                YearPlanDetail yearPlanDetail = yearPlanDetailDao.findByYearAndItemCode(year, basePlanDetail.getItemCode());
                weekReportDetail.setYearPlanQty(yearPlanDetail.getYearPlanQty());
                weekReportDetail.setYearPlanManHour(yearPlanDetail.getYearPlanManHour());
            }
            if (quarterPlan != null && quarterPlan.getReleased()) {
                QuarterPlanDetail quarterPlanDetail = quarterPlanDetailDao.findByYearAndQuarterAndItemCode(year, quarter, basePlanDetail.getItemCode());
                weekReportDetail.setQuarterPlanQty(quarterPlanDetail.getQuarterPlanQty());
                weekReportDetail.setQuarterPlanManHour(quarterPlanDetail.getQuarterPlanManHour());
            }
            if (monthPlan != null && monthPlan.getReleased()) {
                MonthPlanDetail monthPlanDetail = monthPlanDetailDao.findByYearAndMonthAndItemCode(year, month, basePlanDetail.getItemCode());
                weekReportDetail.setMonthPlanQty(monthPlanDetail.getMonthPlanQty());
                weekReportDetail.setMonthPlanManHour(monthPlanDetail.getMonthPlanManHour());
            }
            if (weekPlan != null && weekPlan.getReleased()) {
                WeekPlanDetail weekPlanDetail = weekPlanDetailDao.findByYearAndWeekAndItemCode(year, week, basePlanDetail.getItemCode());
                weekReportDetail.setWeekPlanQty(weekPlanDetail.getWeekPlanQty());
                weekReportDetail.setWeekPlanManHour(weekPlanDetail.getWeekPlanManHour());
            }
            WeekReportDetail last = weekReportDetailDao.findFirstByItemCode(basePlanDetail.getItemCode(), Sort.by(Sort.Order.desc("year"), Sort.Order.desc("week")));
            weekReportDetail.setEndQty(last == null ? 0 : last.getEndQty());
            weekReportDetail.setEndManHour(last == null ? 0 : last.getEndManHour());
            weekReportDetail.setProgress(last == null ? 0 : last.getProgress());
            weekReportDetail.setActualStartTime(last == null ? null : last.getActualStartTime());
            weekReportDetail.setActualEndTime(last == null ? null : last.getActualEndTime());
            weekReportDetail.setStat(last == null ? 1 : last.getStat());
            weekReportDetail.setValidity(true);
            //todo
            //EntityUtils.addCreateInfo(weekReportDetail);
            weekReportDetailDao.save(weekReportDetail);
            if (basePlanDetail.getChildren().size() > 0) {
                newWeekReportDetailFromBasePlanDetail(year, quarter, month, week, basePlanDetail.getChildren(), yearPlan, quarterPlan, monthPlan, weekPlan, null, weekReportDetail);
            }
        });
    }

    @Override
    public WeekReport getWeekReport(String weekReportCode) {
        return weekReportDao.getOne(weekReportCode);
    }

    @Override
    public WeekReportTracking newWeekReportTracking(String weekReportCode, String weekReportDetailCode, Date periodDay) {
        WeekReportTracking weekReportTracking = new WeekReportTracking();
        weekReportTracking.setWeekTrackingCode(UUID.randomUUID().toString());
        weekReportTracking.setWeekReportCode(weekReportCode);
        WeekReportDetail weekReportDetail = weekReportDetailDao.getOne(weekReportDetailCode);
        weekReportTracking.setWeekReportDetail(weekReportDetail);
        weekReportTracking.setItemCode(weekReportDetail.getItemCode());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(periodDay);
        weekReportTracking.setPeriodNo(Integer.parseInt(calendar.get(Calendar.YEAR) + String.format("%02d", calendar.get(Calendar.WEEK_OF_YEAR))));
        weekReportTracking.setPeriodDay(periodDay);
        weekReportTracking.setBasePlanQty(weekReportDetail.getQty());
        WeekReportTracking last = weekReportTrackingDao.findFirstByWeekReportCodeAndWeekReportDetailAndPeriodDayBeforeOrderByPeriodDayDesc(weekReportCode, weekReportDetail, periodDay);
        if (last == null) {
            last = weekReportTrackingDao.findFirstByItemCodeAndPeriodDayBeforeOrderByPeriodDayDesc(weekReportDetail.getItemCode(), periodDay);
        }
        weekReportTracking.setStartQty(last == null ? 0 : last.getEndQty());
        weekReportTracking.setStartManHour(last == null ? 0 : last.getEndManHour());
        weekReportTracking.setValidity(true);
        //todo
        //EntityUtils.addCreateInfo(weekReportTracking);
        return weekReportTrackingDao.save(weekReportTracking);
    }

    @Override
    public void updateWeekReportTrackingQtyAndManHour(String weekReportCode, String weekReportDetailCode, Date periodDay) {
        WeekReportDetail weekReportDetail = weekReportDetailDao.findById(weekReportDetailCode).orElseThrow(() -> new HdException("999", "周汇报清单项不存在！"));
        List<WeekReportTracking> list = weekReportTrackingDao.findByWeekReportCodeAndWeekReportDetailAndPeriodDayGreaterThanEqualOrderByPeriodDayAsc(weekReportCode, weekReportDetail, periodDay);
        final Double[] tmpQty = {list.get(0).getStartQty()};
        final Integer[] tmpManHour = {list.get(0).getStartManHour()};
        list.forEach(tracking -> {
            tracking.setStartQty(tmpQty[0]);
            tmpQty[0] += tracking.getReportQty();
            tracking.setEndQty(tmpQty[0]);
            tracking.setStartManHour(tmpManHour[0]);
            tmpManHour[0] += tracking.getReportManHour();
            tracking.setEndManHour(tmpManHour[0]);
        });
        weekReportTrackingDao.saveAll(list);
        weekReportDetail.setEndQty(tmpQty[0]);
        weekReportDetail.setEndManHour(tmpManHour[0]);
        weekReportDetail.setProgress(weekReportDetail.getEndQty() / weekReportDetail.getQty());
        weekReportDetailDao.save(weekReportDetail);
        if (weekReportDetail.getParent() != null) {
            updateWeekReportDetailProgress(weekReportDetail.getParent().getWeekReportDetailCode());
        }
    }

    private void updateWeekReportDetailProgress(String weekReportDetailCode) {
        WeekReportDetail weekReportDetail = weekReportDetailDao.findById(weekReportDetailCode).orElseThrow(() -> new HdException("999", "周汇报清单项不存在！"));
        double progress = weekReportDetail.getChildren().stream().mapToDouble(child -> child.getProgress() * child.getWeightPercent()).sum();
        weekReportDetail.setProgress(progress);
        weekReportDetailDao.save(weekReportDetail);
        if (weekReportDetail.getParent() != null) {
            updateWeekReportDetailProgress(weekReportDetail.getParent().getWeekReportDetailCode());
        }
    }

    @Override
    public Map<String, Object> progressOverview(String projectCode) {
        Map<String, Object> result = new HashMap<>();
        Date date = new Date();
        Calendar calendarBDate = Calendar.getInstance();
        calendarBDate.setTime(date);
        if (calendarBDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendarBDate.set(Calendar.DATE, calendarBDate.get(Calendar.DATE) - 7);
        } else {
            calendarBDate.set(Calendar.DAY_OF_WEEK, 1);
        }
        date = calendarBDate.getTime();
        BasePlan basePlan = getBasePlan(projectCode);
        if (!basePlan.getReleased()) {
            throw new HdException("999", "基准计划未发布！");
        }
        Double bp = getBp(projectCode, date);
        result.put("基准计划完成", bp);
        Double bc = getBc(projectCode, date);
        result.put("实际完成", bc);
        Double dcp = bc - bp;
        result.put("偏差", dcp);
        String remark;
        if (dcp >= -0.05)
            remark = "可控";
        else if (dcp < -0.05 && dcp > -0.1)
            remark = "滞后";
        else
            remark = "严重滞后";
        result.put("总体进度", remark);
        Double yc = getYc(projectCode, date);
        result.put("年计划完成", yc);
        Double qc = getQc(projectCode, date);
        result.put("季度计划完成", qc);
        Double mc = getMc(projectCode, date);
        result.put("月计划完成", mc);
        return result;
    }

    private Double getMc(String projectCode, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        MonthPlan monthPlan = monthPlanDao.findByProjectCodeAndYearAndMonthAndReleased(projectCode, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, true);
        if (monthPlan == null)
            return 0d;
        double result = 0d;
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = calendar.getTime();
        System.out.println(firstDate);
        List<MonthPlanDetail> monthPlanDetailList = monthPlanDetailDao.findByProjectCodeAndYearAndMonth(projectCode, monthPlan.getYear(), monthPlan.getMonth());
        for (MonthPlanDetail mpd : monthPlanDetailList) {
            if (mpd.getMonthPlanQty() == null || mpd.getMonthPlanQty() == 0) {
                continue;
            }
            List<WeekReportTracking> weekReportTrackingList = weekReportTrackingDao.findByItemCodeAndPeriodDayIsAfterAndPeriodDayIsBefore(mpd.getItemCode(), firstDate, date);
            Double reportQty = 0d;
            for (WeekReportTracking wrt : weekReportTrackingList) {
                reportQty += wrt.getReportQty();
            }
            result += reportQty / mpd.getMonthPlanQty() * mpd.getWeightTotalPercent();
        }
        return (double) Math.round(result * 10000) / 10000;
    }

    private Double getQc(String projectCode, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        QuarterPlan quarterPlan = quarterPlanDao.findByProjectCodeAndYearAndQuarterAndReleased(projectCode, calendar.get(Calendar.YEAR), CalendarUtils.getQuarter(date), true);
        if (quarterPlan == null) {
            return 0d;
        }
        double result = 0d;
        Date firstDate = CalendarUtils.getFirstDayOfQuarter(date);
        List<QuarterPlanDetail> quarterPlanDetailList = quarterPlanDetailDao.findByProjectCodeAndYearAndQuarter(projectCode, quarterPlan.getYear(), quarterPlan.getQuarter());
        for (QuarterPlanDetail qpd : quarterPlanDetailList) {
            if (qpd.getQuarterPlanQty() == null || qpd.getQuarterPlanQty() == 0) {
                continue;
            }
            List<WeekReportTracking> weekReportTrackingList = weekReportTrackingDao.findByItemCodeAndPeriodDayIsAfterAndPeriodDayIsBefore(qpd.getItemCode(), firstDate, date);
            Double reportQty = 0d;
            for (WeekReportTracking wrt : weekReportTrackingList) {
                reportQty += wrt.getReportQty();
            }
            result += reportQty / qpd.getQuarterPlanQty() * qpd.getWeightTotalPercent();
        }
        return (double) Math.round(result * 10000) / 10000;
    }

    private Double getYc(String projectCode, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        YearPlan yearPlan = yearPlanDao.findByProjectCodeAndYearAndReleased(projectCode, calendar.get(Calendar.YEAR), true);
        if (yearPlan == null) {
            return 0d;
        }
        double result = 0d;
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        Date firstDate = calendar.getTime();
        List<YearPlanDetail> yearPlanDetailList = yearPlanDetailDao.findByProjectCodeAndYear(projectCode, yearPlan.getYear());
        for (YearPlanDetail ypd : yearPlanDetailList) {
            if (ypd.getYearPlanQty() == null || ypd.getYearPlanQty() == 0) {
                continue;
            }
            List<WeekReportTracking> weekReportTrackingList = weekReportTrackingDao.findByItemCodeAndPeriodDayIsAfterAndPeriodDayIsBefore(ypd.getItemCode(), firstDate, date);
            Double reportQty = 0d;
            for (WeekReportTracking wrt : weekReportTrackingList) {
                reportQty += wrt.getReportQty();
            }
            result += reportQty / ypd.getYearPlanQty() * ypd.getWeightTotalPercent();
        }
        return (double) Math.round(result * 10000) / 10000;
    }

    private Double getBc(String projectCode, Date bDate) {
        double bc = 0d;
        List<BasePlanDetail> basePlanDetailListBP = basePlanDetailDao.findByProjectCodeAndValidity(projectCode, true);
        for (BasePlanDetail bpd : basePlanDetailListBP) {
            boolean flag = false;
            for (BasePlanDetail ppdChildren : basePlanDetailListBP) {
                if (bpd.equals(ppdChildren.getParent())) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }
            WeekReportTracking weekReportTracking = weekReportTrackingDao.findFirstByItemCodeAndPeriodDayLessThanEqualOrderByPeriodDayDesc(bpd.getItemCode(), bDate);
            if (weekReportTracking != null) {
                WeekReport weekReport = weekReportDao.getOne(weekReportTracking.getWeekReportCode());
                if (weekReport.getReleased())
                    bc += weekReportTracking.getEndQty() / bpd.getQty() * bpd.getWeightTotalPercent();
            }
        }
        return (double) Math.round(bc * 10000) / 10000;
    }

    private Double getBp(String projectCode, Date bDate) {
        Double bp = 0d;
        List<BasePlanDetail> basePlanDetailListBP = basePlanDetailDao.findByProjectCodeAndValidityAndPlanBDateBefore(projectCode, true, bDate);
        for (BasePlanDetail bpd : basePlanDetailListBP) {
            boolean flag = false;
            for (BasePlanDetail ppdChildren : basePlanDetailListBP) {
                if (bpd.equals(ppdChildren.getParent())) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }
            if (bDate.after(bpd.getPlanEDate())) {
                bp += bpd.getWeightTotalPercent();
            } else {
                bp += bpd.getWeightTotalPercent() * ((double) ((int) ((bDate.getTime() - bpd.getPlanBDate().getTime()) / (1000 * 3600 * 24)) + 1) /
                        ((int) ((double) (bpd.getPlanEDate().getTime() - bpd.getPlanBDate().getTime()) / (1000 * 3600 * 24)) + 1));
            }
        }
        return (double) Math.round(bp * 10000) / 10000;
    }

    @Override
    public Map<String, Object> progressMap(String projectCode) {
        BasePlan basePlan = getBasePlan(projectCode);
        if (!basePlan.getReleased()) {
            throw new HdException("999", "基准计划未发布！");
        }
        List<BasePlanDetail> basePlanDetailList = basePlanDetailDao.findByProjectCodeAndValidity(projectCode, true);
        Date bDate = null;
        Date eDate = null;
        for (BasePlanDetail bpd : basePlanDetailList) {
            if (bDate == null) {
                bDate = bpd.getPlanBDate();
            } else {
                bDate = bDate.after(bpd.getPlanBDate()) ? bpd.getPlanBDate() : bDate;
            }
            if (eDate == null) {
                eDate = bpd.getPlanEDate();
            } else {
                eDate = eDate.after(bpd.getPlanEDate()) ? eDate : bpd.getPlanEDate();
            }
        }
        WeekReport weekReportB = weekReportDao.findFirstByProjectCode(projectCode, Sort.by(Sort.Order.asc("startTime")));
        if (weekReportB != null) {
            bDate = Objects.requireNonNull(bDate).after(weekReportB.getStartTime()) ? weekReportB.getStartTime() : bDate;
        }
        Calendar calendarBDate = Calendar.getInstance();
        calendarBDate.setTime(Objects.requireNonNull(bDate));
        if (calendarBDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendarBDate.set(Calendar.DATE, calendarBDate.get(Calendar.DATE) - 7);
        } else {
            calendarBDate.set(Calendar.DAY_OF_WEEK, 1);
        }
        bDate = calendarBDate.getTime();
        WeekReport weekReportE = weekReportDao.findFirstByProjectCode(projectCode, Sort.by(Sort.Order.desc("startTime")));
        if (weekReportE != null) {
            eDate = eDate.after(weekReportE.getEndTime()) ? eDate : weekReportE.getEndTime();
        }
        Calendar calendarEDate = Calendar.getInstance();
        calendarEDate.setTime(eDate);
        if (calendarEDate.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            calendarEDate.set(Calendar.DAY_OF_WEEK, 1);
            calendarEDate.set(Calendar.DATE, calendarEDate.get(Calendar.DATE) + 7);
        }
        eDate = calendarEDate.getTime();
        Map<String, Object> listMap = new HashMap<>();
        //noinspection MismatchedQueryAndUpdateOfCollection
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Date> dateList = new ArrayList<>();
        List<Double> bpList = new ArrayList<>();
        List<Double> wpList = new ArrayList<>();
        List<Double> bcList = new ArrayList<>();
        List<Double> wcList = new ArrayList<>();
        List<Double> dcpList = new ArrayList<>();
        List<Double> weekPlan = new ArrayList<>();
        List<Double> weekTotal = new ArrayList<>();
        do {
            dateList.add(bDate);
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("date", bDate);
            Double bp = getBp(projectCode, bDate);
            bpList.add(bp);
            resultMap.put("bp", (double) Math.round(bp * 10000) / 10000);
            Double wp;
            if (bpList.size() == 1) {
                wp = bp;
            } else {
                wp = bp - bpList.get(bpList.size() - 2);
            }
            wpList.add(wp);
            resultMap.put("wp", (double) Math.round(wp * 10000) / 10000);
            Double bc = getBc(projectCode, bDate);
            if (bc == 0) {
                bc = bcList.size() < 1 ? 0d : bcList.get(bcList.size() - 1);
            }
            bcList.add(bc);
            resultMap.put("bc", (double) Math.round(bc * 10000) / 10000);
            Double wc;
            if (bcList.size() == 1) {
                wc = bc;
            } else {
                wc = bc - bcList.get(bcList.size() - 2);
            }
            wcList.add(wc);
            resultMap.put("wc", (double) Math.round(wc * 10000) / 10000);
            Double dcp = bc - bp;
            dcpList.add(dcp);
            resultMap.put("dcp", (double) Math.round(dcp * 10000) / 10000);
            weekPlan.add(getWeekPlanProgress(projectCode, bDate));
            weekTotal.add(weekPlan.stream().mapToDouble(Double::doubleValue).sum());
            resultList.add(resultMap);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(bDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 7);
            bDate = calendar.getTime();
        } while (bDate.compareTo(eDate) <= 0);
        listMap.put("date", dateList);
        listMap.put("bp", bpList);
        listMap.put("bc", bcList);
        listMap.put("wp", wpList);
        listMap.put("wc", wcList);
        listMap.put("dcp", dcpList);
        listMap.put("weekPlan", weekPlan);
        listMap.put("weekTotal", weekTotal);
        return listMap;
    }

    private Double getWeekPlanProgress(String projectCode, Date bDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bDate);
        WeekPlan weekPlan = weekPlanDao.findByProjectCodeAndYearAndWeekAndReleased(projectCode, calendar.get(Calendar.YEAR), calendar.get(Calendar.WEEK_OF_YEAR), true);
        if (weekPlan == null) {
            return 0d;
        }
        double result = 0d;
        List<WeekPlanDetail> weekPlanDetailList = weekPlanDetailDao.findByProjectCodeAndYearAndWeek(projectCode, weekPlan.getYear(), weekPlan.getWeek());
        for (WeekPlanDetail wpd : weekPlanDetailList) {
            if (wpd.getWeekPlanQty() == null || wpd.getWeekPlanQty() == 0) {
                continue;
            }
            BasePlanDetail bpd = basePlanDetailDao.getOne(wpd.getItemCode());
            result += wpd.getWeekPlanQty() / bpd.getQty() * bpd.getWeightTotalPercent();
        }
        return (double) Math.round(result * 10000) / 10000;
    }

    @Override
    public Object progressExecution(String projectCode) {
        Sort.Order order1 = Sort.Order.asc("year");
        Sort.Order order2 = Sort.Order.asc("week");
        WeekReport weekReportFirst = weekReportDao.findFirstByProjectCode(projectCode, Sort.by(order1, order2));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, weekReportFirst.getYear());
        calendar.set(Calendar.MONTH, weekReportFirst.getMonth());
        calendar.setWeekDate(weekReportFirst.getYear(), weekReportFirst.getWeek(), Calendar.MONDAY);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startTime = calendar.getTime();
        Sort.Order order3 = Sort.Order.desc("year");
        Sort.Order order4 = Sort.Order.desc("week");
        WeekReport weekReportLast = weekReportDao.findFirstByProjectCode(projectCode, Sort.by(order3, order4));
        calendar.setWeekDate(weekReportLast.getYear(), weekReportLast.getWeek(), Calendar.MONDAY);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 7);
        Date endTime = calendar.getTime();
        WeekReportDto weekReportDto = WeekReportMapper.INSTANCE.entityToDto(weekReportLast);
        weekReportDto.setDetails(WeekReportDetailMapper.INSTANCE.entityToDtoList(weekReportLast.getDetails()));
        //todo
        //weekReportDto.getDetails().forEach(detail -> addWeekReportQtySum(detail, startTime, endTime));
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tempTime = startTime;
        while (tempTime.compareTo(endTime) < 1) {
            dateList.add(sdf.format(tempTime));
            Calendar cal = Calendar.getInstance();
            cal.setTime(tempTime);
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 7);
            tempTime = cal.getTime();
        }
        weekReportDto.setWeekReportQtyTime(dateList);
        return weekReportDto;
    }

    private void addWeekReportQtySum(WeekReportDetailDto weekReportDetailDto, Date startTime, Date endTime) {
        if (weekReportDetailDto.getChildren().size() > 0) {
            weekReportDetailDto.getChildren().forEach(detail -> addWeekReportQtySum(detail, startTime, endTime));
        } else {
            Date tempTime = startTime;
            while (tempTime.compareTo(endTime) < 1) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(tempTime);
                List<Map<String, Double>> list = new ArrayList<>();
                Map<String, Double> map = new HashMap<>();
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(tempTime);
                calendar1.set(Calendar.DATE, calendar.get(Calendar.DATE) - 8);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                map.put(sdf.format(tempTime), weekReportTrackingDao.findByItemCodeAndPeriodDayIsAfterAndPeriodDayBefore(weekReportDetailDto.getItemCode(), calendar1.getTime(), tempTime).stream().mapToDouble(WeekReportTracking::getReportQty).sum());
                list.add(map);
                weekReportDetailDto.setWeekReportQtySum(list);
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 7);
                tempTime = calendar.getTime();
            }
        }
    }

    @Override
    public void newVersionData(String content, String busNo, int version, int type) {
        //todo
        //LoginUser user = UserUtils.getCurrentUser();
        PlanVersion planVersion = new PlanVersion();
        planVersion.setId(UUID.randomUUID().toString());
        planVersion.setBusinessNo(busNo);
        planVersion.setContent(content);
        planVersion.setSerialNumber(String.valueOf(content.hashCode()));
        planVersion.setVersion(version);
        planVersion.setType(type);
        planVersion.setCreatedDate(new Date());
        //todo
        //planVersion.setCreatedByLoginName(user.getUserNo());
        //planVersion.setCreatedByUserName(user.getUserName());
        planVersion.setVersionString(String.valueOf(version));
        versionService.addVersion(planVersion);
    }

    @Override
    public List<BasePlanDetail> exportBasePlan(String projectCode) {
        BasePlan basePlan = getBasePlan(projectCode);
        List<BasePlanDetail> basePlanDetailList = new ArrayList<>();
        for (BasePlanDetail bpd : basePlan.getDetails()) {
            basePlanDetailList.add(bpd);
            if (bpd.getChildren() != null && bpd.getChildren().size() > 0) {
                basePlanDetailList.addAll(listChildrenOfBasePlanDetail(bpd.getChildren()));
            }
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (BasePlanDetail ppd : basePlanDetailList) {
            if (ppd.getPlanBDate() != null)
                ppd.setPlanBDateStr(sdf.format(ppd.getPlanBDate()));
            if (ppd.getPlanEDate() != null)
                ppd.setPlanEDateStr(sdf.format(ppd.getPlanEDate()));
            if (ppd.getWeightPercent() != null)
                ppd.setWeightPercentStr(nf.format(ppd.getWeightPercent()));
            if (ppd.getWeightTotalPercent() != null)
                ppd.setWeightTotalPercentStr(nf.format(ppd.getWeightTotalPercent()));
        }
        return basePlanDetailList;
    }

    private List<BasePlanDetail> listChildrenOfBasePlanDetail(List<BasePlanDetail> basePlanDetailList) {
        List<BasePlanDetail> resultList = new ArrayList<>();
        for (BasePlanDetail bpd : basePlanDetailList) {
            resultList.add(bpd);
            if (bpd.getChildren() != null && bpd.getChildren().size() > 0) {
                resultList.addAll(listChildrenOfBasePlanDetail(bpd.getChildren()));
            }
        }
        return resultList;
    }
}
