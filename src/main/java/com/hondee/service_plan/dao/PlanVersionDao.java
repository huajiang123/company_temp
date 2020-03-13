package com.hondee.service_plan.dao;


import com.hondee.common.spring.dao.IVersionDao;
import com.hondee.service_plan.domain.PlanVersion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "plan_versionDao")
public interface PlanVersionDao extends IVersionDao<PlanVersion, String> {
    @Query(value = "select max(u.version) from PlanVersion u where u.businessNo=?1 and u.type=?2")
    Integer maxVersion(String businessNo, int type);

    PlanVersion findByBusinessNoAndVersion(String businessNo, int version);
}
