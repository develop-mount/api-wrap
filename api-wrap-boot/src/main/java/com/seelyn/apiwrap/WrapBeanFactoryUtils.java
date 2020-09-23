package com.seelyn.apiwrap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;

/**
 * BeanFactory
 *
 * @author linfeng-eqxiu
 */
public class WrapBeanFactoryUtils implements ApplicationContextAware {

    /**
     * 此方法可以把ApplicationContext对象inject到当前类中作为一个静态成员变量。
     *
     * @param applicationContext ApplicationContext 对象.
     */
    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) {

        WrapConstant.applicationContext.set(applicationContext);
    }

    /**
     * 这是一个便利的方法，帮助我们快速得到一个BEAN
     *
     * @param beanName bean的名字
     * @return 返回一个bean对象
     */
    public static <T> T getBean(Class<T> beanName) {
        return WrapConstant.applicationContext.get().getBean(beanName);
    }
}










