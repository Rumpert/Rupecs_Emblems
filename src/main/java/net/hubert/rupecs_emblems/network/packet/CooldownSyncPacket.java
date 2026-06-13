package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

// net.hubert.rupecs_emblems.network.packet.CooldownSyncPacket
public class CooldownSyncPacket {
    private final UUID playerUUID;
    private final Map<String, Integer> ticks;
    private final Map<String, ResourceLocation> icons;

    public CooldownSyncPacket(UUID playerUUID, Map<String, Integer> ticks, Map<String, ResourceLocation> icons) {
        this.playerUUID = playerUUID;
        this.ticks = ticks;
        this.icons = icons;
    }

    public CooldownSyncPacket(FriendlyByteBuf buf) {
        playerUUID = buf.readUUID();
        int size = buf.readInt();
        ticks = new HashMap<>();
        icons = new HashMap<>();
        for (int i = 0; i < size; i++) {
            String id = buf.readUtf();
            int tick = buf.readInt();
            ResourceLocation icon = new ResourceLocation(buf.readUtf());
            ticks.put(id, tick);
            icons.put(id, icon);
        }
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUUID(playerUUID);
        buf.writeInt(ticks.size());
        for (Map.Entry<String, Integer> entry : ticks.entrySet()) {
            buf.writeUtf(entry.getKey());
            buf.writeInt(entry.getValue());
            buf.writeUtf(icons.get(entry.getKey()).toString());
        }
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Client side: update local cooldown cache
            CooldownManager.updateClientCooldowns(playerUUID, ticks, icons);
        });
        ctx.get().setPacketHandled(true);
    }
}