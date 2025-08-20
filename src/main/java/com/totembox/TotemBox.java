package com.totembox;

import com.totembox.component.ModComponents;
import com.totembox.config.ModConfig;
import com.totembox.event.ConfigUpdateEvent;
import com.totembox.event.UseTotemBoxEvent;
import com.totembox.item.ModItems;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TotemBox implements ModInitializer {
	public static final String MOD_ID = "totem-box";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		ModItems.init();
		ModComponents.init();
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);

		ConfigHolder<ModConfig> holder = AutoConfig.getConfigHolder(ModConfig.class);
		ModConfig cfg = holder.getConfig();
		ConfigUpdateEvent.updateConfig(cfg);

		holder.registerSaveListener((cfgHolder, newCfg) -> {
			ConfigUpdateEvent.updateConfig(newCfg);
			return ActionResult.SUCCESS;
		});

		ServerLivingEntityEvents.ALLOW_DEATH.register(((entity, damageSource, damageAmount) -> {
			if (damageSource.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
				return true;
			}
			if(entity.isPlayer()){
				return UseTotemBoxEvent.allowPlayerDeath((PlayerEntity) entity);
			}else{
				return true;
			}
		}));
	}
}