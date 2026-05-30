package net.hubert.rupecs_emblems.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
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
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                RinkSyncPacketClient.handle(entityId, rinkActive);
            });
        });

        ctx.get().setPacketHandled(true);
    }
}