package com.hondee.service_plan.domain;

import com.hondee.service_plan.utils.poiutils.ExcelResources;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@ApiModel("基准计划清单项")
@Data
@Entity
@Table(name = "plan_baseplandetail")
@EqualsAndHashCode(callSuper = true)
public class BasePlanDetail extends BaseEntity {
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
    BasePlanDetail parent;

    @ApiModelProperty("子项")
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("sequence asc")
    List<BasePlanDetail> children;

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

    @Transient
    String planBDateStr;
    @Transient
    String planEDateStr;
    @Transient
    String weightPercentStr;
    @Transient
    String weightTotalPercentStr;

    @ExcelResources(title = "序号", order = 1)
    public String getSequence() {
        return sequence;
    }
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    @ExcelResources(title = "清单名称", order = 2)
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    @ExcelResources(title = "编号", order = 3)
    public String getItemShowCode() {
        return itemShowCode;
    }
    public void setItemShowCode(String itemShowCode) {
        this.itemShowCode = itemShowCode;
    }
    @ExcelResources(title = "单位", order = 4)
    public String getUom() {
        return uom;
    }
    public void setUom(String uom) {
        this.uom = uom;
    }
    @ExcelResources(title = "数量", order = 5)
    public Double getQty() {
        return qty;
    }
    public void setQty(Double qty) {
        this.qty = qty;
    }
    @ExcelResources(title = "权重", order = 6)
    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    @ExcelResources(title = "权重百分比", order = 7)
    public String getWeightPercentStr() {
        return weightPercentStr;
    }
    public void setWeightPercentStr(String wPercentStr) {
        this.weightPercentStr = wPercentStr;
    }
    @ExcelResources(title = "综合权重百分比", order = 8)
    public String getWeightTotalPercentStr() {
        return weightTotalPercentStr;
    }
    public void setWeightTotalPercentStr(String wTotalPercentStr) {
        this.weightTotalPercentStr = wTotalPercentStr;
    }
    @ExcelResources(title = "开始日期", order = 9)
    public String getPlanBDateStr() {
        return planBDateStr;
    }
    public void setPlanBDateStr(String planBDateStr) {
        this.planBDateStr = planBDateStr;
    }
    @ExcelResources(title = "完成日期", order = 10)
    public String getPlanEDateStr() {
        return planEDateStr;
    }
    public void setPlanEDateStr(String planEDateStr) {
        this.planEDateStr = planEDateStr;
    }
    @ExcelResources(title = "计划工时", order = 11)
    public Integer getPlanManHour() {
        return planManHour;
    }
    public void setPlanManHour(Integer planManHour) {
        this.planManHour = planManHour;
    }
}
