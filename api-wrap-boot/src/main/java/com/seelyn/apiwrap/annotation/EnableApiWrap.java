package com.seelyn.apiwrap.annotation;

import com.seelyn.apiwrap.ApiWrapConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 允许API签名
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ApiWrapConfiguration.class})
public @interface EnableApiWrap {
}
