package net.hubert.rupecs_emblems.mixin;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
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


    @Unique
    Entity rupec_sEmblems$entity = (Entity) (Object) this;
    @ModifyConstant(
            method = "baseTick",
            constant = @Constant(intValue = 20)
    )
    private int changeFireTickFrequency(int original) {
        if (rupec_sEmblems$entity instanceof Player player) {
            return (int) Math.max(1, 20 * (1+player.getAttributeValue(ModAttributes.ASHEN.get())));
        }
        return 20;
    }
}