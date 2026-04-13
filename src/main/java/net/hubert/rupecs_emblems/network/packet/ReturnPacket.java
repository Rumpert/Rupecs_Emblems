package net.hubert.rupecs_emblems.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ReturnPacket {
    public ReturnPacket() {

    }
    public ReturnPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel();
            if (player.getRespawnPosition() != null && level.getBlockState(player.getRespawnPosition()).is(BlockTags.BEDS)) {
                player.teleportTo(player.getRespawnPosition().getCenter().x,player.getRespawnPosition().getCenter().y,player.getRespawnPosition().getCenter().z);

            } else {
                player.teleportTo(level.getSharedSpawnPos().getCenter().x,level.getSharedSpawnPos().getCenter().y + 1,level.getSharedSpawnPos().getCenter().z);

            }




        });
        return true;
    }


}
