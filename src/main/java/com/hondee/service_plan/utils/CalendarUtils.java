package com.hondee.service_plan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {
    public static Integer getQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        return getQuarter(month);
    }

    public static Integer getQuarter(int month) {
        if (month > 0 && month <= 3)
            return 1;
        if (month > 3 && month <= 6)
            return 2;
        if (month > 6 && month <= 9)
            return 3;
        if (month > 9 && month <= 12)
            return 4;
        return 0;
    }

    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        sb.append(calendar.get(Calendar.YEAR));
        Integer quarter = getQuarter(date);
        switch (quarter) {
            case 1:
                sb.append("-01-01");
                break;
            case 2:
                sb.append("-04-01");
                break;
            case 3:
                sb.append("-07-01");
                break;
            case 4:
                sb.append("-10-01");
                break;
            default:
        }
        try {
            return simpleDateFormat.parse(sb.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Integer differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }
}
