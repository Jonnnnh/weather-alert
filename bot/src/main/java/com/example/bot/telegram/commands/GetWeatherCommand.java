package com.example.bot.telegram.commands;

import com.example.bot.clients.GrpcUserServiceClient;
import com.example.bot.service.WeatherRequestSender;
import com.example.bot.service.storage.UserRequestStorage;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetWeatherCommand implements Command {

    private final GrpcUserServiceClient grpcUserServiceClient;
    private final WeatherRequestSender weatherRequestSender;
    private final UserRequestStorage userRequestStorage;

    @Override
    public SendMessage handle(Update update) {
        String chatId = String.valueOf(update.message().chat().id());
        var userOpt = grpcUserServiceClient.getUserByTelegramId(chatId);

        if (userOpt.isPresent()) {
            var user = userOpt.get();
            String city = user.getCity();

            if (!city.isEmpty()) {
                userRequestStorage.storeTelegramId(city, chatId);
                weatherRequestSender.sendWeatherRequest(chatId, city);
                return new SendMessage(chatId, "Запрос на получение погоды отправлен, ожидайте...");
            } else {
                return new SendMessage(chatId, "У вас не задан город. Используйте команду /setcity <город>");
            }
        } else {
            return new SendMessage(chatId, "Пользователь не найден. Сначала введите /start, чтобы зарегистрироваться");
        }
    }
}


