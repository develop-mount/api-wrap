package icu.develop.apiwrap.annotation;

import icu.develop.apiwrap.ApiWrapEnablerConfiguration;
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
