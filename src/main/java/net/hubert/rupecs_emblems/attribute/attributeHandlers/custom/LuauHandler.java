package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LuauHandler {

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (!entity.level().isClientSide && entity.tickCount % 100 == 0) {
            if (entity.getAttribute(ModAttributes.LUAU.get()) != null) {
                double luauLvl = entity.getAttribute(ModAttributes.LUAU.get()).getValue();
                if (luauLvl > 0) {
                    List<MobEffect> goodEffects = new ArrayList<>();
                    for (MobEffect mobEffect : ForgeRegistries.MOB_EFFECTS.getValues()) {
                        if (mobEffect.isBeneficial()) {
                            goodEffects.add(mobEffect);
                        }
                    }
                    MobEffect chosenEffect = goodEffects.get(entity.getRandom().nextInt(0, goodEffects.size() - 1));
                    int newAmplifier = (int) luauLvl - 1;
                    int newDuration = (int) (150+(150*luauLvl));

                    if (entity.hasEffect(chosenEffect)) {
                        MobEffectInstance existingEffect = entity.getEffect(chosenEffect);
                        assert existingEffect != null;
                        int existingAmplifier = existingEffect.getAmplifier();
                        int existingDuration = existingEffect.getDuration();

                        // Use the higher amplifier
                        int finalAmplifier = Math.max(newAmplifier, existingAmplifier);

                        int finalDuration = Math.min(existingDuration + newDuration, 1200);

                        entity.addEffect(new MobEffectInstance(chosenEffect, finalDuration, finalAmplifier, false, false, true));
                    } else {
                        entity.addEffect(new MobEffectInstance(chosenEffect, newDuration, newAmplifier, false, false, true));
                    }
                }
            }
        }
    }
}
