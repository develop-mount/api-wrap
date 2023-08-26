package icu.develop.apiwrap.client;

import icu.develop.apiwrap.WrapData;
import icu.develop.apiwrap.WrapRequest;

/**
 * 包裹操作接口
 *
 * @author linfeng
 */
public interface WrapOperation {

    /**
     * 包裹签名验签请求对象
     *
     * @param source 原始请求对象
     * @param <T>    泛型
     * @return 包裹签名的请求对象
     */
    <T extends WrapData> WrapRequest<T> wrap(T source);

}
