package com.maxpro.service;

import com.maxpro.service.jms.JMSMessageEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class JMSMessageEventListener implements ApplicationListener<JMSMessageEvent> {
    @Override
    public void onApplicationEvent(JMSMessageEvent event) {
        System.out.println("Received spring custom event - " + event.getMessage());
    }
}
