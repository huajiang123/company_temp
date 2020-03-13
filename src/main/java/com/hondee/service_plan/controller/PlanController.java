package com.hondee.service_plan.controller;

import com.alibaba.fastjson.JSON;
import com.hondee.common.exception.HdException;
import com.hondee.service_plan.dao.*;
import com.hondee.service_plan.domain.*;
import com.hondee.service_plan.dto.*;
import com.hondee.service_plan.mapper.*;
import com.hondee.service_plan.service.IPlanService;
import com.hondee.service_plan.service.IPlanVersionService;
import com.hondee.service_plan.utils.CalendarUtils;
import com.hondee.service_plan.utils.MergeUtil;
import com.hondee.service_plan.utils.PageUtils;
import com.hondee.service_plan.utils.StringUtils;
import com.hondee.service_plan.utils.poiutils.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Api(tags = "进度计划")
@RestController
@RequestMapping("/plan")
public class PlanController {
    private final BasePlanDao basePlanDao;
    private final BasePlanDetailDraftDao basePlanDetailDraftDao;
    private final MonthPlanDao monthPlanDao;
    private final MonthPlanDetailDao monthPlanDetailDao;
    //todo
    //private final PpdQuoteDao ppdQuoteDao;
    private final QuarterPlanDao quarterPlanDao;
    private final QuarterPlanDetailDao quarterPlanDetailDao;
    //todo
    //private final TaskDao taskDao;
    private final WeekPlanDao weekPlanDao;
    private final WeekPlanDetailDao weekPlanDetailDao;
    private final WeekReportDao weekReportDao;
    private final WeekReportDetailDao weekReportDetailDao;
    private final WeekReportTrackingDao weekReportTrackingDao;
    private final YearPlanDao yearPlanDao;
    private final YearPlanDetailDao yearPlanDetailDao;
    //todo
    //private final IFileService fileService;
    private final IPlanService planService;
    private final IPlanVersionService versionService;
    //todo
    //private final IUserService userService;

    @Autowired
    public PlanController(BasePlanDao basePlanDao, BasePlanDetailDraftDao basePlanDetailDraftDao, MonthPlanDao monthPlanDao, MonthPlanDetailDao monthPlanDetailDao, PpdQuoteDao ppdQuoteDao, QuarterPlanDao quarterPlanDao, QuarterPlanDetailDao quarterPlanDetailDao, TaskDao taskDao, WeekPlanDao weekPlanDao, WeekPlanDetailDao weekPlanDetailDao, WeekReportDao weekReportDao, WeekReportDetailDao weekReportDetailDao, WeekReportTrackingDao weekReportTrackingDao, YearPlanDao yearPlanDao, YearPlanDetailDao yearPlanDetailDao, IFileService fileService, IPlanService planService, IPlanVersionService versionService, IUserService userService) {
        this.basePlanDao = basePlanDao;
        this.basePlanDetailDraftDao = basePlanDetailDraftDao;
        this.monthPlanDao = monthPlanDao;
        this.monthPlanDetailDao = monthPlanDetailDao;
        // TODO: 2020/3/13
        //this.ppdQuoteDao = ppdQuoteDao;
        this.quarterPlanDao = quarterPlanDao;
        this.quarterPlanDetailDao = quarterPlanDetailDao;
        //todo
        //this.taskDao = taskDao;
        this.weekPlanDao = weekPlanDao;
        this.weekPlanDetailDao = weekPlanDetailDao;
        this.weekReportDao = weekReportDao;
        this.weekReportDetailDao = weekReportDetailDao;
        this.weekReportTrackingDao = weekReportTrackingDao;
        this.yearPlanDao = yearPlanDao;
        this.yearPlanDetailDao = yearPlanDetailDao;
        //todo
        //this.fileService = fileService;
        this.planService = planService;
        this.versionService = versionService;
        //todo
        //this.userService = userService;
    }

    @ApiOperation("获取基准计划")
    @GetMapping("/getBasePlan/{projectCode}")
    public BasePlanDto getBasePlan(@PathVariable String projectCode) {
        BasePlan basePlan = planService.getBasePlan(projectCode);
        BasePlanDto basePlanDto = BasePlanMapper.INSTANCE.entityToDto(basePlan).setDetails(BasePlanDetailMapper.INSTANCE.entityToDtoList(basePlan.getDetails()));
        List<PlanVersion> planVersionList = versionService.getList(basePlan.getPlanCode(), 1);
        List<Integer> versions = new ArrayList<>();
        planVersionList.forEach(planVersion -> versions.add(planVersion.getVersion()));
        basePlanDto.setVersions(versions);
        return basePlanDto;
    }

    @ApiOperation(value = "获取基准计划指定版本")
    @GetMapping("/getBasePlanVersion/{projectCode}/{version}")
    public BasePlanDto getBasePlanVersion(@PathVariable String projectCode, @PathVariable int version) {
        BasePlan basePlan = planService.getBasePlan(projectCode);
        List<PlanVersion> planVersionList = versionService.getList(basePlan.getPlanCode(), 1);
        PlanVersion planVersion = null;
        for (PlanVersion pv : planVersionList) {
            if (version == pv.getVersion()) {
                planVersion = pv;
            }
        }
        if (planVersion == null) {
            throw new HdException("999", "该版本信息不存在！");
        }
        BasePlanDto basePlanDto = JSON.parseObject(planVersion.getContent(), BasePlanDto.class);
        List<Integer> versions = new ArrayList<>();
        planVersionList.forEach(pv -> versions.add(pv.getVersion()));
        basePlanDto.setVersions(versions);
        return basePlanDto;
    }

    @ApiOperation(value = "导出基准计划")
    @GetMapping("/exportBasePlan/{projectCode}")
    public void exportBasePlan(HttpServletResponse response, @PathVariable String projectCode) throws IOException {
        List<BasePlanDetail> basePlanDetailList = planService.exportBasePlan(projectCode);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

        //构造基础信息
        Map<String, String> map = new HashMap<>();
        map.put("title", "基准计划列表");
        map.put("total", basePlanDetailList.size() + " 条");
        map.put("date", sdf.format(new Date()));

        //文件存储在服务器的位置
        String filePath = "D:/" + map.get("title") + ".xls";
        ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, "template.xls", new FileOutputStream(filePath),
                basePlanDetailList, BasePlanDetail.class, true);

