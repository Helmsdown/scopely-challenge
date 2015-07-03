package com.scopely.redis.challenge.commands;

import com.scopely.redis.challenge.exceptions.SyntaxErrorException;
import com.scopely.redis.challenge.exceptions.WrongNumberOfArgumentsException;

import java.util.Iterator;
import java.util.List;

/**
 * Created by russellb337 on 7/2/15.
 */
public abstract class AbstractCommand {
    public static final String OK = "OK";

    public String ok() {
        return OK;
    }

    protected static String expectAndGetAnotherArg(Iterator<String> commandValuesIter) {
        if(!commandValuesIter.hasNext()) {
            throw new WrongNumberOfArgumentsException();
        }

        return commandValuesIter.next();
    }

    protected static String getNextArgAndExpectValue(Iterator<String> commandValuesIter, String expectedValue) {
        final String next = commandValuesIter.next();

        if(!next.equalsIgnoreCase(expectedValue)) {
            throw new SyntaxErrorException();
        }


        return next;
    }

    protected Iterator<String> getIteratorAndConsumeFirst(List<String> commandValues) {
        final Iterator<String> commandValuesIter = commandValues.iterator();
        commandValuesIter.next(); //consume the first value, e.g. SET
        return commandValuesIter;
    }
}
