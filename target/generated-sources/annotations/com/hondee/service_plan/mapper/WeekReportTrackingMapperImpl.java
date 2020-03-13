package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.WeekReportDetail;
import com.hondee.service_plan.domain.WeekReportTracking;
import com.hondee.service_plan.dto.WeekReportTrackingDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-13T16:25:41+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class WeekReportTrackingMapperImpl implements WeekReportTrackingMapper {

    @Override
    public WeekReportTrackingDto entityToDto(WeekReportTracking entity) {
        if ( entity == null ) {
            return null;
        }

        WeekReportTrackingDto weekReportTrackingDto = new WeekReportTrackingDto();

        String weekReportDetailCode = entityWeekReportDetailWeekReportDetailCode( entity );
        if ( weekReportDetailCode != null ) {
            weekReportTrackingDto.setWeekReportDetailCode( weekReportDetailCode );
        }
        weekReportTrackingDto.setValidity( entity.getValidity() );
        weekReportTrackingDto.setCreatedBy( entity.getCreatedBy() );
        weekReportTrackingDto.setCreatedTime( entity.getCreatedTime() );
        weekReportTrackingDto.setUpdatedBy( entity.getUpdatedBy() );
        weekReportTrackingDto.setUpdatedTime( entity.getUpdatedTime() );
        weekReportTrackingDto.setWeekTrackingCode( entity.getWeekTrackingCode() );
        weekReportTrackingDto.setWeekReportCode( entity.getWeekReportCode() );
        weekReportTrackingDto.setPeriodNo( entity.getPeriodNo() );
        weekReportTrackingDto.setPeriodDay( entity.getPeriodDay() );
        weekReportTrackingDto.setBasePlanQty( entity.getBasePlanQty() );
        weekReportTrackingDto.setStartQty( entity.getStartQty() );
        weekReportTrackingDto.setReportQty( entity.getReportQty() );
        weekReportTrackingDto.setEndQty( entity.getEndQty() );
        weekReportTrackingDto.setStartManHour( entity.getStartManHour() );
        weekReportTrackingDto.setReportManHour( entity.getReportManHour() );
        weekReportTrackingDto.setEndManHour( entity.getEndManHour() );

        return weekReportTrackingDto;
    }

    @Override
    public List<WeekReportTrackingDto> entityToDtoList(List<WeekReportTracking> entities) {
        if ( entities == null ) {
            return null;
        }

        List<WeekReportTrackingDto> list = new ArrayList<WeekReportTrackingDto>( entities.size() );
        for ( WeekReportTracking weekReportTracking : entities ) {
            list.add( entityToDto( weekReportTracking ) );
        }

        return list;
    }

    private String entityWeekReportDetailWeekReportDetailCode(WeekReportTracking weekReportTracking) {
        if ( weekReportTracking == null ) {
            return null;
        }
        WeekReportDetail weekReportDetail = weekReportTracking.getWeekReportDetail();
        if ( weekReportDetail == null ) {
            return null;
        }
        String weekReportDetailCode = weekReportDetail.getWeekReportDetailCode();
        if ( weekReportDetailCode == null ) {
            return null;
        }
        return weekReportDetailCode;
    }
}
