package com.hondee.service_plan.mapper;

import com.hondee.service_plan.domain.BasePlan;
import com.hondee.service_plan.domain.BasePlanDetail;
import com.hondee.service_plan.domain.BasePlanDetailDraft;
import com.hondee.service_plan.dto.BasePlanDetailDto;
import com.hondee.service_plan.dto.BasePlanDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-13T16:25:41+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class BasePlanDetailMapperImpl implements BasePlanDetailMapper {

    @Override
    public BasePlanDetailDraft dtoToDraftEntity(BasePlanDetailDto dto) {
        if ( dto == null ) {
            return null;
        }

        BasePlanDetailDraft basePlanDetailDraft = new BasePlanDetailDraft();

        basePlanDetailDraft.setValidity( dto.getValidity() );
        basePlanDetailDraft.setCreatedBy( dto.getCreatedBy() );
        basePlanDetailDraft.setCreatedTime( dto.getCreatedTime() );
        basePlanDetailDraft.setUpdatedBy( dto.getUpdatedBy() );
        basePlanDetailDraft.setUpdatedTime( dto.getUpdatedTime() );
        basePlanDetailDraft.setItemCode( dto.getItemCode() );
        basePlanDetailDraft.setBasePlan( basePlanDtoToBasePlan( dto.getBasePlan() ) );
        basePlanDetailDraft.setParent( dtoToDraftEntity( dto.getParent() ) );
        basePlanDetailDraft.setChildren( basePlanDetailDtoListToBasePlanDetailDraftList( dto.getChildren() ) );
        basePlanDetailDraft.setItemName( dto.getItemName() );
        basePlanDetailDraft.setItemShowCode( dto.getItemShowCode() );
        basePlanDetailDraft.setUom( dto.getUom() );
        basePlanDetailDraft.setQty( dto.getQty() );
        basePlanDetailDraft.setPlanManHour( dto.getPlanManHour() );
        basePlanDetailDraft.setWeight( dto.getWeight() );
        basePlanDetailDraft.setWeightPercent( dto.getWeightPercent() );
        basePlanDetailDraft.setWeightTotalPercent( dto.getWeightTotalPercent() );
        basePlanDetailDraft.setPlanBDate( dto.getPlanBDate() );
        basePlanDetailDraft.setPlanEDate( dto.getPlanEDate() );
        basePlanDetailDraft.setImportant( dto.getImportant() );
        basePlanDetailDraft.setDescription( dto.getDescription() );
        basePlanDetailDraft.setReleased( dto.getReleased() );

        return basePlanDetailDraft;
    }

    @Override
    public BasePlanDetailDto draftEntityToDto(BasePlanDetailDraft entity) {
        if ( entity == null ) {
            return null;
        }

        BasePlanDetailDto basePlanDetailDto = new BasePlanDetailDto();

        basePlanDetailDto.setValidity( entity.getValidity() );
        basePlanDetailDto.setCreatedBy( entity.getCreatedBy() );
        basePlanDetailDto.setCreatedTime( entity.getCreatedTime() );
        basePlanDetailDto.setUpdatedBy( entity.getUpdatedBy() );
        basePlanDetailDto.setUpdatedTime( entity.getUpdatedTime() );
        basePlanDetailDto.setItemCode( entity.getItemCode() );
        basePlanDetailDto.setChildren( draftEntityToDtoList( entity.getChildren() ) );
        basePlanDetailDto.setItemName( entity.getItemName() );
        basePlanDetailDto.setItemShowCode( entity.getItemShowCode() );
        basePlanDetailDto.setUom( entity.getUom() );
        basePlanDetailDto.setQty( entity.getQty() );
        basePlanDetailDto.setPlanManHour( entity.getPlanManHour() );
        basePlanDetailDto.setWeight( entity.getWeight() );
        basePlanDetailDto.setWeightPercent( entity.getWeightPercent() );
        basePlanDetailDto.setWeightTotalPercent( entity.getWeightTotalPercent() );
        basePlanDetailDto.setPlanBDate( entity.getPlanBDate() );
        basePlanDetailDto.setPlanEDate( entity.getPlanEDate() );
        basePlanDetailDto.setImportant( entity.getImportant() );
        basePlanDetailDto.setDescription( entity.getDescription() );
        basePlanDetailDto.setReleased( entity.getReleased() );

        return basePlanDetailDto;
    }

    @Override
    public List<BasePlanDetailDto> draftEntityToDtoList(List<BasePlanDetailDraft> entities) {
        if ( entities == null ) {
            return null;
        }

        List<BasePlanDetailDto> list = new ArrayList<BasePlanDetailDto>( entities.size() );
        for ( BasePlanDetailDraft basePlanDetailDraft : entities ) {
            list.add( draftEntityToDto( basePlanDetailDraft ) );
        }

        return list;
    }

    @Override
    public BasePlanDetailDto entityToDto(BasePlanDetail entity) {
        if ( entity == null ) {
            return null;
        }

        BasePlanDetailDto basePlanDetailDto = new BasePlanDetailDto();

        basePlanDetailDto.setValidity( entity.getValidity() );
        basePlanDetailDto.setCreatedBy( entity.getCreatedBy() );
        basePlanDetailDto.setCreatedTime( entity.getCreatedTime() );
        basePlanDetailDto.setUpdatedBy( entity.getUpdatedBy() );
        basePlanDetailDto.setUpdatedTime( entity.getUpdatedTime() );
        basePlanDetailDto.setItemCode( entity.getItemCode() );
        basePlanDetailDto.setChildren( entityToDtoList( entity.getChildren() ) );
        basePlanDetailDto.setItemName( entity.getItemName() );
        basePlanDetailDto.setItemShowCode( entity.getItemShowCode() );
        basePlanDetailDto.setUom( entity.getUom() );
        basePlanDetailDto.setQty( entity.getQty() );
        basePlanDetailDto.setPlanManHour( entity.getPlanManHour() );
        basePlanDetailDto.setWeight( entity.getWeight() );
        basePlanDetailDto.setWeightPercent( entity.getWeightPercent() );
        basePlanDetailDto.setWeightTotalPercent( entity.getWeightTotalPercent() );
        basePlanDetailDto.setPlanBDate( entity.getPlanBDate() );
        basePlanDetailDto.setPlanEDate( entity.getPlanEDate() );
        basePlanDetailDto.setImportant( entity.getImportant() );
        basePlanDetailDto.setDescription( entity.getDescription() );

        return basePlanDetailDto;
    }

    @Override
    public List<BasePlanDetailDto> entityToDtoList(List<BasePlanDetail> entities) {
        if ( entities == null ) {
            return null;
        }

        List<BasePlanDetailDto> list = new ArrayList<BasePlanDetailDto>( entities.size() );
        for ( BasePlanDetail basePlanDetail : entities ) {
            list.add( entityToDto( basePlanDetail ) );
        }

        return list;
    }

    @Override
    public List<BasePlanDetail> dtoToEntityList(List<BasePlanDetailDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<BasePlanDetail> list = new ArrayList<BasePlanDetail>( dtos.size() );
        for ( BasePlanDetailDto basePlanDetailDto : dtos ) {
            list.add( basePlanDetailDtoToBasePlanDetail( basePlanDetailDto ) );
        }

        return list;
    }

    protected List<BasePlanDetailDraft> basePlanDetailDtoListToBasePlanDetailDraftList(List<BasePlanDetailDto> list) {
        if ( list == null ) {
            return null;
        }

        List<BasePlanDetailDraft> list1 = new ArrayList<BasePlanDetailDraft>( list.size() );
        for ( BasePlanDetailDto basePlanDetailDto : list ) {
            list1.add( dtoToDraftEntity( basePlanDetailDto ) );
        }

        return list1;
    }

    protected BasePlan basePlanDtoToBasePlan(BasePlanDto basePlanDto) {
        if ( basePlanDto == null ) {
            return null;
        }

        BasePlan basePlan = new BasePlan();

        basePlan.setValidity( basePlanDto.getValidity() );
        basePlan.setCreatedBy( basePlanDto.getCreatedBy() );
        basePlan.setCreatedTime( basePlanDto.getCreatedTime() );
        basePlan.setUpdatedBy( basePlanDto.getUpdatedBy() );
        basePlan.setUpdatedTime( basePlanDto.getUpdatedTime() );
        basePlan.setPlanCode( basePlanDto.getPlanCode() );
        basePlan.setProjectCode( basePlanDto.getProjectCode() );
        basePlan.setVersion( basePlanDto.getVersion() );
        basePlan.setReleased( basePlanDto.getReleased() );
        basePlan.setReleasedTime( basePlanDto.getReleasedTime() );
        basePlan.setDetails( dtoToEntityList( basePlanDto.getDetails() ) );
        basePlan.setDrafts( basePlanDetailDtoListToBasePlanDetailDraftList( basePlanDto.getDrafts() ) );

        return basePlan;
    }

    protected BasePlanDetail basePlanDetailDtoToBasePlanDetail(BasePlanDetailDto basePlanDetailDto) {
        if ( basePlanDetailDto == null ) {
            return null;
        }

        BasePlanDetail basePlanDetail = new BasePlanDetail();

        basePlanDetail.setValidity( basePlanDetailDto.getValidity() );
        basePlanDetail.setCreatedBy( basePlanDetailDto.getCreatedBy() );
        basePlanDetail.setCreatedTime( basePlanDetailDto.getCreatedTime() );
        basePlanDetail.setUpdatedBy( basePlanDetailDto.getUpdatedBy() );
        basePlanDetail.setUpdatedTime( basePlanDetailDto.getUpdatedTime() );
        basePlanDetail.setItemName( basePlanDetailDto.getItemName() );
        basePlanDetail.setItemShowCode( basePlanDetailDto.getItemShowCode() );
        basePlanDetail.setUom( basePlanDetailDto.getUom() );
        basePlanDetail.setQty( basePlanDetailDto.getQty() );
        basePlanDetail.setWeight( basePlanDetailDto.getWeight() );
        basePlanDetail.setPlanManHour( basePlanDetailDto.getPlanManHour() );
        basePlanDetail.setItemCode( basePlanDetailDto.getItemCode() );
        basePlanDetail.setBasePlan( basePlanDtoToBasePlan( basePlanDetailDto.getBasePlan() ) );
        basePlanDetail.setParent( basePlanDetailDtoToBasePlanDetail( basePlanDetailDto.getParent() ) );
        basePlanDetail.setChildren( dtoToEntityList( basePlanDetailDto.getChildren() ) );
        basePlanDetail.setWeightPercent( basePlanDetailDto.getWeightPercent() );
        basePlanDetail.setWeightTotalPercent( basePlanDetailDto.getWeightTotalPercent() );
        basePlanDetail.setPlanBDate( basePlanDetailDto.getPlanBDate() );
        basePlanDetail.setPlanEDate( basePlanDetailDto.getPlanEDate() );
        basePlanDetail.setImportant( basePlanDetailDto.getImportant() );
        basePlanDetail.setDescription( basePlanDetailDto.getDescription() );

        return basePlanDetail;
    }
}
