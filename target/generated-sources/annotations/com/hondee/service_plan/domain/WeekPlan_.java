package com.hondee.service_plan.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WeekPlan.class)
public abstract class WeekPlan_ extends com.hondee.service_plan.domain.BaseEntity_ {

	public static volatile SingularAttribute<WeekPlan, Integer> week;
	public static volatile SingularAttribute<WeekPlan, Integer> year;
	public static volatile SingularAttribute<WeekPlan, Integer> version;
	public static volatile SingularAttribute<WeekPlan, Integer> month;
	public static volatile SingularAttribute<WeekPlan, String> projectCode;
	public static volatile SingularAttribute<WeekPlan, Date> releasedTime;
	public static volatile SingularAttribute<WeekPlan, Date> startTime;
	public static volatile ListAttribute<WeekPlan, WeekPlanDetail> details;
	public static volatile SingularAttribute<WeekPlan, Date> endTime;
	public static volatile SingularAttribute<WeekPlan, String> basePlanCode;
	public static volatile SingularAttribute<WeekPlan, Boolean> released;
	public static volatile SingularAttribute<WeekPlan, String> weekPlanCode;
	public static volatile SingularAttribute<WeekPlan, Integer> quarter;

}

