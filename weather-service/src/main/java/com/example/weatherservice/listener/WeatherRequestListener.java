package com.example.weatherservice.listener;

import com.example.weatherservice.dto.WeatherDataDto;
import com.example.weatherservice.exception.WeatherDataNotFoundException;
import com.example.weatherservice.sender.MessageSender;
import com.example.weatherservice.service.WeatherDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherRequestListener {

    private final WeatherDataService weatherDataService;
    private final MessageSender messageSender;

    @RabbitListener(queues = "weatherRequestQueue")
    public void receiveWeatherRequest(String city) {
        try {
            WeatherDataDto weatherData = weatherDataService.fetchWeatherData(city);
            messageSender.sendMessage(weatherData);
        } catch (WeatherDataNotFoundException e) {
            System.out.println("Error: Weather data not found for city: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unknown Error: " + e.getMessage());
        }
    }
}
