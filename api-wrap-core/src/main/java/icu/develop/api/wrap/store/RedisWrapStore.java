package icu.develop.api.wrap.store;

import icu.develop.api.wrap.ApiWrapProperties;
import icu.develop.api.wrap.SecretKey;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * redis 签名存储
 *
 * @author linfeng-eqxiu
 */
public class RedisWrapStore extends LocalWrapStore {

    private final StringRedisTemplate stringRedisTemplate;
    private final Long legalTime;

    public RedisWrapStore(ApiWrapProperties apiWrapProperties,
                          StringRedisTemplate stringRedisTemplate) {
        super(apiWrapProperties);
        this.stringRedisTemplate = stringRedisTemplate;
        this.legalTime = apiWrapProperties.getLegalTime() == null ? 300 : apiWrapProperties.getLegalTime();
    }

    @Override
    public void putSecret(String appKey, SecretKey secretKey) {

        stringRedisTemplate.opsForValue().set(PREFIX_PRIVATE_SECRET + appKey, secretKey.getPrivateKey());
        stringRedisTemplate.opsForValue().set(PREFIX_PUBLIC_SECRET + appKey, secretKey.getPublicKey());
    }

    @Override
    public SecretKey getSecret(String appKey) {

        String priKey = stringRedisTemplate.opsForValue().get(PREFIX_PRIVATE_SECRET + appKey);
        String pubKey = stringRedisTemplate.opsForValue().get(PREFIX_PUBLIC_SECRET + appKey);
        return new SecretKey(priKey, pubKey);
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
