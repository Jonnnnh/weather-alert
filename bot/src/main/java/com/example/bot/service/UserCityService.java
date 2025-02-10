package com.example.bot.service;

import com.example.bot.dto.UserDTO;
import com.example.bot.enums.Frequency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCityService {

    private final UserUpdateSender userUpdateSender;

    public void updateCity(String chatId, String city) {
        UserDTO dto = UserDTO.builder()
                .telegramId(chatId)
                .city(city)
                .frequency(Frequency.DAILY)
                .build();
        userUpdateSender.sendUserUpdate(dto);
    }
}

