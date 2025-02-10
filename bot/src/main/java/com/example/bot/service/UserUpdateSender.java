package com.example.bot.service;

import com.example.bot.configuration.ApplicationProperties;
import com.example.bot.dto.UserDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUpdateSender {

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties applicationProperties;

    public void sendUserUpdate(UserDTO userDTO) {
        System.out.println("Отправка пользователя: " + userDTO);
        rabbitTemplate.convertAndSend(
                applicationProperties.rabbitmq().dlx(),
                applicationProperties.rabbitmq().userKey(),
                userDTO);
        System.out.println("Запрос на обновление города пользователя " + userDTO.getTelegramId() + " отправлен в очередь RabbitMQ");
    }
}
