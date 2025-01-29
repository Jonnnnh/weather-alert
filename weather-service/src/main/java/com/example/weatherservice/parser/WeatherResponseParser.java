package com.example.weatherservice.parser;

import com.example.weatherservice.dto.WeatherApiResponse;
import com.example.weatherservice.dto.WeatherDataDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WeatherResponseParser {

    private final ObjectMapper objectMapper;

    public WeatherResponseParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public WeatherDataDto parseResponse(String jsonResponse, String city) {
        try {
            WeatherApiResponse response = objectMapper.readValue(jsonResponse, WeatherApiResponse.class);
            return WeatherDataDto.builder()
                    .city(city)
                    .temperature(response.getCurrent().getTempC())
                    .cloudiness(response.getCurrent().getCloud())
                    .humidity(response.getCurrent().getHumidity())
                    .condition(response.getCurrent().getCondition().getText())
                    .isDay(response.getCurrent().getIsDay() == 1)
                    .recordedAt(LocalDateTime.now())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error parsing Weather API response", e);
        }
    }
}

