package com.hondee.service_plan.dao;

import com.hondee.service_plan.domain.QuarterPlanDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuarterPlanDetailDao extends JpaRepository<QuarterPlanDetail, String> {
    void deleteByProjectCodeAndYearAndQuarter(String projectCode, int year, int quarter);

    List<QuarterPlanDetail> findByProjectCodeAndYearAndQuarter(String projectCode, int year, int quarter);

    QuarterPlanDetail findByYearAndQuarterAndItemCode(int year, int quarter, String itemCode);
}
