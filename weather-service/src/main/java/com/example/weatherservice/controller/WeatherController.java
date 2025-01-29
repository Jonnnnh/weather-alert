package com.example.weatherservice.controller;

import com.example.weatherservice.dto.WeatherDataDto;
import com.example.weatherservice.service.FetchWeatherUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final FetchWeatherUseCase fetchWeatherUseCase;

    @PostMapping
    public WeatherDataDto getWeather(@RequestBody CityRequest cityRequest) {
        return fetchWeatherUseCase.execute(cityRequest.city());
    }

    public record CityRequest(String city) {}
}

