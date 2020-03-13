package com.hondee.service_plan.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(QuarterPlan.class)
public abstract class QuarterPlan_ extends com.hondee.service_plan.domain.BaseEntity_ {

	public static volatile SingularAttribute<QuarterPlan, Integer> year;
	public static volatile SingularAttribute<QuarterPlan, String> projectCode;
	public static volatile SingularAttribute<QuarterPlan, Date> releasedTime;
	public static volatile ListAttribute<QuarterPlan, QuarterPlanDetail> details;
	public static volatile SingularAttribute<QuarterPlan, String> quarterPlanCode;
	public static volatile SingularAttribute<QuarterPlan, Integer> version;
	public static volatile SingularAttribute<QuarterPlan, String> basePlanCode;
	public static volatile SingularAttribute<QuarterPlan, Boolean> released;
	public static volatile SingularAttribute<QuarterPlan, Integer> quarter;

}

