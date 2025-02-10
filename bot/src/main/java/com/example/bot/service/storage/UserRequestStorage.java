package com.example.bot.service.storage;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserRequestStorage {

    private final ConcurrentHashMap<String, String> userRequests = new ConcurrentHashMap<>();

    public void storeTelegramId(String city, String telegramId) {
        userRequests.put(city, telegramId);
    }

    public String getTelegramIdByCity(String city) {
        return userRequests.get(city);
    }

    public void clearRequestByCity(String city) {
        userRequests.remove(city);
    }
}

