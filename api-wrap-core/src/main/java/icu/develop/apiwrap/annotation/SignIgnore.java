package icu.develop.apiwrap.annotation;

import java.lang.annotation.*;

/**
 * 签名忽略注解，用于类的属性
 *
 * @author linfeng
 */
@Target({ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
public @interface SignIgnore {

}
