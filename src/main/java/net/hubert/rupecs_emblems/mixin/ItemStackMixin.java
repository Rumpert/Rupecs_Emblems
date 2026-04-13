package net.hubert.rupecs_emblems.mixin;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "getMaxDamage", at = @At("RETURN"), cancellable = true)
    private void modifyMaxDamage(CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = (ItemStack)(Object)this;

        if (!stack.isDamageableItem()) return;


        if (stack.getTag()==null)return;
        double bonus = stack.getTag().getFloat("durability_multiplier"); // 0.05 per level

        int original = cir.getReturnValue();
        int modified = (int)(original * (1+bonus));
        cir.setReturnValue(modified);
    }
}