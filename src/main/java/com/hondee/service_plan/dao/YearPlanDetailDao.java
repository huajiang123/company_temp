package com.hondee.service_plan.dao;

import com.hondee.service_plan.domain.YearPlanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface YearPlanDetailDao extends JpaRepository<YearPlanDetail, String> {
    @Query(value = "select * from plan_yearplandetail where YearPlanCode = ?1", nativeQuery = true)
    List<YearPlanDetail> findByYearPlanCode(String yearPlanCode);

    YearPlanDetail findByYearAndItemCode(int year, String itemCode);

    void deleteByProjectCodeAndYear(String projectCode, int year);

    List<YearPlanDetail> findByProjectCodeAndYear(String projectCode, int year);
}
