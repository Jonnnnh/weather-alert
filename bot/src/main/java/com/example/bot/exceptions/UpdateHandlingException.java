package com.example.bot.exceptions;

import org.springframework.http.HttpStatus;

public class UpdateHandlingException extends AbstractException {
    public UpdateHandlingException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }

    public UpdateHandlingException(String message, Throwable cause) {
        super(HttpStatus.BAD_REQUEST.value(), message);
        initCause(cause);
    }
}
