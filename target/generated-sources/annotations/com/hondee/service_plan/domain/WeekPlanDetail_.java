package com.hondee.service_plan.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WeekPlanDetail.class)
public abstract class WeekPlanDetail_ extends com.hondee.service_plan.domain.BaseEntity_ {

	public static volatile SingularAttribute<WeekPlanDetail, WeekPlanDetail> parent;
	public static volatile SingularAttribute<WeekPlanDetail, Integer> week;
	public static volatile SingularAttribute<WeekPlanDetail, Double> weightPercent;
	public static volatile SingularAttribute<WeekPlanDetail, Integer> year;
	public static volatile SingularAttribute<WeekPlanDetail, Double> quarterPlanQty;
	public static volatile SingularAttribute<WeekPlanDetail, String> itemShowCode;
	public static volatile SingularAttribute<WeekPlanDetail, String> itemCode;
	public static volatile SingularAttribute<WeekPlanDetail, Date> planEDate;
	public static volatile SingularAttribute<WeekPlanDetail, String> description;
	public static volatile SingularAttribute<WeekPlanDetail, Double> monthPlanQty;
	public static volatile SingularAttribute<WeekPlanDetail, Integer> quarterPlanManHour;
	public static volatile SingularAttribute<WeekPlanDetail, String> itemName;
	public static volatile SingularAttribute<WeekPlanDetail, String> uom;
	public static volatile SingularAttribute<WeekPlanDetail, String> projectCode;
	public static volatile ListAttribute<WeekPlanDetail, WeekPlanDetail> children;
	public static volatile SingularAttribute<WeekPlanDetail, Double> weekPlanQty;
	public static volatile SingularAttribute<WeekPlanDetail, WeekPlan> weekPlan;
	public static volatile SingularAttribute<WeekPlanDetail, Integer> planManHour;
	public static volatile SingularAttribute<WeekPlanDetail, Double> yearPlanQty;
	public static volatile SingularAttribute<WeekPlanDetail, Date> planBDate;
	public static volatile SingularAttribute<WeekPlanDetail, Integer> yearPlanManHour;
	public static volatile SingularAttribute<WeekPlanDetail, Integer> weekPlanManHour;
	public static volatile SingularAttribute<WeekPlanDetail, Integer> weight;
	public static volatile SingularAttribute<WeekPlanDetail, Double> weightTotalPercent;
	public static volatile SingularAttribute<WeekPlanDetail, Boolean> important;
	public static volatile SingularAttribute<WeekPlanDetail, String> sequence;
	public static volatile SingularAttribute<WeekPlanDetail, Integer> month;
	public static volatile SingularAttribute<WeekPlanDetail, Double> qty;
	public static volatile SingularAttribute<WeekPlanDetail, Integer> monthPlanManHour;
	public static volatile SingularAttribute<WeekPlanDetail, String> weekPlanDetailCode;
	public static volatile SingularAttribute<WeekPlanDetail, Integer> quarter;

}

