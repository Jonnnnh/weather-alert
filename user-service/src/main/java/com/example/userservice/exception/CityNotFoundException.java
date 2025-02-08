package com.example.userservice.exception;

import org.springframework.http.HttpStatus;

public class CityNotFoundException extends AbstractException {

    public CityNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "City not found");
    }
}
