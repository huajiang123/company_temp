package com.hondee.service_plan.utils.poiutils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelResources {

    /**
     * 属性的标题名称
     */
    String title();

    /**
     * 在excel的顺序
     */
    int order() default 9999;

}
