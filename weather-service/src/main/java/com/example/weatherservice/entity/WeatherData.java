package com.example.weatherservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weather_data")
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String city;

    @Column
    private Double temperature;

    @Column
    private Integer cloudiness;

    @Column
    private Integer humidity;

    @Column
    private String condition;

    @Column
    private Boolean isDay;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;
}
