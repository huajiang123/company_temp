package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.QuarterPlan;
import com.hondee.service_plan.dto.QuarterPlanDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QuarterPlanMapper {
    QuarterPlanMapper INSTANCE = Mappers.getMapper(QuarterPlanMapper.class);

    @Mapping(target = "details", ignore = true)
    QuarterPlanDto entityToDto(QuarterPlan entity);

    List<QuarterPlanDto> entityToDtoList(List<QuarterPlan> entities);
}
