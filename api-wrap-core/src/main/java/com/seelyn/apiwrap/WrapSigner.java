package com.seelyn.apiwrap;

public interface WrapSigner {
    /**
     * 获取签名
     *
     * @param data 待签名数据
     * @return 签名后字符串
     */
    <T extends WrapData> String signature(WrapRequest<T> data);
}