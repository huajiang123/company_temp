package com.hondee.service_plan.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BasePlan.class)
public abstract class BasePlan_ extends com.hondee.service_plan.domain.BaseEntity_ {

	public static volatile SingularAttribute<BasePlan, String> projectCode;
	public static volatile SingularAttribute<BasePlan, Date> releasedTime;
	public static volatile ListAttribute<BasePlan, BasePlanDetail> details;
	public static volatile ListAttribute<BasePlan, BasePlanDetailDraft> drafts;
	public static volatile SingularAttribute<BasePlan, Integer> version;
	public static volatile SingularAttribute<BasePlan, String> planCode;
	public static volatile SingularAttribute<BasePlan, Boolean> released;

}

