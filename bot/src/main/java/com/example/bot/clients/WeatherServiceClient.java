package com.example.bot.clients;

import com.example.bot.dto.WeatherDataDto;

public interface WeatherServiceClient {
    WeatherDataDto fetchCurrentWeather(String city);
}
