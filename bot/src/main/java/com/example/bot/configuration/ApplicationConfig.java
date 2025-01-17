package com.example.bot.configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
        @NotBlank
        String applicationName,
        @NotNull
        ServerConfig server,
        @NotNull
        LoggingConfig logging,
        @NotNull
        RabbitMQConfig rabbitmq,
        @NotNull
        TelegramConfig telegram
) {
    public record ServerConfig(
            @Positive
            Integer port
    ) {
    }

    public record LoggingConfig(
            @NotBlank
            String rootLevel,
            @NotBlank
            String botLevel
    ) {
    }

    public record RabbitMQConfig(
            @NotBlank
            String host,
            @Positive
            Integer port,
            @NotBlank
            String username,
            @NotBlank
            String password
    ) {
    }

    public record TelegramConfig(
            @NotBlank
            String botName,
            @NotBlank
            String token
    ) {
    }
}
