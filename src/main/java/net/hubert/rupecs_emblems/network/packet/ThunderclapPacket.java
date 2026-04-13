package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.attribute.attributeHandlers.custom.VanishHandlerCommon;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ThunderclapPacket {

    public ThunderclapPacket() {

    }
    public ThunderclapPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null || !(player.getAttribute(ModAttributes.THUNDERCLAP.get()) != null && player.getAttribute(ModAttributes.THUNDERCLAP.get()).getValue() > 0)) return;
            double thunderclapLvl = player.getAttribute(ModAttributes.THUNDERCLAP.get()).getValue();
            Vec3 direction = player.getLookAngle();
            Vec3 start = player.position();

            for (int i = 1; i < 4 + thunderclapLvl; i++) {
                LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(player.level());
                if (lightningBolt != null) {
                    Vec3 strikePos = start.add(direction.scale(i * 2));
                    lightningBolt.moveTo(strikePos);
                    lightningBolt.setCause(player);

                    player.level().addFreshEntity(lightningBolt);
                }
            }


        });
        return true;
    }


}
