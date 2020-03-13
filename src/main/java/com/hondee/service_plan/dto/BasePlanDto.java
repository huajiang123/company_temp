package com.hondee.service_plan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@ApiModel("基准计划")
@Data
@EqualsAndHashCode(callSuper = true)
public class BasePlanDto extends BaseDto {
    @ApiModelProperty("基准计划编号")
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
    List<BasePlanDetailDto> details;

    @ApiModelProperty("清单项草稿")
    List<BasePlanDetailDto> drafts;

    @ApiModelProperty("历史版本编号")
    List<Integer> versions;
}
