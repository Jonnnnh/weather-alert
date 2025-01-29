package com.example.weatherservice.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeatherApiUrlBuilderImpl implements WeatherApiUrlBuilder {

    @Value("${weather-api.api-key}")
    private String apiKey;

    @Value("${weather-api.host}")
    private String host;

    @Value("${weather-api.endpoint}")
    private String endpoint;

    @Override
    public String buildUrl(String city) {
        return String.format("%s%s?key=%s&q=%s&lang=ru", host, endpoint, apiKey, city);
    }
}
