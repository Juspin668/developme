package com.juspin.task.exception;


/**
 * Description: The custom business exception.
 *
 * @author juspin
 * @since 2025/1/26
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
