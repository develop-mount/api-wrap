package icu.develop.apiwrap.handler;

import com.seelyn.apiwrap.*;
import icu.develop.apiwrap.utils.WrapUtils;
import icu.develop.apiwrap.*;

import java.util.Base64;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 默认签名
 * @author linfeng
 */
public class DefaultWrapSigner implements WrapSigner {

    private final String appSecret;

    public DefaultWrapSigner(String appSecret) {
        this.appSecret = appSecret;
    }

    @Override
    public <T extends WrapData> String signature(WrapRequest<T> data) {

        Map<String, Object> beanMap = WrapUtils.beanToMap(data.getData());
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
        Wrap wrap = HMACWrap.newSHA256Wrap(appSecret);
        return Base64.getEncoder().encodeToString(wrap.sign(plainText.toString()));
    }
}
