package com.hondee.service_plan.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeUtil {
    public static <T> void mergeObject(T dest, T src) {
        Map<String, Object> srcMap = new HashMap<>();
        Field[] srcFields = src.getClass().getDeclaredFields();
        for (Field fd : srcFields) {
            fd.setAccessible(true);
            try {
                srcMap.put(fd.getName(), fd.get(src));      //获取属性值
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Field[] destFields = dest.getClass().getDeclaredFields();
        for (Field fd : destFields) {
            fd.setAccessible(true);
            if (srcMap.get(fd.getName()) == null) {
                continue;
            }
            try {
                fd.set(dest, srcMap.get(fd.getName()));     //给属性赋值
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> T deepCopy(T src) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);
            out.close();
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            //noinspection unchecked
            T dest = (T) in.readObject();
            in.close();
            return dest;
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> List<T> deepCopy(List<T> src) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);
            out.close();
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            //noinspection unchecked
            List<T> dest = (List<T>) in.readObject();
            in.close();
            return dest;
        } catch (Exception e) {
            return null;
        }
    }

    public static Map<String, Object> transBean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }
}
