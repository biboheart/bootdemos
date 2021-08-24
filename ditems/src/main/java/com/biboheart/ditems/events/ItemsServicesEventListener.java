package com.biboheart.ditems.events;

import com.biboheart.ditems.entity.ItemsServices;
import com.biboheart.ditems.process.ItemsStoreProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ItemsServicesEventListener {
    private final ItemsStoreProcess itemsStoreProcess;

    public ItemsServicesEventListener(ItemsStoreProcess itemsStoreProcess) {
        this.itemsStoreProcess = itemsStoreProcess;
    }

    @Async
    @EventListener
    public void onSchedulingChangeEvent(ItemsServicesEvent event) {
        ItemsServices itemsServices = event.getItemsServices();
        if (null == itemsServices) return;
        if (!"items_services_save".equals(event.getSource())) return;
        itemsStoreProcess.updateItemsStoreTargetFromItemsSn(itemsServices.getItemsSn());
    }
}
