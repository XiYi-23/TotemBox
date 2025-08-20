package com.totembox;

import com.totembox.generator.ModLangGenerator;
import com.totembox.generator.ModLangGeneratorZH_CN;
import com.totembox.generator.ModModelGenerator;
import com.totembox.generator.ModRecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TotemBoxDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModRecipeGenerator::new);
		pack.addProvider(ModLangGenerator::new);
		pack.addProvider(ModModelGenerator::new);
		pack.addProvider(ModLangGeneratorZH_CN::new);
	}
}
