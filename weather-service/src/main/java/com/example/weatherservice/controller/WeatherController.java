package com.example.weatherservice.controller;

import com.example.weatherservice.dto.WeatherDataDto;
import com.example.weatherservice.service.FetchWeather;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final FetchWeather fetchWeather;

    @PostMapping
    public WeatherDataDto getWeather(@RequestBody CityRequest cityRequest) {
        return fetchWeather.execute(cityRequest.city());
    }

    public record CityRequest(String city) {}
}

