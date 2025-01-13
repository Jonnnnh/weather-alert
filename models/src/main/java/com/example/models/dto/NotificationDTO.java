package com.example.models.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {
    private UUID id;
    private UUID userId;
    private String content;
    private LocalDateTime createdAt;
}
