package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.attribute.attributeHandlers.custom.VanishHandlerCommon;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class VanishTickPacket {
    private final int entityId;
    public VanishTickPacket(int entityId) {
        this.entityId = entityId;
    }
    public VanishTickPacket(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(entityId);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;
            ServerLevel serverLevel = context.getSender().serverLevel();
            if (serverLevel.getEntity(entityId) instanceof LivingEntity livingEntity) {

                VanishHandlerCommon.tick(livingEntity);
            }

        });
        return true;
    }


}
