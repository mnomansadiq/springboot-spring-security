package com.maxpro.service.jms;

import org.springframework.context.ApplicationEvent;

public class JMSMessageEvent extends ApplicationEvent {
    private String message;

    public JMSMessageEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
