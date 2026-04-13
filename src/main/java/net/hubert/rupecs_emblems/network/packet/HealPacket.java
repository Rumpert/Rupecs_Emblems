package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.particle.ModParticles;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class HealPacket {
    private final float healAmount;
    private final int entityId;

    public HealPacket(float healAmount, LivingEntity entity) {
        this.healAmount = healAmount;
        this.entityId = entity.getId();
    }

    public HealPacket(FriendlyByteBuf buf) {
        this.healAmount = buf.readFloat();
        this.entityId = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(healAmount);
        buf.writeInt(entityId);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // This runs on the server side
            var sender = ctx.get().getSender();
            if (sender == null) return;

            ServerLevel level = sender.serverLevel();
            Entity entity = level.getEntity(entityId);
            if (entity instanceof LivingEntity living) {
                living.heal(healAmount);
                level.sendParticles(
                        ModParticles.GREEN_HEART_PARTICLES.get(),
                        entity.getX(), entity.getY() + 1, entity.getZ(),
                        10, 0.5, 1, 0.5, 0.01
                );
            }
        });
        ctx.get().setPacketHandled(true);
    }
}


