package com.scopely.redis.challenge.models;

import com.scopely.redis.challenge.utils.NumberUtils;

/**
 * Created by russellb337 on 7/2/15.
 */
public class SortedSetMember {

    private final String value;
    private final String score;
    private final double comparatorScore; //value to use in Java comparator

    public SortedSetMember(String value, String score) {
        this.value = value;
        this.score = score;

        if(score.equals("+inf")) {
            this.comparatorScore = Double.POSITIVE_INFINITY;
        } else if (score.equals("-inf")) {
            this.comparatorScore = Double.NEGATIVE_INFINITY;
        } else {
            this.comparatorScore = NumberUtils.toDouble(score);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SortedSetMember that = (SortedSetMember) o;

        return value.equals(that.value);

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public String getValue() {
        return value;
    }

    public String getScore() {
        return score;
    }

    public double getComparatorScore() {
        return comparatorScore;
    }
}
