package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CooldownServerAddSyncPacket {
    private final String cooldownId;
    private final int ticks;
    private final ResourceLocation icon;

    // Client constructor – send only what's needed
    public CooldownServerAddSyncPacket(String cooldownId, int ticks, ResourceLocation icon) {
        this.cooldownId = cooldownId;
        this.ticks = ticks;
        this.icon = icon;
    }

    // Decode from network
    public CooldownServerAddSyncPacket(FriendlyByteBuf buf) {
        this.cooldownId = buf.readUtf();
        this.ticks = buf.readInt();
        this.icon = new ResourceLocation(buf.readUtf());
    }

    // Encode to network
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(cooldownId);
        buf.writeInt(ticks);
        buf.writeUtf(icon.toString());
    }

    // Server-side handler
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                // Add the cooldown on the server (saves to capability and syncs back to all clients)
                if (ticks > 0) {
                    CooldownManager.addCooldown(player.getUUID(), cooldownId, ticks, icon);
                }
                else if (CooldownManager.getCooldownEntries(player.getUUID()).containsKey(cooldownId)){
                    CooldownManager.removeCooldown(player.getUUID(), cooldownId);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}