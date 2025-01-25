package com.example.bot.service;

import com.example.bot.clients.UserServiceClient;
import com.example.bot.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCityService {

    private final UserServiceClient userServiceClient;

    public boolean updateCity(String chatId, String city) {
        var existingUser = userServiceClient.getUserByTelegramId(chatId);
        if (existingUser.isPresent()) {
            UserDTO userDTO = existingUser.get();
            userDTO.setCity(city);
            userServiceClient.createOrUpdateUser(userDTO);
            log.info("Город пользователя {} обновлен до: {}", chatId, city);
            return true;
        } else {
            log.warn("Пользователь с chatId {} не найден для обновления города", chatId);
            return false;
        }
    }
}

