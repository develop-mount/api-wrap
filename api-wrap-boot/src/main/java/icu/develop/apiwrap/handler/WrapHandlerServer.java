package icu.develop.apiwrap.handler;

import com.seelyn.apiwrap.*;
import icu.develop.apiwrap.exception.WrapReplayAttackException;
import icu.develop.apiwrap.exception.WrapTimestampException;
import icu.develop.apiwrap.*;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 默认API安全处理
 *
 * @author linfeng-eqxiu
 */
public class WrapHandlerServer implements WrapHandler {

    private final Boolean enableSign;
    private final Long legalTime;
    private final String defaultSecret;
    private final WrapStore wrapStore;

    public WrapHandlerServer(ApiWrapProperties apiWrapProperties,
                             WrapStore wrapStore) {

        this.enableSign = apiWrapProperties.getSign();
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
    public boolean verifySignature(String appKey, WrapRequest<WrapData> request) {
        if (enableSign) {
            WrapSigner wrapSigner = new DefaultWrapSigner(getAppSecret(appKey));
            String signature = wrapSigner.signature(request);
            String signatureParam = request.getSignature();
            return signatureParam.equals(signature);
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
