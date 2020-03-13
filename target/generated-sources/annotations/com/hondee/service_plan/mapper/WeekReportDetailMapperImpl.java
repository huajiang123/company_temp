package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.WeekReportDetail;
import com.hondee.service_plan.domain.WeekReportTracking;
import com.hondee.service_plan.dto.WeekReportDetailDto;
import com.hondee.service_plan.dto.WeekReportTrackingDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-13T16:25:41+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class WeekReportDetailMapperImpl implements WeekReportDetailMapper {

    @Override
    public WeekReportDetailDto entityToDto(WeekReportDetail entity) {
        if ( entity == null ) {
            return null;
        }

        WeekReportDetailDto weekReportDetailDto = new WeekReportDetailDto();

        weekReportDetailDto.setValidity( entity.getValidity() );
        weekReportDetailDto.setCreatedBy( entity.getCreatedBy() );
        weekReportDetailDto.setCreatedTime( entity.getCreatedTime() );
        weekReportDetailDto.setUpdatedBy( entity.getUpdatedBy() );
        weekReportDetailDto.setUpdatedTime( entity.getUpdatedTime() );
        weekReportDetailDto.setWeekReportDetailCode( entity.getWeekReportDetailCode() );
        weekReportDetailDto.setChildren( entityToDtoList( entity.getChildren() ) );
        weekReportDetailDto.setItemCode( entity.getItemCode() );
        weekReportDetailDto.setItemName( entity.getItemName() );
        weekReportDetailDto.setItemShowCode( entity.getItemShowCode() );
        weekReportDetailDto.setUom( entity.getUom() );
        weekReportDetailDto.setQty( entity.getQty() );
        weekReportDetailDto.setPlanManHour( entity.getPlanManHour() );
        weekReportDetailDto.setWeight( entity.getWeight() );
        weekReportDetailDto.setWeightPercent( entity.getWeightPercent() );
        weekReportDetailDto.setWeightTotalPercent( entity.getWeightTotalPercent() );
        weekReportDetailDto.setPlanBDate( entity.getPlanBDate() );
        weekReportDetailDto.setPlanEDate( entity.getPlanEDate() );
        weekReportDetailDto.setImportant( entity.getImportant() );
        weekReportDetailDto.setDescription( entity.getDescription() );
        weekReportDetailDto.setYearPlanQty( entity.getYearPlanQty() );
        weekReportDetailDto.setYearPlanManHour( entity.getYearPlanManHour() );
        weekReportDetailDto.setQuarterPlanQty( entity.getQuarterPlanQty() );
        weekReportDetailDto.setQuarterPlanManHour( entity.getQuarterPlanManHour() );
        weekReportDetailDto.setMonthPlanQty( entity.getMonthPlanQty() );
        weekReportDetailDto.setMonthPlanManHour( entity.getMonthPlanManHour() );
        weekReportDetailDto.setWeekPlanQty( entity.getWeekPlanQty() );
        weekReportDetailDto.setWeekPlanManHour( entity.getWeekPlanManHour() );
        weekReportDetailDto.setEndQty( entity.getEndQty() );
        weekReportDetailDto.setEndManHour( entity.getEndManHour() );
        weekReportDetailDto.setProgress( entity.getProgress() );
        weekReportDetailDto.setActualStartTime( entity.getActualStartTime() );
        weekReportDetailDto.setActualEndTime( entity.getActualEndTime() );
        weekReportDetailDto.setStat( entity.getStat() );
        weekReportDetailDto.setTrackings( weekReportTrackingListToWeekReportTrackingDtoList( entity.getTrackings() ) );

        return weekReportDetailDto;
    }

    @Override
    public List<WeekReportDetailDto> entityToDtoList(List<WeekReportDetail> entities) {
        if ( entities == null ) {
            return null;
        }

        List<WeekReportDetailDto> list = new ArrayList<WeekReportDetailDto>( entities.size() );
        for ( WeekReportDetail weekReportDetail : entities ) {
            list.add( entityToDto( weekReportDetail ) );
        }

        return list;
    }

    protected WeekReportTrackingDto weekReportTrackingToWeekReportTrackingDto(WeekReportTracking weekReportTracking) {
        if ( weekReportTracking == null ) {
            return null;
        }

        WeekReportTrackingDto weekReportTrackingDto = new WeekReportTrackingDto();

        weekReportTrackingDto.setValidity( weekReportTracking.getValidity() );
        weekReportTrackingDto.setCreatedBy( weekReportTracking.getCreatedBy() );
        weekReportTrackingDto.setCreatedTime( weekReportTracking.getCreatedTime() );
        weekReportTrackingDto.setUpdatedBy( weekReportTracking.getUpdatedBy() );
        weekReportTrackingDto.setUpdatedTime( weekReportTracking.getUpdatedTime() );
        weekReportTrackingDto.setWeekTrackingCode( weekReportTracking.getWeekTrackingCode() );
        weekReportTrackingDto.setWeekReportCode( weekReportTracking.getWeekReportCode() );
        weekReportTrackingDto.setPeriodNo( weekReportTracking.getPeriodNo() );
        weekReportTrackingDto.setPeriodDay( weekReportTracking.getPeriodDay() );
        weekReportTrackingDto.setBasePlanQty( weekReportTracking.getBasePlanQty() );
        weekReportTrackingDto.setStartQty( weekReportTracking.getStartQty() );
        weekReportTrackingDto.setReportQty( weekReportTracking.getReportQty() );
        weekReportTrackingDto.setEndQty( weekReportTracking.getEndQty() );
        weekReportTrackingDto.setStartManHour( weekReportTracking.getStartManHour() );
        weekReportTrackingDto.setReportManHour( weekReportTracking.getReportManHour() );
        weekReportTrackingDto.setEndManHour( weekReportTracking.getEndManHour() );

        return weekReportTrackingDto;
    }

    protected List<WeekReportTrackingDto> weekReportTrackingListToWeekReportTrackingDtoList(List<WeekReportTracking> list) {
        if ( list == null ) {
            return null;
        }

        List<WeekReportTrackingDto> list1 = new ArrayList<WeekReportTrackingDto>( list.size() );
        for ( WeekReportTracking weekReportTracking : list ) {
            list1.add( weekReportTrackingToWeekReportTrackingDto( weekReportTracking ) );
        }

        return list1;
    }
}
