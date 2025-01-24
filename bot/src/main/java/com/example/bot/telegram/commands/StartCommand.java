package com.example.bot.telegram.commands;

import com.example.bot.clients.UserServiceClient;
import com.example.bot.dto.UserDTO;
import com.example.bot.enums.Frequency;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartCommand implements Command {

    private final UserServiceClient userServiceClient;

    @Override
    public SendMessage handle(Update update) {
        String chatId = update.message().chat().id().toString();
        String text = update.message().text().toLowerCase();

        log.info("Получена команда /start для chatId: {}", chatId);

        var existingUser = userServiceClient.getUserByTelegramId(chatId);
        if (existingUser.isPresent()) {
            log.info("Пользователь {} уже зарегистрирован", chatId);
            return new SendMessage(chatId, "Вы уже зарегистрированы. Пожалуйста, введите ваш город");
        }

        UserDTO newUser = UserDTO.builder()
                .telegramId(chatId)
                .city("не указан")
                .frequency(Frequency.DAILY)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        try {
            log.info("Регистрация нового пользователя: {}", chatId);
            userServiceClient.createOrUpdateUser(newUser);
            log.info("Пользователь {} успешно зарегистрирован", chatId);
        } catch (Exception e) {
            log.error("Ошибка при регистрации пользователя {}: {}", chatId, e.getMessage(), e);
            return new SendMessage(chatId, "Произошла ошибка при регистрации. Пожалуйста, повторите позже");
        }

        return new SendMessage(chatId, "Добро пожаловать! Вы успешно зарегистрированы. Пожалуйста, введите ваш город: /setcity <город>");
    }
}
