package com.totembox.item;

import com.totembox.component.ModComponents;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class TotemBoxItem extends Item {
    private static int capacity = 5;
    public TotemBoxItem(Settings settings) {
        super(settings);
    }

    public static void setCapacity(int i){
        capacity = i;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.totem-box.totem_box.usage"));
        tooltip.add(Text.translatable("item.totem-box.totem_box.usage2"));
        tooltip.add(Text.translatable("item.totem-box.totem_box.info", stack.getOrDefault(ModComponents.TOTEM_BOX_CONTENT,0), capacity));
    }

    @Override
    public void onCraft(ItemStack stack, World world) {
        if(!world.isClient()){
            stack.set(ModComponents.TOTEM_BOX_CONTENT, 1);
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()){
            if(Screen.hasShiftDown()){
                ItemStack totem_stack = user.getStackInHand(hand);
                int num = totem_stack.getOrDefault(ModComponents.TOTEM_BOX_CONTENT,0);
//                while(num > 0){
                    ItemStack stack = new ItemStack(Items.TOTEM_OF_UNDYING,num);
                    boolean bl = user.giveItemStack(stack);
                    ItemEntity itemEntity;
                    if(bl && stack.isEmpty()){
                        itemEntity = user.dropItem(new ItemStack(Items.TOTEM_OF_UNDYING), false);
                        if (itemEntity != null) {
                            itemEntity.setDespawnImmediately();
                        }
                        user.getWorld().playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((user.getRandom().nextFloat() - user.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
                        user.currentScreenHandler.sendContentUpdates();
                    }else{
                        itemEntity = user.dropItem(stack, false);
                        if (itemEntity != null) {
                            itemEntity.resetPickupDelay();
                            itemEntity.setOwner(user.getUuid());
                        }
                    }
//                    num--;
//                }
                totem_stack.set(ModComponents.TOTEM_BOX_CONTENT,0);
            }else{
                ItemStack totem_stack = user.getStackInHand(hand);
                Inventory inv = user.getInventory();
                boolean sound_flg = false;
                for(int i = 0; i < inv.size(); i++){
                    if(totem_stack.getOrDefault(ModComponents.TOTEM_BOX_CONTENT,0) >= TotemBoxItem.capacity){
                        break;
                    }
                    ItemStack stack = inv.getStack(i);
                    if(stack.getItem().equals(Items.TOTEM_OF_UNDYING)){
                        sound_flg = true;
                        stack.decrement(1);
                        totem_stack.set(ModComponents.TOTEM_BOX_CONTENT,totem_stack.getOrDefault(ModComponents.TOTEM_BOX_CONTENT,0) + 1);
                    }
                }
                if(sound_flg){
                    user.getWorld().playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((user.getRandom().nextFloat() - user.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
                }
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return stack.getOrDefault(ModComponents.TOTEM_BOX_CONTENT,0) < capacity;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return MathHelper.clamp(Math.round(stack.getOrDefault(ModComponents.TOTEM_BOX_CONTENT,0) / 5f * 13f), 0, 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        float f = Math.max(0.0F, ((float) stack.getOrDefault(ModComponents.TOTEM_BOX_CONTENT,0) / capacity));
        return MathHelper.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }
}
