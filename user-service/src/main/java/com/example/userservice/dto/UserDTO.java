package com.example.userservice.dto;

import com.example.userservice.enums.Frequency;
import lombok.*;

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
    private String alerts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
