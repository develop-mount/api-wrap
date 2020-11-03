package com.parsechina.apiwarp.web;

import com.seelyn.apiwrap.WrapData;
import com.seelyn.apiwrap.WrapHandler;
import com.seelyn.apiwrap.WrapRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomWrapHandler implements WrapHandler {
    @Override
    public String getAppSecret(String appKey) {
        return null;
    }

    @Override
    public String getSignature(String appKey, WrapRequest<WrapData> request) {
        return null;
    }

    @Override
    public void isLegalTime(long timestamp) {

    }

    @Override
    public void isReplayAttack(String appKey, long timestamp, int nonce, String signature) {

    }
}
