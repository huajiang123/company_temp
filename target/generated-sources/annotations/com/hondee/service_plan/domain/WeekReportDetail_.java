package com.hondee.service_plan.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WeekReportDetail.class)
public abstract class WeekReportDetail_ extends com.hondee.service_plan.domain.BaseEntity_ {

	public static volatile SingularAttribute<WeekReportDetail, WeekReportDetail> parent;
	public static volatile SingularAttribute<WeekReportDetail, Integer> week;
	public static volatile SingularAttribute<WeekReportDetail, Double> weightPercent;
	public static volatile SingularAttribute<WeekReportDetail, Date> actualStartTime;
	public static volatile SingularAttribute<WeekReportDetail, Integer> year;
	public static volatile SingularAttribute<WeekReportDetail, Double> quarterPlanQty;
	public static volatile SingularAttribute<WeekReportDetail, String> itemShowCode;
	public static volatile SingularAttribute<WeekReportDetail, String> itemCode;
	public static volatile SingularAttribute<WeekReportDetail, Date> planEDate;
	public static volatile SingularAttribute<WeekReportDetail, String> description;
	public static volatile SingularAttribute<WeekReportDetail, Double> monthPlanQty;
	public static volatile SingularAttribute<WeekReportDetail, Double> endQty;
	public static volatile SingularAttribute<WeekReportDetail, Integer> quarterPlanManHour;
	public static volatile SingularAttribute<WeekReportDetail, Integer> endManHour;
	public static volatile SingularAttribute<WeekReportDetail, String> itemName;
	public static volatile SingularAttribute<WeekReportDetail, String> uom;
	public static volatile SingularAttribute<WeekReportDetail, String> projectCode;
	public static volatile ListAttribute<WeekReportDetail, WeekReportDetail> children;
	public static volatile ListAttribute<WeekReportDetail, WeekReportTracking> trackings;
	public static volatile SingularAttribute<WeekReportDetail, Double> weekPlanQty;
	public static volatile SingularAttribute<WeekReportDetail, Integer> planManHour;
	public static volatile SingularAttribute<WeekReportDetail, Double> yearPlanQty;
	public static volatile SingularAttribute<WeekReportDetail, Integer> stat;
	public static volatile SingularAttribute<WeekReportDetail, String> weekReportDetailCode;
	public static volatile SingularAttribute<WeekReportDetail, Date> planBDate;
	public static volatile SingularAttribute<WeekReportDetail, Integer> yearPlanManHour;
	public static volatile SingularAttribute<WeekReportDetail, Integer> weekPlanManHour;
	public static volatile SingularAttribute<WeekReportDetail, Integer> weight;
	public static volatile SingularAttribute<WeekReportDetail, Double> weightTotalPercent;
	public static volatile SingularAttribute<WeekReportDetail, WeekReport> weekReport;
	public static volatile SingularAttribute<WeekReportDetail, Boolean> important;
	public static volatile SingularAttribute<WeekReportDetail, String> sequence;
	public static volatile SingularAttribute<WeekReportDetail, Integer> month;
	public static volatile SingularAttribute<WeekReportDetail, Double> qty;
	public static volatile SingularAttribute<WeekReportDetail, Double> progress;
	public static volatile SingularAttribute<WeekReportDetail, Date> actualEndTime;
	public static volatile SingularAttribute<WeekReportDetail, Integer> monthPlanManHour;
	public static volatile SingularAttribute<WeekReportDetail, Integer> quarter;

}

