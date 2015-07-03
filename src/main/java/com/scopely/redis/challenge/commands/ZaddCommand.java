package com.scopely.redis.challenge.commands;

import com.scopely.redis.challenge.models.MasterDictionary;
import com.scopely.redis.challenge.models.SortedSetMember;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by russellb337 on 7/2/15.
 */
public class ZaddCommand extends AbstractCommand {
    @Override
    public String execute(List<String> split, MasterDictionary masterDictionary) {
        final Iterator<String> commandIterator = super.getIteratorAndConsumeFirst(split);

        final String key = expectAndGetAnotherArg(commandIterator);
        final List<SortedSetMember> membersToAdd = new ArrayList<>(10);

        while(commandIterator.hasNext()) {
            final String score = expectAndGetAnotherArg(commandIterator);
            final String value = expectAndGetAnotherArg(commandIterator);

            SortedSetMember member = new SortedSetMember(value, score);
            membersToAdd.add(member);
        }

        final int count = masterDictionary.zadd(key, membersToAdd.toArray(new SortedSetMember[0]));

        return integer(count);
    }
}
