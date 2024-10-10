package icu.develop.api.wrap;

/**
 * API包裹存储接口
 *
 * @author linfeng-eqxiu
 */
public interface WrapStore {

    String PREFIX_PRIVATE_SECRET = "com:example:wrap:private_secret:";
    String PREFIX_PUBLIC_SECRET = "com:example:wrap:public_secret:";
    String PREFIX_SIGN = "com:example:wrap:sign:";
    /**
     * 存入appKey及appSecret
     *
     * @param appKey    应用Key
     * @param secretKey 应用密钥
     */
    void putSecret(String appKey, SecretKey secretKey);

    /**
     * 取出存储的密钥
     *
     * @param appKey 应用Key
     * @return 密钥
     */
    SecretKey getSecret(String appKey);

    /**
     * @param appKey    应用Key
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param signature 签名字符串
     */
    void putSign(String appKey, long timestamp, int nonce, String signature);

    /**
     * 取出签名字符串
     *
     * @param appKey    应用Key
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @return 签名字符串
     */
    String getSign(String appKey, long timestamp, int nonce);

}
