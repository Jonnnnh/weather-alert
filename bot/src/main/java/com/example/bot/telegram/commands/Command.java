package com.example.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface Command {
    SendMessage handle(Update update);
}
