package com.example.userservice.configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationProperties (
        @NotNull RabbitMQConfig rabbitmq
){
    public record RabbitMQConfig(@NotBlank String userQueue, @NotBlank String dlx, @NotBlank String userKey) {}

}



