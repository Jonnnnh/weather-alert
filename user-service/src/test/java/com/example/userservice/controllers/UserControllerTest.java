package com.example.userservice.controllers;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTest {

    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);
    }

    @Test
    void getUserByTelegramId_shouldReturnUserIfExists() {
        String telegramId = "123";
        UserDTO userDTO = UserDTO.builder().telegramId(telegramId).build();

        when(userService.getUserByTelegramId(telegramId)).thenReturn(Optional.of(userDTO));

        ResponseEntity<UserDTO> response = userController.getUserByTelegramId(telegramId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
    }

    @Test
    void getUserByTelegramId_shouldReturnNotFoundIfUserDoesNotExist() {
        String telegramId = "123";

        when(userService.getUserByTelegramId(telegramId)).thenReturn(Optional.empty());

        ResponseEntity<UserDTO> response = userController.getUserByTelegramId(telegramId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void createOrUpdateUser_shouldReturnCreatedUser() {
        UserDTO userDTO = UserDTO.builder()
                .telegramId("123")
                .city("Test City")
                .build();

        when(userService.createOrUpdateUser(userDTO)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.createOrUpdateUser(userDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
    }

    @Test
    void deleteUser_shouldReturnNoContent() {
        String telegramId = "123";

        ResponseEntity<Void> response = userController.deleteUser(telegramId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).deleteUserByTelegramId(telegramId);
    }
}
