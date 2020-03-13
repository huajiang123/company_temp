package com.hondee.service_plan.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@ApiModel("年度计划清单项")
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "plan_yearplandetail")
public class YearPlanDetail extends BaseEntity {
    @ApiModelProperty("年度计划清单项编号")
    @Id
    String yearPlanDetailCode;

    @ApiModelProperty("年度计划")
    @JoinColumn(name = "yearPlanCode")
    @ManyToOne
    YearPlan yearPlan;

    @ApiModelProperty("项目编号")
    String projectCode;

    @ApiModelProperty("父项")
    @JoinColumn(name = "parentCode")
    @ManyToOne
    YearPlanDetail parent;

    @ApiModelProperty("子项")
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("sequence asc")
    List<YearPlanDetail> children;

    @ApiModelProperty("年")
    Integer year;

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

    @ApiModelProperty("序列号")
    String sequence;
}
