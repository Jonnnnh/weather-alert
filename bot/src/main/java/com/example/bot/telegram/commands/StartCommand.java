package com.example.bot.telegram.commands;

import com.example.bot.service.UserRegistrationService;
import com.example.bot.telegram.reply.ReplyMessages;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class StartCommand implements Command {

    private final UserRegistrationService userRegistrationService;

    @Override
    public SendMessage handle(Update update) {
        String chatId = update.message().chat().id().toString();
        userRegistrationService.registerNewUser(chatId);
        return new SendMessage(chatId, ReplyMessages.WELCOME_MESSAGE.getMessage());
    }
}
