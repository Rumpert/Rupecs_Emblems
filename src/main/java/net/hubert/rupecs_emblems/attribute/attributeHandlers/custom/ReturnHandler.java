package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.ReturnPacket;
import net.hubert.rupecs_emblems.util.CooldownEntry;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class ReturnHandler {
    private static final String cooldownName = "Return";
    private static final Map<UUID, Integer> chargeProgress = new HashMap<>();
    private static final int COOLDOWN_SECONDS = 30;
    private static final int CHARGE_TIME = 120; // 3 seconds at 20 ticks/sec
    public static final KeyMapping RETURN_KEY = Keybinds.INSTANCE.primaryEmblemAbility;

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (player.getAttribute(ModAttributes.RETURN.get()) == null ||
                player.getAttribute(ModAttributes.RETURN.get()).getValue() <= 0) {
            return;
        }

        UUID uuid = player.getUUID();
        // Safely retrieve the cooldown for Return.
        CooldownEntry cooldownEntry = CooldownManager.getCooldownEntries(player.getUUID()).get(cooldownName);
        int cooldownTicks = cooldownEntry != null ? cooldownEntry.getTicks() : 0;

        // Client-side charging logic.
        if (player.level().isClientSide) {

            if (RETURN_KEY.isDown() && player.isCrouching()) {
                if (player.level().dimension() != Level.OVERWORLD) {
                    player.displayClientMessage(
                            Component.literal("Ability unavailable outside overworld").withStyle(ChatFormatting.RED),
                            true
                    );
                    return;
                }
                if (cooldownTicks > 0) {
                    player.displayClientMessage(
                            Component.literal("Ability on cooldown: " + cooldownTicks / 20 + "s").withStyle(ChatFormatting.RED),
                            true
                    );
                    return;
                }

                int progress = chargeProgress.getOrDefault(uuid, 0) + 1;
                chargeProgress.put(uuid, progress);

                // Spiral particle effect during charge.
                spawnChargeParticles(player, progress);

                if (progress >= CHARGE_TIME) {
                    triggerReturn(player);
                    chargeProgress.remove(uuid);
                }
            } else {
                chargeProgress.remove(uuid);
            }
        }
    }

    private static void spawnChargeParticles(Player player, int progress) {
        if (player.level().isClientSide) {
            double height = player.getBbHeight() * (progress / (double) CHARGE_TIME) * 2;
            double radius = 1.0;
            int particlesPerLayer = 6;

            for (int i = 0; i < particlesPerLayer; i++) {
                double angle = 2 * Math.PI * (i + height) / particlesPerLayer * height;
                double x = player.getX() + radius * Math.cos(angle);
                double z = player.getZ() + radius * Math.sin(angle);

                for (double y = player.getY(); y < player.getY() + height; y += 0.1) {
                    player.level().addParticle(ParticleTypes.ELECTRIC_SPARK,
                            x, y, z, 0, 0.05, 0);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onInputEvent(MovementInputUpdateEvent event) {
        // While charging, cancel player movement.
        if (chargeProgress.getOrDefault(event.getEntity().getUUID(), 0) > 0) {
            event.getEntity().setDeltaMovement(Vec3.ZERO);
        }
    }

    public static void triggerReturn(Player player) {
        // Safely retrieve the cooldown entry.
        CooldownEntry cooldownEntry = CooldownManager.getCooldownEntries(player.getUUID()).get(cooldownName);
        int currentCooldown = cooldownEntry != null ? cooldownEntry.getTicks() : 0;
        if (currentCooldown > 0) return;

        if (player.getAttribute(ModAttributes.RETURN.get()) != null &&
                player.getAttribute(ModAttributes.RETURN.get()).getValue() > 0) {

            // Final teleportation burst (server-side particle effect).
            if (!player.level().isClientSide && player.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.FLASH,
                        player.getX(), player.getY() + player.getBbHeight() / 2, player.getZ(),
                        30, 0.5, 0.5, 0.5, 0.5);
            }

            returnPlayer(player);
            // Multiply seconds by 20 for ticks.
            int cooldownTicks = COOLDOWN_SECONDS * 20;
            CooldownManager.addCooldown(
                    player.getUUID(),
                    cooldownName,
                    cooldownTicks,
                    new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/return.png")
            );
        }
    }

    private static void returnPlayer(Player player) {
        PacketHandler.sendToServer(new ReturnPacket());
        // Reset the charge progress for the player.
        chargeProgress.remove(player.getUUID());
    }
}
