package net.hubert.rupecs_emblems.mixin;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract int getAirSupply();

    @Shadow
    public abstract void setAirSupply(int air);

    // Override max air supply
    @Inject(method = "getMaxAirSupply", at = @At("RETURN"), cancellable = true)
    private void getMaxAirSupply(CallbackInfoReturnable<Integer> cir) {
        if ((Object) this instanceof Player player) {
            if (player.getAttributes() == null)return;
            var attribute = player.getAttribute(ModAttributes.LUNG_CAPACITY.get());
            if (attribute != null) {
                cir.setReturnValue((int) attribute.getValue());
            }
        }
    }


}