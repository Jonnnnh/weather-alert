package com.example.weatherservice.mapper;

import com.example.weatherservice.dto.WeatherDataDto;
import com.example.weatherservice.entity.WeatherData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherDataMapper {

    WeatherDataDto toDTO(WeatherData weatherData);
    WeatherData toEntity(WeatherDataDto weatherDataDto);
}