package net.hubert.rupecs_emblems.util;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class CooldownCapability implements ICooldownCapability {
    private final Map<String, CooldownEntry> cooldowns = new HashMap<>();

    @Override
    public Map<String, CooldownEntry> getCooldowns() { return cooldowns; }

    @Override
    public void setCooldown(String id, int ticks, ResourceLocation icon) {
        cooldowns.put(id, new CooldownEntry(ticks, icon));
    }

    @Override
    public void removeCooldown(String id) { cooldowns.remove(id); }

    @Override
    public void clear() { cooldowns.clear(); }

    @Override
    public Map<String, Integer> getAllTicks() {
        Map<String, Integer> map = new HashMap<>();
        cooldowns.forEach((id, entry) -> map.put(id, entry.getTicks()));
        return map;
    }

    @Override
    public Map<String, ResourceLocation> getAllIcons() {
        Map<String, ResourceLocation> map = new HashMap<>();
        cooldowns.forEach((id, entry) -> map.put(id, entry.getIcon()));
        return map;
    }

    @Override
    public void setAll(Map<String, Integer> ticks, Map<String, ResourceLocation> icons) {
        cooldowns.clear();
        for (String id : ticks.keySet()) {
            cooldowns.put(id, new CooldownEntry(ticks.get(id), icons.get(id)));
        }
    }
}