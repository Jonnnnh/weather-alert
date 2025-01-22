package com.example.bot.telegram.handler;

import com.example.bot.telegram.commands.HelpCommand;
import com.example.bot.telegram.reply.ReplyMessages;
import com.example.bot.telegram.sender.MessageSender;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateHandlerImpl implements UpdateHandler {
    private final HelpCommand helpCommand;
    private final MessageSender messageSender;

    @Override
    public void handleUpdate(Update update) {
        if (update.message() != null && update.message().text() != null) {
            String text = update.message().text().toLowerCase();
            if (text.equals("/help")) {
                messageSender.sendMessage(helpCommand.handle(update));
            } else {
                String chatId = update.message().chat().id().toString();
                var response = new SendMessage(chatId, ReplyMessages.UNKNOWN_COMMAND.getMessage());
                messageSender.sendMessage(response);
            }
        }
    }
}
