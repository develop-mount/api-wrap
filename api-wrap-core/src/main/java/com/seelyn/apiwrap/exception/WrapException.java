package com.seelyn.apiwrap.exception;

/**
 * Base Wrap Exception
 *
 * @author linfeng
 */
public class WrapException extends RuntimeException {
    public WrapException() {
    }

    public WrapException(String message) {
        super(message);
    }

    public WrapException(String message, Throwable cause) {
        super(message, cause);
    }
}
