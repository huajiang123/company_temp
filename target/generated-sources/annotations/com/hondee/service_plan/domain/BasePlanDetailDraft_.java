package com.hondee.service_plan.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BasePlanDetailDraft.class)
public abstract class BasePlanDetailDraft_ extends com.hondee.service_plan.domain.BaseEntity_ {

	public static volatile SingularAttribute<BasePlanDetailDraft, BasePlanDetailDraft> parent;
	public static volatile SingularAttribute<BasePlanDetailDraft, Double> weightPercent;
	public static volatile SingularAttribute<BasePlanDetailDraft, Date> planBDate;
	public static volatile SingularAttribute<BasePlanDetailDraft, String> itemShowCode;
	public static volatile SingularAttribute<BasePlanDetailDraft, String> itemCode;
	public static volatile SingularAttribute<BasePlanDetailDraft, Date> planEDate;
	public static volatile SingularAttribute<BasePlanDetailDraft, Integer> weight;
	public static volatile SingularAttribute<BasePlanDetailDraft, String> description;
	public static volatile SingularAttribute<BasePlanDetailDraft, Double> weightTotalPercent;
	public static volatile SingularAttribute<BasePlanDetailDraft, Boolean> important;
	public static volatile SingularAttribute<BasePlanDetailDraft, String> sequence;
	public static volatile SingularAttribute<BasePlanDetailDraft, String> itemName;
	public static volatile SingularAttribute<BasePlanDetailDraft, String> uom;
	public static volatile SingularAttribute<BasePlanDetailDraft, String> projectCode;
	public static volatile ListAttribute<BasePlanDetailDraft, BasePlanDetailDraft> children;
	public static volatile SingularAttribute<BasePlanDetailDraft, Double> qty;
	public static volatile SingularAttribute<BasePlanDetailDraft, BasePlan> basePlan;
	public static volatile SingularAttribute<BasePlanDetailDraft, Integer> planManHour;
	public static volatile SingularAttribute<BasePlanDetailDraft, Boolean> released;

}

