package com.example.userservice.controllers;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.exception.*;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{telegramId}")
    public ResponseEntity<UserDTO> getUserByTelegramId(@PathVariable String telegramId) {
        Optional<UserDTO> userDTO = userService.getUserByTelegramId(telegramId);
        if (userDTO.isPresent()) {
            return ResponseEntity.ok(userDTO.get());
        } else {
            throw new UserNotFoundException();
        }
    }

    @GetMapping("/{telegramId}/city")
    public ResponseEntity<String> getCityByTelegramId(@PathVariable String telegramId) {
        Optional<String> city = userService.getCityByTelegramId(telegramId);
        if (city.isPresent()) {
            return ResponseEntity.ok(city.get());
        } else {
            throw new CityNotFoundException();
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createOrUpdateUser(@RequestBody UserDTO userDTO) {
        UserDTO createdOrUpdatedUser = userService.createOrUpdateUser(userDTO);
        return ResponseEntity.ok(createdOrUpdatedUser);
    }

    @DeleteMapping("/{telegramId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String telegramId) {
        if (!userService.doesUserExist(telegramId)) {
            throw new UserNotFoundException();
        }
        userService.deleteUserByTelegramId(telegramId);
        return ResponseEntity.noContent().build();
    }
}
