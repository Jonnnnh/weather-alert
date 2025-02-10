package com.example.bot.clients;

import com.example.bot.exceptions.UserServiceException;
import com.example.grpc.user.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GrpcUserServiceClient {

    private final UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    public Optional<GetUserByTelegramIdResponse> getUserByTelegramId(String telegramId) {
        GetUserByTelegramIdRequest request = GetUserByTelegramIdRequest.newBuilder()
                .setTelegramId(telegramId)
                .build();
        try {
            GetUserByTelegramIdResponse response = userServiceStub.getUserByTelegramId(request);
            if (!response.getFound()) {
                log.warn("Пользователь с Telegram ID: {} не найден.", telegramId);
                return Optional.empty();
            }
            return Optional.of(response);
        } catch (Exception e) {
            log.error("Ошибка при запросе пользователя с Telegram ID: {}: {}", telegramId, e.getMessage(), e);
            throw new UserServiceException("Ошибка при запросе пользователя с Telegram ID: " + telegramId, e);
        }
    }

    public Optional<GetCityByTelegramIdResponse> getCityByTelegramId(String telegramId) {
        GetUserByTelegramIdRequest request = GetUserByTelegramIdRequest.newBuilder()
                .setTelegramId(telegramId)
                .build();
        try {
            GetUserByTelegramIdResponse response = userServiceStub.getUserByTelegramId(request);
            if (!response.getFound()) {
                return Optional.empty();
            }
            GetCityByTelegramIdResponse cityResponse = GetCityByTelegramIdResponse.newBuilder()
                    .setFound(true)
                    .setCity(response.getCity())
                    .build();
            return Optional.of(cityResponse);
        } catch (Exception e) {
            log.error("Ошибка при запросе города для пользователя с Telegram ID: {}: {}", telegramId, e.getMessage(), e);
            throw new UserServiceException("Ошибка при запросе города для пользователя с Telegram ID: " + telegramId, e);
        }
    }
}
