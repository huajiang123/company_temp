package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.YearPlan;
import com.hondee.service_plan.dto.YearPlanDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface YearPlanMapper {
    YearPlanMapper INSTANCE = Mappers.getMapper(YearPlanMapper.class);

    @Mapping(target = "details", ignore = true)
    YearPlanDto entityToDto(YearPlan entity);

    List<YearPlanDto> entityToDtoList(List<YearPlan> entities);
}
