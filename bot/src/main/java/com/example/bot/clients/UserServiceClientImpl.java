package com.example.bot.clients;

import com.example.bot.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserServiceClientImpl implements UserServiceClient {

    private final WebClient.Builder webClientBuilder;

    private static final String USER_SERVICE_URL = "http://userservice:8081";

    @Override
    public Optional<UserDTO> getUserByTelegramId(String telegramId) {
        try {
            UserDTO userDTO = webClientBuilder.build()
                    .get()
                    .uri(USER_SERVICE_URL + "/users/" + telegramId)
                    .retrieve()
                    .bodyToMono(UserDTO.class)
                    .block();
            return Optional.ofNullable(userDTO);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void createOrUpdateUser(UserDTO userDTO) {
        webClientBuilder.build()
                .post()
                .uri(USER_SERVICE_URL + "/users")
                .bodyValue(userDTO)
                .retrieve()
                .bodyToMono(UserDTO.class)
                .block();
    }
}
