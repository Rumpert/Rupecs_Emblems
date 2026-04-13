package net.hubert.rupecs_emblems.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import net.hubert.rupecs_emblems.network.PacketHandler;

import java.util.function.Supplier;

public class RinkSyncPacket {
    private final int entityId;
    private final boolean rinkActive;

    public RinkSyncPacket(LivingEntity entity, boolean rinkActive) {
        this.entityId = entity.getId();
        this.rinkActive = rinkActive;
    }

    public RinkSyncPacket(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
        this.rinkActive = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeBoolean(rinkActive);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // This runs on the CLIENT side
            var player = ctx.get().getSender(); // This will be null on client
            Level level = net.minecraft.client.Minecraft.getInstance().level;
            if (level == null) return;

            Entity entity = level.getEntity(entityId);
            if (entity instanceof Player targetPlayer) {
                if (rinkActive) {
                    targetPlayer.getTags().add("rink_active");
                } else {
                    targetPlayer.getTags().remove("rink_active");
                }
                System.out.println("Client: Set rink_active to " + rinkActive + " for player " + targetPlayer.getName().getString());
            }
        });
        ctx.get().setPacketHandled(true);
    }
}