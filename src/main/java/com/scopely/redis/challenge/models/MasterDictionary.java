package com.scopely.redis.challenge.models;

import com.scopely.redis.challenge.exceptions.NotAnIntegerOrOutOfRangeException;
import com.scopely.redis.challenge.exceptions.WrongTimeException;
import com.scopely.redis.challenge.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by russellb337 on 7/2/15.
 */
public class MasterDictionary {
    private final Map<String, RedisObject> map = new ConcurrentHashMap<>(100);

    public synchronized void set(String key, String value) {
        map.put(key, new SimpleMapValue(value));
    }

    public synchronized void setWithExpiration(String key, String value, int expiration) {
        map.put(key, new SimpleMapValue(value, expiration));
    }

    public synchronized String get(String key) {
        String result = null;

        final SimpleMapValue value = getSimpleMapValue(key);

        if(value != null) {
            if(!value.isExpired()) {
                result = value.getValue();
            } else {
                map.remove(key);
            }
        }

        return result;
    }

    public synchronized int delete(String key) {
        int result = 0;

        final RedisObject removedValue = map.remove(key);

        if(removedValue != null) {
            result = 1;
        }

        return result;
    }

    public synchronized long increment(String key) {

        long result;

        SimpleMapValue redisMapValue = getSimpleMapValue(key);

        if(redisMapValue == null || (redisMapValue != null && redisMapValue.isExpired())) {
            //not currently in the map
            map.put(key, new SimpleMapValue("1"));
            result = 1;
        } else {
            //it is in the map, but it might not be a number
            final String value = redisMapValue.getValue();

            long number = NumberUtils.toLong(value);

            if(number < Long.MAX_VALUE) {
                number++;
                result = number;
                redisMapValue.setValue("" + number);
            } else {
                //incrementing would overflow a long (64-bit int)
                throw new NotAnIntegerOrOutOfRangeException();
            }
        }

        return result;
    }

    public synchronized int mapSize() {
        return map.size();
    }



    private SimpleMapValue getSimpleMapValue(String key) {
        final RedisObject redisObject = map.get(key);

        if(redisObject == null || redisObject instanceof SimpleMapValue) {
            return (SimpleMapValue) redisObject;

        }

        throw new WrongTimeException();

    }

}
