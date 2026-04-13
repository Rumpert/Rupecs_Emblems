package net.hubert.rupecs_emblems.mixin;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingMenu.class)
public abstract class CraftingMenuMixin {

    @Inject(method = "slotChangedCraftingGrid", at = @At("TAIL"))
    private static void modifyResult(AbstractContainerMenu pMenu, Level pLevel, Player pPlayer, CraftingContainer pContainer, ResultContainer pResult, CallbackInfo ci) {

        ItemStack stack = pResult.getItem(0);

        if (stack.isEmpty() || !stack.isDamageableItem()) return;

        AttributeInstance attr = pPlayer.getAttribute(ModAttributes.ANVIL.get());
        if (attr == null) return;

        float multiplier = (float) attr.getValue();

        stack.getOrCreateTag().putFloat("durability_multiplier", multiplier);
    }
}