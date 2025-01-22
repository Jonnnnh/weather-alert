package com.example.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand implements Command {
    @Override
    public SendMessage handle(Update update) {
        String chatId = update.message().chat().id().toString();
        String helpMessage = """
                /help - Show this help message
                """;
        return new SendMessage(chatId, helpMessage);
    }
}
