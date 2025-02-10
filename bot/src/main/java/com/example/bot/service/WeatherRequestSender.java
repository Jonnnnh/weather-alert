package com.example.bot.service;

import com.example.bot.configuration.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherRequestSender {

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties applicationProperties;

    public void sendWeatherRequest(String chatId, String city) {
        rabbitTemplate.convertAndSend(
                applicationProperties.rabbitmq().dlx(),
                applicationProperties.rabbitmq().weatherKey(),
                city
        );
        System.out.println("Запрос на погоду для города " + city + " отправлен в очередь RabbitMQ для пользователя " + chatId);
    }
}

