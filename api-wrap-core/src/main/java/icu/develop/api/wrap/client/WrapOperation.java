package icu.develop.api.wrap.client;

import icu.develop.api.wrap.WrapRequest;

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
    <T extends WrapRequest> WrapRequest wrap(T source);

}
