package com.seelyn.apiwrap.utils;

import com.parsechina.apiwarp.web.DefaultWrapData;
import com.parsechina.apiwarp.web.ExtenDefaultWrapData;
import com.parsechina.apiwarp.web.TestEnum;

public class WrapUtilsTest {

    @org.junit.Test
    public void beanToMap() {
        ExtenDefaultWrapData wrapData = new ExtenDefaultWrapData();
        wrapData.setUrl("http://www.baidu.com");
        wrapData.setChin("http://www.baidu.com");
        wrapData.setTestEnum(TestEnum.TEST1);
        WrapUtils.beanToMap(wrapData);
    }
}
