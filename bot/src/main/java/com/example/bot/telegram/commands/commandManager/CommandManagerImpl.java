package com.example.bot.telegram.commands.commandManager;

import com.example.bot.telegram.commands.Command;
import com.example.bot.telegram.reply.ReplyMessages;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class CommandManagerImpl implements CommandManager {

    private final Map<String, Command> commandMap;

    @Autowired
    public CommandManagerImpl(Command startCommand, Command helpCommand, Command setCityCommand) {
        this.commandMap = new HashMap<>();
        this.commandMap.put("start", startCommand);
        this.commandMap.put("help", helpCommand);
        this.commandMap.put("setcity", setCityCommand);
    }

    @Override
    public SendMessage handleCommand(Update update) {
        String messageText = update.message().text().toLowerCase();
        String commandName = messageText.split(" ")[0].substring(1);

        Command command = commandMap.get(commandName);
        if (command != null) {
            return command.handle(update);
        } else {
            return new SendMessage(update.message().chat().id().toString(), ReplyMessages.UNKNOWN_COMMAND.getMessage());
        }
    }
}
