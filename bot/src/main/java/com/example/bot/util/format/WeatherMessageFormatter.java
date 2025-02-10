package com.example.bot.util.format;

import com.example.bot.dto.WeatherDataDto;
import org.springframework.stereotype.Service;

@Service
public class WeatherMessageFormatter {

    public String formatWeatherMessage(WeatherDataDto weatherData) {
        return "Погода в " + weatherData.getCity() + ":\n" +
                "Температура: " + weatherData.getTemperature() + "°C\n" +
                "Облачность: " + weatherData.getCloudiness() + "%\n" +
                "Влажность: " + weatherData.getHumidity() + "%\n" +
                "Погодные условия: " + weatherData.getCondition();
    }
}
