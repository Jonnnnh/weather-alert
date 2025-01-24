package com.example.bot.telegram.handler;

import com.example.bot.telegram.commands.commandManager.CommandManager;
import com.example.bot.telegram.sender.MessageSender;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateHandlerImpl implements UpdateHandler {
    private final CommandManager commandManager;
    private final MessageSender messageSender;

    @Override
    public void handleUpdate(Update update) {
        if (update.message() != null && update.message().text() != null) {
            SendMessage response = commandManager.handleCommand(update);
            messageSender.sendMessage(response);
        }
    }
}
