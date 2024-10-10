package icu.develop.api.wrap.store;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import icu.develop.api.wrap.ApiWrapProperties;
import icu.develop.api.wrap.SecretKey;
import icu.develop.api.wrap.WrapStore;

import java.util.concurrent.TimeUnit;

/**
 * Guava 签名存储
 *
 * @author linfeng-eqxiu
 */
public class LocalWrapStore implements WrapStore {

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
    public void putSecret(String appKey, SecretKey appSecret) {

        guavaCache.put(PREFIX_PRIVATE_SECRET + appKey, appSecret.getPrivateKey());
        guavaCache.put(PREFIX_PUBLIC_SECRET + appKey, appSecret.getPrivateKey());
    }

    @Override
    public SecretKey getSecret(String appKey) {

        String privateKey = guavaCache.getIfPresent(PREFIX_PRIVATE_SECRET + appKey);
        String publicKye = guavaCache.getIfPresent(PREFIX_PUBLIC_SECRET + appKey);
        return new SecretKey(privateKey, publicKye);
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
