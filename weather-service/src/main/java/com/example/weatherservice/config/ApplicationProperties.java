package com.example.weatherservice.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "rabbitmq", ignoreUnknownFields = false)
public record  ApplicationProperties(
        @NotBlank String exchange,
        @NotBlank String queue,
        @NotBlank String key,
        @NotBlank String dlx,
        @NotBlank String host,
        @NotNull Integer port,
        @NotBlank String username,
        @NotBlank String password
) {
}


