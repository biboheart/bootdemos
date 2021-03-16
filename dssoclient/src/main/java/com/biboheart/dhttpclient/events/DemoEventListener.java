package com.biboheart.dhttpclient.events;

import com.biboheart.dhttpclient.dock.UserDock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoEventListener {
    private final UserDock userDock;

    @Autowired
    public DemoEventListener(UserDock userDock) {
        this.userDock = userDock;
    }

    @Async
    @EventListener
    public void onSchedulingChangeEvent(DemoEvent event) {
        log.info(event.toString());
        log.info(userDock.request());
    }
}
