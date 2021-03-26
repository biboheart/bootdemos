package com.biboheart.devent.events;

import org.springframework.context.ApplicationEvent;

public class DemoEvent extends ApplicationEvent {
    private final String name;

    public DemoEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
