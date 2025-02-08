package com.example.weatherservice.exception;

import org.springframework.http.HttpStatus;

public class WeatherServiceInternalException extends WeatherServiceException {
    public WeatherServiceInternalException(String message, Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
        initCause(cause);
    }

    public WeatherServiceInternalException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }
}
