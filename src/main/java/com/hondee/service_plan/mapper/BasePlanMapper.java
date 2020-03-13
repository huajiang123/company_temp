package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.BasePlan;
import com.hondee.service_plan.dto.BasePlanDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BasePlanMapper {
    BasePlanMapper INSTANCE = Mappers.getMapper(BasePlanMapper.class);

    BasePlan dtoToEntity(BasePlanDto dto);

    @Mappings({
            @Mapping(target = "details", ignore = true),
            @Mapping(target = "drafts", ignore = true)
    })
    BasePlanDto entityToDto(BasePlan entity);
}
