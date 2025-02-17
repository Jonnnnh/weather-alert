package com.example.userservice.listener;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserUpdateListener {

    private final UserService userService;

    @RabbitListener(queues = "userUpdateQueue")
    public void receiveUserUpdate(UserDTO userDTO) {
        if (userDTO == null || userDTO.getTelegramId() == null) {
            log.warn("получено некорректное сообщение: {}", userDTO);
            return;
        }
        try {
            boolean userExists = userService.doesUserExist(userDTO.getTelegramId());
            if (!userExists) {
                log.info("пользователь с tg id {} не найден, создаём нового", userDTO.getTelegramId());
            } else {
                log.info("пользователь с tg id  {} найден, обновляем данные", userDTO.getTelegramId());
            }

            UserDTO updatedUser = userService.createOrUpdateUser(userDTO);

            log.info("пользователь успешно обновлён/создан: {}", updatedUser);
        } catch (Exception e) {
            log.error("ошибка при обработке обновления пользователя с tg id {}: {}",
                    userDTO.getTelegramId(), e.getMessage(), e);
        }
    }
}
