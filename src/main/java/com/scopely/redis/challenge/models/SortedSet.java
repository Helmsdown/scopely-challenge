package com.scopely.redis.challenge.models;

import java.util.HashMap;
import java.util.Map;
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

    public int getRank(String memberValue) {
        int rank = 0;
        double lastScoreSeen = Double.NEGATIVE_INFINITY;

        for (SortedSetMember member : set) {

            if(member.getValue().equals(memberValue)) {
                break;
            }
            if(member.getComparatorScore() > lastScoreSeen) {
                rank++;

            }
            lastScoreSeen = member.getComparatorScore();

        }

        return rank;
    }

    public int size() {
        return set.size();
    }

    public static void main(String[] args) {
        SortedSet set = new SortedSet();
        set.add(new SortedSetMember("hello", "10"));
        set.add(new SortedSetMember("hell01", "10"));
        set.add(new SortedSetMember("world", "20"));
        set.add(new SortedSetMember("foo", "30"));
        set.add(new SortedSetMember("bar", "5"));

        System.out.println(set.getRank("hello"));
    }
}
