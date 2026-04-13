package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.block.ModBlocks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PoopPacket {
    public PoopPacket() {

    }
    public PoopPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;
            ServerLevel level = player.serverLevel();
            level.setBlockAndUpdate(player.blockPosition().below(), ModBlocks.POOP_BLOCK.get().defaultBlockState());

        });
        return true;
    }


}
