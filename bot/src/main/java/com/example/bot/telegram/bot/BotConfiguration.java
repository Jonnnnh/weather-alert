package com.example.bot.telegram.bot;

import com.pengrad.telegrambot.TelegramBot;
import  com.example.bot.configuration.ApplicationConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BotConfiguration {

    private final ApplicationConfig applicationConfig;

    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot(applicationConfig.telegram().token());
    }
}

