package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.attribute.attributeHandlers.custom.MoonWrathHandlerServer;
import net.hubert.rupecs_emblems.command.gamerule.ModGameRules;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MoonWrathPacket {
    public MoonWrathPacket() {

    }
    public MoonWrathPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null || player.getAttribute(ModAttributes.MOON_WRATH.get()) == null) return;
            MoonWrathHandlerServer.summonMoonAsteroids(player, player.level().getGameRules().getRule(ModGameRules.MOON_ASTEROID_COUNT).get() * player.getAttribute(ModAttributes.MOON_WRATH.get()).getValue());




        });
        return true;
    }


}
