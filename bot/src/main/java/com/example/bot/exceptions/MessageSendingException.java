package com.example.bot.exceptions;

import org.springframework.http.HttpStatus;

public class MessageSendingException extends AbstractException {
    public MessageSendingException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public MessageSendingException(String message, Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
        initCause(cause);
    }
}
