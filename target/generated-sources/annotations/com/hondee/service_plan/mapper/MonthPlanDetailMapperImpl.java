package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.MonthPlanDetail;
import com.hondee.service_plan.dto.MonthPlanDetailDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-13T16:25:41+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class MonthPlanDetailMapperImpl implements MonthPlanDetailMapper {

    @Override
    public MonthPlanDetailDto entityToDto(MonthPlanDetail entity) {
        if ( entity == null ) {
            return null;
        }

        MonthPlanDetailDto monthPlanDetailDto = new MonthPlanDetailDto();

        monthPlanDetailDto.setValidity( entity.getValidity() );
        monthPlanDetailDto.setCreatedBy( entity.getCreatedBy() );
        monthPlanDetailDto.setCreatedTime( entity.getCreatedTime() );
        monthPlanDetailDto.setUpdatedBy( entity.getUpdatedBy() );
        monthPlanDetailDto.setUpdatedTime( entity.getUpdatedTime() );
        monthPlanDetailDto.setMonthPlanDetailCode( entity.getMonthPlanDetailCode() );
        monthPlanDetailDto.setChildren( entityToDtoList( entity.getChildren() ) );
        monthPlanDetailDto.setItemCode( entity.getItemCode() );
        monthPlanDetailDto.setItemName( entity.getItemName() );
        monthPlanDetailDto.setItemShowCode( entity.getItemShowCode() );
        monthPlanDetailDto.setUom( entity.getUom() );
        monthPlanDetailDto.setQty( entity.getQty() );
        monthPlanDetailDto.setPlanManHour( entity.getPlanManHour() );
        monthPlanDetailDto.setWeight( entity.getWeight() );
        monthPlanDetailDto.setWeightPercent( entity.getWeightPercent() );
        monthPlanDetailDto.setWeightTotalPercent( entity.getWeightTotalPercent() );
        monthPlanDetailDto.setPlanBDate( entity.getPlanBDate() );
        monthPlanDetailDto.setPlanEDate( entity.getPlanEDate() );
        monthPlanDetailDto.setImportant( entity.getImportant() );
        monthPlanDetailDto.setDescription( entity.getDescription() );
        monthPlanDetailDto.setYearPlanQty( entity.getYearPlanQty() );
        monthPlanDetailDto.setYearPlanManHour( entity.getYearPlanManHour() );
        monthPlanDetailDto.setQuarterPlanQty( entity.getQuarterPlanQty() );
        monthPlanDetailDto.setQuarterPlanManHour( entity.getQuarterPlanManHour() );
        monthPlanDetailDto.setMonthPlanQty( entity.getMonthPlanQty() );
        monthPlanDetailDto.setMonthPlanManHour( entity.getMonthPlanManHour() );

        return monthPlanDetailDto;
    }

    @Override
    public List<MonthPlanDetailDto> entityToDtoList(List<MonthPlanDetail> entities) {
        if ( entities == null ) {
            return null;
        }

        List<MonthPlanDetailDto> list = new ArrayList<MonthPlanDetailDto>( entities.size() );
        for ( MonthPlanDetail monthPlanDetail : entities ) {
            list.add( entityToDto( monthPlanDetail ) );
        }

        return list;
    }
}
