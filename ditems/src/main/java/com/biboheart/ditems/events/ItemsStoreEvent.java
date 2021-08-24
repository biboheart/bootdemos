package com.biboheart.ditems.events;

import com.biboheart.ditems.entity.ItemsStore;
import org.springframework.context.ApplicationEvent;

public class ItemsStoreEvent extends ApplicationEvent {
    private final ItemsStore itemsStore;

    public ItemsStoreEvent(Object source, ItemsStore itemsStore) {
        super(source);
        this.itemsStore = itemsStore;
    }

    public ItemsStore getItemsStore() {
        return itemsStore;
    }
}
