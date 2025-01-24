package com.example.bot.clients;

import com.example.bot.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserServiceClientImpl implements UserServiceClient {

    private final WebClient.Builder webClientBuilder;

    private static final String USER_SERVICE_URL = "http://userservice:8081";

    @Override
    public Optional<UserDTO> getUserByTelegramId(String telegramId) {
        log.info("Попытка получить пользователя с Telegram ID: {}", telegramId);
        try {
            UserDTO userDTO = webClientBuilder.build()
                    .get()
                    .uri(USER_SERVICE_URL + "/users/" + telegramId)
                    .retrieve()
                    .bodyToMono(UserDTO.class)
                    .block();

            if (userDTO != null) {
                log.info("Пользователь с Telegram ID: {} успешно найден", telegramId);
                return Optional.of(userDTO);
            } else {
                log.warn("Пользователь с Telegram ID: {} не найден", telegramId);
                return Optional.empty();
            }
        } catch (Exception e) {
            log.error("Произошла ошибка при получении пользователя с Telegram ID: {}. Ошибка: {}", telegramId, e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public void createOrUpdateUser(UserDTO userDTO) {
        log.info("Попытка создать или обновить пользователя с Telegram ID: {}", userDTO.getTelegramId());
        try {
            userDTO = webClientBuilder.build()
                    .post()
                    .uri(USER_SERVICE_URL + "/users")
                    .bodyValue(userDTO)
                    .retrieve()
                    .bodyToMono(UserDTO.class)
                    .block();

            if (userDTO != null) {
                log.info("Пользователь с Telegram ID: {} успешно создан/обновлен", userDTO.getTelegramId());
            } else {
                log.warn("Не удалось создать/обновить пользователя с Telegram ID: {}", userDTO.getTelegramId());
            }
        } catch (Exception e) {
            log.error("Произошла ошибка при создании/обновлении пользователя с Telegram ID: {}. Ошибка: {}", userDTO.getTelegramId(), e.getMessage(), e);
        }
    }
}
