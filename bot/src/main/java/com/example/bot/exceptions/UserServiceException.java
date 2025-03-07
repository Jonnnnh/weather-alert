package com.example.bot.exceptions;

import org.springframework.http.HttpStatus;

public class UserServiceException extends AbstractException {
    public UserServiceException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public UserServiceException(String message, Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
        initCause(cause);
    }
}
