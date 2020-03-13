package com.hondee.service_plan.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@ApiModel("周汇报")
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "plan_weekreport")
public class WeekReport extends BaseEntity {
    @ApiModelProperty("周汇报编号")
    @Id
    String weekReportCode;

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
    String basePlanCode;

    @ApiModelProperty("项目编号")
    String projectCode;

    @ApiModelProperty("版本")
    Integer version;

    @ApiModelProperty("是否发布")
    Boolean released;

    @ApiModelProperty("版本发布时间")
    Date releasedTime;

    @ApiModelProperty("清单项")
    @OneToMany(mappedBy = "weekReport", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("sequence asc")
    List<WeekReportDetail> details;
}
