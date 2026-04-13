package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static net.hubert.rupecs_emblems.attribute.attributeHandlers.custom.MobHitHandler.zapAndBounce;

public class PulsePacket {
    public PulsePacket() {

    }
    public PulsePacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null)return;
            var pulseAttr = player.getAttribute(ModAttributes.PULSE.get());
            if (pulseAttr == null || pulseAttr.getValue() <= 0) return;
            for (LivingEntity entity : getNearbyLivingEntities(player, 5)){
                Vec3 direction = entity.position().subtract(player.position()).normalize();

                entity.setDeltaMovement(entity.getDeltaMovement().add(direction.scale(0.5 * pulseAttr.getValue())));
                zapAndBounce(player.level(), player, entity, 2, new ArrayList<>(), 5,5);

            }




        });
        return true;
    }
    public static List<LivingEntity> getNearbyLivingEntities(Player player, double range) {
        AABB area = new AABB(
                player.getX() - range, player.getY() - 2, player.getZ() - range,
                player.getX() + range, player.getY() + 3, player.getZ() + range
        );

        return player.level().getEntitiesOfClass(LivingEntity.class, area,
                mob -> mob.isAlive() && mob != player
        );
    }

}
