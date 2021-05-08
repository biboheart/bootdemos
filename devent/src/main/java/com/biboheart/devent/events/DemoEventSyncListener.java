package com.biboheart.devent.events;

import com.biboheart.brick.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoEventSyncListener {
    @EventListener
    public void onSchedulingChangeEvent(DemoEvent event) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("4 {} event sync listener source: {}, name: {}", TimeUtils.formatDate(null, null), event.getSource(), event.getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("5 {} event sync listener end source: {}, name: {}", TimeUtils.formatDate(null, null), event.getSource(), event.getName());
    }
}
