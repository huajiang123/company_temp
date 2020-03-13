package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.WeekReport;
import com.hondee.service_plan.dto.WeekReportDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WeekReportMapper {
    WeekReportMapper INSTANCE = Mappers.getMapper(WeekReportMapper.class);

    @Mapping(target = "details", ignore = true)
    WeekReportDto entityToDto(WeekReport entity);

    List<WeekReportDto> entityToDtoList(List<WeekReport> entities);
}
