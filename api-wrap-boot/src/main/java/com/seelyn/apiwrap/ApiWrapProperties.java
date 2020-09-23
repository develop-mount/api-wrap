package com.seelyn.apiwrap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * API包裹属性配置
 *
 * @author linfeng-eqxiu
 */
@Component
@ConfigurationProperties(
        prefix = ApiWrapProperties.PREFIX
)
public class ApiWrapProperties {

    /**
     * 配置前缀
     */
    static final String PREFIX = "api.wrap";
    /**
     * 请求合法时间，在此时间之内，请求有效,单位 秒
     */
    private Long legalTime = 300L;
    /**
     * 签名密钥
     */
    private String secret;

    public Long getLegalTime() {
        return legalTime;
    }

    public void setLegalTime(Long legalTime) {
        this.legalTime = legalTime;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
