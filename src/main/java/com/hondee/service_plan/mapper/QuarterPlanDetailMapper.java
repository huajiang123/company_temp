package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.QuarterPlanDetail;
import com.hondee.service_plan.dto.QuarterPlanDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QuarterPlanDetailMapper {
    QuarterPlanDetailMapper INSTANCE = Mappers.getMapper(QuarterPlanDetailMapper.class);

    @Mappings({
            @Mapping(target = "quarterPlan", ignore = true),
            @Mapping(target = "parent", ignore = true)
    })
    QuarterPlanDetailDto entityToDto(QuarterPlanDetail entity);

    List<QuarterPlanDetailDto> entityToDtoList(List<QuarterPlanDetail> entities);
}
