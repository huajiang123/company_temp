package com.hondee.service_plan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel("基准计划清单项")
@Data
@EqualsAndHashCode(callSuper = true)
public class BasePlanDetailDto extends BaseDto {
    @ApiModelProperty("清单项编号")
    String itemCode;

    @ApiModelProperty("基准计划")
    BasePlanDto basePlan;

    @ApiModelProperty("父项")
    BasePlanDetailDto parent;

    @ApiModelProperty("子项")
    List<BasePlanDetailDto> children = new ArrayList<>();

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
    Integer weight = 0;

    @ApiModelProperty("权重百分比")
    Double weightPercent = 0d;

    @ApiModelProperty("综合百分比")
    Double weightTotalPercent = 0d;

    @ApiModelProperty("计划开始时间")
    Date planBDate;

    @ApiModelProperty("计划结束时间")
    Date planEDate;

    @ApiModelProperty("是否重要")
    Boolean important;

    @ApiModelProperty("描述")
    String description;

    @ApiModelProperty("是否发布")
    Boolean released;

    @ApiModelProperty("附件")
    List<Long> fileIdList = new ArrayList<>();

    @ApiModelProperty("任务")
    List taskList = new ArrayList();
}
