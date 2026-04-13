package net.hubert.rupecs_emblems.datagen.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class FortuneFeverLootModifier extends LootModifier {

    public static final Codec<FortuneFeverLootModifier> CODEC = RecordCodecBuilder.create(
            instance -> codecStart(instance).apply(instance, FortuneFeverLootModifier::new)
    );

    public FortuneFeverLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!(context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player player)) {
            return generatedLoot;
        }

        BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);
        if (state == null || !(state.is(Tags.Blocks.ORES) || state.is(Blocks.ANCIENT_DEBRIS))) {
            return generatedLoot;
        }

        double value = player.getAttributeValue(ModAttributes.FORTUNE_FEVER.get());
        if (value <= 0) return generatedLoot;

        // --- determine multiplier ---
        int multiplier = 1;
        double chance = value * 0.3; // 30% per attribute level

        while (chance >= 1.0) {
            multiplier++;     // guaranteed extra tier
            chance -= 1.0;    // consume full 100%
        }

        // leftover chance for next tier
        if (player.level().random.nextFloat() < chance) {
            multiplier++;
        }

        // --- apply multiplier, skipping block itself ---
        ObjectArrayList<ItemStack> modified = new ObjectArrayList<>();

        for (ItemStack stack : generatedLoot) {
            // prevent multiplying the mined block itself
            ItemStack copy = stack.copy();
            copy.setCount(stack.getCount() * 4 * multiplier);
            modified.add(copy);
        }

        return modified;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
