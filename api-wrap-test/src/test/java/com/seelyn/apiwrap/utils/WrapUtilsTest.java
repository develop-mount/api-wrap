package com.seelyn.apiwrap.utils;

import com.parsechina.apiwarp.web.DefaultWrapData;
import com.parsechina.apiwarp.web.ExtenDefaultWrapData;

public class WrapUtilsTest {

    @org.junit.Test
    public void beanToMap() {
        ExtenDefaultWrapData wrapData = new ExtenDefaultWrapData();
        wrapData.setUrl("http://www.baidu.com");
        wrapData.setChin("http://www.baidu.com");
        WrapUtils.beanToMap(wrapData);
    }
}