package com.hondee.service_plan.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BasePlanDetail.class)
public abstract class BasePlanDetail_ extends com.hondee.service_plan.domain.BaseEntity_ {

	public static volatile SingularAttribute<BasePlanDetail, BasePlanDetail> parent;
	public static volatile SingularAttribute<BasePlanDetail, Double> weightPercent;
	public static volatile SingularAttribute<BasePlanDetail, Date> planBDate;
	public static volatile SingularAttribute<BasePlanDetail, String> itemShowCode;
	public static volatile SingularAttribute<BasePlanDetail, String> itemCode;
	public static volatile SingularAttribute<BasePlanDetail, Date> planEDate;
	public static volatile SingularAttribute<BasePlanDetail, Integer> weight;
	public static volatile SingularAttribute<BasePlanDetail, String> description;
	public static volatile SingularAttribute<BasePlanDetail, Double> weightTotalPercent;
	public static volatile SingularAttribute<BasePlanDetail, Boolean> important;
	public static volatile SingularAttribute<BasePlanDetail, String> sequence;
	public static volatile SingularAttribute<BasePlanDetail, String> itemName;
	public static volatile SingularAttribute<BasePlanDetail, String> uom;
	public static volatile SingularAttribute<BasePlanDetail, String> projectCode;
	public static volatile ListAttribute<BasePlanDetail, BasePlanDetail> children;
	public static volatile SingularAttribute<BasePlanDetail, Double> qty;
	public static volatile SingularAttribute<BasePlanDetail, BasePlan> basePlan;
	public static volatile SingularAttribute<BasePlanDetail, Integer> planManHour;

}

