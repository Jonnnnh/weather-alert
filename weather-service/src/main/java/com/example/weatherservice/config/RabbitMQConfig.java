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
    public Queue weatherQueue() {
        return new Queue(properties.queue(), true);
    }

    @Bean
    public DirectExchange weatherExchange() {
        return new DirectExchange(properties.exchange());
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(weatherQueue())
                .to(weatherExchange())
                .with(properties.key());
    }
}
