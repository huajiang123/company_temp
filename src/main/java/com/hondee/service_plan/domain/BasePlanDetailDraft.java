package com.hondee.service_plan.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@ApiModel("基准计划清单项草稿")
@Data
@Entity
@Table(name = "plan_baseplandetaildraft")
@EqualsAndHashCode(callSuper = true)
public class BasePlanDetailDraft extends BaseEntity {
    @ApiModelProperty("清单项编号")
    @Id
    String itemCode;

    @ApiModelProperty("基准计划")
    @JoinColumn(name = "planCode")
    @ManyToOne
    BasePlan basePlan;

    @ApiModelProperty("项目编号")
    String projectCode;

    @ApiModelProperty("父项")
    @JoinColumn(name = "parentCode")
    @ManyToOne
    BasePlanDetailDraft parent;

    @ApiModelProperty("子项")
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("sequence asc")
    List<BasePlanDetailDraft> children;

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

    @ApiModelProperty("序列号")
    String sequence;

    @ApiModelProperty("是否发布")
    Boolean released;
}
