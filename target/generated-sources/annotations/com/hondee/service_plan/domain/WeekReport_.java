package com.hondee.service_plan.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WeekReport.class)
public abstract class WeekReport_ extends com.hondee.service_plan.domain.BaseEntity_ {

	public static volatile SingularAttribute<WeekReport, Integer> week;
	public static volatile SingularAttribute<WeekReport, Integer> year;
	public static volatile SingularAttribute<WeekReport, String> weekReportCode;
	public static volatile SingularAttribute<WeekReport, Integer> version;
	public static volatile SingularAttribute<WeekReport, Integer> month;
	public static volatile SingularAttribute<WeekReport, String> projectCode;
	public static volatile SingularAttribute<WeekReport, Date> releasedTime;
	public static volatile SingularAttribute<WeekReport, Date> startTime;
	public static volatile ListAttribute<WeekReport, WeekReportDetail> details;
	public static volatile SingularAttribute<WeekReport, Date> endTime;
	public static volatile SingularAttribute<WeekReport, String> basePlanCode;
	public static volatile SingularAttribute<WeekReport, Boolean> released;
	public static volatile SingularAttribute<WeekReport, Integer> quarter;

}

