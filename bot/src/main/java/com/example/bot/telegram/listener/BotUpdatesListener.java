package com.example.bot.telegram.listener;

import com.example.bot.telegram.handler.UpdateHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class BotUpdatesListener {

    private final TelegramBot telegramBot;
    private final UpdateHandler updateHandler;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                try {
                    log.info("Обновление процесса обработки: {}", update);
                    updateHandler.handleUpdate(update);
                } catch (Exception e) {
                    String chatId = String.valueOf(update.message().chat().id());
                    log.error("Ошибка при обработке обновления для chatId {}: {}", chatId, e.getMessage(), e);
                    telegramBot.execute(new com.pengrad.telegrambot.request.SendMessage(chatId, "Произошла ошибка. Пожалуйста, повторите позже"));
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
