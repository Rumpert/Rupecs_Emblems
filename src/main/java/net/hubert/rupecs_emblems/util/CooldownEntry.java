package net.hubert.rupecs_emblems.util;

import net.minecraft.resources.ResourceLocation;

public class CooldownEntry {
    private int ticks;
    private final ResourceLocation icon;

    public CooldownEntry(int ticks, ResourceLocation icon) {
        this.ticks = ticks;
        this.icon = icon;
    }

    public int getTicks() {
        return ticks;
    }

    public void decrement() {
        if (ticks > 0)
            ticks--;
    }

    public ResourceLocation getIcon() {
        return icon;
    }
}
