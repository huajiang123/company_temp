package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.QuarterPlanDetail;
import com.hondee.service_plan.dto.QuarterPlanDetailDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-13T16:25:41+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class QuarterPlanDetailMapperImpl implements QuarterPlanDetailMapper {

    @Override
    public QuarterPlanDetailDto entityToDto(QuarterPlanDetail entity) {
        if ( entity == null ) {
            return null;
        }

        QuarterPlanDetailDto quarterPlanDetailDto = new QuarterPlanDetailDto();

        quarterPlanDetailDto.setValidity( entity.getValidity() );
        quarterPlanDetailDto.setCreatedBy( entity.getCreatedBy() );
        quarterPlanDetailDto.setCreatedTime( entity.getCreatedTime() );
        quarterPlanDetailDto.setUpdatedBy( entity.getUpdatedBy() );
        quarterPlanDetailDto.setUpdatedTime( entity.getUpdatedTime() );
        quarterPlanDetailDto.setQuarterPlanDetailCode( entity.getQuarterPlanDetailCode() );
        quarterPlanDetailDto.setChildren( entityToDtoList( entity.getChildren() ) );
        quarterPlanDetailDto.setItemCode( entity.getItemCode() );
        quarterPlanDetailDto.setItemName( entity.getItemName() );
        quarterPlanDetailDto.setItemShowCode( entity.getItemShowCode() );
        quarterPlanDetailDto.setUom( entity.getUom() );
        quarterPlanDetailDto.setQty( entity.getQty() );
        quarterPlanDetailDto.setPlanManHour( entity.getPlanManHour() );
        quarterPlanDetailDto.setWeight( entity.getWeight() );
        quarterPlanDetailDto.setWeightPercent( entity.getWeightPercent() );
        quarterPlanDetailDto.setWeightTotalPercent( entity.getWeightTotalPercent() );
        quarterPlanDetailDto.setPlanBDate( entity.getPlanBDate() );
        quarterPlanDetailDto.setPlanEDate( entity.getPlanEDate() );
        quarterPlanDetailDto.setImportant( entity.getImportant() );
        quarterPlanDetailDto.setDescription( entity.getDescription() );
        quarterPlanDetailDto.setYearPlanQty( entity.getYearPlanQty() );
        quarterPlanDetailDto.setYearPlanManHour( entity.getYearPlanManHour() );
        quarterPlanDetailDto.setQuarterPlanQty( entity.getQuarterPlanQty() );
        quarterPlanDetailDto.setQuarterPlanManHour( entity.getQuarterPlanManHour() );

        return quarterPlanDetailDto;
    }

    @Override
    public List<QuarterPlanDetailDto> entityToDtoList(List<QuarterPlanDetail> entities) {
        if ( entities == null ) {
            return null;
        }

        List<QuarterPlanDetailDto> list = new ArrayList<QuarterPlanDetailDto>( entities.size() );
        for ( QuarterPlanDetail quarterPlanDetail : entities ) {
            list.add( entityToDto( quarterPlanDetail ) );
        }

        return list;
    }
}
