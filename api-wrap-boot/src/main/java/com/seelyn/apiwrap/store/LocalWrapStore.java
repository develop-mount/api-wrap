package com.seelyn.apiwrap.store;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.seelyn.apiwrap.ApiWrapProperties;
import com.seelyn.apiwrap.WrapStore;

import java.util.concurrent.TimeUnit;

/**
 * Guava 签名存储
 *
 * @author linfeng-eqxiu
 */
public class LocalWrapStore implements WrapStore {

    private static final String PREFIX_SECRET = "com:eqxiu:wrap:secret:";
    private static final String PREFIX_SIGN = "com:eqxiu:wrap:sign:";
    private final Cache<String, String> guavaCache;
    private final Cache<String, String> expireCache;

    public LocalWrapStore(ApiWrapProperties apiWrapProperties) {
        guavaCache = CacheBuilder.newBuilder()
                .build();
        this.expireCache = CacheBuilder.newBuilder()
                .expireAfterAccess(apiWrapProperties.getLegalTime() == null ? 300 : apiWrapProperties.getLegalTime(), TimeUnit.SECONDS)
                .build();
    }

    @Override
    public void putSecret(String appKey, String appSecret) {

        guavaCache.put(PREFIX_SECRET + appKey, appSecret);
    }

    @Override
    public String getSecret(String appKey) {

        return guavaCache.getIfPresent(PREFIX_SECRET + appKey);
    }

    @Override
    public void putSign(String appKey, long timestamp, int nonce, String signature) {

        String key = PREFIX_SIGN + appKey + "_" + timestamp + "_" + nonce;
        expireCache.put(key, signature);
    }

    @Override
    public String getSign(String appKey, long timestamp, int nonce) {

        String key = PREFIX_SIGN + appKey + "_" + timestamp + "_" + nonce;
        return expireCache.getIfPresent(key);
    }

}
