package com.biboheart.ditems.events;

import com.biboheart.ditems.entity.ItemsDefServices;
import org.springframework.context.ApplicationEvent;

public class ItemsDefServicesEvent extends ApplicationEvent {
    private final ItemsDefServices itemsDefServices;

    public ItemsDefServicesEvent(Object source, ItemsDefServices itemsDefServices) {
        super(source);
        this.itemsDefServices = itemsDefServices;
    }

    public ItemsDefServices getItemsDefServices() {
        return itemsDefServices;
    }
}
