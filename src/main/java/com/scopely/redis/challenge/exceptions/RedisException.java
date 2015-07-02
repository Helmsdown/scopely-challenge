package com.scopely.redis.challenge.exceptions;

/**
 * Created by russellb337 on 7/2/15.
 */
public class RedisException extends RuntimeException{

    private final String wholeMessage;

    public RedisException(String message) {
        wholeMessage = "(error) " + message;
    }

    public String getWholeMessage() {
        return wholeMessage;
    }
}
