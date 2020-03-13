package com.hondee.service_plan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@ApiModel("周计划")
@Data
@EqualsAndHashCode(callSuper = true)
public class WeekPlanDto extends BaseDto {
    @ApiModelProperty("周计划编号")
    String weekPlanCode;

    @ApiModelProperty("年")
    Integer year;

    @ApiModelProperty("季")
    Integer quarter;

    @ApiModelProperty("月")
    Integer month;

    @ApiModelProperty("周")
    Integer week;

    @ApiModelProperty("开始时间")
    Date startTime;

    @ApiModelProperty("结束时间")
    Date endTime;

    @ApiModelProperty("基准计划编号")
    String projectCode;

    @ApiModelProperty("版本")
    Integer version;

    @ApiModelProperty("是否发布")
    Boolean released;

    @ApiModelProperty("版本发布时间")
    Date releasedTime;

    @ApiModelProperty("清单项")
    List<WeekPlanDetailDto> details;
}
