package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.effect.ModEffects;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PriestessPacket {
    public PriestessPacket() {

    }
    public PriestessPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            assert player != null;
            player.addEffect(new MobEffectInstance(ModEffects.IMMORTALITY.get(), (int) Math.min(100*player.getAttributeValue(ModAttributes.THE_HIGH_PRIESTESS.get()),300), 0));




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
