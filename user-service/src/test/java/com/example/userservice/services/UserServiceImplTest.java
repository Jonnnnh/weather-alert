package com.example.userservice.services;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entities.User;
import com.example.userservice.mappers.UserMapper;
import com.example.userservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, userMapper);
    }

    @Test
    void createOrUpdateUser_shouldSaveNewUser() {
        UserDTO userDTO = UserDTO.builder()
                .telegramId("123")
                .city("Test City")
                .build();

        User user = User.builder()
                .telegramId("123")
                .city("Test City")
                .build();

        when(userRepository.findByTelegramId(userDTO.getTelegramId())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDTO(any(User.class))).thenReturn(userDTO);

        UserDTO result = userService.createOrUpdateUser(userDTO);

        assertNotNull(result);
        assertEquals(userDTO.getTelegramId(), result.getTelegramId());
        assertEquals(userDTO.getCity(), result.getCity());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void createOrUpdateUser_shouldUpdateExistingUser() {
        UserDTO userDTO = UserDTO.builder()
                .telegramId("123")
                .city("Updated City")
                .build();

        User existingUser = User.builder()
                .telegramId("123")
                .city("Old City")
                .build();

        User updatedUser = User.builder()
                .telegramId("123")
                .city("Updated City")
                .build();

        when(userRepository.findByTelegramId(userDTO.getTelegramId())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(updatedUser);
        when(userMapper.toDTO(updatedUser)).thenReturn(userDTO);

        UserDTO result = userService.createOrUpdateUser(userDTO);

        assertNotNull(result);
        assertEquals("Updated City", result.getCity());
        verify(userRepository).save(existingUser);
    }

    @Test
    void getUserByTelegramId_shouldReturnUserIfExists() {
        String telegramId = "123";
        User user = User.builder().telegramId(telegramId).build();
        UserDTO userDTO = UserDTO.builder().telegramId(telegramId).build();

        when(userRepository.findByTelegramId(telegramId)).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        Optional<UserDTO> result = userService.getUserByTelegramId(telegramId);

        assertTrue(result.isPresent());
        assertEquals(telegramId, result.get().getTelegramId());
    }

    @Test
    void getUserByTelegramId_shouldReturnEmptyIfNotExists() {
        String telegramId = "123";

        when(userRepository.findByTelegramId(telegramId)).thenReturn(Optional.empty());

        Optional<UserDTO> result = userService.getUserByTelegramId(telegramId);

        assertTrue(result.isEmpty());
    }

    @Test
    void getCityByTelegramId_shouldReturnCityIfExists() {
        String telegramId = "123";
        String city = "Test City";
        User user = User.builder().telegramId(telegramId).city(city).build();

        when(userRepository.findByTelegramId(telegramId)).thenReturn(Optional.of(user));

        Optional<String> result = userService.getCityByTelegramId(telegramId);

        assertTrue(result.isPresent());
        assertEquals(city, result.get());
    }

    @Test
    void getCityByTelegramId_shouldReturnEmptyIfCityNotFound() {
        String telegramId = "123";

        when(userRepository.findByTelegramId(telegramId)).thenReturn(Optional.empty());

        Optional<String> result = userService.getCityByTelegramId(telegramId);

        assertTrue(result.isEmpty());
    }

    @Test
    void doesUserExist_shouldReturnTrueIfUserExists() {
        String telegramId = "123";
        User user = User.builder().telegramId(telegramId).build();

        when(userRepository.findByTelegramId(telegramId)).thenReturn(Optional.of(user));

        boolean exists = userService.doesUserExist(telegramId);

        assertTrue(exists);
    }

    @Test
    void doesUserExist_shouldReturnFalseIfUserDoesNotExist() {
        String telegramId = "123";

        when(userRepository.findByTelegramId(telegramId)).thenReturn(Optional.empty());

        boolean exists = userService.doesUserExist(telegramId);

        assertFalse(exists);
    }

    @Test
    void deleteUserByTelegramId_shouldDeleteUserIfExists() {
        String telegramId = "123";
        User user = User.builder().telegramId(telegramId).build();

        when(userRepository.findByTelegramId(telegramId)).thenReturn(Optional.of(user));

        userService.deleteUserByTelegramId(telegramId);

        verify(userRepository).delete(user);
    }

    @Test
    void deleteUserByTelegramId_shouldDoNothingIfUserDoesNotExist() {
        String telegramId = "123";

        when(userRepository.findByTelegramId(telegramId)).thenReturn(Optional.empty());

        userService.deleteUserByTelegramId(telegramId);

        verify(userRepository, never()).delete(any(User.class));
    }
}

