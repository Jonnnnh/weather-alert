package com.example.bot.telegram.listener;

import com.example.bot.telegram.handler.UpdateHandler;
import com.example.bot.telegram.reply.ReplyMessages;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.example.bot.exceptions.UpdateHandlingException;
import com.example.bot.exceptions.MessageSendingException;
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
                String chatId = String.valueOf(update.message().chat().id());
                try {
                    log.info("Получено обновление от пользователя {}: {}", chatId, update);
                    updateHandler.handleUpdate(update);
                } catch (UpdateHandlingException e) {
                    log.error("Ошибка при обработке обновления для chatId {}: {}, Статус: {}", chatId, e.getMessage(), e.getStatus());
                    telegramBot.execute(new SendMessage(chatId, ReplyMessages.ERROR_OCCURRED.getMessage()));
                } catch (MessageSendingException e) {
                    log.error("Ошибка при отправке сообщения для chatId {}: {}, Статус: {}", chatId, e.getMessage(), e.getStatus());
                    telegramBot.execute(new SendMessage(chatId, ReplyMessages.ERROR_OCCURRED.getMessage()));
                } catch (Exception e) {
                    log.error("Неизвестная ошибка при обработке обновления для chatId {}: {}", chatId, e.getMessage(), e);
                    telegramBot.execute(new SendMessage(chatId, ReplyMessages.ERROR_OCCURRED.getMessage()));
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
