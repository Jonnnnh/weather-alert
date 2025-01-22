package com.example.userservice.controllers;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/{telegramId}")
    public ResponseEntity<UserDTO> getUserByTelegramId(@PathVariable String telegramId) {
        log.info("получен запрос на получение пользователя с tg id: {}", telegramId);

        Optional<UserDTO> userDTO = userService.getUserByTelegramId(telegramId);
        if (userDTO.isPresent()) {
            log.info("пользователь найден: {}", userDTO.get());
            return ResponseEntity.ok(userDTO.get());
        } else {
            log.warn("пользователь с tg id {} не найден", telegramId);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createOrUpdateUser(@RequestBody UserDTO userDTO) {
        log.info("получен запрос на создание или обновление пользователя: {}", userDTO);
        UserDTO createdOrUpdatedUser = userService.createOrUpdateUser(userDTO);
        log.info("пользователь успешно сохранён или обновлён: {}", createdOrUpdatedUser);
        return ResponseEntity.ok(createdOrUpdatedUser);
    }

    @DeleteMapping("/{telegramId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String telegramId) {
        log.info("получен запрос на удаление пользователя с tg id: {}", telegramId);
        userService.deleteUserByTelegramId(telegramId);
        log.info("пользователь с tg id {} успешно удалён", telegramId);
        return ResponseEntity.noContent().build();
    }
}
