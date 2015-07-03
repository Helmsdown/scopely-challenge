package com.scopely.redis.challenge.exceptions;

/**
 *
 */
public class WrongNumberOfArgumentsException extends RedisException {
    public WrongNumberOfArgumentsException() {
        super("wrong number of arguments");
    }
}
