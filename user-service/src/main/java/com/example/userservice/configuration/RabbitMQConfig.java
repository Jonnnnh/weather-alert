package com.example.userservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue userUpdateQueue() {
        return new Queue(applicationProperties.rabbitmq().userQueue(), true);
    }

    @Bean
    public DirectExchange userExchange() {
        return new DirectExchange(applicationProperties.rabbitmq().dlx());
    }

    @Bean
    public Binding userUpdateBinding(Queue userUpdateQueue, DirectExchange userExchange) {
        return BindingBuilder.bind(userUpdateQueue)
                .to(userExchange)
                .with(applicationProperties.rabbitmq().userKey());
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareQueue(userUpdateQueue());
        rabbitAdmin.declareExchange(userExchange());
        rabbitAdmin.declareBinding(userUpdateBinding(userUpdateQueue(), userExchange()));
        return rabbitAdmin;
    }
}
