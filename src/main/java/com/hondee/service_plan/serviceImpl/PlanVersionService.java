package com.hondee.service_plan.serviceImpl;

import com.hondee.common.spring.dao.IVersionDao;
import com.hondee.common.spring.service.impl.VersionBaseService;
import com.hondee.service_plan.domain.PlanVersion;
import com.hondee.service_plan.service.IPlanVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PlanVersionService extends VersionBaseService<PlanVersion, String> implements IPlanVersionService {
    @Autowired()
    @Override
    @Qualifier("plan_versionDao")
    public void setVersionDao(IVersionDao<PlanVersion, String> versionDao) {
        this.versionDao = versionDao;
    }
}
