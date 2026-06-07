package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.network.NetworkEvent;

import java.util.Random;
import java.util.function.Supplier;

public class MagicianPacket {
    public MagicianPacket() {

    }
    public MagicianPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            assert player != null;
            if (getTotalXp(player) >= 200) {
                ItemStack item = player.getMainHandItem();

                // Check if item exists and doesn't already have enchantments
                if (!item.isEmpty() && !item.isEnchanted()) {

                    // Get all possible enchantments for this item
                    var enchantments = net.minecraft.world.item.enchantment.EnchantmentHelper
                            .getAvailableEnchantmentResults(30, item.getItem().getDefaultInstance(), false);
                    System.out.println(enchantments);
                    if (!enchantments.isEmpty()) {

                        player.giveExperiencePoints(-200);
                        // Pick a random enchantment
                        Random random = new Random();
                        var randomEnchantment = enchantments.get(random.nextInt(enchantments.size()));
                        int level = (int) Math.min(Math.ceil(player.getAttributeValue(ModAttributes.THE_MAGICIAN.get())*random.nextFloat()),(randomEnchantment.enchantment.getMaxLevel())); // or random between min and max

                        // Apply the enchantment
                        item.enchant(randomEnchantment.enchantment, level);
                    }
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
