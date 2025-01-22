package com.example.bot.telegram.sender;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSenderImpl implements MessageSender {

    private final TelegramBot telegramBot;

    @Override
    public void sendMessage(SendMessage sendMessage) {
        SendResponse response = telegramBot.execute(sendMessage);
        if (!response.isOk()) {
            throw new RuntimeException("Не удалось отправить сообщение: " + response.description());
        }
    }
}
