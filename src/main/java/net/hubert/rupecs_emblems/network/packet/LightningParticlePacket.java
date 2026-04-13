package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.attribute.attributeHandlers.custom.MobHitHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class LightningParticlePacket {
    private final int sourceID;
    private final int targetID;

    public LightningParticlePacket(int sourceID, int targetID) {
        this.sourceID = sourceID;
        this.targetID = targetID;
    }

    public LightningParticlePacket(FriendlyByteBuf buf) {
        this.sourceID = buf.readInt();
        this.targetID = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(sourceID);
        buf.writeInt(targetID);
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // This code runs on the client side.

            Level level = context.getSender().level();
            // Find the entities by UUID.
            Entity sourceEntity = level.getEntity(this.sourceID);
            Entity targetEntity = level.getEntity(this.targetID);
            if (sourceEntity instanceof LivingEntity source && targetEntity instanceof LivingEntity target) {
                // Put the resolved entities into the shared map.
                MobHitHandler.getLightningParticleSourceTargets().put(source, target);
            }
        });
        context.setPacketHandled(true);
    }

}
