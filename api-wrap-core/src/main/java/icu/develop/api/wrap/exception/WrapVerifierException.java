package icu.develop.api.wrap.exception;

/**
 * 验证异常
 *
 * @author linfeng-eqxiu
 */
public class WrapVerifierException extends WrapException {
    @SuppressWarnings("unused")
    public WrapVerifierException() {
    }

    public WrapVerifierException(String message, Throwable cause) {
        super(message, cause);
    }
}
