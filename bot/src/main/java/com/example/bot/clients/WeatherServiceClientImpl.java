package com.example.bot.clients;

import com.example.bot.dto.WeatherDataDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;


@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceClientImpl  implements WeatherServiceClient {

    private final RestTemplate restTemplate;

    @Override
    public WeatherDataDto fetchCurrentWeather(String city) {
        String weatherServiceUrl = "http://weatherservice:8083";
        String url = weatherServiceUrl + "/api/weather?";
        String jsonBody = "{\"city\": \"" + city + "\"}";
        HttpEntity<String> request = new HttpEntity<>(jsonBody, createHeaders());
        ResponseEntity<WeatherDataDto> response = restTemplate.exchange(url, HttpMethod.POST, request, WeatherDataDto.class);
        return response.getBody();
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
