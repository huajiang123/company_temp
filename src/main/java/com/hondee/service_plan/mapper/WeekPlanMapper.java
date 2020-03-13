package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.WeekPlan;
import com.hondee.service_plan.dto.WeekPlanDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WeekPlanMapper {
    WeekPlanMapper INSTANCE = Mappers.getMapper(WeekPlanMapper.class);

    @Mapping(target = "details", ignore = true)
    WeekPlanDto entityToDto(WeekPlan entity);

    List<WeekPlanDto> entityToDtoList(List<WeekPlan> entities);
}
