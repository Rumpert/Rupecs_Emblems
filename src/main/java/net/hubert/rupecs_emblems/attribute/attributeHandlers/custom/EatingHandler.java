package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import com.mojang.datafixers.util.Pair;
import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EatingHandler {
    @SubscribeEvent
    public static void onEat(LivingEntityUseItemEvent.Start event) {
        LivingEntity entity = event.getEntity();

        // Check if entity has vegetarian attribute and it's active
        if (entity.getAttribute(ModAttributes.VEGE.get()) != null &&
                entity.getAttribute(ModAttributes.VEGE.get()).getValue() > 0) {

            // Check if the item is meat
            FoodProperties foodProperties = event.getItem().getFoodProperties(entity);
            if (foodProperties != null && foodProperties.isMeat()) {
                // Cancel both the action and animation
                event.setCanceled(true);

                // Reset item use if it's a player
                if (entity instanceof Player player) {
                    player.stopUsingItem();
                }
            } else if (foodProperties != null) {
                if ((int) (event.getDuration() - entity.getAttribute(ModAttributes.VEGE.get()).getValue() * 10) > 0) {
                    event.setDuration((int) (event.getDuration() - entity.getAttribute(ModAttributes.VEGE.get()).getValue() * 10));
                } else {
                    event.setDuration(1);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onFinishEat(LivingEntityUseItemEvent.Finish event) {
        LivingEntity entity = event.getEntity();
        if (entity.getAttribute(ModAttributes.ROTTEN.get()) != null &&
                entity.getAttribute(ModAttributes.ROTTEN.get()).getValue() > 0) {

            FoodProperties foodProperties = event.getItem().getFoodProperties(entity);
            if (foodProperties != null && !foodProperties.getEffects().isEmpty()) {
                for (Pair<MobEffectInstance, Float> effect : foodProperties.getEffects()){
                    if (!effect.getFirst().getEffect().isBeneficial()){
                        event.getEntity().removeEffect(effect.getFirst().getEffect());
                        System.out.println("non beneficial");
                    }
                }
            }
        }
    }
}