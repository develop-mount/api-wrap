package icu.develop.apiwrap;

import icu.develop.apiwrap.annotation.ApiWrap;
import icu.develop.apiwrap.exception.InvalidWrapSignatureException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * API安全AOP
 *
 * @author linfeng-eqxiu
 */
@Aspect
@Primary
public class ApiWrapAspect {

    /**
     * APIWrap pointcut
     */
    @Pointcut("@within(icu.develop.apiwrap.annotation.ApiWrap) || @annotation(icu.develop.apiwrap.annotation.ApiWrap)")
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
        if (args == null || args.length == 0) {
            throw new InvalidWrapSignatureException("签名有误");
        }
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
        //验证重放攻击
        wrapHandler.isReplayAttack(appKey, timestamp, nonce, signatureParam);
        // 验证签名
        if (!wrapHandler.verifySignature(appKey, request)) {
            throw new InvalidWrapSignatureException("签名问题");
        }

    }
}
