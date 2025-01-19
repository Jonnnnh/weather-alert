package com.example.bot.telegram.commands;

import com.example.models.dto.UserDTO;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface Command {
    SendMessage handle(UserDTO userDTO , Update update);
}
