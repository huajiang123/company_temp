package com.hondee.service_plan.dao;



import com.hondee.service_plan.domain.BasePlanDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface BasePlanDetailDao extends JpaRepository<BasePlanDetail, String> {
    List<BasePlanDetail> findByProjectCodeAndValidityAndPlanBDateBefore(String projectCode, boolean validity, Date planBDate);

    List<BasePlanDetail> findByProjectCodeAndValidity(String projectCode, boolean validity);
}
