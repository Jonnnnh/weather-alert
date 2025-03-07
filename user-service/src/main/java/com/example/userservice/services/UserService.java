package com.example.userservice.services;

import com.example.userservice.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    UserDTO createOrUpdateUser(UserDTO userDTO);
    Optional<UserDTO> getUserByTelegramId(String telegramId);
    Optional<String> getCityByTelegramId(String telegramId);
    void deleteUserByTelegramId(String telegramId);
    boolean doesUserExist(String telegramId);
}
