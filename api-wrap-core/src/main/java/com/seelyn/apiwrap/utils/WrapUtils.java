package com.seelyn.apiwrap.utils;


import org.apache.commons.beanutils.BeanMap;

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
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = new BeanMap(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key.toString(), beanMap.get(key));
            }
        }
        return map;
    }

}
