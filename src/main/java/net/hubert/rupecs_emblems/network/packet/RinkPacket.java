package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.network.PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class RinkPacket {
    private final int entityId;
    private boolean forceDisable = false;

    public RinkPacket(LivingEntity entity, boolean forceDisable) {
        this.entityId = entity.getId();
        this.forceDisable = forceDisable;
    }
    public RinkPacket(LivingEntity entity) {
        this.entityId = entity.getId();
    }

    public RinkPacket(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(entityId);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            var sender = ctx.get().getSender();
            if (sender == null) return;

            ServerLevel level = sender.serverLevel();
            Entity entity = level.getEntity(entityId);
            if (entity instanceof Player player) {
                // Toggle the tag
                boolean newState;
                if (player.getTags().contains("rink_active") || forceDisable) {
                    player.getTags().remove("rink_active");
                    newState = false;
                } else {
                    player.getTags().add("rink_active");
                    newState = true;
                }

                System.out.println("Server: Set rink_active to " + newState + " for player " + player.getName().getString());

                // Sync to ALL players (including the one who toggled)
                PacketHandler.INSTANCE.send(
                        PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
                        new RinkSyncPacket(player, newState)
                );
            }
        });
        ctx.get().setPacketHandled(true);
    }
}