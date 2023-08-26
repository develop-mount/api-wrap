package icu.develop.apiwrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * API签名配置
 * @author linfeng
 */
@Configuration
public class ApiWrapEnablerConfiguration {

    public ApiWrapEnablerConfiguration() {
        // Do nothing because
    }

    @Bean
    public ApiWrapEnablerConfiguration.Marker enableApiWrapMarker() {
        return new ApiWrapEnablerConfiguration.Marker();
    }

    public static class Marker {
        Marker() {
        }
    }
}
