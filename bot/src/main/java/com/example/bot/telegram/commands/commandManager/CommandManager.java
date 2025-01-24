package com.example.bot.telegram.commands.commandManager;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface CommandManager {
    SendMessage handleCommand(Update update);
}
