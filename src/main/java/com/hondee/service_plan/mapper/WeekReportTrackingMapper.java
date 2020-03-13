package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.WeekReportTracking;
import com.hondee.service_plan.dto.WeekReportTrackingDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WeekReportTrackingMapper {
    WeekReportTrackingMapper INSTANCE = Mappers.getMapper(WeekReportTrackingMapper.class);

    @Mapping(target = "weekReportDetailCode", source = "weekReportDetail.weekReportDetailCode")
    WeekReportTrackingDto entityToDto(WeekReportTracking entity);

    @InheritConfiguration(name = "entityToDto")
    List<WeekReportTrackingDto> entityToDtoList(List<WeekReportTracking> entities);
}
