package com.example.weatherservice.service;

import com.example.weatherservice.client.CurrentWeatherApiClient;
import com.example.weatherservice.dto.WeatherDataDto;
import com.example.weatherservice.entity.WeatherData;
import com.example.weatherservice.mapper.WeatherDataMapper;
import com.example.weatherservice.parser.WeatherResponseParser;
import com.example.weatherservice.repository.WeatherDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class WeatherService {

    private final CurrentWeatherApiClient weatherApiClient;
    private final WeatherDataRepository weatherDataRepository;
    private final WeatherDataMapper weatherDataMapper;
    private final WeatherResponseParser responseParser;

    public WeatherDataDto fetchWeather(String city) {
        log.info("Fetching weather data for city: {}", city);

        try {
            String response = weatherApiClient.fetchCurrentWeather(city);
            WeatherDataDto weatherDataDto = responseParser.parseResponse(response, city);
            WeatherData weatherData = weatherDataMapper.toEntity(weatherDataDto);

            WeatherData savedData = weatherDataRepository.save(weatherData);
            log.info("Saved weather data: {}", savedData);

            return weatherDataMapper.toDTO(savedData);
        } catch (Exception e) {
            log.error("Error fetching weather data for city: {}", city, e);
            throw new RuntimeException("Failed to fetch and save weather data", e);
        }
    }
}

