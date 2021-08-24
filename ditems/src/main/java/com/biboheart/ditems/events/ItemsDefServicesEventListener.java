package com.biboheart.ditems.events;

import com.biboheart.ditems.entity.ItemsDefServices;
import com.biboheart.ditems.process.ItemsStoreProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ItemsDefServicesEventListener {
    private final ItemsStoreProcess itemsStoreProcess;

    public ItemsDefServicesEventListener(ItemsStoreProcess itemsStoreProcess) {
        this.itemsStoreProcess = itemsStoreProcess;
    }

    @Async
    @EventListener
    public void onSchedulingChangeEvent(ItemsDefServicesEvent event) {
        ItemsDefServices itemsDefServices = event.getItemsDefServices();
        if (null == itemsDefServices) return;
        if (!"items_def_services_save".equals(event.getSource())) return;
        itemsStoreProcess.updateItemsStoreTargetFromTypeSn(itemsDefServices.getTypeSn());
    }
}
