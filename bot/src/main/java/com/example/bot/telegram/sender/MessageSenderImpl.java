package com.example.bot.telegram.sender;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.example.bot.exceptions.MessageSendingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageSenderImpl implements MessageSender {

    private final TelegramBot telegramBot;

    @Override
    public void sendMessage(SendMessage sendMessage) {
        try {
            SendResponse response = telegramBot.execute(sendMessage);
            if (!response.isOk()) {
                log.error("Не удалось отправить сообщение: {}", response.description());
                throw new MessageSendingException("Не удалось отправить сообщение: " + response.description());
            }
            log.info("Сообщение успешно отправлено. Ответ: {}", response.description());
        } catch (Exception e) {
            log.error("Ошибка при попытке отправить сообщение: {}", e.getMessage(), e);
            throw new MessageSendingException("Ошибка при попытке отправить сообщение", e);
        }
    }
}
