package com.example.bot.telegram.commands;

import com.example.bot.clients.UserServiceClient;
import com.example.bot.dto.UserDTO;
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

    private final UserServiceClient userServiceClient;

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

        try {
            var existingUser = userServiceClient.getUserByTelegramId(chatId);
            if (existingUser.isPresent()) {
                UserDTO userDTO = existingUser.get();
                userDTO.setCity(city);
                userServiceClient.createOrUpdateUser(userDTO);
                log.info("Город пользователя {} обновлен до: {}", chatId, city);
                return new SendMessage(chatId, ReplyMessages.CITY_UPDATED.getMessage());
            } else {
                log.warn("Пользователь {} не найден для команды /setcity", chatId);
                return new SendMessage(chatId, ReplyMessages.USER_NOT_FOUND.getMessage());
            }
        } catch (Exception e) {
            log.error("Ошибка при обновлении города для пользователя {}: {}", chatId, e.getMessage(), e);
            return new SendMessage(chatId, ReplyMessages.ERROR_OCCURRED.getMessage());
        }
    }

    private String parseCityFromCommand(String text) {
        String[] parts = text.split(" ");
        return parts.length > 1 ? parts[1] : null;
    }
}
