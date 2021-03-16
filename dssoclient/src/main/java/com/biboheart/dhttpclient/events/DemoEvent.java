package com.biboheart.dhttpclient.events;

import org.springframework.context.ApplicationEvent;

public class DemoEvent extends ApplicationEvent {
    public DemoEvent(Object source) {
        super(source);
    }
}
