package com.example.bot.telegram.listener;

import com.example.bot.telegram.handler.UpdateHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class BotUpdatesListener {

    private final TelegramBot telegramBot;
    private final UpdateHandler updateHandler;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                try {
                    updateHandler.handleUpdate(update);
                } catch (Exception e) {
                    String chatId = String.valueOf(update.message().chat().id());
                    telegramBot.execute(new com.pengrad.telegrambot.request.SendMessage(chatId, "Произошла ошибка. Пожалуйста, повторите позже"));
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}

