package com.example.weatherservice.service;

import com.example.weatherservice.dto.WeatherDataDto;

public interface FetchWeatherUseCase {
    WeatherDataDto execute(String city);
}
