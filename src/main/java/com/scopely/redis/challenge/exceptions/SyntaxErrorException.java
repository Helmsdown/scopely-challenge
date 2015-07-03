package com.scopely.redis.challenge.exceptions;

/**
 * Created by russellb337 on 7/2/15.
 */
public class SyntaxErrorException extends RedisException {
    public SyntaxErrorException() {
        super("ERR Syntax error");
    }
}
