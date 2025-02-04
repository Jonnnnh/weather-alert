package com.example.bot.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private ApplicationConfig applicationConfig;
    @Bean
    public Queue weatherRequestQueue() {
        return new Queue(applicationConfig.rabbitmq().weatherQueue(), true);
    }

    @Bean
    public Queue userUpdateQueue() {
        return new Queue(applicationConfig.rabbitmq().userQueue(), true);
    }

    @Bean
    public DirectExchange weatherExchange() {
        return new DirectExchange(applicationConfig.rabbitmq().dlx());
    }

    @Bean
    public Binding weatherRequestBinding() {
        return BindingBuilder.bind(weatherRequestQueue())
                .to(weatherExchange())
                .with(applicationConfig.rabbitmq().weatherKey());
    }

    @Bean
    public Binding userUpdateBinding() {
        return BindingBuilder.bind(userUpdateQueue())
                .to(weatherExchange())
                .with(applicationConfig.rabbitmq().userKey());
    }
}
