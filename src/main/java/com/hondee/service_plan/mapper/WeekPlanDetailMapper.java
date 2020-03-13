package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.WeekPlanDetail;
import com.hondee.service_plan.dto.WeekPlanDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WeekPlanDetailMapper {
    WeekPlanDetailMapper INSTANCE = Mappers.getMapper(WeekPlanDetailMapper.class);

    @Mappings({
            @Mapping(target = "weekPlan", ignore = true),
            @Mapping(target = "parent", ignore = true)
    })
    WeekPlanDetailDto entityToDto(WeekPlanDetail entity);

    List<WeekPlanDetailDto> entityToDtoList(List<WeekPlanDetail> entities);
}
