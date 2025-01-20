package com.example.userservice.services;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entities.User;
import com.example.userservice.mappers.UserMapper;
import com.example.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public Optional<UserDTO> getUserByTelegramId(String telegramId) {
        log.info("Получение пользователя по id");
        return userRepository.findByTelegramId(telegramId).map(userMapper::toDTO);
    }

    @Override
    public void deleteUserByTelegramId(String telegramId) {
        userRepository.findByTelegramId(telegramId)
                .ifPresent(userRepository::delete);
    }

}

