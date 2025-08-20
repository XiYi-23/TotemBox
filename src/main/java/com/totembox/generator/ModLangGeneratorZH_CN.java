package com.totembox.generator;

import com.totembox.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLangGeneratorZH_CN extends FabricLanguageProvider {
    public ModLangGeneratorZH_CN(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput,"zh_cn", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.TOTEM_BOX,"图腾收纳盒");
        translationBuilder.add("item.totem-box.totem_box.usage","\u00a7e右键\u00a7r 装填");
        translationBuilder.add("item.totem-box.totem_box.usage2","\u00a7eSHIFT+右键\u00a7r 取出");
        translationBuilder.add("item.totem-box.totem_box.info", "不死图腾 %1$s/%2$s");
        translationBuilder.add("message.totem-box.empty","你的不死图腾收纳盒空了！");
        translationBuilder.add("message.totem-box.last_one","还剩一个不死图腾！");
        translationBuilder.add("text.autoconfig.totem-box.option.capacity","容量");
        translationBuilder.add("text.autoconfig.totem-box.option.capacity.@Tooltip","盒子中能存放的不死图腾数量");
    }
}
