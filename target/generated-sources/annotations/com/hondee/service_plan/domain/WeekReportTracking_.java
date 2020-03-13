package com.hondee.service_plan.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WeekReportTracking.class)
public abstract class WeekReportTracking_ extends com.hondee.service_plan.domain.BaseEntity_ {

	public static volatile SingularAttribute<WeekReportTracking, Double> basePlanQty;
	public static volatile SingularAttribute<WeekReportTracking, WeekReportDetail> weekReportDetail;
	public static volatile SingularAttribute<WeekReportTracking, String> weekTrackingCode;
	public static volatile SingularAttribute<WeekReportTracking, Integer> startManHour;
	public static volatile SingularAttribute<WeekReportTracking, String> itemCode;
	public static volatile SingularAttribute<WeekReportTracking, Integer> reportManHour;
	public static volatile SingularAttribute<WeekReportTracking, Double> startQty;
	public static volatile SingularAttribute<WeekReportTracking, String> weekReportCode;
	public static volatile SingularAttribute<WeekReportTracking, Double> endQty;
	public static volatile SingularAttribute<WeekReportTracking, Integer> endManHour;
	public static volatile SingularAttribute<WeekReportTracking, Double> reportQty;
	public static volatile SingularAttribute<WeekReportTracking, Date> periodDay;
	public static volatile SingularAttribute<WeekReportTracking, Integer> periodNo;

}

