package com.example.userservice.controllers;

import com.example.userservice.dto.UserDTO;
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
        return userDTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{telegramId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String telegramId, @RequestBody UserDTO userDTO) {
        Optional<UserDTO> existingUser = userService.getUserByTelegramId(telegramId);
        if (existingUser.isPresent()) {
            UserDTO updatedUser = userService.createUser(userDTO);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{telegramId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String telegramId) {
        Optional<UserDTO> userDTO = userService.getUserByTelegramId(telegramId);
        if (userDTO.isPresent()) {
            userService.deleteUserByTelegramId(telegramId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

