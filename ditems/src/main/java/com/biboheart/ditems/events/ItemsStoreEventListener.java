package com.biboheart.ditems.events;

import com.biboheart.ditems.entity.ItemsStore;
import com.biboheart.ditems.process.ItemsStoreProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ItemsStoreEventListener {
    private final ItemsStoreProcess itemsStoreProcess;

    public ItemsStoreEventListener(ItemsStoreProcess itemsStoreProcess) {
        this.itemsStoreProcess = itemsStoreProcess;
    }

    @Async
    @EventListener
    public void onSchedulingChangeEvent(ItemsStoreEvent event) {
        ItemsStore itemsStore = event.getItemsStore();
        if (!"items_store_save".equals(event.getSource())) return;
        if (null == itemsStore) return;
        itemsStoreProcess.updateItemsStoreTarget(itemsStore, null);
    }
}
