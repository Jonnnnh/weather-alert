package com.example.models.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationUtilsTest {

    @Test
    public void testIsValidTelegramId() {
        assertTrue(ValidationUtils.isValidTelegramId("123456789"));
        assertFalse(ValidationUtils.isValidTelegramId(null));
        assertFalse(ValidationUtils.isValidTelegramId(""));
        assertFalse(ValidationUtils.isValidTelegramId("a".repeat(51)));
    }

    @Test
    public void testIsValidFrequency() {
        assertTrue(ValidationUtils.isValidFrequency("DAILY"));
        assertTrue(ValidationUtils.isValidFrequency("HOURLY"));
        assertTrue(ValidationUtils.isValidFrequency("WEEKLY"));

        assertTrue(ValidationUtils.isValidFrequency("daily"));
        assertTrue(ValidationUtils.isValidFrequency("Hourly"));
        assertTrue(ValidationUtils.isValidFrequency("WeEkLy"));

        assertFalse(ValidationUtils.isValidFrequency("MONTHLY"));
        assertFalse(ValidationUtils.isValidFrequency(null));
        assertFalse(ValidationUtils.isValidFrequency(""));
        assertFalse(ValidationUtils.isValidFrequency("random"));
    }
}
