package com.scopely.redis.challenge.commands;

import com.scopely.redis.challenge.models.MasterDictionary;
import com.scopely.redis.challenge.utils.MyStringUtils;

import java.util.List;

/**
 * Created by russellb337 on 7/2/15.
 */
public class CommandProcessor {

    private final MasterDictionary masterDictionary;
    private final CommandRegistry commandRegistry;

    public CommandProcessor(MasterDictionary masterDictionary) {
        this.masterDictionary = masterDictionary;
        this.commandRegistry = new CommandRegistry();
    }

    public String processCommand(String commandStr) {

        if(commandStr == null || commandStr.isEmpty()) {
            return "";
        }

        final List<String> commandElements = MyStringUtils.smartSplit(commandStr);

        final String baseCommand = commandElements.get(0);
        final CommandType commandType = CommandType.toCommandType(baseCommand);

        final Command command = this.commandRegistry.getCommand(commandType);
        final String result = command.execute(commandElements, masterDictionary);

        return result;
    }
}
