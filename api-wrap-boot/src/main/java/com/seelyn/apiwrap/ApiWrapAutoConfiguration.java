package com.seelyn.apiwrap;

import com.seelyn.apiwrap.handler.WrapHandlerServer;
import com.seelyn.apiwrap.store.LocalWrapStore;
import com.seelyn.apiwrap.store.RedisWrapStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * API安全自动配置
 *
 * @author linfeng-eqxiu
 */
@Configuration
@ConditionalOnBean(value = {ApiWrapConfiguration.Marker.class})
@EnableConfigurationProperties({ApiWrapProperties.class})
@Import({AopAutoConfiguration.class})
public class ApiWrapAutoConfiguration {

    /**
     * API 签名包裹AOP
     *
     * @return API签名AOP
     */
    @Bean
    public ApiWrapAspect apiWrapAspect() {
        return new ApiWrapAspect();
    }

    /**
     * API包裹存储
     *
     * @param apiWrapProperties   配置
     * @param stringRedisTemplate redis
     * @return 签名验签处理服务
     */
    @Bean
    @ConditionalOnBean(value = StringRedisTemplate.class)
    @ConditionalOnMissingBean
    public WrapStore redisWrapStore(@Autowired ApiWrapProperties apiWrapProperties,
                                    @Autowired StringRedisTemplate stringRedisTemplate) {

        return new RedisWrapStore(apiWrapProperties, stringRedisTemplate);
    }

    /**
     * @param apiWrapProperties 配置
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(value = {RedisTemplate.class, WrapStore.class})
    public WrapStore localWrapStore(@Autowired ApiWrapProperties apiWrapProperties) {

        return new LocalWrapStore(apiWrapProperties);
    }

    /**
     * 包裹通过
     *
     * @param apiWrapProperties API包裹属性
     * @param wrapStore         API存储服务
     * @return 签名验签处理
     */
    @Bean
    @ConditionalOnMissingBean(value = {WrapHandler.class})
    public WrapHandler defaultWrapHandler(@Autowired ApiWrapProperties apiWrapProperties,
                                          @Autowired WrapStore wrapStore) {

        return new WrapHandlerServer(apiWrapProperties, wrapStore);
    }

    /**
     * API签名BeanFactory
     *
     * @return BeanFactory工具
     */
    @Bean
    public WrapBeanFactoryUtils wrapBeanFactoryUtils() {
        return new WrapBeanFactoryUtils();
    }

}
