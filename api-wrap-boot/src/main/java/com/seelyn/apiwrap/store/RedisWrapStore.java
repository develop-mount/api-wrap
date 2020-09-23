package com.seelyn.apiwrap.store;

import com.seelyn.apiwrap.WrapStore;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * redis 签名存储
 *
 * @author linfeng-eqxiu
 */
public class RedisWrapStore implements WrapStore {

    private static final String PREFIX_SECRET = "com:eqxiu:wrap:secret:";
    private static final String PREFIX_SIGN = "com:eqxiu:wrap:sign:";
    private final StringRedisTemplate stringRedisTemplate;

    public RedisWrapStore(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void putSecret(String appKey, String appSecret) {

        stringRedisTemplate.opsForValue().set(PREFIX_SECRET + appKey, appSecret);
    }

    @Override
    public String getSecret(String appKey) {

        return stringRedisTemplate.opsForValue().get(PREFIX_SECRET + appKey);
    }

    @Override
    public void putSign(String appKey, long timestamp, int nonce,
                        String signature, Long time, TimeUnit unit) {

        String key = PREFIX_SIGN + appKey + "_" + timestamp + "_" + nonce;
        stringRedisTemplate.opsForValue().set(key, signature, time, unit);
    }

    @Override
    public String getSign(String appKey, long timestamp, int nonce) {

        String key = PREFIX_SIGN + appKey + "_" + timestamp + "_" + nonce;
        return stringRedisTemplate.opsForValue().get(key);
    }

}
