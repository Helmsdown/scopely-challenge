package com.scopely.redis.challenge.commands;

import com.scopely.redis.challenge.models.MasterDictionary;

import java.util.List;

/**
 * Created by russellb337 on 7/2/15.
 */
public interface Command {
    /**
     *
     * @param split The whole command string split along space (and/or) quoted value boundaries
     * @param masterDictionary the master dictionary
     * @return The successful result
     */
    String execute(List<String> split, MasterDictionary masterDictionary);

}
