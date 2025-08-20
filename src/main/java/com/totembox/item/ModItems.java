package com.totembox.item;

import com.totembox.component.ModComponents;
import com.totembox.TotemBox;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems{

    public static final Item TOTEM_BOX = register("totem_box",new TotemBoxItem((new Item.Settings()).maxCount(1).component(ModComponents.TOTEM_BOX_CONTENT,0)));
    public static void init(){
        TotemBox.LOGGER.info("initialize mod items");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.addAfter(Items.TOTEM_OF_UNDYING, ModItems.TOTEM_BOX);
        });
    }
    private static Item register(String id, Item item) {
        return register(Identifier.of(TotemBox.MOD_ID,id), item);
    }

    private static Item register(Identifier id, Item item) {
        return register(RegistryKey.of(Registries.ITEM.getKey(), id), item);
    }

    private static Item register(RegistryKey<Item> key, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return (Item) Registry.register(Registries.ITEM, key, item);
    }
}
