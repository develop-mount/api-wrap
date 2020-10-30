package com.seelyn.apiwrap.client;

import com.seelyn.apiwrap.WrapData;
import com.seelyn.apiwrap.WrapRequest;
import com.seelyn.apiwrap.WrapSigner;
import com.seelyn.apiwrap.handler.DefaultWrapSigner;
import com.seelyn.apiwrap.utils.NonceUtils;

public class WrapClient implements WrapOperation {

    private String appKey;
    private WrapSigner wrapSigner;

    private WrapClient(String appKey, String appSecret) {
        this.appKey = appKey;
        this.wrapSigner = new DefaultWrapSigner(appSecret);
    }

    @Override
    public <T extends WrapData> WrapRequest<T> wrap(T source) {

        WrapRequest<T> wrapRequest = new WrapRequest<>();
        wrapRequest.setData(source);
        wrapRequest.setAppKey(appKey);
        wrapRequest.setTimestamp(System.currentTimeMillis());
        wrapRequest.setNonce(NonceUtils.nonce());
        wrapRequest.setSignature(wrapSigner.signature(wrapRequest));

        return wrapRequest;
    }

    /**
     * 创建包裹客户端对象
     *
     * @param appKey    应用Key
     * @param appSecret 应用密钥
     * @return 客户端对象
     */
    public static WrapClient create(String appKey, String appSecret) {
        return new WrapClient(appKey, appSecret);
    }
}
