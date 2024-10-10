package com.parsechina.apiwarp.web;

import icu.develop.api.wrap.WrapHandler;
import icu.develop.api.wrap.WrapRequest;
import icu.develop.api.wrap.SecretKey;
import org.springframework.stereotype.Component;

/**
 * @author linfeng
 */
@Component
public class CustomWrapHandler implements WrapHandler {
    @Override
    public SecretKey getAppSecret(String appKey) {
        return null;
    }

    @Override
    public <T extends WrapRequest> boolean verifySignature(String appKey, T request) {
        return true;
    }


    @Override
    public void isLegalTime(long timestamp) {

    }

    @Override
    public void isReplayAttack(String appKey, long timestamp, int nonce, String signature) {

    }
}
