package com.example.weatherservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final ApplicationProperties properties;

    @Bean
    public Queue weatherRequestQueue() {
        return new Queue(properties.rabbitmq().weatherQueue(), true);
    }

    @Bean
    public DirectExchange weatherExchange() {
        return new DirectExchange(properties.rabbitmq().dlx());
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(weatherRequestQueue())
                .to(weatherExchange())
                .with(properties.rabbitmq().weatherKey());
    }
}
