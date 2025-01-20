package com.example.userservice.mappers;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testToDTO() {
        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        User user = User.builder()
                .id(userId)
                .telegramId("123456789")
                .createdAt(now)
                .updatedAt(now)
                .build();

        UserDTO userDTO = userMapper.toDTO(user);

        assertEquals(userId, userDTO.getId());
        assertEquals("123456789", userDTO.getTelegramId());
        assertEquals(now, userDTO.getCreatedAt());
        assertEquals(now, userDTO.getUpdatedAt());
    }

    @Test
    public void testToEntity() {
        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        UserDTO userDTO = UserDTO.builder()
                .id(userId)
                .telegramId("123456789")
                .createdAt(now)
                .updatedAt(now)
                .build();

        User user = userMapper.toEntity(userDTO);

        assertEquals(userId, user.getId());
        assertEquals("123456789", user.getTelegramId());
        assertEquals(now, user.getCreatedAt());
        assertEquals(now, user.getUpdatedAt());
    }
}