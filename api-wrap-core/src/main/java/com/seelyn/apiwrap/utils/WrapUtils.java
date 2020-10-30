package com.seelyn.apiwrap.utils;


import com.seelyn.apiwrap.annotation.SignIgnore;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

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
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            //获取该字段的注解
            SignIgnore annotation = field.getAnnotation(SignIgnore.class);
            //如果没有注解 或者 注解值为false 则获取该值存入返回的map中
            if (annotation == null) {
                field.setAccessible(true);
                try {
                    returnMap.put(field.getName(), field.get(bean));
                } catch (IllegalAccessException ignored) {
                }
            }
        }
        return returnMap;
    }


}
