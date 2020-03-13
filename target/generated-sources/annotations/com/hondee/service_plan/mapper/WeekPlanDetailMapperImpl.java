package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.WeekPlanDetail;
import com.hondee.service_plan.dto.WeekPlanDetailDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-13T16:25:41+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class WeekPlanDetailMapperImpl implements WeekPlanDetailMapper {

    @Override
    public WeekPlanDetailDto entityToDto(WeekPlanDetail entity) {
        if ( entity == null ) {
            return null;
        }

        WeekPlanDetailDto weekPlanDetailDto = new WeekPlanDetailDto();

        weekPlanDetailDto.setValidity( entity.getValidity() );
        weekPlanDetailDto.setCreatedBy( entity.getCreatedBy() );
        weekPlanDetailDto.setCreatedTime( entity.getCreatedTime() );
        weekPlanDetailDto.setUpdatedBy( entity.getUpdatedBy() );
        weekPlanDetailDto.setUpdatedTime( entity.getUpdatedTime() );
        weekPlanDetailDto.setWeekPlanDetailCode( entity.getWeekPlanDetailCode() );
        weekPlanDetailDto.setChildren( entityToDtoList( entity.getChildren() ) );
        weekPlanDetailDto.setItemCode( entity.getItemCode() );
        weekPlanDetailDto.setItemName( entity.getItemName() );
        weekPlanDetailDto.setItemShowCode( entity.getItemShowCode() );
        weekPlanDetailDto.setUom( entity.getUom() );
        weekPlanDetailDto.setQty( entity.getQty() );
        weekPlanDetailDto.setPlanManHour( entity.getPlanManHour() );
        weekPlanDetailDto.setWeight( entity.getWeight() );
        weekPlanDetailDto.setWeightPercent( entity.getWeightPercent() );
        weekPlanDetailDto.setWeightTotalPercent( entity.getWeightTotalPercent() );
        weekPlanDetailDto.setPlanBDate( entity.getPlanBDate() );
        weekPlanDetailDto.setPlanEDate( entity.getPlanEDate() );
        weekPlanDetailDto.setImportant( entity.getImportant() );
        weekPlanDetailDto.setDescription( entity.getDescription() );
        weekPlanDetailDto.setYearPlanQty( entity.getYearPlanQty() );
        weekPlanDetailDto.setYearPlanManHour( entity.getYearPlanManHour() );
        weekPlanDetailDto.setQuarterPlanQty( entity.getQuarterPlanQty() );
        weekPlanDetailDto.setQuarterPlanManHour( entity.getQuarterPlanManHour() );
        weekPlanDetailDto.setMonthPlanQty( entity.getMonthPlanQty() );
        weekPlanDetailDto.setMonthPlanManHour( entity.getMonthPlanManHour() );
        weekPlanDetailDto.setWeekPlanQty( entity.getWeekPlanQty() );
        weekPlanDetailDto.setWeekPlanManHour( entity.getWeekPlanManHour() );

        return weekPlanDetailDto;
    }

    @Override
    public List<WeekPlanDetailDto> entityToDtoList(List<WeekPlanDetail> entities) {
        if ( entities == null ) {
            return null;
        }

        List<WeekPlanDetailDto> list = new ArrayList<WeekPlanDetailDto>( entities.size() );
        for ( WeekPlanDetail weekPlanDetail : entities ) {
            list.add( entityToDto( weekPlanDetail ) );
        }

        return list;
    }
}
