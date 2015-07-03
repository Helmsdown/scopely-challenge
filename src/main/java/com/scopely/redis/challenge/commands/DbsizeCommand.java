package com.scopely.redis.challenge.commands;

import com.scopely.redis.challenge.models.MasterDictionary;

import java.util.Iterator;
import java.util.List;

/**
 * Created by russellb337 on 7/2/15.
 */
public class DbsizeCommand extends AbstractCommand {
    @Override
    public String execute(List<String> split, MasterDictionary masterDictionary) {
        final Iterator<String> commandIterator = super.getIteratorAndConsumeFirst(split);

        expectNoMoreArguments(commandIterator);

        final int size = masterDictionary.size();

        return integer(size);
    }
}
