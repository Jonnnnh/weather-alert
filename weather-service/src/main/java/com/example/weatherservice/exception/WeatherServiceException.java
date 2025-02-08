package com.example.weatherservice.exception;

import lombok.Getter;

@Getter
public abstract class WeatherServiceException extends RuntimeException {

    private final int status;

    public WeatherServiceException(int status, String message) {
        super(message);
        this.status = status;
    }
}
