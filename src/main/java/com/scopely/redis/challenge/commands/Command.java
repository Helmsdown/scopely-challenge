package com.scopely.redis.challenge.commands;

import com.scopely.redis.challenge.models.MasterDictionary;

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
    String execute(String[] split, MasterDictionary masterDictionary);

}
