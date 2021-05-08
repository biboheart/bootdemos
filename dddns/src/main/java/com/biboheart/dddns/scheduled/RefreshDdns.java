package com.biboheart.dddns.scheduled;

import com.biboheart.dddns.DDNS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class RefreshDdns {
    private final DDNS ddns;

    public RefreshDdns(DDNS ddns) {
        this.ddns = ddns;
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void process() {
        ddns.update();
    }
}
