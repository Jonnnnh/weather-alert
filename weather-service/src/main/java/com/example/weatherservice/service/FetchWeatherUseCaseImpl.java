package com.example.weatherservice.service;

import com.example.weatherservice.dto.WeatherDataDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FetchWeatherUseCaseImpl implements FetchWeatherUseCase {

    private final WeatherService weatherService;

    @Override
    public WeatherDataDto execute(String city) {
        return weatherService.fetchWeather(city);
    }
}

