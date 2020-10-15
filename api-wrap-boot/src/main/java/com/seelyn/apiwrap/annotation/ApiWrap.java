package com.seelyn.apiwrap.annotation;

import com.seelyn.apiwrap.WrapHandler;
import com.seelyn.apiwrap.handler.WrapHandlerServer;

import java.lang.annotation.*;

/**
 * ApiWrap 接口签名注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
public @interface ApiWrap {
    Class<? extends WrapHandler> value() default WrapHandlerServer.class;
}
