package icu.develop.apiwrap.store;

import icu.develop.apiwrap.ApiWrapProperties;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * redis 签名存储
 *
 * @author linfeng-eqxiu
 */
public class RedisWrapStore extends LocalWrapStore {

    private static final String PREFIX_SECRET = "com:eqxiu:wrap:secret:";
    private static final String PREFIX_SIGN = "com:eqxiu:wrap:sign:";
    private final StringRedisTemplate stringRedisTemplate;
    private final Long legalTime;

    public RedisWrapStore(ApiWrapProperties apiWrapProperties,
                          StringRedisTemplate stringRedisTemplate) {
        super(apiWrapProperties);
        this.stringRedisTemplate = stringRedisTemplate;
        this.legalTime = apiWrapProperties.getLegalTime() == null ? 300 : apiWrapProperties.getLegalTime();
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
                        String signature) {

        String key = PREFIX_SIGN + appKey + "_" + timestamp + "_" + nonce;
        stringRedisTemplate.opsForValue().set(key, signature, legalTime, TimeUnit.SECONDS);
    }

    @Override
    public String getSign(String appKey, long timestamp, int nonce) {

        String key = PREFIX_SIGN + appKey + "_" + timestamp + "_" + nonce;
        return stringRedisTemplate.opsForValue().get(key);
    }

}
