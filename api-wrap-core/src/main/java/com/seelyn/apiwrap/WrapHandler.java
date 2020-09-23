package com.seelyn.apiwrap;

import java.util.Map;

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
     * 获取签名
     *
     * @param appKey 应用Key
     * @param data   待签名数据
     * @return 签名后字符串
     */
    String getSignature(String appKey, Map<String, Object> data);

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
