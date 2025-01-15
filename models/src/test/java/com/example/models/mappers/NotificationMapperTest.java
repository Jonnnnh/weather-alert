package com.example.models.mappers;

import com.example.models.dto.NotificationDTO;
import com.example.models.entities.Notification;
import com.example.models.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationMapperTest {

    @Autowired
    private NotificationMapper notificationMapper;

    @Test
    void testToDTO() {
        Notification notification = new Notification();
        notification.setId(UUID.randomUUID());
        User user = new User();
        user.setId(UUID.randomUUID());
        notification.setUser(user);
        notification.setContent("Test content");
        notification.setCreatedAt(LocalDateTime.now());

        NotificationDTO dto = notificationMapper.toDTO(notification);

        assertNotNull(dto);
        assertEquals(notification.getId(), dto.getId());
        assertEquals(notification.getUser().getId(), dto.getUserId());
        assertEquals(notification.getContent(), dto.getContent());
    }

    @Test
    public void testToEntity() {
        UUID notificationId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        NotificationDTO notificationDTO = NotificationDTO.builder()
                .id(notificationId)
                .userId(userId)
                .content("Test Notification")
                .createdAt(now)
                .build();

        User user = User.builder()
                .id(userId)
                .telegramId("123456789")
                .createdAt(now)
                .updatedAt(now)
                .build();

        Notification notification = notificationMapper.toEntity(notificationDTO);
        notification.setUser(user);

        assertEquals(notificationId, notification.getId());
        assertEquals(user, notification.getUser());
        assertEquals("Test Notification", notification.getContent());
        assertEquals(now, notification.getCreatedAt());
    }
}
