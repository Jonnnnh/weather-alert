package com.example.bot.telegram.commands;

import com.example.bot.clients.UserServiceClient;
import com.example.bot.dto.UserDTO;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SetCityCommand implements Command {

    private final UserServiceClient userServiceClient;

    @Override
    public SendMessage handle(Update update) {
        String chatId = update.message().chat().id().toString();
        String text = update.message().text().toLowerCase();

        String city = parseCityFromCommand(text);
        if (city == null) {
            return new SendMessage(chatId, "Пожалуйста, укажите город: /setcity <город>");
        }

        var existingUser = userServiceClient.getUserByTelegramId(chatId);
        if (existingUser.isPresent()) {
            UserDTO userDTO = existingUser.get();
            userDTO.setCity(city);
            userServiceClient.createOrUpdateUser(userDTO);
            return new SendMessage(chatId, "Ваш город был успешно обновлен на: " + city);
        } else {
            return new SendMessage(chatId, "Пользователь не найден. Пожалуйста, сначала используйте команду /start");
        }
    }

    private String parseCityFromCommand(String text) {
        String[] parts = text.split(" ");
        return parts.length > 1 ? parts[1] : null;
    }
}
