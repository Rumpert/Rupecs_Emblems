package net.hubert.rupecs_emblems.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FoolPacket {
    public FoolPacket() {

    }
    public FoolPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            assert player != null;
            if (getTotalXp(player) >= 100 && player.getRandom().nextFloat() < 0.6) {
                player.giveExperiencePoints(-100);
                ServerLevel level = player.serverLevel();
                if (player.getRespawnPosition() != null && level.getBlockState(player.getRespawnPosition()).is(BlockTags.BEDS)) {
                    player.teleportTo(player.getRespawnPosition().getCenter().x, player.getRespawnPosition().getCenter().y, player.getRespawnPosition().getCenter().z);

                } else {
                    player.teleportTo(level.getSharedSpawnPos().getCenter().x, level.getSharedSpawnPos().getCenter().y + 1, level.getSharedSpawnPos().getCenter().z);

                }
            }




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
