package com.seelyn.apiwrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * API签名配置
 * @author linfeng
 */
@Configuration
public class ApiWrapConfiguration {

    public ApiWrapConfiguration() {
        // Do nothing because
    }

    @Bean
    public ApiWrapConfiguration.Marker enableApiWrapMarker() {
        return new ApiWrapConfiguration.Marker();
    }

    public static class Marker {
        Marker() {
        }
    }
}
