package com.hondee.service_plan.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(YearPlan.class)
public abstract class YearPlan_ extends com.hondee.service_plan.domain.BaseEntity_ {

	public static volatile SingularAttribute<YearPlan, String> yearPlanCode;
	public static volatile SingularAttribute<YearPlan, Integer> year;
	public static volatile SingularAttribute<YearPlan, String> projectCode;
	public static volatile SingularAttribute<YearPlan, Date> releasedTime;
	public static volatile ListAttribute<YearPlan, YearPlanDetail> details;
	public static volatile SingularAttribute<YearPlan, Integer> version;
	public static volatile SingularAttribute<YearPlan, String> basePlanCode;
	public static volatile SingularAttribute<YearPlan, Boolean> released;

}

