package com.seelyn.apiwrap.utils;

import com.parsechina.apiwarp.web.DefaultWrapData;

public class WrapUtilsTest {

    @org.junit.Test
    public void beanToMap() {
        DefaultWrapData wrapData = new DefaultWrapData();
        wrapData.setUrl("http://www.baidu.com");
        WrapUtils.beanToMap(wrapData);
    }
}