package com.totembox.generator;

import com.totembox.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLangGenerator extends FabricLanguageProvider {
    public ModLangGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }


    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.TOTEM_BOX,"totem_box");
        translationBuilder.add("item.totem-box.totem_box.usage","\u00a7eRIGHTCLICK\u00a7r to reload");
        translationBuilder.add("item.totem-box.totem_box.usage2","\u00a7eSHIFT+RIGHTCLICK\u00a7r to unload");
        translationBuilder.add("item.totem-box.totem_box.info", "Totem_of_undying %1$s/%2$s");
        translationBuilder.add("message.totem-box.empty","Your totem_box is empty now!");
        translationBuilder.add("message.totem-box.last_one","There is only one totem left!");
        translationBuilder.add("text.autoconfig.totem-box.option.capacity","Capacity");
        translationBuilder.add("text.autoconfig.totem-box.option.capacity.@Tooltip","How many totem can box contain");
    }
}
