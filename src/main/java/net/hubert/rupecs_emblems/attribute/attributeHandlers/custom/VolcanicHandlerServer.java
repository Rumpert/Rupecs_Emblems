package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.entity.ModEntities;
import net.hubert.rupecs_emblems.entity.custom.VolcanicShardProjectile;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID)
public class VolcanicHandlerServer {
    private static final Map<UUID, Integer> activePlayers = new HashMap<>();
    private static int tickDelay = 2;
    private static final float HIT_NEAREST_CHANCE = 0.2f;

    public static void startVolcanicAction(Player player) {
        if (player.level().isClientSide) return;

        int duration = (int) player.getAttributeValue(ModAttributes.VOLCANIC.get()) * 12 * tickDelay;
        activePlayers.put(player.getUUID(), duration);
        Rupecs_Emblems.LOGGER.info("Started volcanic action for {} for {} ticks",
                player.getName().getString(), duration);
    }

    public static void stopVolcanicAction(Player player) {
        activePlayers.remove(player.getUUID());
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        // Only process on server and at END phase
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide) return;

        Player player = event.player;
        UUID playerId = player.getUUID();

        if (activePlayers.containsKey(playerId)) {
            int remainingTicks = activePlayers.get(playerId);

            if (remainingTicks > 0) {
                applyVolcanicEffect(player, remainingTicks);
                activePlayers.put(playerId, remainingTicks - 1);
            } else {
                activePlayers.remove(playerId);
                onVolcanicEffectEnd(player);
            }
        }
    }

    private static void applyVolcanicEffect(Player player, int remainingTicks) {
        if (remainingTicks % tickDelay == 0) {
            double attributeValue = player.getAttributeValue(ModAttributes.VOLCANIC.get());
            int shardCount = Math.min(Math.max(1, (int) (attributeValue * 2)), 4);

            for (int i = 0; i < shardCount; i++) {
                VolcanicShardProjectile shard = new VolcanicShardProjectile(
                        ModEntities.VOLCANIC_SHARD.get(),
                        player.level()
                );
                shard.setOwner(player);

                // Random position around player (-1.5 to 1.5)
                double offsetX = (player.getRandom().nextDouble() - 0.5) * 3;
                double offsetZ = (player.getRandom().nextDouble() - 0.5) * 3;
                shard.setPos(
                        player.getX() + offsetX,
                        player.getY() + 0.5,
                        player.getZ() + offsetZ
                );

                double vx, vy, vz;

                // 50% chance to target nearest mob (or adjust percentage as needed)
                if (player.getRandom().nextDouble() < HIT_NEAREST_CHANCE) { // 50% chance
                    // Find nearest living entity (excluding the player)
                    LivingEntity target = player.level().getEntitiesOfClass(
                                    LivingEntity.class,
                                    player.getBoundingBox().inflate(10), // Search radius 10 blocks
                                    entity -> entity != player && entity.isAlive()
                            ).stream()
                            .min(java.util.Comparator.comparingDouble(entity -> entity.distanceToSqr(player)))
                            .orElse(null);

                    if (target != null) {
                        // Calculate direction to target
                        double dx = target.getX() - (player.getX() + offsetX);
                        double dy = target.getY() + target.getEyeHeight() - (player.getY() + 1.0);
                        double dz = target.getZ() - (player.getZ() + offsetZ);
                        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

                        // Normalize and add slight randomness
                        vx = dx / distance + (player.getRandom().nextDouble() - 0.5) * 0.2;
                        vy = dy / distance + (player.getRandom().nextDouble() - 0.5) * 0.2;
                        vz = dz / distance + (player.getRandom().nextDouble() - 0.5) * 0.2;

                        // Normalize again after adding randomness
                        double len = Math.sqrt(vx * vx + vy * vy + vz * vz);
                        vx /= len;
                        vy /= len;
                        vz /= len;
                    } else {
                        // No target found, use random direction
                        vx = (player.getRandom().nextDouble() - 0.5);
                        vy = 0.3 + (player.getRandom().nextDouble() * 0.5);
                        vz = (player.getRandom().nextDouble() - 0.5);
                    }
                } else {
                    // Random direction
                    vx = (player.getRandom().nextDouble() - 0.5);
                    vy = 0.3 + (player.getRandom().nextDouble() * 0.5);
                    vz = (player.getRandom().nextDouble() - 0.5);
                }

                shard.shoot(vx, vy, vz, 0.8f, 0.5f); // Higher velocity, lower inaccuracy when targeting
                player.level().addFreshEntity(shard);
                player.level().playSound(null,
                        new BlockPos((int) (player.getX() + offsetX),
                                (int) (player.getY() + 1.0),
                                (int) (player.getZ() + offsetZ)),
                        SoundEvents.FIRECHARGE_USE,
                        SoundSource.PLAYERS);
            }
        }
    }



    private static void onVolcanicEffectEnd(Player player) {
        Rupecs_Emblems.LOGGER.info("Volcanic effect ended for {}", player.getName().getString());
        stopVolcanicAction(player);
    }

    public static boolean isPlayerActive(Player player) {
        return activePlayers.containsKey(player.getUUID());
    }

    public static int getRemainingTicks(Player player) {
        return activePlayers.getOrDefault(player.getUUID(), 0);
    }
}