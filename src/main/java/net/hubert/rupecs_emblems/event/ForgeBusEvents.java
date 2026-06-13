package net.hubert.rupecs_emblems.event;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.util.*;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeBusEvents {

    @SubscribeEvent
    public static void attachPlayerCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(Rupecs_Emblems.MOD_ID, "cooldowns"), new CooldownProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            CooldownManager.loadFromCapability(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            CooldownManager.saveToCapability(player);
            CooldownManager.cooldowns.remove(player.getUUID());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.getEntity() instanceof ServerPlayer newPlayer) {
            // Save the current runtime cooldowns (static map) to the new player's capability
            // Do NOT load from capability – that would overwrite with stale data.
            CooldownManager.saveToCapability(newPlayer);
        }
    }

    // CooldownProvider must be a proper ICapabilitySerializable
    private static class CooldownProvider implements ICapabilitySerializable<CompoundTag> {
        private final ICooldownCapability instance = new CooldownCapability();
        private final LazyOptional<ICooldownCapability> holder = LazyOptional.of(() -> instance);

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            return CapabilityHandler.COOLDOWN_CAPABILITY.orEmpty(cap, holder);
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            CompoundTag ticks = new CompoundTag();
            CompoundTag icons = new CompoundTag();
            for (Map.Entry<String, CooldownEntry> entry : instance.getCooldowns().entrySet()) {
                ticks.putInt(entry.getKey(), entry.getValue().getTicks());
                icons.putString(entry.getKey(), entry.getValue().getIcon().toString());
            }
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

}