package com.example.bot.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommandUtils {

    public static String parseCityFromCommand(String text) {
        String[] parts = text.split(" ");
        return parts.length > 1 ? parts[1] : null;
    }
}
