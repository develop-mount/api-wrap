package icu.develop.api.wrap.client;

import icu.develop.api.wrap.WrapRequest;
import icu.develop.api.wrap.WrapSigner;
import icu.develop.api.wrap.handler.DefaultWrapSigner;
import icu.develop.api.wrap.utils.NonceUtils;

/**
 * 包裹客户端，用于包装需要请求的对象
 *
 * @author linfeng
 */
public class WrapClient implements WrapOperation {

    private String appKey;
    private WrapSigner wrapSigner;

    private WrapClient(String appKey, String priKey, String pubKey) {
        this.appKey = appKey;
        this.wrapSigner = new DefaultWrapSigner(priKey, pubKey);
    }

    @Override
    public <T extends WrapRequest> WrapRequest wrap(T source) {

        source.setAppKey(appKey);
        source.setTimestamp(System.currentTimeMillis());
        source.setNonce(NonceUtils.nonce());
        source.setSignature(wrapSigner.signature(source));

        return (WrapRequest) source;
    }

    /**
     * 创建包裹客户端对象
     *
     * @param appKey    应用Key
     * @param priKey 应用密钥
     * @param pubKey 应用密钥
     * @return 客户端对象
     */
    public static WrapClient create(String appKey, String priKey, String pubKey) {
        return new WrapClient(appKey, priKey, pubKey);
    }
}
