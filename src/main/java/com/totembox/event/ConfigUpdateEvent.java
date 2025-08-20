package com.totembox.event;

import com.totembox.config.ModConfig;
import com.totembox.item.TotemBoxItem;

public class ConfigUpdateEvent {
    public static void updateConfig(ModConfig cfg){
        TotemBoxItem.setCapacity(cfg.getCapacity());
    }
}
