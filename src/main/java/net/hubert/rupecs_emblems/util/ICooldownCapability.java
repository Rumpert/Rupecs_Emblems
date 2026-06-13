package net.hubert.rupecs_emblems.util;

import net.minecraft.resources.ResourceLocation;
import java.util.Map;

public interface ICooldownCapability {
    Map<String, CooldownEntry> getCooldowns();
    void setCooldown(String id, int ticks, ResourceLocation icon);
    void removeCooldown(String id);
    void clear();
    // For sync and NBT
    Map<String, Integer> getAllTicks();
    Map<String, ResourceLocation> getAllIcons();
    void setAll(Map<String, Integer> ticks, Map<String, ResourceLocation> icons);
}