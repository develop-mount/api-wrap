package icu.develop.api.wrap;

/**
 * 签名接口
 * @author linfeng
 */
public interface WrapSigner {
    /**
     * 获取签名
     *
     * @param data 待签名数据
     * @return 签名后字符串
     */
    <T extends WrapRequest> String signature(T data);

    /**
     *
     * @param data
     * @return
     * @param <T>
     */
    <T extends WrapRequest> boolean verify(T data);
}
