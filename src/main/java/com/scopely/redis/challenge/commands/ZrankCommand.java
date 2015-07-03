package com.scopely.redis.challenge.commands;

import com.scopely.redis.challenge.models.MasterDictionary;

import java.util.Iterator;
import java.util.List;

/**
 * Created by russellb337 on 7/2/15.
 */
public class ZrankCommand extends AbstractCommand {
    @Override
    public String execute(List<String> split, MasterDictionary masterDictionary) {
        final Iterator<String> commandItertator = super.getIteratorAndConsumeFirst(split);

        final String key = expectAndGetAnotherArg(commandItertator);
        final String member = expectAndGetAnotherArg(commandItertator);

        String result = nil();

        final Integer rank = masterDictionary.zrank(key, member);

        if(rank != null) {
            result = integer(rank);
        }

        return result;
    }
}
