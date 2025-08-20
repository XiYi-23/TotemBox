package com.totembox.generator;

import com.mojang.datafixers.types.templates.Tag;
import com.totembox.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.TOTEM_BOX)
                .pattern("iii")
                .pattern("ptp")
                .pattern("ppp")
                .input('i', Items.IRON_INGOT)
                .input('p', ItemTags.PLANKS)
                .input('t', Items.TOTEM_OF_UNDYING)
                .criterion(FabricRecipeProvider.hasItem(Items.TOTEM_OF_UNDYING),FabricRecipeProvider.conditionsFromItem(Items.TOTEM_OF_UNDYING))
                .offerTo(exporter);
    }
}
