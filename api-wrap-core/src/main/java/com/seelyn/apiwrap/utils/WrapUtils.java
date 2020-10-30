package com.seelyn.apiwrap.utils;


import com.seelyn.apiwrap.WrapData;
import com.seelyn.apiwrap.annotation.SignIgnore;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class WrapUtils {

    public static final String APP_KEY = "appKey";
    public static final String TIMESTAMP = "timestamp";
    public static final String NONCE = "nonce";

    private WrapUtils() {
    }

    /**
     * 将对象装换为map
     *
     * @param bean 待转换对象
     * @return 转换后的Map对象
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> returnMap = new HashMap<>();
        Class<?> clazz = bean.getClass();
        //向上循环  遍历父类
        for (; clazz != WrapData.class; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                //获取该字段的注解
                SignIgnore annotation = field.getAnnotation(SignIgnore.class);
                //如果没有注解 或者 注解值为false 则获取该值存入返回的map中
                if (annotation == null) {
                    field.setAccessible(true);
                    Object fieldVal = null;
                    try {
                        fieldVal = field.get(bean);
                    } catch (IllegalAccessException ignored) {
                    }
                    if (Objects.isNull(fieldVal)) {
                        continue;
                    }
                    returnMap.put(field.getName(), fieldVal);
                }
            }
        }
        return returnMap;
    }


}
