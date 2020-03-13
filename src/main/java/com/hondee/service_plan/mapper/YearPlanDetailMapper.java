package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.YearPlanDetail;
import com.hondee.service_plan.dto.YearPlanDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface YearPlanDetailMapper {
    YearPlanDetailMapper INSTANCE = Mappers.getMapper(YearPlanDetailMapper.class);

    @Mappings({
            @Mapping(target = "yearPlan", ignore = true),
            @Mapping(target = "parent", ignore = true)
    })
    YearPlanDetailDto entityToDto(YearPlanDetail entity);

    List<YearPlanDetailDto> entityToDtoList(List<YearPlanDetail> entities);
}
