package com.example.models.utils;

import com.example.models.enums.Frequency;
import org.apache.commons.lang3.StringUtils;

public class ValidationUtils {

    public static boolean isValidTelegramId(String telegramId) {
        return StringUtils.isNotBlank(telegramId) && telegramId.length() <= 50;
    }

    public static boolean isValidFrequency(String frequency) {
        if (StringUtils.isBlank(frequency)) {
            return false;
        }
        try {
            Frequency.valueOf(frequency.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
