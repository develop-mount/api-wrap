package icu.develop.api.wrap.handler;

import icu.develop.api.wrap.*;
import icu.develop.api.wrap.exception.WrapReplayAttackException;
import icu.develop.api.wrap.exception.WrapTimestampException;
import icu.develop.api.wrap.SecretKey;

import java.util.concurrent.TimeUnit;

/**
 * 默认API安全处理
 *
 * @author linfeng-eqxiu
 */
public class WrapHandlerServer implements WrapHandler {

    private final Boolean enableSign;
    private final Long legalTime;
    private final WrapStore wrapStore;

    public WrapHandlerServer(ApiWrapProperties apiWrapProperties,
                             WrapStore wrapStore) {

        this.enableSign = apiWrapProperties.getSign();
        this.legalTime = apiWrapProperties.getLegalTime() == null ? 300 : apiWrapProperties.getLegalTime();
        this.wrapStore = wrapStore;
    }

    @Override
    public SecretKey getAppSecret(String appKey) {

        return wrapStore.getSecret(appKey);
    }

    @Override
    public <T extends WrapRequest> boolean verifySignature(String appKey, T request) {
        if (enableSign) {
            SecretKey appSecret = getAppSecret(appKey);
            WrapSigner wrapSigner = new DefaultWrapSigner(appSecret.getPrivateKey(), appSecret.getPublicKey());
            return wrapSigner.verify(request);
        } else {
            return true;
        }
    }

    @Override
    public void isLegalTime(long timestamp) {

        long diff = TimeUnit.MILLISECONDS.toSeconds(timestamp - System.currentTimeMillis());
        if (Math.abs(diff) > legalTime) {
            throw new WrapTimestampException(String.valueOf(diff));
        }
    }

    @Override
    public void isReplayAttack(String appKey, long timestamp, int nonce, String signature) {

        String sign = wrapStore.getSign(appKey, timestamp, nonce);
        if (signature.equals(sign)) {
            throw new WrapReplayAttackException(String.format("ReplayAttackException, appKey:%s, timestamp:%s, nonce:%s", appKey, timestamp, nonce));
        } else {
            wrapStore.putSign(appKey, timestamp, nonce, signature);
        }
    }
}
