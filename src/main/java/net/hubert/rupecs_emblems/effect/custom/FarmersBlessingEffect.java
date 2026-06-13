package net.hubert.rupecs_emblems.effect.custom;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.awt.*;
import java.util.UUID;

import static net.hubert.rupecs_emblems.providers.EmblemEffectProvider.applyStaticModifier;
import static net.hubert.rupecs_emblems.providers.EmblemEffectProvider.removeModifierIfPresent;

public class FarmersBlessingEffect extends MobEffect {

    public FarmersBlessingEffect() {
        super(MobEffectCategory.BENEFICIAL, Color.WHITE.getRGB());
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            applyStaticModifier(
                    entity,
                    ModAttributes.FARMERS_BLESSING.get(),
                    UUID.nameUUIDFromBytes(this.getDescriptionId().getBytes()),
                    "Farmers blessing effect",
                    amplifier+1,
                    AttributeModifier.Operation.ADDITION
            );
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        if (!entity.level().isClientSide) {
            removeModifierIfPresent(
                    entity,
                    ModAttributes.FARMERS_BLESSING.get(),
                    UUID.nameUUIDFromBytes(this.getDescriptionId().getBytes())
            );
        }

        super.removeAttributeModifiers(entity, attributeMap, amplifier);
    }


    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Apply every tick (20 times per second)
        return true;
    }
}