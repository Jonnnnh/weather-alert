package com.example.bot.configuration;

import com.example.bot.dto.UserDTO;
import com.example.bot.dto.WeatherDataDto;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessageConverterConfig {

    private static final String USER_DTO = "com.example.userservice.dto.UserDTO";
    private static final String WEATHER_DATA_DTO = "com.example.weatherservice.dto.WeatherDataDto";

    @Bean
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
        jsonConverter.setClassMapper(classMapper());
        return jsonConverter;
    }

    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put(USER_DTO, UserDTO.class);
        idClassMapping.put(WEATHER_DATA_DTO, WeatherDataDto.class);
        classMapper.setIdClassMapping(idClassMapping);
        classMapper.setTrustedPackages("com.example.weatherservice.dto", "com.example.bot.dto", "com.example.userservice.dto");
        return classMapper;
    }
}
