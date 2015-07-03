package com.scopely.redis.challenge.utils;

import com.scopely.redis.challenge.exceptions.NotAValidFloatException;

/**
 * Created by russellb337 on 7/2/15.
 */
public class NumberUtils {

    public static long toLong(String value) {
        long result;

        try {
            result = Long.parseLong(value);
        } catch (NumberFormatException ex) {
            throw new NotAValidFloatException();
        }

        return result;
    }

    public static int toInt(String value) {
        int result;

        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            throw new NotAValidFloatException();
        }

        return result;
    }

    public static double toDouble(String value) {
        double result;

        try {
            result = Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            throw new NotAValidFloatException();
        }

        return result;
    }
}
