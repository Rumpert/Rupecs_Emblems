package net.hubert.rupecs_emblems.util;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
// Client-side only – no @Mod.EventBusSubscriber needed
public class ClientCooldownData {
    // Map from player UUID to cooldowns (usually only the local player)
    private static final Map<UUID, Map<String, CooldownEntry>> clientCooldowns = new HashMap<>();

    public static void setCooldowns(UUID playerUUID, Map<String, Integer> ticks, Map<String, ResourceLocation> icons) {
        Map<String, CooldownEntry> entries = new HashMap<>();
        for (String id : ticks.keySet()) {
            entries.put(id, new CooldownEntry(ticks.get(id), icons.get(id)));
        }
        clientCooldowns.put(playerUUID, entries);
    }

    public static Map<String, CooldownEntry> getCooldownsForPlayer(UUID playerUUID) {
        return clientCooldowns.getOrDefault(playerUUID, Collections.emptyMap());
    }

    public static Map<String, CooldownEntry> getLocalPlayerCooldowns() {
        UUID localUUID = Minecraft.getInstance().player != null ? Minecraft.getInstance().player.getUUID() : null;
        return localUUID != null ? getCooldownsForPlayer(localUUID) : Collections.emptyMap();
    }

    public static void clear() {
        clientCooldowns.clear();
    }

}