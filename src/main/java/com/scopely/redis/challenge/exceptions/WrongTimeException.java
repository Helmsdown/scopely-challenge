package com.scopely.redis.challenge.exceptions;

/**
 * Created by russellb337 on 7/2/15.
 */
public class WrongTimeException extends RedisException {
    public WrongTimeException() {
        super("WRONGTYPE Operation against a key holding the wrong kind of value");
    }
}
