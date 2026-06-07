package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class GamblingHandlerCommon {
    private static final Map<UUID, Integer> playerGambleTimes = new HashMap<>();
    private static boolean hasJustWon = false;


    public static void gamble(Player player) {
        if (!player.getMainHandItem().is(Items.AIR)) {
            if (playerGambleTimes.containsKey(player.getUUID())) {
                playerGambleTimes.put(player.getUUID(), playerGambleTimes.get(player.getUUID()) + 1);
            } else {
                playerGambleTimes.put(player.getUUID(), 1);
            }
            if (hasJustWon){
                playerGambleTimes.put(player.getUUID(), 1);
                hasJustWon = false;
            }
            if ((double) player.getMainHandItem().getCount() /player.getMainHandItem().getMaxStackSize() < 0.3){
                sendMsg(player, Component.literal("Must gamble at least "+(int)Math.ceil(0.3*player.getMainHandItem().getMaxStackSize())).withStyle(ChatFormatting.RED));
                return;
            }


            // Respect cooldown
            var gamblerAttr = player.getAttribute(ModAttributes.GAMBLER.get());
            if (gamblerAttr == null || gamblerAttr.getValue() <= 0) return;

            double luck = gamblerAttr.getValue();
            ItemStack itemGambled = player.getMainHandItem();

            if (itemGambled.isEmpty()) return;

            // Roll luck
            float bestRoll = 1f;
            Random random = new Random();
            for (int i = 0; i < (int) luck+0.5*(playerGambleTimes.get(player.getUUID())-1); i++) {
                float roll = random.nextFloat();
                bestRoll = Math.min(bestRoll, roll);
            }

            // Gamble outcome
            processGambleResult(player, itemGambled, bestRoll);
        }
    }private static void processGambleResult(Player player, ItemStack itemStack, float luckRoll) {
        float rollType = player.getRandom().nextFloat();
        boolean isWinning = luckRoll < 0.3f;
        float roll = player.getRandom().nextFloat();

        if (rollType < 0.8f) {
            // Percentage mode
            if (isWinning) {
                hasJustWon = true;
                int count = itemStack.getCount();

                if (roll <= 0.01f) {
                    giveItems(player, itemStack, count * 3);
                    sendMsg(player, "JACKPOT! +300% items!");
                } else if (roll <= 0.1f) {
                    giveItems(player, itemStack, count * 2);
                    sendMsg(player, "Lucky! +200% items!");
                } else if (roll <= 0.2f) {
                    giveItems(player, itemStack, count + Math.round(count * 0.5f));
                    sendMsg(player, "Nice! +150% items!");
                } else if (roll <= 0.3f) {
                    giveItems(player, itemStack, count);
                    sendMsg(player, "Alright! +100% items!");
                } else {
                    sendMsg(player, "Welp. -1 item.");
                    player.getMainHandItem().shrink(1);
                }
            } else {
                int count = itemStack.getCount();

                if (roll <= 0.5f) {
                    player.getMainHandItem().shrink(
                            Math.max(1, Math.round(count * 0.25f))
                    );
                    sendMsg(player, "Lost 25% items.");
                } else if (roll <= 0.6f) {
                    player.getMainHandItem().shrink(
                            Math.max(1, Math.round(count * 0.5f))
                    );
                    sendMsg(player, "Lost 50% items.");
                } else {
                    player.getMainHandItem().shrink(count);
                    sendMsg(player, "Lost 100% items.");
                }
            }
        } else {
            // Flat reward mode
            int maxStack = itemStack.getMaxStackSize();

            if (luckRoll < 0.2) {
                hasJustWon = true;
                if (roll <= 0.01f) {
                    giveItems(player, itemStack, maxStack * 2);
                    sendMsg(player, "JACKPOT! +" + (maxStack * 2) + " items!");
                } else if (roll <= 0.1f) {
                    giveItems(player, itemStack, maxStack);
                    sendMsg(player, "Lucky! +" + maxStack + " items!");
                } else if (roll <= 0.2f) {
                    int reward = Math.max(1, maxStack / 2);
                    giveItems(player, itemStack, reward);
                    sendMsg(player, "Nice! +" + reward + " items!");
                } else if (roll <= 0.3f) {
                    int reward = Math.max(1, maxStack / 4);
                    giveItems(player, itemStack, reward);
                    sendMsg(player, "Alright! +" + reward + " items!");
                } else {
                    sendMsg(player, "Welp. -1 item.");
                    player.getMainHandItem().shrink(1);
                }
            } else {
                if (roll <= 0.5f) {
                    int loss = Math.max(1, maxStack / 8);
                    player.getMainHandItem().shrink(loss);
                    sendMsg(player, "Lost " + loss + " items.");
                } else if (roll <= 0.6f) {
                    int loss = Math.max(1, maxStack / 4);
                    player.getMainHandItem().shrink(loss);
                    sendMsg(player, "Lost " + loss + " items.");
                } else {
                    int loss = Math.max(1, maxStack / 2);
                    player.getMainHandItem().shrink(loss);
                    sendMsg(player, "Lost " + loss + " items.");
                }
            }
        }
    }

    private static void giveItems(Player player, ItemStack original, int amount) {
        if (amount <= 0) return;

        int maxStack = original.getMaxStackSize();

        while (amount > 0) {
            int stackSize = Math.min(amount, maxStack);

            ItemStack reward = original.copy();
            reward.setCount(stackSize);

            player.level().addFreshEntity(
                    new ItemEntity(
                            player.level(),
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            reward
                    )
            );

            amount -= stackSize;
        }
    }

    private static void sendMsg(Player player, String msg) {
        if (!player.level().isClientSide) {
            player.displayClientMessage(Component.literal(msg), true);
        }
    }
    private static void sendMsg(Player player, Component msg) {
        if (!player.level().isClientSide) {
            player.displayClientMessage(msg, true);
        }
    }
}
