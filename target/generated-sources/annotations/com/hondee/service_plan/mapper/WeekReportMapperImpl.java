package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.WeekReport;
import com.hondee.service_plan.dto.WeekReportDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-13T16:25:41+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class WeekReportMapperImpl implements WeekReportMapper {

    @Override
    public WeekReportDto entityToDto(WeekReport entity) {
        if ( entity == null ) {
            return null;
        }

        WeekReportDto weekReportDto = new WeekReportDto();

        weekReportDto.setValidity( entity.getValidity() );
        weekReportDto.setCreatedBy( entity.getCreatedBy() );
        weekReportDto.setCreatedTime( entity.getCreatedTime() );
        weekReportDto.setUpdatedBy( entity.getUpdatedBy() );
        weekReportDto.setUpdatedTime( entity.getUpdatedTime() );
        weekReportDto.setWeekReportCode( entity.getWeekReportCode() );
        weekReportDto.setYear( entity.getYear() );
        weekReportDto.setQuarter( entity.getQuarter() );
        weekReportDto.setMonth( entity.getMonth() );
        weekReportDto.setWeek( entity.getWeek() );
        weekReportDto.setStartTime( entity.getStartTime() );
        weekReportDto.setEndTime( entity.getEndTime() );
        weekReportDto.setProjectCode( entity.getProjectCode() );
        weekReportDto.setVersion( entity.getVersion() );
        weekReportDto.setReleased( entity.getReleased() );
        weekReportDto.setReleasedTime( entity.getReleasedTime() );

        return weekReportDto;
    }

    @Override
    public List<WeekReportDto> entityToDtoList(List<WeekReport> entities) {
        if ( entities == null ) {
            return null;
        }

        List<WeekReportDto> list = new ArrayList<WeekReportDto>( entities.size() );
        for ( WeekReport weekReport : entities ) {
            list.add( entityToDto( weekReport ) );
        }

        return list;
    }
}
