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
    public UserDTO createOrUpdateUser(UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findByTelegramId(userDTO.getTelegramId());
        User user = existingUser.orElseGet(() -> User.builder()
                .telegramId(userDTO.getTelegramId())
                .build());

        user.setCity(userDTO.getCity());
        user.setFrequency(userDTO.getFrequency());
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public Optional<UserDTO> getUserByTelegramId(String telegramId) {
        return userRepository.findByTelegramId(telegramId).map(userMapper::toDTO);
    }

    @Override
    public Optional<String> getCityByTelegramId(String telegramId) {
        return userRepository.findByTelegramId(telegramId)
                .map(User::getCity);
    }

    @Override
    public boolean doesUserExist(String telegramId) {
        return userRepository.findByTelegramId(telegramId).isPresent();
    }

    @Override
    public void deleteUserByTelegramId(String telegramId) {
        userRepository.findByTelegramId(telegramId)
                .ifPresent(user -> {
                    userRepository.delete(user);
                    log.info("User Deleted: {}", telegramId);
                });
    }
}
