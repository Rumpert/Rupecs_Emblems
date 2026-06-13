package net.hubert.rupecs_emblems.util;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import java.util.HashMap;
import java.util.Map;

public class CooldownProvider implements ICapabilitySerializable<CompoundTag> {
    private final ICooldownCapability instance = new CooldownCapability();
    private final LazyOptional<ICooldownCapability> holder = LazyOptional.of(() -> instance);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return CapabilityHandler.COOLDOWN_CAPABILITY.orEmpty(cap, holder);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        CompoundTag ticks = new CompoundTag();
        CompoundTag icons = new CompoundTag();
        instance.getCooldowns().forEach((id, entry) -> {
            ticks.putInt(id, entry.getTicks());
            icons.putString(id, entry.getIcon().toString());
        });
        tag.put("ticks", ticks);
        tag.put("icons", icons);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        CompoundTag ticksTag = tag.getCompound("ticks");
        CompoundTag iconsTag = tag.getCompound("icons");
        Map<String, Integer> ticks = new HashMap<>();
        Map<String, ResourceLocation> icons = new HashMap<>();
        for (String key : ticksTag.getAllKeys()) {
            ticks.put(key, ticksTag.getInt(key));
            icons.put(key, new ResourceLocation(iconsTag.getString(key)));
        }
        instance.setAll(ticks, icons);
    }
}