package com.scopely.redis.challenge.exceptions;

/**
 * Created by russellb337 on 7/2/15.
 */
public class NotAValidFloatException extends RedisException {

    public NotAValidFloatException() {
        super("ERR value is not a valid float");
    }
}
