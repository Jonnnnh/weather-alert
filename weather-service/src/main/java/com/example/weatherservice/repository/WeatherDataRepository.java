package com.example.weatherservice.repository;

import com.example.weatherservice.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WeatherDataRepository extends JpaRepository<WeatherData, UUID> {
}
