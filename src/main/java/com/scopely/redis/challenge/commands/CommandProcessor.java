package com.scopely.redis.challenge.commands;

import com.scopely.redis.challenge.models.MasterDictionary;

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
        final String[] split = commandStr.split(" ");

        final String baseCommand = split[0];
        final CommandType commandType = CommandType.toCommandType(baseCommand);

        final Command command = this.commandRegistry.getCommand(commandType);
        final String result = command.execute(split, masterDictionary);

        return result;
    }



    public static void main(String[] args) {
        System.out.println(CommandType.valueOf("foo"));
    }

}
