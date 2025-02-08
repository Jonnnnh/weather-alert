package com.example.weatherservice.client;

import com.example.weatherservice.exception.WeatherServiceInternalException;
import com.example.weatherservice.util.WeatherApiUrlBuilderImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class CurrentWeatherApiClientImpl implements CurrentWeatherApiClient {

    private final RestTemplate restTemplate;
    private final WeatherApiUrlBuilderImpl urlBuilder;

    @Override
    public String fetchCurrentWeather(String city) {
        String url = urlBuilder.buildUrl(city);
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            throw new WeatherServiceInternalException("Failed to fetch weather data", e);
        }
    }
}
