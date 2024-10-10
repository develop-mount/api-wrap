package icu.develop.api.wrap.annotation;

import icu.develop.api.wrap.ApiWrapEnablerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 允许API签名
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ApiWrapEnablerConfiguration.class})
public @interface EnableApiWrap {
}
