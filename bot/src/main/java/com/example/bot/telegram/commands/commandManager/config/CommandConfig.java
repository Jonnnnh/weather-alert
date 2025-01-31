package com.example.bot.telegram.commands.commandManager.config;

import com.example.bot.telegram.commands.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CommandConfig {

    @Bean
    public Map<String, Command> commandMap(HelpCommand helpCommand, StartCommand startCommand, SetCityCommand setCityCommand, GetWeatherCommand getWeatherCommand) {
        Map<String, Command> commands = new HashMap<>();
        commands.put("start", startCommand);
        commands.put("help", helpCommand);
        commands.put("setcity", setCityCommand);
        commands.put("getweather", getWeatherCommand);
        return commands;
    }
}

