package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.server.command.ForgeCommand;

import java.util.function.Supplier;

public class TimeControlPacket {

    public TimeControlPacket() {

    }
    public TimeControlPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            var timeAttr = player.getAttribute(ModAttributes.TIME_CONTROL.get());
            if (timeAttr == null || timeAttr.getValue() <= 0) return;

            double timeControlLvl = timeAttr.getValue();
            long currentTime = player.serverLevel().getDayTime();
            long newTime = (long) (currentTime + timeControlLvl * 10); // Advance time

            player.serverLevel().setDayTime(newTime);
            player.serverLevel().getServer().getPlayerList().broadcastAll(
                    new ClientboundSetTimePacket(player.serverLevel().getGameTime(), newTime, true),
                    player.serverLevel().dimension()
            );


        });
        return true;
    }



}
