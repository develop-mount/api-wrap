package com.seelyn.apiwrap.utils;

import com.alibaba.fastjson.JSON;
import com.parsechina.apiwarp.web.DefaultWrapData;
import com.seelyn.apiwrap.WrapRequest;
import com.seelyn.apiwrap.client.WrapClient;

public class WrapUtilsTest {

    @org.junit.Test
    public void beanToMap() {
        DefaultWrapData wrapData = new DefaultWrapData();
        wrapData.setUrl("http://www.baidu.com");
        WrapClient wrapClient = WrapClient.create("eqxiu", "eqxiu");
        WrapRequest<DefaultWrapData> wrapDataWrapRequest = wrapClient.wrap(wrapData);
        System.out.println(JSON.toJSONString(wrapDataWrapRequest));
    }
}
