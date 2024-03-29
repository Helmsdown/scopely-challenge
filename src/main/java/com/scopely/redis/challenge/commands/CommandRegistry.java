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
        registry.put(CommandType.INCR, new IncrCommand());
        registry.put(CommandType.ZADD, new ZaddCommand());
        registry.put(CommandType.ZCARD, new ZcardCommand());
        registry.put(CommandType.ZRANK, new ZrankCommand());
    }

    public Command getCommand(CommandType commandType) {
        final Command command = registry.get(commandType);

        if(command == null) {
            throw new NotImplementedException("No command registered for " + commandType);
        }

        return command;
    }




}
