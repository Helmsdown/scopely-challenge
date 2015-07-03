package com.scopely.redis.challenge;

import com.scopely.redis.challenge.commands.CommandProcessor;
import com.scopely.redis.challenge.models.MasterDictionary;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by russellb337 on 7/2/15.
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final int PORT = 5555;

    public static void main(String[] args) throws IOException {

        MasterDictionary masterDictionary = new MasterDictionary();
        CommandProcessor commandProcessor = new CommandProcessor(masterDictionary);

        startMina(commandProcessor);
    }

    private static void startMina(CommandProcessor commandProcessor) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();

//        acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

        acceptor.setHandler(new MinaCommandHandler(commandProcessor));

        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 300);
        acceptor.bind(new InetSocketAddress(PORT));

        LOGGER.info("Listening on port {}", PORT);
    }
}
