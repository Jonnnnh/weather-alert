package com.example.weatherservice.service;

import com.example.weatherservice.client.CurrentWeatherApiClient;
import com.example.weatherservice.dto.WeatherDataDto;
import com.example.weatherservice.entity.WeatherData;
import com.example.weatherservice.exception.WeatherDataNotFoundException;
import com.example.weatherservice.exception.WeatherServiceInternalException;
import com.example.weatherservice.mapper.WeatherDataMapper;
import com.example.weatherservice.parser.WeatherResponseParser;
import com.example.weatherservice.repository.WeatherDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WeatherService {

    private final CurrentWeatherApiClient weatherApiClient;
    private final WeatherDataRepository weatherDataRepository;
    private final WeatherDataMapper weatherDataMapper;
    private final WeatherResponseParser responseParser;

    public WeatherDataDto fetchWeather(String city) {
        try {
            String response = weatherApiClient.fetchCurrentWeather(city);
            if (response == null || response.isEmpty()) {
                throw new WeatherDataNotFoundException("No weather data was found for city " + city);
            }
            WeatherDataDto weatherDataDto = responseParser.parseResponse(response, city);
            WeatherData weatherData = weatherDataMapper.toEntity(weatherDataDto);
            WeatherData savedData = weatherDataRepository.save(weatherData);
            return weatherDataMapper.toDTO(savedData);
        } catch (WeatherDataNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new WeatherServiceInternalException("Error when retrieving weather data for city: " + city, e);
        }
    }
}

