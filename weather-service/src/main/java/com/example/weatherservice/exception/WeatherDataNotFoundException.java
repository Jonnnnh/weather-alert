package com.example.weatherservice.exception;

import org.springframework.http.HttpStatus;

public class WeatherDataNotFoundException extends WeatherServiceException {
    public WeatherDataNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }

    public WeatherDataNotFoundException(String message, Throwable cause) {
        super(HttpStatus.NOT_FOUND.value(), message);
        initCause(cause);
    }
}
