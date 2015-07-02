package com.scopely.redis.challenge;

import com.scopely.redis.challenge.commands.CommandProcessor;
import com.scopely.redis.challenge.exceptions.RedisException;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by russellb337 on 7/2/15.
 */
public class MinaCommandHandler implements IoHandler {

    private final CommandProcessor commandProcessor;

    public MinaCommandHandler(CommandProcessor commandProcessor) {

        this.commandProcessor = commandProcessor;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(MinaCommandHandler.class);

    @Override
    public void sessionCreated(IoSession session) throws Exception {
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        LOGGER.error("Exception caught", cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {

        String response;
        try {
            response = commandProcessor.processCommand((String) message);
        } catch(RedisException e) {
            response = e.getWholeMessage();
        }

        session.write(response);
        LOGGER.info("command={}; response={}", message, response);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {

    }

    @Override
    public void inputClosed(IoSession session) throws Exception {

    }
}
