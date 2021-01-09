package com.seelyn.apiwrap;

/**
 * Api处理
 *
 * @author linfeng-eqxiu
 */
public interface WrapHandler {

    /**
     * 密钥
     *
     * @param appKey 应用Key
     * @return 密钥
     */
    String getAppSecret(String appKey);

    /**
     * 验证请求是否正确
     * @param appKey 应用Key
     * @param request 请求数据
     * @return 是否正确
     */
    boolean verifySignature(String appKey, WrapRequest<WrapData> request);

    /**
     * 是否在有效时间只能发起的请求
     *
     * @param timestamp 时间戳
     */
    void isLegalTime(long timestamp);

    /**
     * 重放攻击
     *
     * @param appKey    应用Key
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param signature 签名字符串
     */
    void isReplayAttack(String appKey, long timestamp, int nonce, String signature);

}
