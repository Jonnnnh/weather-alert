package com.example.bot.service;

import com.example.bot.clients.UserServiceClient;
import com.example.bot.dto.UserDTO;
import com.example.bot.enums.Frequency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserServiceClient userServiceClient;

    public boolean isUserRegistered(String chatId) {
        return userServiceClient.getUserByTelegramId(chatId).isPresent();
    }

    public void registerNewUser(String chatId) {
        try {
            UserDTO newUser = UserDTO.builder()
                    .telegramId(chatId)
                    .city("не указан")
                    .frequency(Frequency.DAILY)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            log.info("Регистрация нового пользователя: {}", chatId);
            userServiceClient.createOrUpdateUser(newUser);
            log.info("Пользователь {} успешно зарегистрирован", chatId);
        } catch (Exception e) {
            log.error("Ошибка при регистрации пользователя с chatId {}: {}", chatId, e.getMessage());
            throw new RuntimeException("Ошибка при регистрации нового пользователя", e);
        }
    }
}