        //下载
        ExportUtils.WriteOutWbs(filePath, map, response);
    }

    @ApiOperation("获取清单项")
    @GetMapping("/getBasePlanDetail/{itemCode}")
    public BasePlanDetailDto getBasePlanDetail(@PathVariable String itemCode) {
        BasePlanDetail basePlanDetail = planService.getBasePlanDetail(itemCode);
        BasePlanDetailDto basePlanDetailDto = BasePlanDetailMapper.INSTANCE.entityToDto(basePlanDetail);
        BasePlanDto basePlanDto = BasePlanMapper.INSTANCE.entityToDto(basePlanDetail.getBasePlan());
        basePlanDetailDto.setBasePlan(basePlanDto == null ? null : basePlanDto.setDetails(null));
        BasePlanDetailDto parent = BasePlanDetailMapper.INSTANCE.entityToDto(basePlanDetail.getParent());
        basePlanDetailDto.setParent(parent == null ? null : parent.setChildren(null));
        basePlanDetailDto.setFileIdList(fileService.listFileId(itemCode));
        List<PpdQuote> ppdQuoteList = ppdQuoteDao.findByItemCode(itemCode);
        List<String> taskCodeList = new ArrayList<>();
        ppdQuoteList.forEach(ppdQuote -> taskCodeList.add(ppdQuote.getTaskCode()));
        List<Task> taskList = taskDao.findByTaskCodeIn(taskCodeList);
        taskList.forEach(task -> task.setAssignedToUser(userService.getPrjUserInfoView(task.getProjectCode(), task.getAssignedTo())));
        basePlanDetailDto.setTaskList(taskList);
        return basePlanDetailDto.setChildren(null);
    }

    @ApiOperation("获取基准计划草稿")
    @GetMapping("/getBasePlanDraft/{projectCode}")
    public BasePlanDto getBasePlanDraft(@PathVariable String projectCode) {
        BasePlan basePlan = planService.getBasePlan(projectCode);
        return BasePlanMapper.INSTANCE.entityToDto(basePlan).setDrafts(BasePlanDetailMapper.INSTANCE.draftEntityToDtoList(basePlan.getDrafts()));
    }

    @ApiOperation("获取清单项草稿")
    @GetMapping("/getBasePlanDetailDraft/{itemCode}")
    public BasePlanDetailDto getBasePlanDetailDraft(@PathVariable String itemCode) {
        BasePlanDetailDraft basePlanDetailDraft = planService.getBasePlanDetailDraft(itemCode);
        BasePlanDetailDto basePlanDetailDto = BasePlanDetailMapper.INSTANCE.draftEntityToDto(basePlanDetailDraft);
        BasePlanDto basePlanDto = BasePlanMapper.INSTANCE.entityToDto(basePlanDetailDraft.getBasePlan());
        basePlanDetailDto.setBasePlan(basePlanDto == null ? null : basePlanDto.setDetails(null));
        BasePlanDetailDto parent = BasePlanDetailMapper.INSTANCE.draftEntityToDto(basePlanDetailDraft.getParent());
        basePlanDetailDto.setParent(parent == null ? null : parent.setChildren(null));
        basePlanDetailDto.setFileIdList(fileService.listFileId(itemCode));
        return basePlanDetailDto.setChildren(null);
    }

    @ApiOperation("新增清单项草稿")
    @PostMapping("/newBasePlanDetailDraft")
    public Map<String, Boolean> newBasePlanDetailDraft(@RequestBody BasePlanDetailDto basePlanDetailDto) {
        BasePlanDetailDraft basePlanDetailDraft = BasePlanDetailMapper.INSTANCE.dtoToDraftEntity(basePlanDetailDto);
        basePlanDetailDraft.setItemCode(UUID.randomUUID().toString());
        boolean flag = basePlanDetailDto.getParent() != null && !StringUtils.isNullOrEmpty(basePlanDetailDto.getParent().getItemCode());
        if (flag) {
            BasePlanDetailDraft parent = basePlanDetailDraftDao.getOne(basePlanDetailDto.getParent().getItemCode());
            basePlanDetailDraft.setParent(parent);
            basePlanDetailDraft.setBasePlan(null);
            basePlanDetailDraft.setProjectCode(parent.getProjectCode());
        } else {
            BasePlan basePlan = basePlanDao.getOne(basePlanDetailDto.getBasePlan().getPlanCode());
            basePlanDetailDraft.setBasePlan(basePlan);
            basePlanDetailDraft.setParent(null);
            basePlanDetailDraft.setProjectCode(basePlan.getProject().getProjectCode());
        }
        basePlanDetailDraft.setValidity(true);
        basePlanDetailDraft.setReleased(false);
        EntityUtils.addCreateInfo(basePlanDetailDraft);
        planService.initBasePlanDetailDraftSequence(basePlanDetailDraft);
        planService.saveBasePlanDetailDraft(basePlanDetailDraft);
        if (flag) {
            BasePlanDetailDraft parent = basePlanDetailDraftDao.findById(basePlanDetailDraft.getParent().getItemCode()).orElseThrow(() -> new HdException("999", "新增清单项时更新权重百分比失败！"));
            planService.updateBasePlanDetailDraftPercent(parent.getChildren(), parent);
        } else {
            BasePlan basePlan = basePlanDao.findById(basePlanDetailDraft.getBasePlan().getPlanCode()).orElseThrow(() -> new HdException("999", "新增清单项时更新权重百分比失败！"));
            planService.updateBasePlanDetailDraftPercent(basePlan.getDrafts(), null);
        }
        fileService.insert(basePlanDetailDraft.getItemCode(), basePlanDetailDto.getFileIdList());
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("编辑清单项草稿")
    @PostMapping("/editBasePlanDetailDraft")
    public Map<String, Boolean> editBasePlanDetailDraft(@RequestBody BasePlanDetailDto basePlanDetailDto) {
        BasePlanDetailDraft basePlanDetailDraft = BasePlanDetailMapper.INSTANCE.dtoToDraftEntity(basePlanDetailDto);
        boolean flag = basePlanDetailDto.getParent() != null && !StringUtils.isNullOrEmpty(basePlanDetailDto.getParent().getItemCode());
        if (flag) {
            basePlanDetailDraft.setBasePlan(null);
            basePlanDetailDraft.setParent(basePlanDetailDraftDao.getOne(basePlanDetailDto.getParent().getItemCode()));
        } else {
            basePlanDetailDraft.setParent(null);
            basePlanDetailDraft.setBasePlan(basePlanDao.getOne(basePlanDetailDto.getBasePlan().getPlanCode()));
        }
        BasePlanDetailDraft basePlanDetailDraftDB = basePlanDetailDraftDao.findById(basePlanDetailDraft.getItemCode()).orElseThrow(() -> new HdException("999", "清单项不存在！"));
        MergeUtil.mergeObject(basePlanDetailDraftDB, basePlanDetailDraft);
        if (basePlanDetailDraft.getBasePlan() == null) {
            basePlanDetailDraftDB.setBasePlan(null);
        }
        if (basePlanDetailDraft.getParent() == null) {
            basePlanDetailDraftDB.setParent(null);
        }
        basePlanDetailDraftDB.setReleased(false);
        EntityUtils.addUpdateInfo(basePlanDetailDraftDB);
        planService.saveBasePlanDetailDraft(basePlanDetailDraftDB);
        if (flag) {
            BasePlanDetailDraft parent = basePlanDetailDraftDao.findById(basePlanDetailDraft.getParent().getItemCode()).orElseThrow(() -> new HdException("999", "编辑清单项时更新权重百分比失败！"));
            planService.updateBasePlanDetailDraftPercent(parent.getChildren(), parent);
        } else {
            BasePlan basePlan = basePlanDao.findById(basePlanDetailDraft.getBasePlan().getPlanCode()).orElseThrow(() -> new HdException("999", "编辑清单项时更新权重百分比失败！"));
            planService.updateBasePlanDetailDraftPercent(basePlan.getDrafts(), null);
        }
        fileService.updateQuote(basePlanDetailDraft.getItemCode(), basePlanDetailDto.getFileIdList());
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("删除清单项草稿")
    @DeleteMapping("/deleteBasePlanDetailDraft/{itemCode}")
    @Transactional
    public Map<String, Boolean> deleteBasePlanDetailDraft(@PathVariable String itemCode) {
        BasePlanDetailDraft basePlanDetailDraft = basePlanDetailDraftDao.findById(itemCode).orElseThrow(() -> new HdException("999", "清单项不存在！"));
        if (basePlanDetailDraft.getChildren().size() > 0 && basePlanDetailDraft.getChildren().stream().anyMatch(BaseEntity::getValidity)) {
            throw new HdException("999", "存在子项不能删除！");
        }
        basePlanDetailDraft.setWeightPercent(0d);
        basePlanDetailDraft.setWeightTotalPercent(0d);
        basePlanDetailDraft.setReleased(false);
        basePlanDetailDraft.setValidity(false);
        EntityUtils.addUpdateInfo(basePlanDetailDraft);
        planService.saveBasePlanDetailDraft(basePlanDetailDraft);
        if (basePlanDetailDraft.getParent() != null) {
            BasePlanDetailDraft parent = basePlanDetailDraftDao.findById(basePlanDetailDraft.getParent().getItemCode()).orElseThrow(() -> new HdException("999", "删除清单项时更新权重百分比失败！"));
            planService.updateBasePlanDetailDraftPercent(parent.getChildren(), parent);
        } else {
            BasePlan basePlan = basePlanDao.findById(basePlanDetailDraft.getBasePlan().getPlanCode()).orElseThrow(() -> new HdException("999", "删除清单项时更新权重百分比失败！"));
            planService.updateBasePlanDetailDraftPercent(basePlan.getDrafts(), null);
        }
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("发布基准计划")
    @PostMapping("/releaseBasePlan/{projectCode}")
    public Map<String, Boolean> releaseBasePlan(@PathVariable String projectCode) {
        planService.releaseBasePlan(projectCode);
        BasePlan basePlan = planService.getBasePlan(projectCode);
        BasePlanDto basePlanDto = BasePlanMapper.INSTANCE.entityToDto(basePlan).setDetails(BasePlanDetailMapper.INSTANCE.entityToDtoList(basePlan.getDetails()));
        String versionData = JSON.toJSONString(basePlanDto);
        planService.newVersionData(versionData, basePlan.getPlanCode(), basePlan.getVersion(), 1);
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("年度计划列表")
    @GetMapping("/listYearPlan/{projectCode}")
    public Page<YearPlanDto> listYearPlan(@PathVariable String projectCode) {
        Pageable pageable = PageUtils.getPageInfo();
        BasePlan basePlan = planService.getBasePlan(projectCode);
        if (!basePlan.getReleased()) {
            throw new HdException("999", "基准计划未发布！");
        }
        Page<YearPlan> page = yearPlanDao.findByProjectCodeOrderByYearDesc(projectCode, pageable);
        return new PageImpl<>(YearPlanMapper.INSTANCE.entityToDtoList(page.getContent()), pageable, page.getTotalElements());
    }

    @ApiOperation("新增年度计划")
    @PostMapping("/newYearPlan")
    public Map<String, Object> newYearPlan(@RequestBody YearPlanDto dto) {
        YearPlan yearPlanLast = yearPlanDao.findFirstByProjectCodeOrderByYearDesc(dto.getProjectCode());
        if (yearPlanLast != null && !yearPlanLast.getReleased()) {
            throw new HdException("999", "存在未发布的年度计划！");
        }
        YearPlan yearPlan = yearPlanDao.findByProjectCodeAndYear(dto.getProjectCode(), dto.getYear());
        if (yearPlan != null) {
            throw new HdException("999", "该年度计划已存在！");
        }
        yearPlan = planService.newYearPlan(dto.getProjectCode(), dto.getYear());
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", true);
        result.put("yearPlanCode", yearPlan.getYearPlanCode());
        return result;
    }

    @ApiOperation("删除年度计划")
    @DeleteMapping("/deleteYearPlan/{yearPlanCode}")
    @Transactional
    public Map<String, Boolean> deleteYearPlan(@PathVariable String yearPlanCode) {
        YearPlan yearPlan = yearPlanDao.findById(yearPlanCode).orElseThrow(() -> new HdException("999", "该年度计划不存在！"));
        if (yearPlan.getReleased()) {
            throw new HdException("999", "已发布的年度计划无法删除！");
        }
        yearPlanDetailDao.deleteByProjectCodeAndYear(yearPlan.getProjectCode(), yearPlan.getYear());
        yearPlanDao.delete(yearPlan);
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("发布/撤销年度计划(on/off)")
    @PostMapping("/releaseYearPlan/{yearPlanCode}/{label}")
    public Map<String, Boolean> releaseYearPlan(@PathVariable String yearPlanCode, @PathVariable String label) {
        YearPlan yearPlan = yearPlanDao.findById(yearPlanCode).orElseThrow(() -> new HdException("999", "该年度计划不存在！"));
        switch (label) {
            case "on":
                yearPlan.setReleased(true);
                break;
            case "off":
                yearPlan.setReleased(false);
                break;
            default:
        }
        yearPlanDao.save(yearPlan);
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("获取年度计划")
    @GetMapping("/getYearPlan/{yearPlanCode}")
    public YearPlanDto getYearPlan(@PathVariable String yearPlanCode) {
        YearPlan yearPlan = planService.getYearPlan(yearPlanCode);
        YearPlanDto yearPlanDto = YearPlanMapper.INSTANCE.entityToDto(yearPlan);
        yearPlanDto.setDetails(YearPlanDetailMapper.INSTANCE.entityToDtoList(yearPlan.getDetails()));
        return yearPlanDto;
    }

    @ApiOperation("获取年度计划清单项")
    @GetMapping("/getYearPlanDetail/{yearPlanDetailCode}")
    public YearPlanDetailDto getYearPlanDetail(@PathVariable String yearPlanDetailCode) {
        YearPlanDetail yearPlanDetail = yearPlanDetailDao.findById(yearPlanDetailCode).orElseThrow(() -> new HdException("999", "清单项不存在！"));
        YearPlanDetailDto yearPlanDetailDto = YearPlanDetailMapper.INSTANCE.entityToDto(yearPlanDetail);
        YearPlanDto yearPlanDto = YearPlanMapper.INSTANCE.entityToDto(yearPlanDetail.getYearPlan());
        yearPlanDetailDto.setYearPlan(yearPlanDto == null ? null : yearPlanDto.setDetails(null));
        YearPlanDetailDto parent = YearPlanDetailMapper.INSTANCE.entityToDto(yearPlanDetail.getParent());
        yearPlanDetailDto.setParent(parent == null ? null : parent.setChildren(null));
        yearPlanDetailDto.setFileIdList(fileService.listFileId(yearPlanDetail.getItemCode()));
        List<PpdQuote> ppdQuoteList = ppdQuoteDao.findByItemCode(yearPlanDetailDto.getItemCode());
        List<String> taskCodeList = new ArrayList<>();
        ppdQuoteList.forEach(ppdQuote -> taskCodeList.add(ppdQuote.getTaskCode()));
        List<Task> taskList = taskDao.findByTaskCodeIn(taskCodeList);
        taskList.forEach(task -> task.setAssignedToUser(userService.getPrjUserInfoView(task.getProjectCode(), task.getAssignedTo())));
        yearPlanDetailDto.setTaskList(taskList);
        return yearPlanDetailDto.setChildren(null);
    }

    @ApiOperation("编辑年度计划清单项")
    @PostMapping("/editYearPlanDetail")
    public Map<String, Boolean> editYearPlanDetail(@RequestBody List<YearPlanDetailDto> yearPlanDetailDtoList) {
        yearPlanDetailDtoList.forEach(dto -> {
            YearPlanDetail entity = yearPlanDetailDao.findById(dto.getYearPlanDetailCode()).orElseThrow(() -> new HdException("999", "清单项不存在！"));
            entity.setYearPlanQty(dto.getYearPlanQty());
            entity.setYearPlanManHour(dto.getYearPlanManHour());
            yearPlanDetailDao.save(entity);
        });
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("季度计划列表")
    @GetMapping("/listQuarterPlan/{projectCode}")
    public Page<QuarterPlanDto> listQuarterPlan(@PathVariable String projectCode) {
        BasePlan basePlan = planService.getBasePlan(projectCode);
        if (!basePlan.getReleased()) {
            throw new HdException("999", "基准计划未发布！");
        }
        Sort.Order order1 = Sort.Order.desc("year");
        Sort.Order order2 = Sort.Order.desc("quarter");
        Pageable pageable = PageUtils.getPageInfo(Sort.by(order1, order2));
        Page<QuarterPlan> page = quarterPlanDao.findByProjectCode(projectCode, pageable);
        return new PageImpl<>(QuarterPlanMapper.INSTANCE.entityToDtoList(page.getContent()), pageable, page.getTotalElements());
    }

    @ApiOperation("新增季度计划")
    @PostMapping("/newQuarterPlan")
    public Map<String, Object> newQuarterPlan(@RequestBody QuarterPlanDto dto) {
        Sort.Order order1 = Sort.Order.desc("year");
        Sort.Order order2 = Sort.Order.desc("quarter");
        QuarterPlan quarterPlanLast = quarterPlanDao.findFirstByProjectCode(dto.getProjectCode(), Sort.by(order1, order2));
        if (quarterPlanLast != null && !quarterPlanLast.getReleased()) {
            throw new HdException("999", "存在未发布的季度计划！");
        }
        QuarterPlan quarterPlan = quarterPlanDao.findByProjectCodeAndYearAndQuarter(dto.getProjectCode(), dto.getYear(), dto.getQuarter());
        if (quarterPlan != null) {
            throw new HdException("999", "该季度计划已存在！");
        }
        quarterPlan = planService.newQuarterPlan(dto.getProjectCode(), dto.getYear(), dto.getQuarter());
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", true);
        result.put("quarterPlanCode", quarterPlan.getQuarterPlanCode());
        return result;
    }

    @ApiOperation("删除季度计划")
    @DeleteMapping("/deleteQuarterPlan/{quarterPlanCode}")
    @Transactional
    public Map<String, Boolean> deleteQuarterPlan(@PathVariable String quarterPlanCode) {
        QuarterPlan quarterPlan = quarterPlanDao.findById(quarterPlanCode).orElseThrow(() -> new HdException("999", "该季度计划不存在！"));
        if (quarterPlan.getReleased()) {
            throw new HdException("999", "已发布的季度计划无法删除！");
        }
        quarterPlanDetailDao.deleteByProjectCodeAndYearAndQuarter(quarterPlan.getProjectCode(), quarterPlan.getYear(), quarterPlan.getQuarter());
        quarterPlanDao.delete(quarterPlan);
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("发布/撤销季度计划(on/off)")
    @PostMapping("/releaseQuarterPlan/{quarterPlanCode}/{label}")
    public Map<String, Boolean> releaseQuarterPlan(@PathVariable String quarterPlanCode, @PathVariable String label) {
        QuarterPlan quarterPlan = quarterPlanDao.findById(quarterPlanCode).orElseThrow(() -> new HdException("999", "该季度计划不存在！"));
        switch (label) {
            case "on":
                quarterPlan.setReleased(true);
                break;
            case "off":
                quarterPlan.setReleased(false);
                break;
            default:
        }
        quarterPlanDao.save(quarterPlan);
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("获取季度计划")
    @GetMapping("/getQuarterPlan/{quarterPlanCode}")
    public QuarterPlanDto getQuarterPlan(@PathVariable String quarterPlanCode) {
        QuarterPlan quarterPlan = planService.getQuarterPlan(quarterPlanCode);
        QuarterPlanDto quarterPlanDto = QuarterPlanMapper.INSTANCE.entityToDto(quarterPlan);
        quarterPlanDto.setDetails(QuarterPlanDetailMapper.INSTANCE.entityToDtoList(quarterPlan.getDetails()));
        return quarterPlanDto;
    }

    @ApiOperation("获取季度计划清单项")
    @GetMapping("/getQuarterPlanDetail/{quarterPlanDetailCode}")
    public QuarterPlanDetailDto getQuarterPlanDetail(@PathVariable String quarterPlanDetailCode) {
        QuarterPlanDetail quarterPlanDetail = quarterPlanDetailDao.findById(quarterPlanDetailCode).orElseThrow(() -> new HdException("999", "清单项不存在！"));
        QuarterPlanDetailDto quarterPlanDetailDto = QuarterPlanDetailMapper.INSTANCE.entityToDto(quarterPlanDetail);
        QuarterPlanDto quarterPlanDto = QuarterPlanMapper.INSTANCE.entityToDto(quarterPlanDetail.getQuarterPlan());
        quarterPlanDetailDto.setQuarterPlan(quarterPlanDto == null ? null : quarterPlanDto.setDetails(null));
        QuarterPlanDetailDto parent = QuarterPlanDetailMapper.INSTANCE.entityToDto(quarterPlanDetail.getParent());
        quarterPlanDetailDto.setParent(parent == null ? null : parent.setChildren(null));
        quarterPlanDetailDto.setFileIdList(fileService.listFileId(quarterPlanDetail.getItemCode()));
        List<PpdQuote> ppdQuoteList = ppdQuoteDao.findByItemCode(quarterPlanDetailDto.getItemCode());
        List<String> taskCodeList = new ArrayList<>();
        ppdQuoteList.forEach(ppdQuote -> taskCodeList.add(ppdQuote.getTaskCode()));
        List<Task> taskList = taskDao.findByTaskCodeIn(taskCodeList);
        taskList.forEach(task -> task.setAssignedToUser(userService.getPrjUserInfoView(task.getProjectCode(), task.getAssignedTo())));
        quarterPlanDetailDto.setTaskList(taskList);
        return quarterPlanDetailDto.setChildren(null);
    }

    @ApiOperation("编辑季度计划清单项")
    @PostMapping("/editQuarterPlanDetail")
    public Map<String, Boolean> editQuarterPlanDetail(@RequestBody List<QuarterPlanDetailDto> quarterPlanDetailDtoList) {
        quarterPlanDetailDtoList.forEach(dto -> {
            QuarterPlanDetail entity = quarterPlanDetailDao.findById(dto.getQuarterPlanDetailCode()).orElseThrow(() -> new HdException("999", "清单项不存在！"));
            entity.setQuarterPlanQty(dto.getQuarterPlanQty());
            entity.setQuarterPlanManHour(dto.getQuarterPlanManHour());
            quarterPlanDetailDao.save(entity);
        });
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("月计划列表")
    @GetMapping("/listMonthPlan/{projectCode}")
    public Page<MonthPlanDto> listMonthPlan(@PathVariable String projectCode) {
        BasePlan basePlan = planService.getBasePlan(projectCode);
        if (!basePlan.getReleased()) {
            throw new HdException("999", "基准计划未发布！");
        }
        Sort.Order order1 = Sort.Order.desc("year");
        Sort.Order order2 = Sort.Order.desc("month");
        Pageable pageable = PageUtils.getPageInfo(Sort.by(order1, order2));
        Page<MonthPlan> page = monthPlanDao.findByProjectCode(projectCode, pageable);
        return new PageImpl<>(MonthPlanMapper.INSTANCE.entityToDtoList(page.getContent()), pageable, page.getTotalElements());
    }

    @ApiOperation("新增月计划")
    @PostMapping("/newMonthPlan")
    public Map<String, Object> newMonthPlan(@RequestBody MonthPlanDto dto) {
        Sort.Order order1 = Sort.Order.desc("year");
        Sort.Order order2 = Sort.Order.desc("month");
        MonthPlan monthPlanLast = monthPlanDao.findFirstByProjectCode(dto.getProjectCode(), Sort.by(order1, order2));
        if (monthPlanLast != null && !monthPlanLast.getReleased()) {
            throw new HdException("999", "存在未发布的月度计划！");
        }
        MonthPlan monthPlan = monthPlanDao.findByProjectCodeAndYearAndMonth(dto.getProjectCode(), dto.getYear(), dto.getMonth());
        if (monthPlan != null) {
            throw new HdException("999", "该月度计划已存在！");
        }
        monthPlan = planService.newMonthPlan(dto.getProjectCode(), dto.getYear(), CalendarUtils.getQuarter(dto.getMonth()), dto.getMonth());
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", true);
        result.put("monthPlanCode", monthPlan.getMonthPlanCode());
        return result;
    }

    @ApiOperation("删除月计划")
    @DeleteMapping("/deleteMonthPlan/{monthPlanCode}")
    @Transactional
    public Map<String, Boolean> deleteMonthPlan(@PathVariable String monthPlanCode) {
        MonthPlan monthPlan = monthPlanDao.findById(monthPlanCode).orElseThrow(() -> new HdException("999", "该月度计划不存在！"));
        if (monthPlan.getReleased()) {
            throw new HdException("999", "已发布的月度计划无法删除！");
        }
        monthPlanDetailDao.deleteByProjectCodeAndYearAndMonth(monthPlan.getProjectCode(), monthPlan.getYear(), monthPlan.getMonth());
        monthPlanDao.delete(monthPlan);
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("发布/撤销月度计划(on/off)")
    @PostMapping("/releaseMonthPlan/{monthPlanCode}/{label}")
    public Map<String, Boolean> releaseMonthPlan(@PathVariable String monthPlanCode, @PathVariable String label) {
        MonthPlan monthPlan = monthPlanDao.findById(monthPlanCode).orElseThrow(() -> new HdException("999", "该月度计划不存在！"));
        switch (label) {
            case "on":
                monthPlan.setReleased(true);
                break;
            case "off":
                monthPlan.setReleased(false);
                break;
            default:
        }
        monthPlanDao.save(monthPlan);
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("获取月度计划")
    @GetMapping("/getMonthPlan/{monthPlanCode}")
    public MonthPlanDto getMonthPlan(@PathVariable String monthPlanCode) {
        MonthPlan monthPlan = planService.getMonthPlan(monthPlanCode);
        MonthPlanDto monthPlanDto = MonthPlanMapper.INSTANCE.entityToDto(monthPlan);
        monthPlanDto.setDetails(MonthPlanDetailMapper.INSTANCE.entityToDtoList(monthPlan.getDetails()));
        return monthPlanDto;
    }

    @ApiOperation("获取月度计划清单项")
    @GetMapping("/getMonthPlanDetail/{monthPlanDetailCode}")
    public MonthPlanDetailDto getMonthPlanDetail(@PathVariable String monthPlanDetailCode) {
        MonthPlanDetail monthPlanDetail = monthPlanDetailDao.findById(monthPlanDetailCode).orElseThrow(() -> new HdException("999", "清单项不存在！"));
        MonthPlanDetailDto monthPlanDetailDto = MonthPlanDetailMapper.INSTANCE.entityToDto(monthPlanDetail);
        MonthPlanDto monthPlanDto = MonthPlanMapper.INSTANCE.entityToDto(monthPlanDetail.getMonthPlan());
        monthPlanDetailDto.setMonthPlan(monthPlanDto == null ? null : monthPlanDto.setDetails(null));
        MonthPlanDetailDto parent = MonthPlanDetailMapper.INSTANCE.entityToDto(monthPlanDetail.getParent());
        monthPlanDetailDto.setParent(parent == null ? null : parent.setChildren(null));
        monthPlanDetailDto.setFileIdList(fileService.listFileId(monthPlanDetail.getItemCode()));
        List<PpdQuote> ppdQuoteList = ppdQuoteDao.findByItemCode(monthPlanDetailDto.getItemCode());
        List<String> taskCodeList = new ArrayList<>();
        ppdQuoteList.forEach(ppdQuote -> taskCodeList.add(ppdQuote.getTaskCode()));
        List<Task> taskList = taskDao.findByTaskCodeIn(taskCodeList);
        taskList.forEach(task -> task.setAssignedToUser(userService.getPrjUserInfoView(task.getProjectCode(), task.getAssignedTo())));
        monthPlanDetailDto.setTaskList(taskList);
        return monthPlanDetailDto.setChildren(null);
    }

    @ApiOperation("编辑月度计划清单项")
    @PostMapping("/editMonthPlanDetail")
    public Map<String, Boolean> editMonthPlanDetail(@RequestBody List<MonthPlanDetailDto> monthPlanDetailDtoList) {
        monthPlanDetailDtoList.forEach(dto -> {
            MonthPlanDetail entity = monthPlanDetailDao.findById(dto.getMonthPlanDetailCode()).orElseThrow(() -> new HdException("999", "清单项不存在！"));
            entity.setMonthPlanQty(dto.getMonthPlanQty());
            entity.setMonthPlanManHour(dto.getMonthPlanManHour());
            monthPlanDetailDao.save(entity);
        });
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("周计划列表")
    @GetMapping("/listWeekPlan/{projectCode}")
    public List<Map<String, Object>> listWeekPlan(@PathVariable String projectCode) {
        BasePlan basePlan = planService.getBasePlan(projectCode);
        if (!basePlan.getReleased()) {
            throw new HdException("999", "基准计划未发布！");
        }
        Sort.Order order1 = Sort.Order.desc("year");
        Sort.Order order2 = Sort.Order.desc("week");
        Pageable pageable = PageUtils.getPageInfo(Sort.by(order1, order2));
        Page<WeekPlan> page = weekPlanDao.findByProjectCode(projectCode, pageable);
        List<WeekPlanDto> weekPlanDtoList = WeekPlanMapper.INSTANCE.entityToDtoList(page.getContent());
        List<Map<String, Object>> yearList = new ArrayList<>();
        weekPlanDtoList.forEach(dto -> {
            AtomicBoolean flagYear = new AtomicBoolean(false);
            yearList.forEach(yearMap -> {
                if (dto.getYear().equals(yearMap.get("itemName"))) {
                    flagYear.set(true);
                    //noinspection unchecked
                    List<Map<String, Object>> monthList = (List<Map<String, Object>>) yearMap.get("children");
                    AtomicBoolean flagMonth = new AtomicBoolean(false);
                    monthList.forEach(monthMap -> {
                        if (dto.getMonth().equals(monthMap.get("itemName"))) {
                            flagMonth.set(true);
                            List<WeekPlanDto> weekList = JSON.parseArray(JSON.toJSONString(monthMap.get("children")), WeekPlanDto.class);
                            weekList.add(dto);
                            monthMap.replace("children", weekList);
                        }
                    });
                    if (!flagMonth.get()) {
                        Map<String, Object> monthMap = new HashMap<>();
                        List<WeekPlanDto> weekList = new ArrayList<>();
                        weekList.add(dto);
                        monthMap.put("children", weekList);
                        monthMap.put("itemName", dto.getMonth());
                        monthMap.put("weekPlanCode", dto.getMonth());
                        monthList.add(monthMap);
                    }
                }
            });
            if (!flagYear.get()) {
                Map<String, Object> yearMap = new HashMap<>();
                List<Map<String, Object>> monthList = new ArrayList<>();
                Map<String, Object> monthMap = new HashMap<>();
                List<WeekPlanDto> weekList = new ArrayList<>();
                weekList.add(dto);
                monthMap.put("children", weekList);
                monthMap.put("itemName", dto.getMonth());
                monthMap.put("weekPlanCode", dto.getMonth());
                monthList.add(monthMap);
                yearMap.put("children", monthList);
                yearMap.put("itemName", dto.getYear());
                monthMap.put("weekPlanCode", dto.getYear());
                yearList.add(yearMap);
            }
        });
        return yearList;
    }

    @ApiOperation("新增周计划")
    @PostMapping("/newWeekPlan")
    public Map<String, Object> newWeekPlan(@RequestBody WeekPlanDto dto) {
        Date startTime = dto.getStartTime();
        String projectCode = dto.getProjectCode();
        int year;
        int quarter;
        int month;
        int week;
        Sort.Order order1 = Sort.Order.desc("year");
        Sort.Order order2 = Sort.Order.desc("week");
        WeekPlan weekPlanLast = weekPlanDao.findFirstByProjectCode(projectCode, Sort.by(order1, order2));
        if (weekPlanLast == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startTime);
            year = calendar.get(Calendar.YEAR);
            quarter = CalendarUtils.getQuarter(startTime);
            month = calendar.get(Calendar.MONTH) + 1;
            week = calendar.get(Calendar.WEEK_OF_YEAR);
        } else {
            if (!weekPlanLast.getReleased())
                throw new HdException("999", "存在未发布的周计划！");
            else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(weekPlanLast.getEndTime());
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
                year = calendar.get(Calendar.YEAR);
                quarter = CalendarUtils.getQuarter(calendar.getTime());
                month = calendar.get(Calendar.MONTH) + 1;
                week = calendar.get(Calendar.WEEK_OF_YEAR);
                startTime = calendar.getTime();
            }
        }
        WeekPlan weekPlan = planService.newWeekPlan(projectCode, year, quarter, month, week, startTime);
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", true);
        result.put("weekPlanCode", weekPlan.getWeekPlanCode());
        return result;
    }

    @ApiOperation("删除周计划")
    @DeleteMapping("/deleteWeekPlan/{weekPlanCode}")
    @Transactional
    public Map<String, Boolean> deleteWeekPlan(@PathVariable String weekPlanCode) {
        WeekPlan weekPlan = weekPlanDao.findById(weekPlanCode).orElseThrow(() -> new HdException("999", "该周计划不存在！"));
        if (weekPlan.getReleased()) {
            throw new HdException("999", "已发布的周计划无法删除！");
        }
        List<WeekPlan> weekPlanList = weekPlanDao.findByProjectCodeAndYearAndWeekAfter(weekPlan.getProjectCode(), weekPlan.getYear(), weekPlan.getWeek());
        if (weekPlanList.size() > 0) {
            throw new HdException("999", "历史数据无法删除！");
        }
        weekPlanDetailDao.deleteByProjectCodeAndYearAndWeek(weekPlan.getProjectCode(), weekPlan.getYear(), weekPlan.getWeek());
        weekPlanDao.delete(weekPlan);
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("发布/撤销周计划(on/off)")
    @PostMapping("/releaseWeekPlan/{weekPlanCode}/{label}")
    public Map<String, Boolean> releaseWeekPlan(@PathVariable String weekPlanCode, @PathVariable String label) {
        WeekPlan weekPlan = weekPlanDao.findById(weekPlanCode).orElseThrow(() -> new HdException("999", "该周计划不存在！"));
        switch (label) {
            case "on":
                weekPlan.setReleased(true);
                break;
            case "off":
                weekPlan.setReleased(false);
                break;
            default:
        }
        weekPlanDao.save(weekPlan);
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("获取周计划")
    @GetMapping("/getWeekPlan/{weekPlanCode}")
    public WeekPlanDto getWeekPlan(@PathVariable String weekPlanCode) {
        WeekPlan weekPlan = planService.getWeekPlan(weekPlanCode);
        WeekPlanDto weekPlanDto = WeekPlanMapper.INSTANCE.entityToDto(weekPlan);
        weekPlanDto.setDetails(WeekPlanDetailMapper.INSTANCE.entityToDtoList(weekPlan.getDetails()));
        return weekPlanDto;
    }

    @ApiOperation("获取周计划清单项")
    @GetMapping("/getWeekPlanDetail/{weekPlanDetailCode}")
    public WeekPlanDetailDto getWeekPlanDetail(@PathVariable String weekPlanDetailCode) {
        WeekPlanDetail weekPlanDetail = weekPlanDetailDao.findById(weekPlanDetailCode).orElseThrow(() -> new HdException("999", "清单项不存在！"));
        WeekPlanDetailDto weekPlanDetailDto = WeekPlanDetailMapper.INSTANCE.entityToDto(weekPlanDetail);
        WeekPlanDto weekPlanDto = WeekPlanMapper.INSTANCE.entityToDto(weekPlanDetail.getWeekPlan());
        weekPlanDetailDto.setWeekPlan(weekPlanDto == null ? null : weekPlanDto.setDetails(null));
        WeekPlanDetailDto parent = WeekPlanDetailMapper.INSTANCE.entityToDto(weekPlanDetail.getParent());
        weekPlanDetailDto.setParent(parent == null ? null : parent.setChildren(null));
        weekPlanDetailDto.setFileIdList(fileService.listFileId(weekPlanDetail.getItemCode()));
        List<PpdQuote> ppdQuoteList = ppdQuoteDao.findByItemCode(weekPlanDetailDto.getItemCode());
        List<String> taskCodeList = new ArrayList<>();
        ppdQuoteList.forEach(ppdQuote -> taskCodeList.add(ppdQuote.getTaskCode()));
        List<Task> taskList = taskDao.findByTaskCodeIn(taskCodeList);
        taskList.forEach(task -> task.setAssignedToUser(userService.getPrjUserInfoView(task.getProjectCode(), task.getAssignedTo())));
        weekPlanDetailDto.setTaskList(taskList);
        return weekPlanDetailDto.setChildren(null);
    }

    @ApiOperation("编辑周计划清单项")
    @PostMapping("/editWeekPlanDetail")
    public Map<String, Boolean> editWeekPlanDetail(@RequestBody List<WeekPlanDetailDto> weekPlanDetailDtoList) {
        weekPlanDetailDtoList.forEach(dto -> {
            WeekPlanDetail entity = weekPlanDetailDao.findById(dto.getWeekPlanDetailCode()).orElseThrow(() -> new HdException("999", "清单项不存在！"));
            entity.setWeekPlanQty(dto.getWeekPlanQty());
            entity.setWeekPlanManHour(dto.getWeekPlanManHour());
            weekPlanDetailDao.save(entity);
        });
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("周汇报列表")
    @GetMapping("/listWeekReport/{projectCode}")
    public Page<WeekReportDto> listWeekReport(@PathVariable String projectCode) {
        BasePlan basePlan = planService.getBasePlan(projectCode);
        if (!basePlan.getReleased()) {
            throw new HdException("999", "基准计划未发布！");
        }
        Pageable pageable = PageUtils.getPageInfo(Sort.by(Sort.Order.desc("startTime")));
        Page<WeekReport> page = weekReportDao.findByProjectCode(projectCode, pageable);
        return new PageImpl<>(WeekReportMapper.INSTANCE.entityToDtoList(page.getContent()), pageable, page.getTotalElements());
    }

    @ApiOperation("新增周汇报")
    @PostMapping("/newWeekReport")
    public Map<String, Object> newWeekReport(@RequestBody WeekReportDto dto) {
        Date startTime = dto.getStartTime();
        String projectCode = dto.getProjectCode();
        int year;
        int quarter;
        int month;
        int week;
        Sort.Order order1 = Sort.Order.desc("year");
        Sort.Order order2 = Sort.Order.desc("week");
        WeekReport weekReportLast = weekReportDao.findFirstByProjectCode(projectCode, Sort.by(order1, order2));
        if (weekReportLast == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startTime);
            year = calendar.get(Calendar.YEAR);
            quarter = CalendarUtils.getQuarter(startTime);
            month = calendar.get(Calendar.MONTH) + 1;
            week = calendar.get(Calendar.WEEK_OF_YEAR);
        } else {
            if (!weekReportLast.getReleased())
                throw new HdException("999", "存在未发布的周汇报！");
            else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(weekReportLast.getEndTime());
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
                year = calendar.get(Calendar.YEAR);
                quarter = CalendarUtils.getQuarter(calendar.getTime());
                month = calendar.get(Calendar.MONTH) + 1;
                week = calendar.get(Calendar.WEEK_OF_YEAR);
                startTime = calendar.getTime();
            }
        }
        WeekReport weekReport = planService.newWeekReport(projectCode, year, quarter, month, week, startTime);
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", true);
        result.put("weekReportCode", weekReport.getWeekReportCode());
        return result;
    }

    @ApiOperation("删除周汇报")
    @DeleteMapping("/deleteWeekReport/{weekReportCode}")
    @Transactional
    public Map<String, Boolean> deleteWeekReport(@PathVariable String weekReportCode) {
        WeekReport weekReport = weekReportDao.findById(weekReportCode).orElseThrow(() -> new HdException("999", "该周汇报不存在！"));
        if (weekReport.getReleased()) {
            throw new HdException("999", "已发布的周汇报无法删除！");
        }
        List<WeekReport> weekReportList = weekReportDao.findByProjectCodeAndYearAndWeekAfter(weekReport.getProjectCode(), weekReport.getYear(), weekReport.getWeek());
        if (weekReportList.size() > 0) {
            throw new HdException("999", "历史数据无法删除！");
        }
        weekReportTrackingDao.deleteByWeekReportCode(weekReportCode);
        weekReportDetailDao.deleteByProjectCodeAndYearAndWeek(weekReport.getProjectCode(), weekReport.getYear(), weekReport.getWeek());
        weekReportDao.delete(weekReport);
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("发布/撤销周汇报(on/off)")
    @PostMapping("/releaseWeekReport/{weekReportCode}/{label}")
    public Map<String, Boolean> releaseWeekReport(@PathVariable String weekReportCode, @PathVariable String label) {
        WeekReport weekReport = weekReportDao.findById(weekReportCode).orElseThrow(() -> new HdException("999", "该周汇报不存在！"));
        switch (label) {
            case "on":
                weekReport.setReleased(true);
                break;
            case "off":
                weekReport.setReleased(false);
                break;
            default:
        }
        weekReportDao.save(weekReport);
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("获取周汇报")
    @GetMapping("/getWeekReport/{weekReportCode}")
    public WeekReportDto getWeekReport(@PathVariable String weekReportCode) {
        WeekReport weekReport = planService.getWeekReport(weekReportCode);
        WeekReportDto weekReportDto = WeekReportMapper.INSTANCE.entityToDto(weekReport);
        weekReportDto.setDetails(WeekReportDetailMapper.INSTANCE.entityToDtoList(weekReport.getDetails()));
        return weekReportDto;
    }

    @ApiOperation("获取周汇报清单项")
    @GetMapping("/getWeekReportDetail/{weekReportDetailCode}")
    public WeekReportDetailDto getWeekReportDetail(@PathVariable String weekReportDetailCode) {
        WeekReportDetail weekReportDetail = weekReportDetailDao.findById(weekReportDetailCode).orElseThrow(() -> new HdException("999", "清单项不存在！"));
        WeekReportDetailDto weekReportDetailDto = WeekReportDetailMapper.INSTANCE.entityToDto(weekReportDetail);
        WeekReportDto weekReportDto = WeekReportMapper.INSTANCE.entityToDto(weekReportDetail.getWeekReport());
        weekReportDetailDto.setWeekReport(weekReportDto == null ? null : weekReportDto.setDetails(null));
        WeekReportDetailDto parent = WeekReportDetailMapper.INSTANCE.entityToDto(weekReportDetail.getParent());
        weekReportDetailDto.setParent(parent == null ? null : parent.setChildren(null));
        weekReportDetailDto.setTrackings(WeekReportTrackingMapper.INSTANCE.entityToDtoList(weekReportDetail.getTrackings()));
        weekReportDetailDto.setFileIdList(fileService.listFileId(weekReportDetail.getItemCode()));
        List<PpdQuote> ppdQuoteList = ppdQuoteDao.findByItemCode(weekReportDetailDto.getItemCode());
        List<String> taskCodeList = new ArrayList<>();
        ppdQuoteList.forEach(ppdQuote -> taskCodeList.add(ppdQuote.getTaskCode()));
        List<Task> taskList = taskDao.findByTaskCodeIn(taskCodeList);
        taskList.forEach(task -> task.setAssignedToUser(userService.getPrjUserInfoView(task.getProjectCode(), task.getAssignedTo())));
        weekReportDetailDto.setTaskList(taskList);
        return weekReportDetailDto.setChildren(null);
    }

    @ApiOperation("编辑周汇报明细")
    @PostMapping("/editWeekReportTracking")
    public Map<String, Boolean> editWeekReportTracking(@RequestBody WeekReportDetailDto weekReportDetailDto) {
        WeekReportDetail weekReportDetail = weekReportDetailDao.findById(weekReportDetailDto.getWeekReportDetailCode()).orElseThrow(() -> new HdException("999", "周汇报清单项不存在！"));
        WeekReport weekReport = weekReportDao.findByProjectCodeAndYearAndWeek(weekReportDetail.getProjectCode(), weekReportDetail.getYear(), weekReportDetail.getWeek());
        List<WeekReportTrackingDto> weekReportTrackingDtoList = weekReportDetailDto.getTrackings();
        weekReportTrackingDtoList.stream().sorted(Comparator.comparing(WeekReportTrackingDto::getPeriodDay)).forEach(dto -> {
            WeekReportTracking entity;
            if (StringUtils.isNullOrEmpty(dto.getWeekTrackingCode())) {
                entity = planService.newWeekReportTracking(weekReport.getWeekReportCode(), weekReportDetailDto.getWeekReportDetailCode(), dto.getPeriodDay());
            } else {
                entity = weekReportTrackingDao.findById(dto.getWeekTrackingCode()).orElseThrow(() -> new HdException("999", "该周汇报明细不存在！"));
            }
            entity.setReportQty(dto.getReportQty());
            entity.setEndQty(entity.getStartQty() + entity.getReportQty());
            entity.setReportManHour(dto.getReportManHour());
            entity.setEndManHour(entity.getStartManHour() + entity.getReportManHour());
            EntityUtils.addUpdateInfo(entity);
            weekReportTrackingDao.save(entity);
        });
        weekReportDetail.setActualStartTime(weekReportDetailDto.getActualStartTime());
        weekReportDetail.setActualEndTime(weekReportDetailDto.getActualEndTime());
        weekReportDetail.setStat(weekReportDetailDto.getStat());
        weekReportDetailDao.save(weekReportDetail);
        WeekReportTrackingDto dto = weekReportTrackingDtoList.stream().min(Comparator.comparing(WeekReportTrackingDto::getPeriodDay)).orElseThrow(() -> new HdException("999", "周汇报明细列表为空！"));
        planService.updateWeekReportTrackingQtyAndManHour(weekReport.getWeekReportCode(), weekReportDetailDto.getWeekReportDetailCode(), dto.getPeriodDay());
        Map<String, Boolean> result = new HashMap<>();
        result.put("isSuccess", true);
        return result;
    }

    @ApiOperation("进度概况")
    @GetMapping("/progressOverview/{projectCode}")
    public Map<String, Object> progressOverview(@PathVariable String projectCode) {
        return planService.progressOverview(projectCode);
    }

    @ApiOperation("完成情况")
    @GetMapping("/progressExecution/{projectCode}")
    public Object progressExecution(@PathVariable String projectCode) {
        return planService.progressExecution(projectCode);
    }

    @ApiOperation("形象进度图")
    @GetMapping("/progressMap/{projectCode}")
    public Map<String, Object> progressMap(@PathVariable String projectCode) {
        return planService.progressMap(projectCode);
    }
}
