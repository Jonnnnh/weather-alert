package com.example.bot.telegram.commands;

import com.example.bot.clients.UserServiceClient;
import com.example.bot.dto.UserDTO;
import com.example.bot.enums.Frequency;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class StartCommand implements Command {

    private final UserServiceClient userServiceClient;

    @Override
    public SendMessage handle(Update update) {
        String chatId = update.message().chat().id().toString();
        String text = update.message().text().toLowerCase();

        var existingUser = userServiceClient.getUserByTelegramId(chatId);
        if (existingUser.isPresent()) {
            return new SendMessage(chatId, "Вы уже зарегистрированы. Пожалуйста, введите ваш город");
        }

        UserDTO newUser = UserDTO.builder()
                .telegramId(chatId)
                .city("не указан")
                .frequency(Frequency.DAILY)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userServiceClient.createOrUpdateUser(newUser);

        return new SendMessage(chatId, "Добро пожаловать! Вы успешно зарегистрированы. Пожалуйста, введите ваш город: /setcity <город>");
    }
}
