package com.hondee.service_plan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@ApiModel("年度计划")
@Data
@EqualsAndHashCode(callSuper = true)
public class YearPlanDto extends BaseDto {
    @ApiModelProperty("年度计划编号")
    String yearPlanCode;

    @ApiModelProperty("年")
    Integer year;

    @ApiModelProperty("项目编号")
    String projectCode;

    @ApiModelProperty("版本")
    Integer version;

    @ApiModelProperty("是否发布")
    Boolean released;

    @ApiModelProperty("版本发布时间")
    Date releasedTime;

    @ApiModelProperty("清单项")
    List<YearPlanDetailDto> details;
}
