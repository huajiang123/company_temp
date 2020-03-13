package com.hondee.service_plan.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MonthPlanDetail.class)
public abstract class MonthPlanDetail_ extends com.hondee.service_plan.domain.BaseEntity_ {

	public static volatile SingularAttribute<MonthPlanDetail, MonthPlanDetail> parent;
	public static volatile SingularAttribute<MonthPlanDetail, Double> weightPercent;
	public static volatile SingularAttribute<MonthPlanDetail, Integer> year;
	public static volatile SingularAttribute<MonthPlanDetail, Double> quarterPlanQty;
	public static volatile SingularAttribute<MonthPlanDetail, String> itemShowCode;
	public static volatile SingularAttribute<MonthPlanDetail, String> itemCode;
	public static volatile SingularAttribute<MonthPlanDetail, Date> planEDate;
	public static volatile SingularAttribute<MonthPlanDetail, String> description;
	public static volatile SingularAttribute<MonthPlanDetail, MonthPlan> monthPlan;
	public static volatile SingularAttribute<MonthPlanDetail, Double> monthPlanQty;
	public static volatile SingularAttribute<MonthPlanDetail, Integer> quarterPlanManHour;
	public static volatile SingularAttribute<MonthPlanDetail, String> itemName;
	public static volatile SingularAttribute<MonthPlanDetail, String> uom;
	public static volatile SingularAttribute<MonthPlanDetail, String> projectCode;
	public static volatile ListAttribute<MonthPlanDetail, MonthPlanDetail> children;
	public static volatile SingularAttribute<MonthPlanDetail, Integer> planManHour;
	public static volatile SingularAttribute<MonthPlanDetail, Double> yearPlanQty;
	public static volatile SingularAttribute<MonthPlanDetail, Date> planBDate;
	public static volatile SingularAttribute<MonthPlanDetail, Integer> yearPlanManHour;
	public static volatile SingularAttribute<MonthPlanDetail, Integer> weight;
	public static volatile SingularAttribute<MonthPlanDetail, Double> weightTotalPercent;
	public static volatile SingularAttribute<MonthPlanDetail, Boolean> important;
	public static volatile SingularAttribute<MonthPlanDetail, String> sequence;
	public static volatile SingularAttribute<MonthPlanDetail, String> monthPlanDetailCode;
	public static volatile SingularAttribute<MonthPlanDetail, Integer> month;
	public static volatile SingularAttribute<MonthPlanDetail, Double> qty;
	public static volatile SingularAttribute<MonthPlanDetail, Integer> monthPlanManHour;
	public static volatile SingularAttribute<MonthPlanDetail, Integer> quarter;

}

