package com.example.bot.dto;

import com.example.bot.enums.Frequency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("telegram_id")
    private String telegramId;

    @JsonProperty("city")
    private String city;

    @JsonProperty("frequency")
    private Frequency frequency;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
