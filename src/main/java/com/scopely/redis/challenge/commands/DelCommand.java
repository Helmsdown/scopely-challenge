package com.scopely.redis.challenge.commands;

import com.scopely.redis.challenge.commands.AbstractCommand;
import com.scopely.redis.challenge.models.MasterDictionary;

import java.util.Iterator;
import java.util.List;

/**
 * Created by russellb337 on 7/2/15.
 */
public class DelCommand extends AbstractCommand {
    @Override
    public String execute(List<String> split, MasterDictionary masterDictionary) {
        final Iterator<String> commandIterator = super.getIteratorAndConsumeFirst(split);

        final String key = expectAndGetAnotherArg(commandIterator);

        expectNoMoreArguments(commandIterator);

        final int count = masterDictionary.delete(key);

        return integer(count);
    }
}
