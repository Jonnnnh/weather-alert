package com.example.bot.telegram.commands;

import com.example.bot.service.UserCityService;
import com.example.bot.telegram.reply.ReplyMessages;
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
        String text = update.message().text().toLowerCase();

        log.info("Получена команда /setcity для chatId: {}", chatId);

        String city = parseCityFromCommand(text);
        if (city == null) {
            log.warn("Город не указан в команде /setcity для chatId: {}", chatId);
            return new SendMessage(chatId, ReplyMessages.SET_CITY_PROMPT.getMessage());
        }

        boolean isUpdated = userCityService.updateCity(chatId, city);
        if (isUpdated) {
            return new SendMessage(chatId, ReplyMessages.CITY_UPDATED.getMessage());
        } else {
            return new SendMessage(chatId, ReplyMessages.USER_NOT_FOUND.getMessage());
        }
    }

    private String parseCityFromCommand(String text) {
        String[] parts = text.split(" ");
        return parts.length > 1 ? parts[1] : null;
    }
}
