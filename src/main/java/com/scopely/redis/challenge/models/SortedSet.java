package com.scopely.redis.challenge.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by russellb337 on 7/2/15.
 */
public class SortedSet extends RedisObject implements Iterable<SortedSetMember> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SortedSet.class);

    private final TreeSet<SortedSetMember> set = new TreeSet<>((o1, o2) -> {

        int scoreComparison = (int) (o1.getComparatorScore() - o2.getComparatorScore());

        if(o1.getValue().equals(o2.getValue())) {
            return 0;
        } else if (scoreComparison == 0) { //
            return o1.getValue().compareTo(o2.getValue());
        }

        return scoreComparison;
    });

    public void add(SortedSetMember member) {
        final boolean successfullyAdded = set.add(member);

        if(!successfullyAdded) {
            set.remove(member);
            set.add(member);
        }

        LOGGER.info("op=add; newElement={}; newSize={}; successfullyAdded={}; contents={};", member.getValue(), set.size(), successfullyAdded, toString());
    }

    public Integer getRank(String memberValue) {
        int rank = 0;
        Double lastScoreSeen = null;

        final Iterator<SortedSetMember> iterator = set.iterator();

        if(iterator.hasNext()) {
            final SortedSetMember next = iterator.next();
            lastScoreSeen = next.getComparatorScore();

            if(memberValue.equals(next.getValue())) {
                return rank;
            }
        }


        while (iterator.hasNext()) {
            SortedSetMember member = iterator.next();

            if (member.getComparatorScore() > lastScoreSeen) {
                rank++;
            }

            if (member.getValue().equals(memberValue)) {
                return rank;
            }

            lastScoreSeen = member.getComparatorScore();
        }

        return null;
    }

    public int size() {
        return set.size();
    }

    @Override
    public String toString() {
        return Arrays.toString(set.toArray(new SortedSetMember[0]));
    }

    @Override
    public Iterator<SortedSetMember> iterator() {
        return set.iterator();
    }

    public SortedSetMember[] toArray() {
        return set.toArray(new SortedSetMember[0]);
    }
}
