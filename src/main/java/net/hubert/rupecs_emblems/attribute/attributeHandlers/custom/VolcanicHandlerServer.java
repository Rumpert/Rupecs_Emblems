package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.entity.ModEntities;
import net.hubert.rupecs_emblems.entity.custom.VolcanicShardProjectile;
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

    public static void startVolcanicAction(Player player) {
        if (player.level().isClientSide) return;

        int duration = (int) player.getAttributeValue(ModAttributes.VOLCANIC.get()) * 12; // 3 seconds
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
        if (remainingTicks > 0){
                VolcanicShardProjectile shard = new VolcanicShardProjectile(
                        ModEntities.VOLCANIC_SHARD.get(),
                        player.level()
                );
                shard.setOwner(player);

                // Fix: Use nextDouble() without bounds, then calculate range manually
                // Random position around player (-1.5 to 1.5)
                double offsetX = (player.getRandom().nextDouble() - 0.5) * 3;
                double offsetZ = (player.getRandom().nextDouble() - 0.5) * 3;
                shard.setPos(
                        player.getX() + offsetX,
                        player.getY() + 1.0,
                        player.getZ() + offsetZ
                );

                // Fix: Direction vectors with proper ranges
                // vx: -0.5 to 0.5
                double vx = (player.getRandom().nextDouble() - 0.5);
                // vy: 0.3 to 0.8
                double vy = 0.3 + (player.getRandom().nextDouble() * 0.5);
                // vz: -0.5 to 0.5
                double vz = (player.getRandom().nextDouble() - 0.5);

                shard.shoot(vx, vy, vz, 0.6f, 1.5f);
                player.level().addFreshEntity(shard);
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