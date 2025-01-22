package com.example.bot.telegram.sender;

import com.pengrad.telegrambot.request.SendMessage;

public interface MessageSender {
    void sendMessage(SendMessage sendMessage);
}
