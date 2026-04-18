package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.attribute.attributeHandlers.custom.VolcanicHandlerServer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class VolcanicPacket {
    public VolcanicPacket() {

    }
    public VolcanicPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null || player.getAttribute(ModAttributes.VOLCANIC.get()) == null) return;
            VolcanicHandlerServer.startVolcanicAction(player);




        });
        return true;
    }


}
