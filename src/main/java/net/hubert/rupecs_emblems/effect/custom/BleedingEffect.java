package net.hubert.rupecs_emblems.effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.awt.*;
public class BleedingEffect extends MobEffect {
    public BleedingEffect() {
        super(MobEffectCategory.HARMFUL, Color.RED.getRGB());
    }
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            if (entity.isAlive()){
                entity.hurt(entity.damageSources().magic(), amplifier);
            }
        }
    }



    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 60 == 0; // Effect applies every second (20 ticks)
    }


}
