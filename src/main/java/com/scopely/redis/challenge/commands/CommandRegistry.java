package com.scopely.redis.challenge.commands;

import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by russellb337 on 7/2/15.
 */
public class CommandRegistry {

    private final Map<CommandType, Command> registry = new HashMap<>();

    public CommandRegistry() {
        registry.put(CommandType.SET, new SetCommand());
        registry.put(CommandType.GET, new GetCommand());
        registry.put(CommandType.DEL, new DelCommand());
        registry.put(CommandType.DBSIZE, new DbsizeCommand());
    }

    public Command getCommand(CommandType commandType) {
        final Command command = registry.get(commandType);

        if(command == null) {
            throw new NotImplementedException("No command registered for " + commandType);
        }

        return command;
    }




}
