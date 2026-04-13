package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DrowningHandler {
    // Map to store fractional air "refund" accumulations for each entity
    private static final Map<LivingEntity, Float> airRefundAccumulator = new HashMap<>();

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        // Make sure we're on the server side and the entity is underwater
        if (!entity.level().isClientSide() && entity.isAlive() && entity.isInWater()) {
            if (entity.getAttribute(ModAttributes.RESPIRATION.get()) != null) {
                double respirationLevel = entity.getAttribute(ModAttributes.RESPIRATION.get()).getValue();
                if (respirationLevel > 0) {
                    // Get or initialize the accumulator for this entity
                    float accumulator = airRefundAccumulator.getOrDefault(entity, 0f);

                    // Calculate how much air to "refund" based on respiration level
                    accumulator += (float) (respirationLevel / 5f);

                    // When we've accumulated at least 1, refund air
                    if (accumulator >= 1f) {
                        int refundAmount = (int) accumulator;
                        accumulator -= refundAmount;

                        // Increase the entity's air supply
                        entity.setAirSupply(Math.min(entity.getAirSupply() + refundAmount, entity.getMaxAirSupply()));
                    }

                    // Store the updated accumulator
                    airRefundAccumulator.put(entity, accumulator);
                }
            }
        }

        // Clean up the map when entities are no longer in water or dead
        if (!entity.isInWater() || !entity.isAlive()) {
            airRefundAccumulator.remove(entity);
        }
    }
}