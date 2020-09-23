package com.seelyn.apiwrap;

import com.seelyn.apiwrap.annotation.ApiWrap;
import com.seelyn.apiwrap.exception.InvalidWrapSignatureException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * API安全AOP
 *
 * @author linfeng-eqxiu
 */
@Aspect
@Primary
public class ApiWrapAspect {

    private static final String APP_KEY = "appKey";
    private static final String TIMESTAMP = "timestamp";
    private static final String NONCE = "nonce";

    /**
     * APIWrap pointcut
     */
    @Pointcut("@within(com.seelyn.apiwrap.annotation.ApiWrap) || @annotation(com.seelyn.apiwrap.annotation.ApiWrap)")
    public void apiWrapPointcut() {
        // Do nothing because of pointcut
    }

    /**
     * AOP 之前插入
     *
     * @param joinPoint {@link JoinPoint}
     */
    @Before("apiWrapPointcut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();

        ApiWrap wrapAnnotation = AnnotationUtils.findAnnotation(method, ApiWrap.class);
        if (wrapAnnotation == null) {
            wrapAnnotation = AnnotationUtils.findAnnotation(joinPoint.getTarget().getClass(), ApiWrap.class);
        }
        Objects.requireNonNull(wrapAnnotation);

        Class<?> clazz = wrapAnnotation.value();
        WrapHandler wrapHandler = (WrapHandler) WrapBeanFactoryUtils.getBean(clazz);

        Object[] args = joinPoint.getArgs();
        for (Object obj : args) {
            if (obj instanceof WrapRequest) {
                //noinspection unchecked
                WrapRequest<WrapData> request = (WrapRequest<WrapData>) obj;
                verify(wrapHandler, request);
            }
        }

    }

    /**
     * 验证
     *
     * @param wrapHandler 包裹处理
     * @param request     鉴权请求
     */
    private void verify(WrapHandler wrapHandler, WrapRequest<WrapData> request) {

        long timestamp = request.getTimestamp();
        wrapHandler.isLegalTime(timestamp);

        String appKey = request.getAppKey();
        int nonce = request.getNonce();
        String signatureParam = request.getSignature();
        wrapHandler.isReplayAttack(appKey, timestamp, nonce, signatureParam);

        Map<String, Object> beanMap = beanToMap(request.getData());
        beanMap.put(APP_KEY, appKey);
        beanMap.put(TIMESTAMP, timestamp);
        beanMap.put(NONCE, nonce);
        String signature = wrapHandler.getSignature(appKey, beanMap);

        if (!signatureParam.equals(signature)) {
            throw new InvalidWrapSignatureException();
        }

    }

    /**
     * 将对象装换为map
     *
     * @param bean 待转换对象
     * @return 转换后的Map对象
     */
    private static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key.toString(), beanMap.get(key));
            }
        }
        return map;
    }
}
