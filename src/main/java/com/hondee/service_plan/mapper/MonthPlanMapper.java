package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.MonthPlan;
import com.hondee.service_plan.dto.MonthPlanDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MonthPlanMapper {
    MonthPlanMapper INSTANCE = Mappers.getMapper(MonthPlanMapper.class);

    @Mapping(target = "details", ignore = true)
    MonthPlanDto entityToDto(MonthPlan entity);

    List<MonthPlanDto> entityToDtoList(List<MonthPlan> entities);
}
