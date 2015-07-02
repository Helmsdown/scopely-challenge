package com.scopely.redis.challenge.models;

/**
 * Created by russellb337 on 7/2/15.
 */
public class SimpleMapValue extends RedisObject {
    private String value;
    private final boolean hasExpiration;
    private final long expirationTimestamp;

    public SimpleMapValue(String value) {
        this(value, -1);
    }

    public SimpleMapValue(String value, int expirationSecs) {
        this.value = value;
        if(expirationSecs != -1) {
            final long currentTime = System.currentTimeMillis();
            this.expirationTimestamp = currentTime + (1000 * expirationSecs);
            this.hasExpiration = true;
        } else {
            this.hasExpiration = false;
            expirationTimestamp = 0;
        }
    }

    public String getValue() {
        return value;
    }


    public boolean isExpired() {
        boolean result = false;
        if(hasExpiration && System.currentTimeMillis() >= expirationTimestamp) {
            result = true;
        }

        return result;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
