package com.hondee.service_plan.dao;


import com.hondee.service_plan.domain.BasePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BasePlanDao extends JpaRepository<BasePlan, String> {
    @Query(value = "select * from plan_baseplan where projectCode = ?1", nativeQuery = true)
    BasePlan findByProjectCode(String projectCode);
}
