package com.example.weatherservice.service;

import com.example.weatherservice.client.CurrentWeatherApiClient;
import com.example.weatherservice.dto.WeatherDataDto;
import com.example.weatherservice.entity.WeatherData;
import com.example.weatherservice.mapper.WeatherDataMapper;
import com.example.weatherservice.parser.WeatherResponseParser;
import com.example.weatherservice.repository.WeatherDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {

    private CurrentWeatherApiClient weatherApiClient;
    private WeatherDataRepository weatherDataRepository;
    private WeatherDataMapper weatherDataMapper;
    private WeatherResponseParser responseParser;
    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        weatherApiClient = mock(CurrentWeatherApiClient.class);
        weatherDataRepository = mock(WeatherDataRepository.class);
        weatherDataMapper = mock(WeatherDataMapper.class);
        responseParser = mock(WeatherResponseParser.class);

        weatherService = new WeatherService(weatherApiClient, weatherDataRepository, weatherDataMapper, responseParser);
    }

    @Test
    void fetchWeather_success() {
        String city = "Moscow";
        String apiResponse = "{ \"temp\": \"20\" }";
        WeatherDataDto dto = WeatherDataDto.builder()
                .city("Moscow")
                .temperature(20.0)
                .build();
        WeatherData entity = new WeatherData();

        when(weatherApiClient.fetchCurrentWeather(city)).thenReturn(apiResponse);
        when(responseParser.parseResponse(apiResponse, city)).thenReturn(dto);
        when(weatherDataMapper.toEntity(dto)).thenReturn(entity);
        when(weatherDataRepository.save(entity)).thenReturn(entity);
        when(weatherDataMapper.toDTO(entity)).thenReturn(dto);

        WeatherDataDto result = weatherService.fetchWeather(city);

        assertNotNull(result);
        assertEquals(dto, result);
        verify(weatherApiClient, times(1)).fetchCurrentWeather(city);
        verify(responseParser, times(1)).parseResponse(apiResponse, city);
        verify(weatherDataRepository, times(1)).save(entity);
    }

    @Test
    void fetchWeather_failure() {
        String city = "Moscow";

        when(weatherApiClient.fetchCurrentWeather(city)).thenThrow(new RuntimeException("API failure"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> weatherService.fetchWeather(city));

        assertEquals("Failed to fetch and save weather data", exception.getMessage());
        verify(weatherApiClient, times(1)).fetchCurrentWeather(city);
    }
}
