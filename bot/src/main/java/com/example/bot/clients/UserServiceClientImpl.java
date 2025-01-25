package com.example.bot.clients;

import com.example.bot.dto.UserDTO;
import com.example.bot.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserServiceClientImpl implements UserServiceClient {

    private final RestTemplate restTemplate;
    private final String userServiceUrl = "http://userservice:8081";
    private static final String GET_USER_PATH = "/users/";

    @Override
    public Optional<UserDTO> getUserByTelegramId(String telegramId) {
        String url = userServiceUrl + GET_USER_PATH + telegramId;
        try {
            ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);
            if (response.getBody() != null) {
                return Optional.of(response.getBody());
            } else {
                throw new UserNotFoundException("Пользователь с Telegram ID " + telegramId + " не найден");
            }
        } catch (UserNotFoundException e) {
            log.warn(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Произошла ошибка при получении пользователя с Telegram ID: {}. Ошибка: {}", telegramId, e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public void createOrUpdateUser(UserDTO userDTO) {
        String url = userServiceUrl + "/users";
        try {
            HttpEntity<UserDTO> request = new HttpEntity<>(userDTO);
            ResponseEntity<UserDTO> response = restTemplate.postForEntity(url, request, UserDTO.class);
            if (response.getBody() != null) {
                log.info("Пользователь с Telegram ID: {} успешно создан/обновлен", userDTO.getTelegramId());
            } else {
                log.warn("Не удалось создать/обновить пользователя с Telegram ID: {}", userDTO.getTelegramId());
            }
        } catch (Exception e) {
            log.error("Произошла ошибка при создании/обновлении пользователя с Telegram ID: {}. Ошибка: {}", userDTO.getTelegramId(), e.getMessage(), e);
        }
    }
}

