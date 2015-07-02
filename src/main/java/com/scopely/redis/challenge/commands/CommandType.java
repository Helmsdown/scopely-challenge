package com.scopely.redis.challenge.commands;

import com.scopely.redis.challenge.exceptions.UnrecognizedCommandException;

/**
 * Created by russellb337 on 7/2/15.
 *
 * new enum values __NEED___ to be uppercase
 */
public enum CommandType {
    SET,
    GET,
    DEL,
    DBSIZE,
    INCR,
    ZADD,
    ZCARD,
    ZRANK,
    ZRANGE;

    public static CommandType toCommandType(String type) {
        final String upperCase = type.toUpperCase();

        try {
            final CommandType commandType = CommandType.valueOf(upperCase);
            return commandType;
        } catch (IllegalArgumentException ex) {
            throw new UnrecognizedCommandException();
        }
    }
}
