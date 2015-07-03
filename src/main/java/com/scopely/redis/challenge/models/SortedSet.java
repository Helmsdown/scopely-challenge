package com.scopely.redis.challenge.models;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by russellb337 on 7/2/15.
 */
public class SortedSet extends RedisObject {

    private final Set<SortedSetMember> set = new TreeSet<>((o1, o2) -> (int) (o1.getComparatorScore() - o2.getComparatorScore()));

    public void add(SortedSetMember member) {
        final boolean successfullyAdded = set.add(member);

        if(!successfullyAdded) {
            set.remove(member);
            set.add(member);
        }
    }

    public int size() {
        return set.size();
    }
}
