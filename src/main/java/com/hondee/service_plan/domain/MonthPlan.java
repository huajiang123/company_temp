package com.hondee.service_plan.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@ApiModel("月度计划")
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "plan_monthplan")
public class MonthPlan extends BaseEntity {
    @ApiModelProperty("月度计划编号")
    @Id
    String monthPlanCode;

    @ApiModelProperty("年")
    Integer year;

    @ApiModelProperty("季")
    Integer quarter;

    @ApiModelProperty("月")
    Integer month;

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
    @OneToMany(mappedBy = "monthPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("sequence asc")
    List<MonthPlanDetail> details;
}
