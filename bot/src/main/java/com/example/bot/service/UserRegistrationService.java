package com.example.bot.service;

import com.example.bot.dto.UserDTO;
import com.example.bot.enums.Frequency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserUpdateSender userUpdateSender;

    public void registerNewUser(String chatId) {
        UserDTO newUser = UserDTO.builder()
                .telegramId(chatId)
                .city("не указан")
                .frequency(Frequency.DAILY)
                .build();
        userUpdateSender.sendUserUpdate(newUser);
    }
}
