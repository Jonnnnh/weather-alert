package com.example.weatherservice.service;

import com.example.weatherservice.dto.WeatherDataDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FetchWeatherUseCaseImplTest {

    private WeatherService weatherService;
    private FetchWeatherUseCaseImpl fetchWeatherUseCase;

    @BeforeEach
    void setUp() {
        weatherService = mock(WeatherService.class);
        fetchWeatherUseCase = new FetchWeatherUseCaseImpl(weatherService);
    }

    @Test
    void execute_success() {
        String city = "Moscow";
        WeatherDataDto expectedDto = WeatherDataDto.builder()
                .city("Moscow")
                .temperature(20.0)
                .build();

        when(weatherService.fetchWeather(city)).thenReturn(expectedDto);

        WeatherDataDto result = fetchWeatherUseCase.execute(city);

        assertNotNull(result);
        assertEquals(expectedDto, result);
        verify(weatherService, times(1)).fetchWeather(city);
    }
}
