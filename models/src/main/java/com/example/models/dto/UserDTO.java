package com.example.models.dto;

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
