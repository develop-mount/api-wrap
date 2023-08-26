package icu.develop.apiwrap.annotation;

import icu.develop.apiwrap.WrapHandler;
import icu.develop.apiwrap.handler.WrapHandlerServer;

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
