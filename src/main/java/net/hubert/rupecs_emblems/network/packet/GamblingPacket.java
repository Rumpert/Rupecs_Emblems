package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.attribute.attributeHandlers.custom.GamblingHandlerCommon;
import net.hubert.rupecs_emblems.block.ModBlocks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class GamblingPacket {
    public GamblingPacket() {

    }
    public GamblingPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;
            GamblingHandlerCommon.gamble(player);
        });
        return true;
    }


}
