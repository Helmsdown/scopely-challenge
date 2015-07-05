package com.scopely.redis.challenge.models;

import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by russellb337 on 7/5/15.
 */
@RunWith(JMockit.class)
public class SortedSetTests {

    @Test
    public void testAddThreeValues_DistinctValues_DistinctScores() throws Exception {
        SortedSet set = new SortedSet();
        set.add(new SortedSetMember("hello", "10"));
        set.add(new SortedSetMember("world", "20"));
        set.add(new SortedSetMember("foo", "30"));

        assertEquals(3, set.size());

        final SortedSetMember[] sortedSetMembers = set.toArray();

        assertEquals("hello", sortedSetMembers[0].getValue());
        assertEquals("world", sortedSetMembers[1].getValue());
        assertEquals("foo", sortedSetMembers[2].getValue());
    }

    @Test
    public void testAddThreeValues_DistinctValues_SameScores() throws Exception {
        SortedSet set = new SortedSet();
        set.add(new SortedSetMember("hello", "10"));
        set.add(new SortedSetMember("world", "10"));
        set.add(new SortedSetMember("foo", "10"));

        assertEquals(3, set.size());

        final SortedSetMember[] sortedSetMembers = set.toArray();

        assertEquals("foo", sortedSetMembers[0].getValue());
        assertEquals("hello", sortedSetMembers[1].getValue());
        assertEquals("world", sortedSetMembers[2].getValue());
    }

    @Test
    public void testAddThreeValues_SameValues_SameScores() throws Exception {
        SortedSet set = new SortedSet();
        set.add(new SortedSetMember("hello", "10"));
        set.add(new SortedSetMember("hello", "10"));
        set.add(new SortedSetMember("hello", "10"));

        assertEquals(1, set.size());

        final SortedSetMember[] sortedSetMembers = set.toArray();

        assertEquals("hello", sortedSetMembers[0].getValue());
    }

    @Test
    public void testAddThreeValues_SameValues_DistinctScores() throws Exception {
        SortedSet set = new SortedSet();
        set.add(new SortedSetMember("hello", "12"));
        set.add(new SortedSetMember("hello", "10"));
        set.add(new SortedSetMember("hello", "11"));

        assertEquals(1, set.size());

        final SortedSetMember[] sortedSetMembers = set.toArray();

        assertEquals("hello", sortedSetMembers[0].getValue());
        assertEquals("11", sortedSetMembers[0].getScore());
    }

    @Test
    public void testRank_DistinctValues_DistinctScores() throws Exception {
        SortedSet set = new SortedSet();
        set.add(new SortedSetMember("hello", "10"));
        set.add(new SortedSetMember("world", "20"));
        set.add(new SortedSetMember("foo", "30"));


        assertEquals(new Integer(0), set.getRank("hello"));
        assertEquals(new Integer(1), set.getRank("world"));
        assertEquals(new Integer(2), set.getRank("foo"));
    }

    @Test
    public void testRank_DistinctValues_SameScores() throws Exception {
        SortedSet set = new SortedSet();
        set.add(new SortedSetMember("hello", "10"));
        set.add(new SortedSetMember("world", "10"));
        set.add(new SortedSetMember("foo", "10"));


        assertEquals(new Integer(0), set.getRank("hello"));
        assertEquals(new Integer(0), set.getRank("world"));
        assertEquals(new Integer(0), set.getRank("foo"));
    }

    @Test
    public void testRank_SameValues_SameScores() throws Exception {
        SortedSet set = new SortedSet();
        set.add(new SortedSetMember("hello", "10"));
        set.add(new SortedSetMember("hello", "10"));
        set.add(new SortedSetMember("hello", "10"));


        assertEquals(new Integer(0), set.getRank("hello"));
    }

    @Test
    public void testRank_SameValues_DistinctScores() throws Exception {
        SortedSet set = new SortedSet();
        set.add(new SortedSetMember("hello", "11"));
        set.add(new SortedSetMember("hello", "10"));
        set.add(new SortedSetMember("hello", "12"));


        assertEquals(new Integer(0), set.getRank("hello"));
    }

    @Test
    public void testRank_MemberNotInSet() throws Exception {
        SortedSet set = new SortedSet();
        final Integer rank = set.getRank("foo");
        assertNull(rank);

    }
}
