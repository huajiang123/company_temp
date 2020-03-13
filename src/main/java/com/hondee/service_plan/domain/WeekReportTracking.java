package com.hondee.service_plan.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@ApiModel("周汇报明细")
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "plan_weekreporttracking")
public class WeekReportTracking extends BaseEntity {
    @ApiModelProperty("周汇报明细编号")
    @Id
    String weekTrackingCode;

    @ApiModelProperty("周汇报编号")
    String weekReportCode;

    @ApiModelProperty("周汇报清单项")
    @JoinColumn(name = "weekReportDetailCode")
    @ManyToOne
    WeekReportDetail weekReportDetail;

    @ApiModelProperty("基准计划清单项编号")
    String itemCode;

    @ApiModelProperty("汇报周期")
    Integer periodNo;

    @ApiModelProperty("汇报日")
    Date periodDay;

    @ApiModelProperty("基准计划量")
    Double basePlanQty;

    @ApiModelProperty("期初累计量")
    Double startQty;

    @ApiModelProperty("当期汇报量")
    Double reportQty;

    @ApiModelProperty("期末累计量")
    Double endQty;

    @ApiModelProperty("期初累计工时")
    Integer startManHour;

    @ApiModelProperty("当期汇报工时")
    Integer reportManHour;

    @ApiModelProperty("期末累计工时")
    Integer endManHour;
}
