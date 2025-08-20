package com.totembox.config;

import com.totembox.TotemBox;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = TotemBox.MOD_ID)
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    private int capacity = 3;

    public int getCapacity() {
        return capacity;
    }
}
