package com.hondee.service_plan.utils;

import java.text.*;
import java.util.Date;

public class DateFormatUtils extends DateFormat {
    private DateFormat df;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

    public DateFormatUtils(DateFormat df) {
        this.df = df;
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return df.format(date, toAppendTo, fieldPosition);
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        Date date;
        try {
            date = sdf.parse(source, pos);
        } catch (Exception e) {
            date = df.parse(source, pos);
        }
        return date;
    }

    // 主要还是装饰这个方法
    @Override
    public Date parse(String source) throws ParseException {
        Date date;
        try {
            // 先按我的规则来
            date = sdf.parse(source);
        } catch (Exception e) {
            // 不行，那就按原先的规则吧
            date = df.parse(source);
        }
        return date;
    }

    // 这里装饰clone方法的原因是因为clone方法在jackson中也有用到
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Object clone() {
        Object format = df.clone();
        return new DateFormatUtils((DateFormat) format);
    }
}
