package com.totembox.component;

import com.mojang.serialization.Codec;
import com.totembox.TotemBox;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModComponents {
    public static final ComponentType<Integer> TOTEM_BOX_CONTENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(TotemBox.MOD_ID,"totem_box_content"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );
    public static void init(){
        TotemBox.LOGGER.info("initialize components");
    }
}
