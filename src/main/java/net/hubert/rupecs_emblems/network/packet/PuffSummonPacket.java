package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.block.ModBlocks;
import net.hubert.rupecs_emblems.entity.ModEntities;
import net.hubert.rupecs_emblems.entity.custom.TameablePufferfish;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PuffSummonPacket {
    public PuffSummonPacket() {

    }
    public PuffSummonPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;
            ServerLevel level = player.serverLevel();
            TameablePufferfish puff = new TameablePufferfish(ModEntities.TAMEABLE_PUFFERFISH.get(), level);
            puff.moveTo(player.position());
            puff.setOwner(player);
            level.addFreshEntity(puff);

        });
        return true;
    }


}
