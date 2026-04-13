package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.entity.ModEntities;
import net.hubert.rupecs_emblems.entity.custom.TameableBee;
import net.hubert.rupecs_emblems.entity.custom.TameablePufferfish;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BeeSummonPacket {
    public BeeSummonPacket() {

    }
    public BeeSummonPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;
            ServerLevel level = player.serverLevel();
            TameableBee bee = new TameableBee(ModEntities.TAMEABLE_BEE.get(), level);
            bee.moveTo(player.position());
            bee.setOwner(player);
            level.addFreshEntity(bee);

        });
        return true;
    }


}
