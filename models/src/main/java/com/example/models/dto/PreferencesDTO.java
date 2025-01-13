package com.example.models.dto;

import com.example.models.enums.Frequency;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreferencesDTO {
    private UUID id;
    private UUID userId;
    private String city;
    private Frequency frequency;
    private String alerts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
