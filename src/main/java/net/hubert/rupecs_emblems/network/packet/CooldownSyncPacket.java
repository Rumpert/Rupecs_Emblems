package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class CooldownSyncPacket {
    private final UUID playerUUID;
    private final Map<String, Integer> cooldownTicks;
    private final Map<String, ResourceLocation> icons;

    public CooldownSyncPacket(UUID playerUUID, Map<String, Integer> cooldownTicks, Map<String, ResourceLocation> icons) {
        this.playerUUID = playerUUID;
        this.cooldownTicks = cooldownTicks;
        this.icons = icons;
    }

    public CooldownSyncPacket(FriendlyByteBuf buf) {
        this.playerUUID = buf.readUUID();
        this.cooldownTicks = buf.readMap(FriendlyByteBuf::readUtf, FriendlyByteBuf::readVarInt);
        this.icons = buf.readMap(FriendlyByteBuf::readUtf, FriendlyByteBuf::readResourceLocation);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUUID(playerUUID);
        buf.writeMap(cooldownTicks, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeVarInt);
        buf.writeMap(icons, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeResourceLocation);
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // Handle the sync on client side
            CooldownManager.updateClientCooldowns(playerUUID, cooldownTicks, icons);
        });
        context.setPacketHandled(true);
    }
}
