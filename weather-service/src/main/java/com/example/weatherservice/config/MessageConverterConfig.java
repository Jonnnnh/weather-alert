package com.example.weatherservice.config;

import com.example.weatherservice.dto.WeatherDataDto;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessageConverterConfig {

    private static final String WEATHER_DATA_DTO = "com.example.bot.dto.WeatherDataDto";

    @Bean(name = "messageConverter")
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
        jsonConverter.setClassMapper(classMapper());
        return jsonConverter;
    }

    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put(WEATHER_DATA_DTO, WeatherDataDto.class);
        classMapper.setIdClassMapping(idClassMapping);
        classMapper.setTrustedPackages("com.example.weatherservice.dto", "com.example.bot.dto");
        return classMapper;
    }
}
