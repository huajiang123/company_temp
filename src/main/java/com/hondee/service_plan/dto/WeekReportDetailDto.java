package com.hondee.service_plan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@ApiModel("周汇报清单项")
@Data
@EqualsAndHashCode(callSuper = true)
public class WeekReportDetailDto extends BaseDto {
    @ApiModelProperty("周汇报清单项编号")
    String weekReportDetailCode;

    @ApiModelProperty("周计划")
    WeekReportDto weekReport;

    @ApiModelProperty("父项")
    WeekReportDetailDto parent;

    @ApiModelProperty("子项")
    List<WeekReportDetailDto> children = new ArrayList<>();

    @ApiModelProperty("清单项编号")
    String itemCode;

    @ApiModelProperty("清单项名称")
    String itemName;

    @ApiModelProperty("编号(自定义显示编号)")
    String itemShowCode;

    @ApiModelProperty("单位")
    String uom;

    @ApiModelProperty("数量")
    Double qty;

    @ApiModelProperty("计划工时")
    Integer planManHour;

    @ApiModelProperty("权重")
    Integer weight;

    @ApiModelProperty("权重百分比")
    Double weightPercent;

    @ApiModelProperty("综合百分比")
    Double weightTotalPercent;

    @ApiModelProperty("计划开始时间")
    Date planBDate;

    @ApiModelProperty("计划结束时间")
    Date planEDate;

    @ApiModelProperty("是否重要")
    Boolean important;

    @ApiModelProperty("描述")
    String description;

    @ApiModelProperty("年度计划量")
    Double yearPlanQty;

    @ApiModelProperty("年度计划工时")
    Integer yearPlanManHour;

    @ApiModelProperty("季度计划量")
    Double quarterPlanQty;

    @ApiModelProperty("季度计划工时")
    Integer quarterPlanManHour;

    @ApiModelProperty("月度计划量")
    Double monthPlanQty;

    @ApiModelProperty("月度计划工时")
    Integer monthPlanManHour;

    @ApiModelProperty("周计划量")
    Double weekPlanQty;

    @ApiModelProperty("周计划工时")
    Integer weekPlanManHour;

    @ApiModelProperty("累计完成量")
    Double endQty;

    @ApiModelProperty("累计工时")
    Integer endManHour;

    @ApiModelProperty("进度")
    Double progress;

    @ApiModelProperty("实际开始时间")
    Date actualStartTime;

    @ApiModelProperty("实际结束时间")
    Date actualEndTime;

    @ApiModelProperty("状态")
    Integer stat;

    @ApiModelProperty("明细")
    List<WeekReportTrackingDto> trackings;

    @ApiModelProperty("附件")
    List<Long> fileIdList = new ArrayList<>();

    @ApiModelProperty("任务")
    List taskList;

    @ApiModelProperty("周汇报量合计")
    List<Map<String, Double>> weekReportQtySum;
}
