package net.hubert.rupecs_emblems.effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

import java.awt.*;

public class ImmortalityEffect extends MobEffect {

    public ImmortalityEffect() {
        super(MobEffectCategory.BENEFICIAL, Color.WHITE.getRGB());
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            entity.setInvulnerable(true);
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if (!pLivingEntity.level().isClientSide) {
            // Only disable if no other immortality effect is active
            pLivingEntity.setInvulnerable(false);

        }
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }


    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Apply every tick (20 times per second)
        return true;
    }
}