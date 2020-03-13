package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.WeekReportDetail;
import com.hondee.service_plan.dto.WeekReportDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WeekReportDetailMapper {
    WeekReportDetailMapper INSTANCE = Mappers.getMapper(WeekReportDetailMapper.class);

    @Mappings({
            @Mapping(target = "weekReport", ignore = true),
            @Mapping(target = "parent", ignore = true)
    })
    WeekReportDetailDto entityToDto(WeekReportDetail entity);

    List<WeekReportDetailDto> entityToDtoList(List<WeekReportDetail> entities);
}
