package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class FloristPacket {

    public FloristPacket() {

    }
    public FloristPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            var timeAttr = player.getAttribute(ModAttributes.FLORIST.get());
            if (timeAttr == null || timeAttr.getValue() <= 0) return;

            double timeControlLvl = timeAttr.getValue();List<Item> flowerItems = ForgeRegistries.ITEMS.getEntries().stream()
                    .map(Map.Entry::getValue)
                    .filter(item -> item != null && item.getDefaultInstance().is(ItemTags.FLOWERS))
                    .toList();

            if (flowerItems.isEmpty()) return;

            RandomSource random = player.getRandom();

            for (int i = 0; i < timeControlLvl*5; i++) {
                Item randomFlower = flowerItems.get(random.nextInt(flowerItems.size()));
                while (randomFlower == Items.WITHER_ROSE){
                    randomFlower = flowerItems.get(random.nextInt(flowerItems.size()));
                }
                ItemStack stack = new ItemStack(randomFlower);

                ItemEntity itemEntity = new ItemEntity(
                        player.serverLevel(),
                        player.getX(),
                        player.getY() + 1,
                        player.getZ(),
                        stack
                );

                // Optionally give it a small motion
                itemEntity.setDeltaMovement(
                        (random.nextDouble() - 0.5) * 0.2,
                        0.2,
                        (random.nextDouble() - 0.5) * 0.2
                );

                player.serverLevel().addFreshEntity(itemEntity);
            }




        });
        return true;
    }



}
