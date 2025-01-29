package com.example.weatherservice.client;

import com.example.weatherservice.util.WeatherApiUrlBuilderImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
@RequiredArgsConstructor
public class CurrentWeatherApiClientImpl implements CurrentWeatherApiClient {

    private final RestTemplate restTemplate;
    private final WeatherApiUrlBuilderImpl urlBuilder;

    @Override
    public String fetchCurrentWeather(String city) {
        log.info("Запрашиваем данные о погоде для города: {}", city);

        String url = urlBuilder.buildUrl(city);

        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            log.error("Ошибка при вызове Weather API", e);
            throw new RuntimeException("Failed to fetch weather data", e);
        }
    }
}
