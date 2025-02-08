package com.example.weatherservice.controller;

import com.example.weatherservice.dto.WeatherDataDto;
import com.example.weatherservice.service.FetchWeather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class WeatherControllerTest {

    private FetchWeather fetchWeatherUseCase;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        fetchWeatherUseCase = mock(FetchWeather.class);
        WeatherController weatherController = new WeatherController(fetchWeatherUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
    }

    @Test
    void getWeather_success() throws Exception {
        String city = "Moscow";
        WeatherDataDto dto = WeatherDataDto.builder()
                .city("Moscow")
                .temperature(20.0)
                .build();

        when(fetchWeatherUseCase.execute(city)).thenReturn(dto);

        mockMvc.perform(post("/api/weather")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"city\": \"Moscow\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value(city))
                .andExpect(jsonPath("$.temperature").value(20.0));

        verify(fetchWeatherUseCase, times(1)).execute(city);
    }
}
