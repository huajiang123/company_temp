package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.YearPlan;
import com.hondee.service_plan.dto.YearPlanDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-13T16:25:41+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class YearPlanMapperImpl implements YearPlanMapper {

    @Override
    public YearPlanDto entityToDto(YearPlan entity) {
        if ( entity == null ) {
            return null;
        }

        YearPlanDto yearPlanDto = new YearPlanDto();

        yearPlanDto.setValidity( entity.getValidity() );
        yearPlanDto.setCreatedBy( entity.getCreatedBy() );
        yearPlanDto.setCreatedTime( entity.getCreatedTime() );
        yearPlanDto.setUpdatedBy( entity.getUpdatedBy() );
        yearPlanDto.setUpdatedTime( entity.getUpdatedTime() );
        yearPlanDto.setYearPlanCode( entity.getYearPlanCode() );
        yearPlanDto.setYear( entity.getYear() );
        yearPlanDto.setProjectCode( entity.getProjectCode() );
        yearPlanDto.setVersion( entity.getVersion() );
        yearPlanDto.setReleased( entity.getReleased() );
        yearPlanDto.setReleasedTime( entity.getReleasedTime() );

        return yearPlanDto;
    }

    @Override
    public List<YearPlanDto> entityToDtoList(List<YearPlan> entities) {
        if ( entities == null ) {
            return null;
        }

        List<YearPlanDto> list = new ArrayList<YearPlanDto>( entities.size() );
        for ( YearPlan yearPlan : entities ) {
            list.add( entityToDto( yearPlan ) );
        }

        return list;
    }
}
