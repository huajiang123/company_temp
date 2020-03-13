package com.hondee.service_plan.dao;

import com.hondee.service_plan.domain.BasePlan;
import com.hondee.service_plan.domain.BasePlanDetailDraft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasePlanDetailDraftDao extends JpaRepository<BasePlanDetailDraft, String> {
    BasePlanDetailDraft findFirstByBasePlanAndParentIsNullOrderBySequenceDesc(BasePlan basePlan);

    BasePlanDetailDraft findFirstByParentOrderBySequenceDesc(BasePlanDetailDraft parent);

    List<BasePlanDetailDraft> findByProjectCodeAndReleasedOrderBySequenceAsc(String projectCode, boolean released);

    List<BasePlanDetailDraft> findByParent(BasePlanDetailDraft parent);
}
