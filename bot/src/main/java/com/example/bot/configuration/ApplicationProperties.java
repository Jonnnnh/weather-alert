package com.example.bot.configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationProperties(
        @NotBlank String applicationName,
        @NotNull RabbitMQConfig rabbitmq,
        @NotNull TelegramConfig telegram
) {
    public record RabbitMQConfig(@NotBlank String weatherQueue, @NotBlank String userQueue, @NotBlank String dlx, @NotBlank String weatherKey,@NotBlank String userKey) {}
    public record TelegramConfig(@NotBlank String botName, @NotBlank String token) {}
}
