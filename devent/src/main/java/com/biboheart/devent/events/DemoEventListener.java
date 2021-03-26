package com.biboheart.devent.events;

import com.biboheart.brick.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoEventListener {
    @Async
    @EventListener
    public void onSchedulingChangeEvent(DemoEvent event) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("1 {} event listener source: {}, name: {}", TimeUtils.formatDate(null, null), String.valueOf(event.getSource()), event.getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
