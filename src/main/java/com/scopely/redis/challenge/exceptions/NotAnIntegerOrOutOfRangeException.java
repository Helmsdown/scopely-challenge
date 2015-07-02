package com.scopely.redis.challenge.exceptions;

/**
 * Created by russellb337 on 7/2/15.
 */
public class NotAnIntegerOrOutOfRangeException extends RedisException {

    public NotAnIntegerOrOutOfRangeException() {
        super("ERR value is not an integer or out of range");
    }
}
