package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.MonthPlanDetail;
import com.hondee.service_plan.dto.MonthPlanDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MonthPlanDetailMapper {
    MonthPlanDetailMapper INSTANCE = Mappers.getMapper(MonthPlanDetailMapper.class);

    @Mappings({
            @Mapping(target = "monthPlan", ignore = true),
            @Mapping(target = "parent", ignore = true)
    })
    MonthPlanDetailDto entityToDto(MonthPlanDetail entity);

    List<MonthPlanDetailDto> entityToDtoList(List<MonthPlanDetail> entities);
}
