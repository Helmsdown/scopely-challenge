package com.scopely.redis.challenge.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Called "MyStringUtils" because this project also uses Apache Commons Lang's StringUtils class elsewhere
 */
public class MyStringUtils {
    private static final Pattern SPLIT_PATTERN = Pattern.compile("([^\"]\\S*|\".+?\")\\s*");

    public static List<String> smartSplit(String str) {
        List<String> result = new ArrayList<>();
        Matcher m = SPLIT_PATTERN.matcher(str);
        while (m.find()) {
            result.add(m.group(1));
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(smartSplit("zadd 1 \"hello world\""));
    }
}
