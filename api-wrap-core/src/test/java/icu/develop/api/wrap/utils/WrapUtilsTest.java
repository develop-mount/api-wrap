package icu.develop.api.wrap.utils;

import com.alibaba.fastjson.JSON;
import icu.develop.api.wrap.WrapRequest;
import icu.develop.api.wrap.handler.DefaultWrapSigner;
import icu.develop.api.wrap.SecretKey;
import icu.develop.api.wrap.client.WrapClient;

public class WrapUtilsTest {

    @org.junit.Test
    public void beanToMap() {

        SecretKey secretKey = WrapUtils.generatorKey();
        DefaultWrapSigner wrapSigner = new DefaultWrapSigner(secretKey.getPrivateKey(), secretKey.getPublicKey());
        DefaultWrapData wrapData = new DefaultWrapData();
        wrapData.setUrl("http://www.baidu.com");
        WrapClient wrapClient = WrapClient.create("eqxiu", secretKey.getPrivateKey(), secretKey.getPublicKey());
        WrapRequest wrapDataWrapRequest = wrapClient.wrap(wrapData);

        WrapRequest wrapData2 = (WrapRequest) wrapData;
        System.out.println(JSON.toJSONString(wrapData2));

        String signature = wrapSigner.signature(wrapData2);
        wrapDataWrapRequest.setSignature(signature);


        System.out.println(wrapSigner.verify(wrapData2));
    }
}
