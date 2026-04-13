package net.hubert.rupecs_emblems.datagen.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.HashMap;

public class LeafLuckLootModifier extends LootModifier {

    public static final Codec<LeafLuckLootModifier> CODEC = RecordCodecBuilder.create(
            instance -> codecStart(instance).apply(instance, LeafLuckLootModifier::new)
    );

    public LeafLuckLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    private static final Field LOOT_CONTEXT_PARAMS_FIELD;

    static {
        Field field = null;
        try {
            // Official Forge 1.20.1 mapped name: f_78914_
            // For other mappings (Mojmap, Yarn) the name may differ.
            field = LootContext.class.getDeclaredField("params");
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            // Fallback: try MCP name or other common names
            e.printStackTrace();

        }
        LOOT_CONTEXT_PARAMS_FIELD = field;
    }

    private static final ThreadLocal<Boolean> IN_EXTRA_ROLL = ThreadLocal.withInitial(() -> false);

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        // ✅ Guard against recursion
        if (IN_EXTRA_ROLL.get()) {
            return generatedLoot;
        }

        // --- your existing checks (Player, BlockState, etc.) ---
        if (!(context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player player))
            return generatedLoot;
        BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);
        if (state == null || !state.is(BlockTags.LEAVES))
            return generatedLoot;
        double value = player.getAttributeValue(ModAttributes.LEAF_LUCK.get());
        if (value <= 0)
            return generatedLoot;

        // --- multiplier logic (unchanged) ---
        int multiplier = (int) value * 3;

        if (multiplier <= 1)
            return generatedLoot;
        LootParams params = null;
        // --- get LootParams (reflection, if you still need it) ---
        try {
            params = (LootParams) LOOT_CONTEXT_PARAMS_FIELD.get(context);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if (params == null)
            return generatedLoot;

        ResourceLocation lootTableId = state.getBlock().getLootTable();
        LootTable lootTable = context.getResolver().getLootTable(lootTableId);

        ObjectArrayList<ItemStack> allLoot = new ObjectArrayList<>(generatedLoot);
        Block minedBlock = state.getBlock();

        // ✅ Set flag before generating extra rolls
        IN_EXTRA_ROLL.set(true);
        try {
            for (int i = 1; i < multiplier; i++) {
                ObjectArrayList<ItemStack> extraRoll = lootTable.getRandomItems(params);
                for (ItemStack stack : extraRoll) {
                    if (Block.byItem(stack.getItem()) != minedBlock) {
                        allLoot.add(stack);
                    }
                }
            }
        } finally {
            IN_EXTRA_ROLL.remove();  // Important: clear the flag
        }

        return allLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
