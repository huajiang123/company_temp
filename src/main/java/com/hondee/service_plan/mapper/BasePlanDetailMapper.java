package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.BasePlanDetail;
import com.hondee.service_plan.domain.BasePlanDetailDraft;
import com.hondee.service_plan.dto.BasePlanDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BasePlanDetailMapper {
    BasePlanDetailMapper INSTANCE = Mappers.getMapper(BasePlanDetailMapper.class);

    BasePlanDetailDraft dtoToDraftEntity(BasePlanDetailDto dto);

    @Mappings({
            @Mapping(target = "basePlan", ignore = true),
            @Mapping(target = "parent", ignore = true)
    })
    BasePlanDetailDto draftEntityToDto(BasePlanDetailDraft entity);

    List<BasePlanDetailDto> draftEntityToDtoList(List<BasePlanDetailDraft> entities);

    @Mappings({
            @Mapping(target = "basePlan", ignore = true),
            @Mapping(target = "parent", ignore = true)
    })
    BasePlanDetailDto entityToDto(BasePlanDetail entity);

    List<BasePlanDetailDto> entityToDtoList(List<BasePlanDetail> entities);

    List<BasePlanDetail> dtoToEntityList(List<BasePlanDetailDto> dtos);
}
