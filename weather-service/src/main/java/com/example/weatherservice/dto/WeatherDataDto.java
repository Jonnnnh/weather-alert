package com.example.weatherservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDataDto {
    private String city;
    private Double temperature;
    private Integer cloudiness;
    private Integer humidity;
    private String condition;
    private Boolean isDay;
    private LocalDateTime recordedAt;
}
