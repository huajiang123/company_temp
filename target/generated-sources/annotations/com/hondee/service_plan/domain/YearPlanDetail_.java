package com.hondee.service_plan.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(YearPlanDetail.class)
public abstract class YearPlanDetail_ extends com.hondee.service_plan.domain.BaseEntity_ {

	public static volatile SingularAttribute<YearPlanDetail, YearPlanDetail> parent;
	public static volatile SingularAttribute<YearPlanDetail, Double> weightPercent;
	public static volatile SingularAttribute<YearPlanDetail, Date> planBDate;
	public static volatile SingularAttribute<YearPlanDetail, Integer> year;
	public static volatile SingularAttribute<YearPlanDetail, String> yearPlanDetailCode;
	public static volatile SingularAttribute<YearPlanDetail, String> itemShowCode;
	public static volatile SingularAttribute<YearPlanDetail, String> itemCode;
	public static volatile SingularAttribute<YearPlanDetail, Date> planEDate;
	public static volatile SingularAttribute<YearPlanDetail, Integer> yearPlanManHour;
	public static volatile SingularAttribute<YearPlanDetail, Integer> weight;
	public static volatile SingularAttribute<YearPlanDetail, String> description;
	public static volatile SingularAttribute<YearPlanDetail, Double> weightTotalPercent;
	public static volatile SingularAttribute<YearPlanDetail, YearPlan> yearPlan;
	public static volatile SingularAttribute<YearPlanDetail, Boolean> important;
	public static volatile SingularAttribute<YearPlanDetail, String> sequence;
	public static volatile SingularAttribute<YearPlanDetail, String> itemName;
	public static volatile SingularAttribute<YearPlanDetail, String> uom;
	public static volatile SingularAttribute<YearPlanDetail, String> projectCode;
	public static volatile ListAttribute<YearPlanDetail, YearPlanDetail> children;
	public static volatile SingularAttribute<YearPlanDetail, Double> qty;
	public static volatile SingularAttribute<YearPlanDetail, Integer> planManHour;
	public static volatile SingularAttribute<YearPlanDetail, Double> yearPlanQty;

}

