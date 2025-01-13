package com.example.models.entities;

import com.example.models.enums.Frequency;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "preferences")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Preferences {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(name = "frequency", nullable = false, length = 20)
    private Frequency frequency;

    @Column(name = "alerts", columnDefinition = "jsonb", nullable = false)
    private String alerts; // Хранение в формате JSON

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Добавьте другие поля, если необходимо

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

