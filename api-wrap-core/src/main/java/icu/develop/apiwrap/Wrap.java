package icu.develop.apiwrap;

import icu.develop.apiwrap.exception.WrapSigningException;
import icu.develop.apiwrap.exception.WrapVerifierException;

/**
 * 包裹接口
 *
 * @author linfeng-eqxiu
 */
public interface Wrap {

    /**
     * 算法
     *
     * @return 算法.
     */
    Algorithm getAlgorithm();

    /**
     * 对提供的消息进行签名并返回签名
     *
     * @param payload 待签名的文本
     * @return 签名后的字节数组
     * @throws WrapSigningException 签名异常
     */
    byte[] sign(String payload);

    /**
     * 验证签名是否正确
     *
     * @param payload   原始消息
     * @param signature 签名字信息
     * @throws WrapVerifierException 验证异常
     */
    void verify(byte[] payload, byte[] signature);
}
