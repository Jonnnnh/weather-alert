package com.example.bot.service;

import com.example.bot.dto.WeatherDataDto;
import com.example.bot.util.format.WeatherMessageFormatter;
import com.example.bot.service.storage.UserRequestStorage;
import com.example.bot.telegram.sender.MessageSender;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherResponseReceiver {

    private final WeatherMessageFormatter weatherMessageFormatter;
    private final MessageSender messageSender;
    private final UserRequestStorage userRequestStorage;

    @RabbitListener(queues = "weatherRequestQueue")
    public void receiveWeatherData(WeatherDataDto weatherDataDto) {
        try {
            String formattedMessage = weatherMessageFormatter.formatWeatherMessage(weatherDataDto);
            String telegramId = userRequestStorage.getTelegramIdByCity(weatherDataDto.getCity());
            if (telegramId != null) {
                SendMessage sendMessage = new SendMessage(telegramId, formattedMessage);
                messageSender.sendMessage(sendMessage);
                userRequestStorage.clearRequestByCity(weatherDataDto.getCity());
            } else {
                System.out.println("Не найден telegramId для города: " + weatherDataDto.getCity());
            }
        } catch (Exception e) {
            System.out.println("Ошибка при обработке данных о погоде: " + e.getMessage());
        }
    }
}


