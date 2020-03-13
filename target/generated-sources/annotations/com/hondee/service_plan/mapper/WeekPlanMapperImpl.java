package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.WeekPlan;
import com.hondee.service_plan.dto.WeekPlanDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-13T16:25:41+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class WeekPlanMapperImpl implements WeekPlanMapper {

    @Override
    public WeekPlanDto entityToDto(WeekPlan entity) {
        if ( entity == null ) {
            return null;
        }

        WeekPlanDto weekPlanDto = new WeekPlanDto();

        weekPlanDto.setValidity( entity.getValidity() );
        weekPlanDto.setCreatedBy( entity.getCreatedBy() );
        weekPlanDto.setCreatedTime( entity.getCreatedTime() );
        weekPlanDto.setUpdatedBy( entity.getUpdatedBy() );
        weekPlanDto.setUpdatedTime( entity.getUpdatedTime() );
        weekPlanDto.setWeekPlanCode( entity.getWeekPlanCode() );
        weekPlanDto.setYear( entity.getYear() );
        weekPlanDto.setQuarter( entity.getQuarter() );
        weekPlanDto.setMonth( entity.getMonth() );
        weekPlanDto.setWeek( entity.getWeek() );
        weekPlanDto.setStartTime( entity.getStartTime() );
        weekPlanDto.setEndTime( entity.getEndTime() );
        weekPlanDto.setProjectCode( entity.getProjectCode() );
        weekPlanDto.setVersion( entity.getVersion() );
        weekPlanDto.setReleased( entity.getReleased() );
        weekPlanDto.setReleasedTime( entity.getReleasedTime() );

        return weekPlanDto;
    }

    @Override
    public List<WeekPlanDto> entityToDtoList(List<WeekPlan> entities) {
        if ( entities == null ) {
            return null;
        }

        List<WeekPlanDto> list = new ArrayList<WeekPlanDto>( entities.size() );
        for ( WeekPlan weekPlan : entities ) {
            list.add( entityToDto( weekPlan ) );
        }

        return list;
    }
}
