package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.attribute.attributeHandlers.custom.RandomHandlerCommon;
import net.hubert.rupecs_emblems.block.ModBlocks;
import net.hubert.rupecs_emblems.item.ModItems;
import net.hubert.rupecs_emblems.item.custom.ModEmblemItem;
import net.hubert.rupecs_emblems.util.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class RandomPacket {
    private final boolean isRemove;
    public RandomPacket() {
            isRemove = false;
    }
    public RandomPacket(boolean isRemove) {
        this.isRemove = isRemove;
    }
    public RandomPacket(FriendlyByteBuf buf) {

        this.isRemove = buf.readBoolean();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(isRemove);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;
            ServerLevel level = player.serverLevel();
            if (!isRemove) {
                if (player.getAttribute(ModAttributes.RANDOM.get()) != null && Objects.requireNonNull(player.getAttribute(ModAttributes.RANDOM.get())).getValue() > 0) {
                    int times = (int) Objects.requireNonNull(player.getAttribute(ModAttributes.RANDOM.get())).getValue();
                    List<ModEmblemItem> itemsToGive = new ArrayList<>();
                    for (int i = 0; i < times; i++) {
                        float randf = level.random.nextFloat();
                        TagKey<Item> key = getItemTagKey(randf);
                        ModEmblemItem item = (ModEmblemItem) getRandomItemFromTag(level, key);
                        while (item.getDefaultInstance().is(ModItems.RANDOM_EMBLEM.get())) {
                            item = (ModEmblemItem) getRandomItemFromTag(level, key);
                        }
                        itemsToGive.add(item);
                    }
                    RandomHandlerCommon.removeEmblemList(player);
                    RandomHandlerCommon.setEmblemList(itemsToGive, player);
                }
            } else {
                RandomHandlerCommon.shrinkEmblemList(player);
                System.out.println(player);
            }

        });
        return true;
    }

    private static TagKey<Item> getItemTagKey(float randf) {
        TagKey<Item> key;
        if (randf <= 0.01){
            key = ModTags.Items.S_TIER_EMBLEMS;
        } else if (randf <= 0.05){
            key = ModTags.Items.A_TIER_EMBLEMS;
        } else if (randf <= 0.1){
            key = ModTags.Items.B_TIER_EMBLEMS;
        } else if (randf <= 0.2){
            key = ModTags.Items.C_TIER_EMBLEMS;
        } else if (randf <= 0.4){
            key = ModTags.Items.D_TIER_EMBLEMS;
        } else if (randf <= 0.5){
            key = ModTags.Items.E_TIER_EMBLEMS;
        } else {
            key = ModTags.Items.F_TIER_EMBLEMS;
        }
        return key;
    }

    public static Item getRandomItemFromTag(Level level, TagKey<Item> tagKey) {
        HolderSet.Named<Item> holderSet = BuiltInRegistries.ITEM.getOrCreateTag(tagKey);
        Optional<Holder<Item>> randomHolder = holderSet.getRandomElement(level.random);
        return randomHolder.map(Holder::value).orElse(Items.AIR); // fallback if empty
    }
}
