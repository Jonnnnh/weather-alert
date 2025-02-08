package com.example.weatherservice.service;

import com.example.weatherservice.dto.WeatherDataDto;

public interface FetchWeather {
    WeatherDataDto execute(String city);
}
