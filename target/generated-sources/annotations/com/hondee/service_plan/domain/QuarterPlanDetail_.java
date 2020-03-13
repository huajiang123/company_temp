package com.hondee.service_plan.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(QuarterPlanDetail.class)
public abstract class QuarterPlanDetail_ extends com.hondee.service_plan.domain.BaseEntity_ {

	public static volatile SingularAttribute<QuarterPlanDetail, QuarterPlanDetail> parent;
	public static volatile SingularAttribute<QuarterPlanDetail, Double> weightPercent;
	public static volatile SingularAttribute<QuarterPlanDetail, Integer> year;
	public static volatile SingularAttribute<QuarterPlanDetail, Double> quarterPlanQty;
	public static volatile SingularAttribute<QuarterPlanDetail, String> itemShowCode;
	public static volatile SingularAttribute<QuarterPlanDetail, String> itemCode;
	public static volatile SingularAttribute<QuarterPlanDetail, Date> planEDate;
	public static volatile SingularAttribute<QuarterPlanDetail, String> description;
	public static volatile SingularAttribute<QuarterPlanDetail, Integer> quarterPlanManHour;
	public static volatile SingularAttribute<QuarterPlanDetail, String> itemName;
	public static volatile SingularAttribute<QuarterPlanDetail, String> uom;
	public static volatile SingularAttribute<QuarterPlanDetail, String> projectCode;
	public static volatile ListAttribute<QuarterPlanDetail, QuarterPlanDetail> children;
	public static volatile SingularAttribute<QuarterPlanDetail, String> quarterPlanDetailCode;
	public static volatile SingularAttribute<QuarterPlanDetail, Integer> planManHour;
	public static volatile SingularAttribute<QuarterPlanDetail, Double> yearPlanQty;
	public static volatile SingularAttribute<QuarterPlanDetail, Date> planBDate;
	public static volatile SingularAttribute<QuarterPlanDetail, QuarterPlan> quarterPlan;
	public static volatile SingularAttribute<QuarterPlanDetail, Integer> yearPlanManHour;
	public static volatile SingularAttribute<QuarterPlanDetail, Integer> weight;
	public static volatile SingularAttribute<QuarterPlanDetail, Double> weightTotalPercent;
	public static volatile SingularAttribute<QuarterPlanDetail, Boolean> important;
	public static volatile SingularAttribute<QuarterPlanDetail, String> sequence;
	public static volatile SingularAttribute<QuarterPlanDetail, Double> qty;
	public static volatile SingularAttribute<QuarterPlanDetail, Integer> quarter;

}

