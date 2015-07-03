package com.scopely.redis.challenge.commands.set;

import com.scopely.redis.challenge.commands.AbstractCommand;
import com.scopely.redis.challenge.commands.Command;
import com.scopely.redis.challenge.models.MasterDictionary;
import com.scopely.redis.challenge.utils.NumberUtils;

import java.util.Iterator;
import java.util.List;

/**
 * Created by russellb337 on 7/2/15.
 */
public class SetCommand extends AbstractCommand implements Command {
    @Override
    public String execute(List<String> commandValues, MasterDictionary masterDictionary) {

        final Iterator<String> commandValuesIter = getIteratorAndConsumeFirst(commandValues);

        final String key = expectAndGetAnotherArg(commandValuesIter);
        final String value = expectAndGetAnotherArg(commandValuesIter);

        final String result = ok();

        if (commandValuesIter.hasNext()) {
            //this only thing we are going to allow after this is "ex number", anything else will be rejected

            getNextArgAndExpectValue(commandValuesIter, "ex");

            final String expSecsStr = expectAndGetAnotherArg(commandValuesIter);
            final int expSecs = NumberUtils.toInt(expSecsStr);
            masterDictionary.setWithExpiration(key, value, expSecs);
        } else {
            masterDictionary.set(key, value);
        }

        return result;
    }
}
