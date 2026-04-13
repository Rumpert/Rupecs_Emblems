package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

public class GamblingHandlerCommon {


    public static void gamble(Player player) {


        // Respect cooldown
        var gamblerAttr = player.getAttribute(ModAttributes.GAMBLER.get());
        if (gamblerAttr == null || gamblerAttr.getValue() <= 0) return;

        double luck = gamblerAttr.getValue();
        ItemStack itemGambled = player.getMainHandItem();

        if (itemGambled.isEmpty()) return;

        // Roll luck
        float bestRoll = 1f;
        Random random = new Random();
        for (int i = 0; i < (int) luck; i++) {
            float roll = random.nextFloat();
            bestRoll = Math.min(bestRoll, roll);
        }

        // Gamble outcome
        processGambleResult(player, itemGambled, bestRoll);

    }

    private static void processGambleResult(Player player, ItemStack itemStack, float luckRoll) {
        int count = itemStack.getCount();
        ItemStack item = itemStack.copy();

        if (luckRoll <= 0.01f) {
            item.grow(item.getCount()*2);
            for (int i = 0; i < item.getCount(); i++){
                ItemStack itemToAdd = item.copy();
                itemToAdd.setCount(1);
                player.level().addFreshEntity(new ItemEntity(player.level(), player.getX(), player.getY(), player.getZ(), itemToAdd));
            }
            sendMsg(player, "JACKPOT! 300% items!");
        } else if (luckRoll <= 0.1f) {
            item.grow(item.getCount());
            for (int i = 0; i < item.getCount(); i++){
                ItemStack itemToAdd = item.copy();
                itemToAdd.setCount(1);
                player.level().addFreshEntity(new ItemEntity(player.level(), player.getX(), player.getY(), player.getZ(), itemToAdd));
            }
            sendMsg(player, "Lucky! 200% items!");
        } else if (luckRoll <= 0.2f) {
            item.grow(Math.round(item.getCount()*0.5f));
            for (int i = 0; i < item.getCount(); i++){
                ItemStack itemToAdd = item.copy();
                itemToAdd.setCount(1);
                player.level().addFreshEntity(new ItemEntity(player.level(), player.getX(), player.getY(), player.getZ(), itemToAdd));
            }
            sendMsg(player, "Nice! 150% item!");
        } else if (luckRoll <= 0.3f) {
            player.getMainHandItem().shrink(Math.max(1,Math.round(item.getCount()*0.25f)));
            sendMsg(player, "Lost 25% items.");
        } else if (luckRoll <= 0.5f) {
            player.getMainHandItem().shrink(Math.max(1,Math.round(item.getCount()*0.5f)));
            sendMsg(player, "Lost 50% items.");
        } else {
            player.getMainHandItem().shrink(item.getCount());
            player.hurt(player.damageSources().generic(), 10.0F);
            sendMsg(player, "Lost 100% items & 10 damage.");
        }

    }

    private static void sendMsg(Player player, String msg) {
        if (!player.level().isClientSide) {
            player.displayClientMessage(Component.literal(msg), true);
        }
    }
}
