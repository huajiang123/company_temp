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
public class BasePlanMapperImpl implements BasePlanMapper {

    @Override
    public BasePlan dtoToEntity(BasePlanDto dto) {
        if ( dto == null ) {
            return null;
        }

        BasePlan basePlan = new BasePlan();

        basePlan.setValidity( dto.getValidity() );
        basePlan.setCreatedBy( dto.getCreatedBy() );
        basePlan.setCreatedTime( dto.getCreatedTime() );
        basePlan.setUpdatedBy( dto.getUpdatedBy() );
        basePlan.setUpdatedTime( dto.getUpdatedTime() );
        basePlan.setPlanCode( dto.getPlanCode() );
        basePlan.setProjectCode( dto.getProjectCode() );
        basePlan.setVersion( dto.getVersion() );
        basePlan.setReleased( dto.getReleased() );
        basePlan.setReleasedTime( dto.getReleasedTime() );
        basePlan.setDetails( basePlanDetailDtoListToBasePlanDetailList( dto.getDetails() ) );
        basePlan.setDrafts( basePlanDetailDtoListToBasePlanDetailDraftList( dto.getDrafts() ) );

        return basePlan;
    }

    @Override
    public BasePlanDto entityToDto(BasePlan entity) {
        if ( entity == null ) {
            return null;
        }

        BasePlanDto basePlanDto = new BasePlanDto();

        basePlanDto.setValidity( entity.getValidity() );
        basePlanDto.setCreatedBy( entity.getCreatedBy() );
        basePlanDto.setCreatedTime( entity.getCreatedTime() );
        basePlanDto.setUpdatedBy( entity.getUpdatedBy() );
        basePlanDto.setUpdatedTime( entity.getUpdatedTime() );
        basePlanDto.setPlanCode( entity.getPlanCode() );
        basePlanDto.setProjectCode( entity.getProjectCode() );
        basePlanDto.setVersion( entity.getVersion() );
        basePlanDto.setReleased( entity.getReleased() );
        basePlanDto.setReleasedTime( entity.getReleasedTime() );

        return basePlanDto;
    }

    protected List<BasePlanDetail> basePlanDetailDtoListToBasePlanDetailList(List<BasePlanDetailDto> list) {
        if ( list == null ) {
            return null;
        }

        List<BasePlanDetail> list1 = new ArrayList<BasePlanDetail>( list.size() );
        for ( BasePlanDetailDto basePlanDetailDto : list ) {
            list1.add( basePlanDetailDtoToBasePlanDetail( basePlanDetailDto ) );
        }

        return list1;
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
        basePlanDetail.setBasePlan( dtoToEntity( basePlanDetailDto.getBasePlan() ) );
        basePlanDetail.setParent( basePlanDetailDtoToBasePlanDetail( basePlanDetailDto.getParent() ) );
        basePlanDetail.setChildren( basePlanDetailDtoListToBasePlanDetailList( basePlanDetailDto.getChildren() ) );
        basePlanDetail.setWeightPercent( basePlanDetailDto.getWeightPercent() );
        basePlanDetail.setWeightTotalPercent( basePlanDetailDto.getWeightTotalPercent() );
        basePlanDetail.setPlanBDate( basePlanDetailDto.getPlanBDate() );
        basePlanDetail.setPlanEDate( basePlanDetailDto.getPlanEDate() );
        basePlanDetail.setImportant( basePlanDetailDto.getImportant() );
        basePlanDetail.setDescription( basePlanDetailDto.getDescription() );

        return basePlanDetail;
    }

    protected List<BasePlanDetailDraft> basePlanDetailDtoListToBasePlanDetailDraftList(List<BasePlanDetailDto> list) {
        if ( list == null ) {
            return null;
        }

        List<BasePlanDetailDraft> list1 = new ArrayList<BasePlanDetailDraft>( list.size() );
        for ( BasePlanDetailDto basePlanDetailDto : list ) {
            list1.add( basePlanDetailDtoToBasePlanDetailDraft( basePlanDetailDto ) );
        }

        return list1;
    }

    protected BasePlanDetailDraft basePlanDetailDtoToBasePlanDetailDraft(BasePlanDetailDto basePlanDetailDto) {
        if ( basePlanDetailDto == null ) {
            return null;
        }

        BasePlanDetailDraft basePlanDetailDraft = new BasePlanDetailDraft();

        basePlanDetailDraft.setValidity( basePlanDetailDto.getValidity() );
        basePlanDetailDraft.setCreatedBy( basePlanDetailDto.getCreatedBy() );
        basePlanDetailDraft.setCreatedTime( basePlanDetailDto.getCreatedTime() );
        basePlanDetailDraft.setUpdatedBy( basePlanDetailDto.getUpdatedBy() );
        basePlanDetailDraft.setUpdatedTime( basePlanDetailDto.getUpdatedTime() );
        basePlanDetailDraft.setItemCode( basePlanDetailDto.getItemCode() );
        basePlanDetailDraft.setBasePlan( dtoToEntity( basePlanDetailDto.getBasePlan() ) );
        basePlanDetailDraft.setParent( basePlanDetailDtoToBasePlanDetailDraft( basePlanDetailDto.getParent() ) );
        basePlanDetailDraft.setChildren( basePlanDetailDtoListToBasePlanDetailDraftList( basePlanDetailDto.getChildren() ) );
        basePlanDetailDraft.setItemName( basePlanDetailDto.getItemName() );
        basePlanDetailDraft.setItemShowCode( basePlanDetailDto.getItemShowCode() );
        basePlanDetailDraft.setUom( basePlanDetailDto.getUom() );
        basePlanDetailDraft.setQty( basePlanDetailDto.getQty() );
        basePlanDetailDraft.setPlanManHour( basePlanDetailDto.getPlanManHour() );
        basePlanDetailDraft.setWeight( basePlanDetailDto.getWeight() );
        basePlanDetailDraft.setWeightPercent( basePlanDetailDto.getWeightPercent() );
        basePlanDetailDraft.setWeightTotalPercent( basePlanDetailDto.getWeightTotalPercent() );
        basePlanDetailDraft.setPlanBDate( basePlanDetailDto.getPlanBDate() );
        basePlanDetailDraft.setPlanEDate( basePlanDetailDto.getPlanEDate() );
        basePlanDetailDraft.setImportant( basePlanDetailDto.getImportant() );
        basePlanDetailDraft.setDescription( basePlanDetailDto.getDescription() );
        basePlanDetailDraft.setReleased( basePlanDetailDto.getReleased() );

        return basePlanDetailDraft;
    }
}
