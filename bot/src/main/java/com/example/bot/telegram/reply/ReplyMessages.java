package com.example.bot.telegram.reply;

import lombok.Getter;

@Getter
public enum ReplyMessages {

    UNKNOWN_COMMAND("Unknown command. Use /help for available commands");

    private final String message;

    ReplyMessages(String message) {
        this.message = message;
    }
}

