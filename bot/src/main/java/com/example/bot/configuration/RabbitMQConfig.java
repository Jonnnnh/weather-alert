package com.example.bot.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final ApplicationProperties applicationProperties;

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue weatherRequestQueue() {
        return new Queue(applicationProperties.rabbitmq().weatherQueue(), true);
    }

    @Bean
    public Queue userUpdateQueue() {
        return new Queue(applicationProperties.rabbitmq().userQueue(), true);
    }

    @Bean
    public DirectExchange weatherExchange() {
        return new DirectExchange(applicationProperties.rabbitmq().dlx());
    }

    @Bean
    public Binding weatherRequestBinding(Queue weatherRequestQueue, DirectExchange weatherExchange) {
        return BindingBuilder.bind(weatherRequestQueue)
                .to(weatherExchange)
                .with(applicationProperties.rabbitmq().weatherKey());
    }

    @Bean
    public Binding userUpdateBinding(Queue userUpdateQueue, DirectExchange weatherExchange) {
        return BindingBuilder.bind(userUpdateQueue)
                .to(weatherExchange)
                .with(applicationProperties.rabbitmq().userKey());
    }
}
