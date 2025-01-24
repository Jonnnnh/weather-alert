package com.example.bot.clients;

import com.example.bot.dto.UserDTO;

import java.util.Optional;

public interface UserServiceClient {
    Optional<UserDTO> getUserByTelegramId(String telegramId);
    void createOrUpdateUser(UserDTO userDTO);
}
