package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EmperorPacket {
    private final BlockPos pos;
    public EmperorPacket(BlockPos pPos) {
        this.pos = pPos;
    }
    public EmperorPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            assert player != null;
            player.teleportTo(pos.getX(), pos.getY()+1, pos.getZ());




        });
        return true;
    }

    public static int getTotalXp(Player player) {
        int level = player.experienceLevel;
        int total;

        if (level <= 16) {
            total = level * level + 6 * level;
        } else if (level <= 31) {
            total = (int)(2.5 * level * level - 40.5 * level + 360);
        } else {
            total = (int)(4.5 * level * level - 162.5 * level + 2220);
        }

        total += Math.round(player.experienceProgress * player.getXpNeededForNextLevel());

        return total;
    }
}
