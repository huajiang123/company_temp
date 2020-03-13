package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.QuarterPlan;
import com.hondee.service_plan.dto.QuarterPlanDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-13T16:25:41+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class QuarterPlanMapperImpl implements QuarterPlanMapper {

    @Override
    public QuarterPlanDto entityToDto(QuarterPlan entity) {
        if ( entity == null ) {
            return null;
        }

        QuarterPlanDto quarterPlanDto = new QuarterPlanDto();

        quarterPlanDto.setValidity( entity.getValidity() );
        quarterPlanDto.setCreatedBy( entity.getCreatedBy() );
        quarterPlanDto.setCreatedTime( entity.getCreatedTime() );
        quarterPlanDto.setUpdatedBy( entity.getUpdatedBy() );
        quarterPlanDto.setUpdatedTime( entity.getUpdatedTime() );
        quarterPlanDto.setQuarterPlanCode( entity.getQuarterPlanCode() );
        quarterPlanDto.setYear( entity.getYear() );
        quarterPlanDto.setQuarter( entity.getQuarter() );
        quarterPlanDto.setProjectCode( entity.getProjectCode() );
        quarterPlanDto.setVersion( entity.getVersion() );
        quarterPlanDto.setReleased( entity.getReleased() );
        quarterPlanDto.setReleasedTime( entity.getReleasedTime() );

        return quarterPlanDto;
    }

    @Override
    public List<QuarterPlanDto> entityToDtoList(List<QuarterPlan> entities) {
        if ( entities == null ) {
            return null;
        }

        List<QuarterPlanDto> list = new ArrayList<QuarterPlanDto>( entities.size() );
        for ( QuarterPlan quarterPlan : entities ) {
            list.add( entityToDto( quarterPlan ) );
        }

        return list;
    }
}
