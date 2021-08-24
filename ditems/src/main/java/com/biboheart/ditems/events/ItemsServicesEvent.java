package com.biboheart.ditems.events;

import com.biboheart.ditems.entity.ItemsServices;
import org.springframework.context.ApplicationEvent;

public class ItemsServicesEvent extends ApplicationEvent {
    private final ItemsServices itemsServices;

    public ItemsServicesEvent(Object source, ItemsServices itemsServices) {
        super(source);
        this.itemsServices = itemsServices;
    }

    public ItemsServices getItemsServices() {
        return itemsServices;
    }
}
