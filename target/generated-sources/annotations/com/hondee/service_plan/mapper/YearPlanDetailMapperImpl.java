package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.YearPlanDetail;
import com.hondee.service_plan.dto.YearPlanDetailDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-13T16:25:41+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class YearPlanDetailMapperImpl implements YearPlanDetailMapper {

    @Override
    public YearPlanDetailDto entityToDto(YearPlanDetail entity) {
        if ( entity == null ) {
            return null;
        }

        YearPlanDetailDto yearPlanDetailDto = new YearPlanDetailDto();

        yearPlanDetailDto.setValidity( entity.getValidity() );
        yearPlanDetailDto.setCreatedBy( entity.getCreatedBy() );
        yearPlanDetailDto.setCreatedTime( entity.getCreatedTime() );
        yearPlanDetailDto.setUpdatedBy( entity.getUpdatedBy() );
        yearPlanDetailDto.setUpdatedTime( entity.getUpdatedTime() );
        yearPlanDetailDto.setYearPlanDetailCode( entity.getYearPlanDetailCode() );
        yearPlanDetailDto.setChildren( entityToDtoList( entity.getChildren() ) );
        yearPlanDetailDto.setItemCode( entity.getItemCode() );
        yearPlanDetailDto.setItemName( entity.getItemName() );
        yearPlanDetailDto.setItemShowCode( entity.getItemShowCode() );
        yearPlanDetailDto.setUom( entity.getUom() );
        yearPlanDetailDto.setQty( entity.getQty() );
        yearPlanDetailDto.setPlanManHour( entity.getPlanManHour() );
        yearPlanDetailDto.setWeight( entity.getWeight() );
        yearPlanDetailDto.setWeightPercent( entity.getWeightPercent() );
        yearPlanDetailDto.setWeightTotalPercent( entity.getWeightTotalPercent() );
        yearPlanDetailDto.setPlanBDate( entity.getPlanBDate() );
        yearPlanDetailDto.setPlanEDate( entity.getPlanEDate() );
        yearPlanDetailDto.setImportant( entity.getImportant() );
        yearPlanDetailDto.setDescription( entity.getDescription() );
        yearPlanDetailDto.setYearPlanQty( entity.getYearPlanQty() );
        yearPlanDetailDto.setYearPlanManHour( entity.getYearPlanManHour() );

        return yearPlanDetailDto;
    }

    @Override
    public List<YearPlanDetailDto> entityToDtoList(List<YearPlanDetail> entities) {
        if ( entities == null ) {
            return null;
        }

        List<YearPlanDetailDto> list = new ArrayList<YearPlanDetailDto>( entities.size() );
        for ( YearPlanDetail yearPlanDetail : entities ) {
            list.add( entityToDto( yearPlanDetail ) );
        }

        return list;
    }
}
