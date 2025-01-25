package com.example.bot.telegram.reply;

import lombok.Getter;

@Getter
public enum ReplyMessages {

    UNKNOWN_COMMAND("Unknown command. Use /help for available commands"),
    WELCOME_MESSAGE("Welcome! You have successfully registered. Please set your city using /setcity <city>"),
    HELP_MESSAGE("Here are the available commands:\n/start - Register\n/help - Show help\n/setcity <city> - Set your city"),
    CITY_UPDATED("Your city has been updated successfully"),
    ERROR_OCCURRED("An error occurred. Please try again later"),
    USER_ALREADY_REGISTERED("You are already registered. Please enter your city"),
    SET_CITY_PROMPT("Please enter your city using /setcity <city>"),
    INVALID_COMMAND_FORMAT("Invalid command format. Please use the correct format"),
    CHAT_ID_NOT_FOUND("Chat ID not found"),
    USER_NOT_FOUND("User not found");

    private final String message;

    ReplyMessages(String message) {
        this.message = message;
    }
}

