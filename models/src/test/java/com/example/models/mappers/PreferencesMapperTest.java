package com.example.models.mappers;

import com.example.models.dto.PreferencesDTO;
import com.example.models.entities.Preferences;
import com.example.models.entities.User;
import com.example.models.enums.Frequency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class PreferencesMapperTest {

    @Autowired
    private PreferencesMapper preferencesMapper;

    @Test
    public void testToDTO() {
        UUID preferenceId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        User user = User.builder()
                .id(userId)
                .telegramId("123456789")
                .createdAt(now)
                .updatedAt(now)
                .build();

        Preferences preferences = Preferences.builder()
                .id(preferenceId)
                .user(user)
                .city("Moscow")
                .frequency(Frequency.DAILY)
                .alerts("{\"STORM\": true}")
                .createdAt(now)
                .updatedAt(now)
                .build();

        PreferencesDTO preferencesDTO = preferencesMapper.toDTO(preferences);

        assertEquals(preferenceId, preferencesDTO.getId(), "ID должно совпадать");
        assertEquals(userId, preferencesDTO.getUserId(), "User ID должно совпадать");
        assertEquals("Moscow", preferencesDTO.getCity(), "Город должен совпадать");
        assertEquals(Frequency.DAILY, preferencesDTO.getFrequency(), "Частота должна совпадать");
        assertEquals("{\"STORM\": true}", preferencesDTO.getAlerts(), "Оповещения должны совпадать");
        assertEquals(now, preferencesDTO.getCreatedAt(), "Дата создания должна совпадать");
        assertEquals(now, preferencesDTO.getUpdatedAt(), "Дата обновления должна совпадать");
    }

    @Test
    public void testToEntity() {
        UUID preferenceId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        PreferencesDTO preferencesDTO = PreferencesDTO.builder()
                .id(preferenceId)
                .userId(userId)
                .city("Moscow")
                .frequency(Frequency.DAILY)
                .alerts("{\"STORM\": true}")
                .createdAt(now)
                .updatedAt(now)
                .build();

        Preferences preferences = preferencesMapper.toEntity(preferencesDTO);

        User user = User.builder()
                .id(userId)
                .telegramId("123456789")
                .createdAt(now)
                .updatedAt(now)
                .build();
        preferences.setUser(user);

        assertEquals(preferenceId, preferences.getId(), "ID должно совпадать");
        assertNotNull(preferences.getUser(), "User не должен быть null");
        assertEquals(userId, preferences.getUser().getId(), "User ID должно совпадать");
        assertEquals("Moscow", preferences.getCity(), "Город должен совпадать");
        assertEquals(Frequency.DAILY, preferences.getFrequency(), "Частота должна совпадать");
        assertEquals("{\"STORM\": true}", preferences.getAlerts(), "Оповещения должны совпадать");
        assertEquals(now, preferences.getCreatedAt(), "Дата создания должна совпадать");
        assertEquals(now, preferences.getUpdatedAt(), "Дата обновления должна совпадать");
    }
}
