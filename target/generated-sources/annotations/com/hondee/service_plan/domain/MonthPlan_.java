package com.hondee.service_plan.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MonthPlan.class)
public abstract class MonthPlan_ extends com.hondee.service_plan.domain.BaseEntity_ {

	public static volatile SingularAttribute<MonthPlan, Integer> month;
	public static volatile SingularAttribute<MonthPlan, Integer> year;
	public static volatile SingularAttribute<MonthPlan, String> projectCode;
	public static volatile SingularAttribute<MonthPlan, Date> releasedTime;
	public static volatile SingularAttribute<MonthPlan, String> monthPlanCode;
	public static volatile ListAttribute<MonthPlan, MonthPlanDetail> details;
	public static volatile SingularAttribute<MonthPlan, Integer> version;
	public static volatile SingularAttribute<MonthPlan, String> basePlanCode;
	public static volatile SingularAttribute<MonthPlan, Boolean> released;
	public static volatile SingularAttribute<MonthPlan, Integer> quarter;

}

