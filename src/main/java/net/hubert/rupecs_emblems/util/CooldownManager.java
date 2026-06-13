package net.hubert.rupecs_emblems.util;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.CooldownServerAddSyncPacket;
import net.hubert.rupecs_emblems.network.packet.CooldownSyncPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.*;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CooldownManager {
    public static final Map<UUID, Map<String, CooldownEntry>> cooldowns = new HashMap<>();

    // ========== API for abilities (unchanged signatures) ==========
    public static void addCooldown(UUID playerUUID, String cooldownId, int ticks, ResourceLocation icon) {
        if (ticks <= 0) return;  // ignore invalid cooldowns

        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        boolean isServer = (server != null);

        if (!isServer) {
            // Client: send request to server
            PacketHandler.sendToServer(new CooldownServerAddSyncPacket(cooldownId, ticks, icon));
        } else {
            // Server: apply cooldown (but don't overwrite an active one)
            Map<String, CooldownEntry> playerMap = cooldowns.computeIfAbsent(playerUUID, k -> new HashMap<>());
            CooldownEntry existing = playerMap.get(cooldownId);
            if (existing == null || existing.getTicks() <= 0) {
                playerMap.put(cooldownId, new CooldownEntry(ticks, icon));
                ServerPlayer player = server.getPlayerList().getPlayer(playerUUID);
                if (player != null) {
                    saveToCapability(player);
                    syncCooldownsToClient(player);
                }
            }
        }
    }

    public static Map<String, CooldownEntry> getCooldownEntries(UUID playerUUID) {
        return cooldowns.getOrDefault(playerUUID, Collections.emptyMap());
    }

    public static void removeCooldown(UUID playerUUID, String cooldownId) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        boolean isServer = (server != null);

        if (!isServer) {
            // Client: send request
            // Need icon – we can fetch from local map
            Map<String, CooldownEntry> map = cooldowns.get(playerUUID);
            ResourceLocation icon = (map != null && map.containsKey(cooldownId)) ? map.get(cooldownId).getIcon() : null;
            if (icon != null) {
                PacketHandler.sendToServer(new CooldownServerAddSyncPacket(cooldownId, 0, icon));
            }
        } else {
            // Server: remove locally
            Map<String, CooldownEntry> playerMap = cooldowns.get(playerUUID);
            if (playerMap != null) {
                playerMap.remove(cooldownId);
                if (playerMap.isEmpty()) {
                    cooldowns.remove(playerUUID);
                }
                ServerPlayer player = server.getPlayerList().getPlayer(playerUUID);
                if (player != null) {
                    saveToCapability(player);
                    syncCooldownsToClient(player);
                }
            }
        }
    }

    // ========== Ticking (server only) ==========
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        tickCooldowns();
    }

    private static void tickCooldowns() {
        boolean changed = false;
        Set<ServerPlayer> playersToSync = new HashSet<>();

        for (UUID playerUUID : new ArrayList<>(cooldowns.keySet())) {
            Map<String, CooldownEntry> playerMap = cooldowns.get(playerUUID);
            if (playerMap == null) continue;

            boolean playerChanged = false;
            for (String id : new ArrayList<>(playerMap.keySet())) {
                CooldownEntry entry = playerMap.get(id);
                if (entry == null) {
                    playerMap.remove(id);
                    playerChanged = true;
                    continue;
                }
                entry.decrement();
                playerChanged = true;
                if (entry.getTicks() <= 0) {
                    playerMap.remove(id);
                }
            }
            if (playerMap.isEmpty()) {
                cooldowns.remove(playerUUID);
                playerChanged = true;
            }
            if (playerChanged) {
                changed = true;
                MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
                if (server != null) {
                    ServerPlayer player = server.getPlayerList().getPlayer(playerUUID);
                    if (player != null) playersToSync.add(player);
                }
            }
        }

        if (changed) {
            for (ServerPlayer player : playersToSync) {
                saveToCapability(player);
                syncCooldownsToClient(player);
            }
        }
    }

    // ========== Persistence & Network ==========
    public static void saveToCapability(ServerPlayer player) {
        player.getCapability(CapabilityHandler.COOLDOWN_CAPABILITY).ifPresent(cap -> {
            Map<String, Integer> ticks = new HashMap<>();
            Map<String, ResourceLocation> icons = new HashMap<>();
            Map<String, CooldownEntry> entries = cooldowns.get(player.getUUID());
            if (entries != null) {
                for (Map.Entry<String, CooldownEntry> e : entries.entrySet()) {
                    ticks.put(e.getKey(), e.getValue().getTicks());
                    icons.put(e.getKey(), e.getValue().getIcon());
                }
            }
            cap.setAll(ticks, icons);
        });
    }

    public static void loadFromCapability(ServerPlayer player) {
        player.getCapability(CapabilityHandler.COOLDOWN_CAPABILITY).ifPresent(cap -> {
            Map<String, CooldownEntry> loaded = new HashMap<>();
            for (String id : cap.getAllTicks().keySet()) {
                loaded.put(id, new CooldownEntry(cap.getAllTicks().get(id), cap.getAllIcons().get(id)));
            }
            cooldowns.put(player.getUUID(), loaded);
            syncCooldownsToClient(player);
        });
    }

    public static void syncCooldownsToClient(ServerPlayer player) {
        UUID uuid = player.getUUID();
        Map<String, CooldownEntry> entries = getCooldownEntries(uuid);
        Map<String, Integer> ticks = new HashMap<>();
        Map<String, ResourceLocation> icons = new HashMap<>();
        for (Map.Entry<String, CooldownEntry> e : entries.entrySet()) {
            if (e.getValue().getTicks() > 0) {  // only send positive cooldowns
                ticks.put(e.getKey(), e.getValue().getTicks());
                icons.put(e.getKey(), e.getValue().getIcon());
            }
        }
        Rupecs_Emblems.LOGGER.info("Server sending cooldown sync to {}: {}", player.getName().getString(), ticks);
        PacketHandler.sendToClient(new CooldownSyncPacket(uuid, ticks, icons), player);
    }

    public static void updateClientCooldowns(UUID playerUUID, Map<String, Integer> ticks, Map<String, ResourceLocation> icons) {
        System.out.println("Client received cooldowns for " + playerUUID + ": " + ticks);
        Map<String, CooldownEntry> updated = new HashMap<>();
        for (String id : ticks.keySet()) {
            updated.put(id, new CooldownEntry(ticks.get(id), icons.get(id)));
        }
        if (updated.isEmpty()) {
            cooldowns.remove(playerUUID);
        } else {
            cooldowns.put(playerUUID, updated);
        }
    }

    public static void clear() {
        cooldowns.clear();
    }
}