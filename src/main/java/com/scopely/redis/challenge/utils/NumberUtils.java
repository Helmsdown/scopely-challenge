package com.scopely.redis.challenge.utils;

import com.scopely.redis.challenge.exceptions.NotAnIntegerOrOutOfRangeException;

/**
 * Created by russellb337 on 7/2/15.
 */
public class NumberUtils {

    public static long toLong(String value) {
        long result;

        try {
            result = Long.parseLong(value);
        } catch (NumberFormatException ex) {
            throw new NotAnIntegerOrOutOfRangeException();
        }

        return result;
    }

    public static int toInt(String value) {
        int result;

        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            throw new NotAnIntegerOrOutOfRangeException();
        }

        return result;
    }
}
