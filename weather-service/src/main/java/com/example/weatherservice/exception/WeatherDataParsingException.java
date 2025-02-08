package com.example.weatherservice.exception;

import org.springframework.http.HttpStatus;

public class WeatherDataParsingException extends WeatherServiceException {
    public WeatherDataParsingException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }
    public WeatherDataParsingException(String message, Throwable cause) {
        super(HttpStatus.BAD_REQUEST.value(), message);
        initCause(cause);
    }
}
