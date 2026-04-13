package net.hubert.rupecs_emblems.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosCapability;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class EmblementalEntheraItem extends Item {

    public EmblementalEntheraItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        super.onCraftedBy(stack, level, player);
        ensureEntherTag(stack);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        ensureEntherTag(stack);
        return stack;
    }

    private void ensureEntherTag(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("enther")) {
            tag.putInt("enther", 0);
        }
    }

    public int getEnther(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.getInt("enther") < 0) {
            tag.putInt("enther", 0);
        }
        return tag.getInt("enther");
    }

    public void setEnther(ItemStack stack, int value) {
        stack.getOrCreateTag().putInt("enther", value);
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (pLevel == null) return;
        pTooltipComponents.add(Component.literal("Enthereal charge level: "+getEnther(pStack)).withStyle(ChatFormatting.YELLOW));

    }
}