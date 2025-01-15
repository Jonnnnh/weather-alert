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
}
