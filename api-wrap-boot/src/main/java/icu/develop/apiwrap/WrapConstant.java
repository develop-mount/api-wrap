package icu.develop.apiwrap;

import org.springframework.context.ApplicationContext;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 包裹静态类
 *
 * @author linfeng-eqxiu
 */
public final class WrapConstant {

    private WrapConstant() {

    }

    public static final AtomicReference<ApplicationContext> applicationContext = new AtomicReference<>();

}























