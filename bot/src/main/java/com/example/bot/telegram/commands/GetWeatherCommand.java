package com.example.bot.telegram.commands;

import com.example.bot.clients.UserServiceClient;
import com.example.bot.clients.WeatherServiceClient;
import com.example.bot.dto.UserDTO;
import com.example.bot.dto.WeatherDataDto;
import com.example.bot.telegram.reply.ReplyMessages;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetWeatherCommand implements Command {

    private final UserServiceClient userServiceClient;
    private final WeatherServiceClient weatherServiceClient;

    @Override
    public SendMessage handle(Update update) {
        String chatId = String.valueOf(update.message().chat().id());
        Optional<UserDTO> userOpt = userServiceClient.getUserByTelegramId(chatId);

        if (userOpt.isPresent()) {
            UserDTO user = userOpt.get();
            String city = user.getCity();

            if (city != null && !city.isEmpty()) {
                WeatherDataDto weatherData = weatherServiceClient.fetchCurrentWeather(city);

                if (weatherData != null) {
                    return new SendMessage(chatId, "Погода в городе " + city + ":\n" +
                            "Температура: " + weatherData.getTemperature() + "°C\n" +
                            "Облачность: " + weatherData.getCloudiness() + "%\n" +
                            "Влажность: " + weatherData.getHumidity() + "%\n" +
                            " " + weatherData.getCondition());
                } else {
                    return new SendMessage(chatId, ReplyMessages.WEATHER_DATA_ERROR.getMessage());
                }
            } else {
                return new SendMessage(chatId, ReplyMessages.CITY_NOT_SET.getMessage());
            }
        } else {
            return new SendMessage(chatId, ReplyMessages.USER_NOT_FOUND.getMessage());
        }
    }
}
