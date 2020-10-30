package com.seelyn.apiwrap.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
public @interface SignIgnore {

}
