package com.example.models.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SerializationUtilsTest {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class User {
        private String firstName;
        private String lastName;
    }

    @Test
    @DisplayName("Сериализация объекта User в JSON")
    public void testSerializeToJson() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .build();

        try {
            String json = SerializationUtils.serializeToJson(user);
            assertNotNull(json, "JSON строка не должна быть null");
            assertTrue(json.contains("\"firstName\":\"John\""), "JSON должен содержать поле firstName");
            assertTrue(json.contains("\"lastName\":\"Doe\""), "JSON должен содержать поле lastName");
        } catch (JsonProcessingException e) {
            fail("Сериализация вызвала исключение: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Десериализация JSON в объект User")
    public void testDeserializeFromJson() {
        String json = "{\"firstName\":\"Jane\",\"lastName\":\"Smith\"}";

        try {
            User user = SerializationUtils.deserializeFromJson(json, User.class);
            assertNotNull(user, "Объект User не должен быть null");
            assertEquals("Jane", user.getFirstName(), "firstName должно быть 'Jane'");
            assertEquals("Smith", user.getLastName(), "lastName должно быть 'Smith'");
        } catch (JsonProcessingException e) {
            fail("Десериализация вызвала исключение: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Сериализация null объекта должна вернуть null")
    public void testSerializeNullObject() {
        try {
            String json = SerializationUtils.serializeToJson(null);
            assertNull(json, "Сериализация null должна вернуть null");
        } catch (JsonProcessingException e) {
            fail("Сериализация вызвала исключение: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Десериализация некорректного JSON должна вызвать JsonProcessingException")
    public void testDeserializeInvalidJson() {
        String invalidJson = "{invalid json}";

        assertThrows(JsonProcessingException.class, () -> {
            SerializationUtils.deserializeFromJson(invalidJson, User.class);
        }, "Десериализация некорректного JSON должна вызвать JsonProcessingException");
    }
}