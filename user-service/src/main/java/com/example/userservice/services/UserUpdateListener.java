package com.example.userservice.services;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUpdateListener {

    private final UserService userService;

    @RabbitListener(queues = "userUpdateQueue")
    public void receiveUserUpdate(UserDTO userDTO) {
        if (userDTO == null) {
            throw new UserNotFoundException();
        }
        try {
            boolean userExists = userService.doesUserExist(userDTO.getTelegramId());
            if (!userExists) {
                throw new UserNotFoundException();
            }
            userService.createOrUpdateUser(userDTO);
        } catch (UserNotFoundException e) {
            System.err.println("User not found: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error processing user update: " + e.getMessage());
            throw e;
        }
    }
}
