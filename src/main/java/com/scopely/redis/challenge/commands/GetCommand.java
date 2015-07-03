package com.scopely.redis.challenge.commands;

import com.scopely.redis.challenge.models.MasterDictionary;

import java.util.Iterator;
import java.util.List;

/**
 * Created by russellb337 on 7/2/15.
 */
public class GetCommand extends AbstractCommand {
    @Override
    public String execute(List<String> split, MasterDictionary masterDictionary) {
        final Iterator<String> commandIterator = super.getIteratorAndConsumeFirst(split);

        final String key = expectAndGetAnotherArg(commandIterator);

        expectNoMoreArguments(commandIterator);

        String result = nil();

        final String value = masterDictionary.get(key);

        if(value != null) {
            result = value;
        }

        return result;
    }

}
