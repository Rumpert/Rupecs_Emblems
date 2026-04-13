package net.hubert.rupecs_emblems.datagen.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class RichHarvestLootModifier extends LootModifier {

    public static final Codec<RichHarvestLootModifier> CODEC = RecordCodecBuilder.create(
            instance -> codecStart(instance).apply(instance, RichHarvestLootModifier::new)
    );

    public RichHarvestLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        if (!(context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player player))
            return generatedLoot;

        BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);
        if (state == null || !(state.is(BlockTags.CROPS))) {
            return generatedLoot;
        }

        double value = player.getAttributeValue(ModAttributes.RICH_SOIL.get());
        if (value <= 0)
            return generatedLoot;

        int multiplier = 1;
        double chance = value * 0.3;

        while (chance >= 1.0) {
            multiplier++;
            chance -= 1.0;
        }

        if (player.level().random.nextFloat() < chance)
            multiplier++;

        ObjectArrayList<ItemStack> modified = new ObjectArrayList<>();
        Block minedBlock = state.getBlock();

        for (ItemStack stack : generatedLoot) {

            if (!(minedBlock instanceof CropBlock cropBlock &&
                    cropBlock.isMaxAge(state))) {
                modified.add(stack);

                continue;
            }


            ItemStack copy = stack.copy();
            copy.setCount(Math.min(stack.getCount() * multiplier, stack.getMaxStackSize()));
            modified.add(copy);
        }

        return modified;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
