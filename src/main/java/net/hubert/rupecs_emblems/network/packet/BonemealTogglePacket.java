package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.attribute.attributeHandlers.custom.MotherEarthBlessingHandler;
import net.hubert.rupecs_emblems.entity.ModEntities;
import net.hubert.rupecs_emblems.entity.custom.TameableBee;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BonemealTogglePacket {
    private final boolean value;
    public BonemealTogglePacket(boolean value) {
        this.value = value;
    }
    public BonemealTogglePacket(FriendlyByteBuf buf) {
        this.value = buf.readBoolean();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(value);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            MotherEarthBlessingHandler.setBonemealToggled(value);


        });
        return true;
    }


}
