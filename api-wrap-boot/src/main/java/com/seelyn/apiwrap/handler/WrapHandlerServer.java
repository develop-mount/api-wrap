package com.seelyn.apiwrap.handler;

import com.seelyn.apiwrap.*;
import com.seelyn.apiwrap.exception.WrapReplayAttackException;
import com.seelyn.apiwrap.exception.WrapTimestampException;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 默认API安全处理
 *
 * @author linfeng-eqxiu
 */
public class WrapHandlerServer implements WrapHandler {

    private final Long legalTime;
    private final String defaultSecret;
    private final WrapStore wrapStore;

    public WrapHandlerServer(ApiWrapProperties apiWrapProperties,
                             WrapStore wrapStore) {

        this.legalTime = apiWrapProperties.getLegalTime() == null ? 300 : apiWrapProperties.getLegalTime();
        this.defaultSecret = apiWrapProperties.getSecret();
        this.wrapStore = wrapStore;
    }

    @Override
    public String getAppSecret(String appKey) {

        String secret = wrapStore.getSecret(appKey);
        if (StringUtils.isEmpty(secret)) {
            return defaultSecret;
        }
        return secret;
    }

    @Override
    public String getSignature(String appKey, WrapRequest<WrapData> request) {

        WrapSigner wrapSigner = new DefaultWrapSigner(getAppSecret(appKey));
        return wrapSigner.signature(request);
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
            throw new WrapReplayAttackException("appKey:" + appKey + ",timestamp" + timestamp + ",nonce:" + nonce);
        } else {
            wrapStore.putSign(appKey, timestamp, nonce, signature, legalTime, TimeUnit.SECONDS);
        }
    }
}
