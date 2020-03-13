package com.hondee.service_plan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@ApiModel("周汇报明细")
@Data
@EqualsAndHashCode(callSuper = true)
public class WeekReportTrackingDto extends BaseDto {
    @ApiModelProperty("周汇报明细编号")
    String weekTrackingCode;

    @ApiModelProperty("周汇报编号")
    String weekReportCode;

    @ApiModelProperty("周汇报清单项编号")
    String weekReportDetailCode;

    @ApiModelProperty("汇报周期")
    Integer periodNo;

    @ApiModelProperty("汇报日")
    Date periodDay;

    @ApiModelProperty("基准计划量")
    Double basePlanQty;

    @ApiModelProperty("期初累计量")
    Double startQty;

    @ApiModelProperty("当期汇报量")
    Double reportQty = 0d;

    @ApiModelProperty("期末累计量")
    Double endQty;

    @ApiModelProperty("期初累计工时")
    Integer startManHour;

    @ApiModelProperty("当期汇报工时")
    Integer reportManHour = 0;

    @ApiModelProperty("期末累计工时")
    Integer endManHour;
}
