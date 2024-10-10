package icu.develop.api.wrap.handler;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import icu.develop.api.wrap.WrapRequest;
import icu.develop.api.wrap.WrapSigner;
import icu.develop.api.wrap.utils.WrapUtils;
import icu.develop.api.wrap.*;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 默认签名
 * @author linfeng
 */
public class DefaultWrapSigner implements WrapSigner {

    private final Sign sign;

    public DefaultWrapSigner(String privateKey, String publicKey) {
        this.sign = new Sign(SignAlgorithm.SHA256withRSA, privateKey, publicKey);;
    }

    private <T extends WrapRequest> String dataToString(T data) {
        Map<String, Object> beanMap = WrapUtils.beanToMap(data);
        beanMap.put(WrapUtils.APP_KEY, data.getAppKey());
        beanMap.put(WrapUtils.TIMESTAMP, data.getTimestamp());
        beanMap.put(WrapUtils.NONCE, data.getNonce());

        SortedMap<Object, Object> sortedMap = new TreeMap<>(beanMap);
        StringBuilder plainText = new StringBuilder();
        for (Map.Entry<Object, Object> entry : sortedMap.entrySet()) {
            plainText.append(entry.getKey()).append("=").append(entry.getValue());
            plainText.append("&");
        }
        plainText.deleteCharAt(plainText.length() - 1);
        return plainText.toString();
    }

    @Override
    public <T extends WrapRequest> String signature(T data) {

        String plainText = dataToString(data);
        return Base64.encode(sign.sign(plainText, StandardCharsets.UTF_8));
    }

    @Override
    public <T extends WrapRequest> boolean verify(T data) {

        String signature = data.getSignature();
        String plainText = dataToString(data);
        return sign.verify(plainText.getBytes(StandardCharsets.UTF_8), Base64.decode(signature));
    }
}
