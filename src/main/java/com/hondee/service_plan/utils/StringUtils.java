package com.hondee.service_plan.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static Boolean isEmpty(String str) {
        return str == null;
    }

    public static Boolean isNullOrEmpty(String str) {
        if (str == null)
            return true;
        return str.length() < 1;
    }

    //首字母大写
    public static String captureName(String s) {
        s = s.substring(0, 1).toUpperCase() + s.substring(1);
        return s;
    }

    //手机号验证
    public static Boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(166)|(17[0135678])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }

    public static String convert(Object obj) {
        String str = null;
        if (obj != null)
            str = obj.toString();
        return str;
    }

    public static Boolean tryParseInt(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
