package com.example.userservice.controllers;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.exception.CityNotFoundException;
import com.example.userservice.exception.UserNotFoundException;
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
    void getUserByTelegramId_shouldThrowUserNotFoundExceptionIfUserDoesNotExist() {
        String telegramId = "123";

        when(userService.getUserByTelegramId(telegramId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userController.getUserByTelegramId(telegramId));
    }

    @Test
    void getCityByTelegramId_shouldReturnCityIfExists() {
        String telegramId = "123";
        String city = "Test City";

        when(userService.getCityByTelegramId(telegramId)).thenReturn(Optional.of(city));

        ResponseEntity<String> response = userController.getCityByTelegramId(telegramId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(city, response.getBody());
    }

    @Test
    void getCityByTelegramId_shouldThrowCityNotFoundExceptionIfCityDoesNotExist() {
        String telegramId = "123";

        when(userService.getCityByTelegramId(telegramId)).thenReturn(Optional.empty());

        assertThrows(CityNotFoundException.class, () -> userController.getCityByTelegramId(telegramId));
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
    void deleteUser_shouldReturnNoContentIfUserExists() {
        String telegramId = "123";

        when(userService.doesUserExist(telegramId)).thenReturn(true);
        doNothing().when(userService).deleteUserByTelegramId(telegramId);

        ResponseEntity<Void> response = userController.deleteUser(telegramId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).deleteUserByTelegramId(telegramId);
    }

    @Test
    void deleteUser_shouldThrowUserNotFoundExceptionIfUserDoesNotExist() {
        String telegramId = "123";

        when(userService.doesUserExist(telegramId)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userController.deleteUser(telegramId));
    }
}
