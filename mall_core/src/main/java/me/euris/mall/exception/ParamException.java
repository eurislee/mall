package me.euris.mall.exception;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-10-2023 20:28:00
 */
public class ParamException extends RuntimeException{
    public ParamException() {
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamException(Throwable cause) {
        super(cause);
    }

    public ParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
