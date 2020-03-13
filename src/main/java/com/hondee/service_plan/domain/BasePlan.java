package com.hondee.service_plan.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@ApiModel("基准计划")
@Data
@Entity
@Table(name = "plan_baseplan")
@EqualsAndHashCode(callSuper = true)
public class BasePlan extends BaseEntity {
    @ApiModelProperty("基准计划编号")
    @Id
    String planCode;

    @ApiModelProperty("项目编号")
    String projectCode;

    @ApiModelProperty("版本")
    Integer version;

    @ApiModelProperty("是否发布")
    Boolean released;

    @ApiModelProperty("版本发布时间")
    Date releasedTime;

    @ApiModelProperty("清单项")
    @OneToMany(mappedBy = "basePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("sequence asc")
    List<BasePlanDetail> details;

    @ApiModelProperty("清单项草稿")
    @OrderBy("sequence asc")
    @OneToMany(mappedBy = "basePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<BasePlanDetailDraft> drafts;
}
