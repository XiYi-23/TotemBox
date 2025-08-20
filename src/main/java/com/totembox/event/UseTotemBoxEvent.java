package com.totembox.event;

import com.totembox.component.ModComponents;
import com.totembox.item.ModItems;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.world.event.GameEvent;

import java.util.ArrayList;
import java.util.List;

public class UseTotemBoxEvent {
    public static boolean allowPlayerDeath(PlayerEntity player){
        Inventory inv = player.getInventory();
        List<ItemStack> stacks = new ArrayList<ItemStack>();
        //find all totem_box in bag
        for(int i = 0; i < inv.size(); i++){
            if(inv.getStack(i).getItem().equals(ModItems.TOTEM_BOX)){
                stacks.add(inv.getStack(i));
            }
        }
        if(stacks.isEmpty()){//dont have totem_box
            return true;
        }
        int content = 0;
        for(ItemStack stack:stacks){
            content = stack.getOrDefault(ModComponents.TOTEM_BOX_CONTENT,0);
            if(content > 0){//use totem
                if(content == 1){
                    player.sendMessage(Text.translatable("message.totem-box.empty"));
                }
                if(content == 2){
                    player.sendMessage(Text.translatable("message.totem-box.last_one"));
                }
                stack.set(ModComponents.TOTEM_BOX_CONTENT,content - 1);
                if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                    serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(Items.TOTEM_OF_UNDYING));
                    Criteria.USED_TOTEM.trigger(serverPlayerEntity, new ItemStack(Items.TOTEM_OF_UNDYING));
                    player.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
                }
                player.setHealth(1.0F);
                player.clearStatusEffects();
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
                player.getWorld().sendEntityStatus(player, (byte)35);
                return false;//prevent death
            }
        }
        return true;//no totem in box
    }
}
