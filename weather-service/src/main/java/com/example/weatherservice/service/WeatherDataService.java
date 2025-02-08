package com.example.weatherservice.service;

import com.example.weatherservice.dto.WeatherDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherDataService {

    private final WeatherService weatherService;

    public WeatherDataDto fetchWeatherData(String city) {
        return weatherService.fetchWeather(city);
    }
}

