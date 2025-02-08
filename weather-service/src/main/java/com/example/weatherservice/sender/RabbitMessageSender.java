package com.example.weatherservice.sender;

import com.example.weatherservice.config.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMessageSender implements MessageSender {

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties applicationProperties;

    @Override
    public void sendMessage(Object message) {
        rabbitTemplate.convertAndSend(
                applicationProperties.rabbitmq().dlx(),
                applicationProperties.rabbitmq().weatherKey(),
                message
        );
    }
}