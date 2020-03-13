package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.MonthPlan;
import com.hondee.service_plan.dto.MonthPlanDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-13T16:25:41+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class MonthPlanMapperImpl implements MonthPlanMapper {

    @Override
    public MonthPlanDto entityToDto(MonthPlan entity) {
        if ( entity == null ) {
            return null;
        }

        MonthPlanDto monthPlanDto = new MonthPlanDto();

        monthPlanDto.setValidity( entity.getValidity() );
        monthPlanDto.setCreatedBy( entity.getCreatedBy() );
        monthPlanDto.setCreatedTime( entity.getCreatedTime() );
        monthPlanDto.setUpdatedBy( entity.getUpdatedBy() );
        monthPlanDto.setUpdatedTime( entity.getUpdatedTime() );
        monthPlanDto.setMonthPlanCode( entity.getMonthPlanCode() );
        monthPlanDto.setYear( entity.getYear() );
        monthPlanDto.setQuarter( entity.getQuarter() );
        monthPlanDto.setMonth( entity.getMonth() );
        monthPlanDto.setProjectCode( entity.getProjectCode() );
        monthPlanDto.setVersion( entity.getVersion() );
        monthPlanDto.setReleased( entity.getReleased() );
        monthPlanDto.setReleasedTime( entity.getReleasedTime() );

        return monthPlanDto;
    }

    @Override
    public List<MonthPlanDto> entityToDtoList(List<MonthPlan> entities) {
        if ( entities == null ) {
            return null;
        }

        List<MonthPlanDto> list = new ArrayList<MonthPlanDto>( entities.size() );
        for ( MonthPlan monthPlan : entities ) {
            list.add( entityToDto( monthPlan ) );
        }

        return list;
    }
}
