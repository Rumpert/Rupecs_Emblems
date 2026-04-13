package net.hubert.rupecs_emblems.util;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.CooldownSyncPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CooldownManager {
    // Map from player UUID to a mapping of cooldown IDs to their CooldownEntry.
    private static final Map<UUID, Map<String, CooldownEntry>> cooldowns = new HashMap<>();


    /**
     * Adds (or replaces) a cooldown for a player with the given id.
     *
     * @param playerUUID the player's UUID
     * @param cooldownId an identifier for the cooldown (ex: "return", "specialattack", etc.)
     * @param time       ticks (20 ticks ~= 1 second)
     * @param icon       a ResourceLocation pointing to a 16x16 texture icon
     */
    public static void addCooldown(UUID playerUUID, String cooldownId, int time, ResourceLocation icon) {
        // Only proceed if we're on server side
        Map<String, CooldownEntry> playerCooldowns = cooldowns.computeIfAbsent(playerUUID, k -> new HashMap<>());
        playerCooldowns.put(cooldownId, new CooldownEntry(time, icon));

        // Only try to sync if we have a server
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) {
            ServerPlayer player = server.getPlayerList().getPlayer(playerUUID);
            if (player != null) {
                syncCooldownsToClient(player);
            }
        }
    }

    /**
     * Returns all cooldown entries for the given player UUID.
     */
    public static Map<String, CooldownEntry> getCooldownEntries(UUID playerUUID) {
        return cooldowns.getOrDefault(playerUUID, new HashMap<>());
    }

    /**
     * Remove a specific cooldown by id for the player.
     */
    public static void removeCooldown(UUID playerUUID, String cooldownId) {
        Map<String, CooldownEntry> playerCooldowns = cooldowns.get(playerUUID);
        if (playerCooldowns != null) {
            playerCooldowns.remove(cooldownId);
            if (playerCooldowns.isEmpty()) {
                cooldowns.remove(playerUUID);
            }
        }
    }

    @SubscribeEvent
    public static void onTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.side.isServer()) {
            return;
        }
        for (UUID playerUUID : new ArrayList<>(cooldowns.keySet())) {
            Map<String, CooldownEntry> playerCooldowns = cooldowns.get(playerUUID);
            if (playerCooldowns == null) continue;
            for (String id : new ArrayList<>(playerCooldowns.keySet())) {
                CooldownEntry entry = playerCooldowns.get(id);
                if (entry == null) {
                    playerCooldowns.remove(id);
                    continue;
                }
                entry.decrement();

                if (entry.getTicks() <= 0) {
                    playerCooldowns.remove(id);
                }
            }
            if (playerCooldowns.isEmpty()) {
                cooldowns.remove(playerUUID);
            }
        }
    }

    public static void updateClientCooldowns(UUID playerUUID, Map<String, Integer> cooldownTicks, Map<String, ResourceLocation> icons) {
        Map<String, CooldownEntry> updated = new HashMap<>();
        for (String id : cooldownTicks.keySet()) {
            int ticks = cooldownTicks.get(id);
            ResourceLocation icon = icons.getOrDefault(id, new ResourceLocation("rupecs_emblems", "textures/gui/default_icon.png"));
            updated.put(id, new CooldownEntry(ticks, icon));
        }
        cooldowns.put(playerUUID, updated);
    }

    public static void syncCooldownsToClient(ServerPlayer player) {
        UUID uuid = player.getUUID();
        Map<String, CooldownEntry> entries = getCooldownEntries(uuid);
        Map<String, Integer> ticks = new HashMap<>();
        Map<String, ResourceLocation> icons = new HashMap<>();

        for (Map.Entry<String, CooldownEntry> entry : entries.entrySet()) {
            ticks.put(entry.getKey(), entry.getValue().getTicks());
            icons.put(entry.getKey(), entry.getValue().getIcon());
        }

        CooldownSyncPacket packet = new CooldownSyncPacket(uuid, ticks, icons);
        PacketHandler.sendToClient(packet, player);
    }


    public static void clear() {
        cooldowns.clear();
    }




}
