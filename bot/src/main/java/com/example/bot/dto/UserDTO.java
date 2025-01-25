package com.example.bot.dto;

import com.example.bot.enums.Frequency;
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
    private UUID id;
    private String telegramId;
    private String city;
    private Frequency frequency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
