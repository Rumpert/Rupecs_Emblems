package net.hubert.rupecs_emblems.util;

import net.hubert.rupecs_emblems.command.gamerule.ModGameRules;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class AttributeHelper {
    public static double getValueIfNotNull(LivingEntity entity, Attribute attribute) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance != null) {
            try {
                return instance.getValue();
            } catch (NullPointerException ignored) {
                System.err.println("Failed to get attribute value: " + attribute.getDescriptionId());
            }
        }
        return 0;
    }

    private static void applyStaticModifier(LivingEntity entity, Attribute attribute,
                                            UUID modifierId, String name, double amount,
                                            AttributeModifier.Operation operation) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance == null || instance.getModifier(modifierId) != null) return;

        AttributeModifier modifier = new AttributeModifier(
                modifierId, name, amount, operation);
        instance.addTransientModifier(modifier);
    }
    public static void updateAttributeModifier(LivingEntity entity, Attribute attribute,
                                                UUID modifierId, String name, double amount,
                                                AttributeModifier.Operation operation) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance == null) return;

        if(attribute == Attributes.MOVEMENT_SPEED && !entity.level().getGameRules().getRule(ModGameRules.ALLOW_EMBLEM_SPEED_BONUS).get())return;
        AttributeModifier existing = instance.getModifier(modifierId);
        if (existing != null) {
            if (existing.getAmount() != amount || existing.getOperation() != operation) {
                instance.removeModifier(modifierId);
                existing = null;
            }
        }

        if (existing == null) {
            AttributeModifier modifier = new AttributeModifier(
                    modifierId, name, amount, operation);
            instance.addTransientModifier(modifier);
        }
    }

    public static void updateConditionalModifier(LivingEntity entity, Attribute attribute,
                                                  UUID modifierId, String name, double amount,
                                                  AttributeModifier.Operation operation,
                                                  boolean shouldApply) {
        if (shouldApply) {
            updateAttributeModifier(entity, attribute, modifierId, name, amount, operation);
        } else {
            removeModifierIfPresent(entity, attribute, modifierId);
        }
    }

    public static void removeModifierIfPresent(LivingEntity entity, Attribute attribute, UUID modifierId) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance != null && instance.getModifier(modifierId) != null) {
            instance.removeModifier(modifierId);
        }
    }
}
