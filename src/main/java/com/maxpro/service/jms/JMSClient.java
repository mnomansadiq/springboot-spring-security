package com.maxpro.service.jms;

import com.maxpro.configuration.database.MongoConfiguration;
import org.hornetq.api.core.HornetQException;
import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.core.client.*;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class JMSClient {
    private static final Logger logger = LoggerFactory.getLogger(JMSClient.class);
    @Autowired
    private MongoConfiguration configuration;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private String JMS_QUEUE_NAME;
    private String MESSAGE_PROPERTY_NAME;
    private ClientSessionFactory sf = null;

    @PostConstruct
    public void setUp() {

        Map<String, Object> map = new HashMap<>();
        map.put("host", configuration.getJMSServerURL());
        map.put("port", configuration.getJMSServerPort());
        this.JMS_QUEUE_NAME = configuration.getJMSMessageQueueName();
        this.MESSAGE_PROPERTY_NAME = configuration.getJMSMessagePropertyName();
        ServerLocator serverLocator = HornetQClient.createServerLocatorWithoutHA(new TransportConfiguration(NettyConnectorFactory.class.getName(), map));

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    try {
                        sf = serverLocator.createSessionFactory();
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        logger.error("error while connecting JMS server ", e);
                    }
                }
                while (sf == null);
                startReadMessages();
            }
        });
        t1.start();
    }

    private void startReadMessages() {
        ClientSession session = null;
        try {
            if (sf != null) {
                session = sf.createSession(true, true);

                while (true) {
                    ClientConsumer messageConsumer = session.createConsumer(JMS_QUEUE_NAME);
                    session.start();

                    ClientMessage messageReceived = messageConsumer.receive(1000);
                    if (messageReceived != null && messageReceived.getStringProperty(MESSAGE_PROPERTY_NAME) != null) {
                        messageReceived.acknowledge();
                        String stringProperty = messageReceived.getStringProperty(MESSAGE_PROPERTY_NAME);
                        logger.info("JMS message " + stringProperty);

                        //broadcast
                        JMSMessageEvent customSpringEvent = new JMSMessageEvent(this, stringProperty);
                        applicationEventPublisher.publishEvent(customSpringEvent);

                    } else
                        logger.debug("No message available");
                    messageConsumer.close();
                    Thread.sleep(500);
                }
            }
        } catch (Exception e) {
            logger.error("Error while adding message by producer.", e);
        } finally {
            try {
                session.close();
            } catch (HornetQException e) {
                logger.error("Error while closing producer session,", e);
            }
        }
    }

}
