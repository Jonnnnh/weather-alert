package com.example.bot.telegram.sender;


public interface MessageSender {
    void sendMessage(Long chatId, String message);
}
