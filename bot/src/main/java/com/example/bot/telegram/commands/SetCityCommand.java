package com.example.bot.telegram.commands;

import com.example.bot.service.UserCityService;
import com.example.bot.telegram.reply.ReplyMessages;
import com.example.bot.util.CommandUtils;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SetCityCommand implements Command {

    private final UserCityService userCityService;

    @Override
    public SendMessage handle(Update update) {
        String chatId = update.message().chat().id().toString();
        String text = update.message().text();
        String city = CommandUtils.parseCityFromCommand(text);
        if (city == null) {
            return new SendMessage(chatId, ReplyMessages.SET_CITY_PROMPT.getMessage());
        }
        userCityService.updateCity(chatId, city);
        return new SendMessage(chatId, "Город обновлён");
    }
}
