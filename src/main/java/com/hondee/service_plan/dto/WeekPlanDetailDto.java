package com.hondee.service_plan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel("周计划清单项")
@Data
@EqualsAndHashCode(callSuper = true)
public class WeekPlanDetailDto extends BaseDto {
    @ApiModelProperty("周计划清单项编号")
    String weekPlanDetailCode;

    @ApiModelProperty("周计划")
    WeekPlanDto weekPlan;

    @ApiModelProperty("父项")
    WeekPlanDetailDto parent;

    @ApiModelProperty("子项")
    List<WeekPlanDetailDto> children = new ArrayList<>();

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

    @ApiModelProperty("附件")
    List<Long> fileIdList = new ArrayList<>();

    @ApiModelProperty("任务")
    List taskList;
}
