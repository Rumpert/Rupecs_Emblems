package net.hubert.rupecs_emblems.datagen.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BurnerLootModifier extends LootModifier {

    public BurnerLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }
    public static final Codec<BurnerLootModifier> CODEC = RecordCodecBuilder.create(
            instance -> codecStart(instance).apply(instance, BurnerLootModifier::new)
    );
    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!(context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player player)) {
            return generatedLoot;
        }

        var state = context.getParamOrNull(LootContextParams.BLOCK_STATE);

        // If not an ore, check burning attribute
        if (state == null || !state.is(Tags.Blocks.ORES)) {
            double burnValue = player.getAttributeValue(ModAttributes.BLOCK_BURNING.get());
            if (burnValue <= 0) return generatedLoot;

            ObjectArrayList<ItemStack> modified = new ObjectArrayList<>();
            for (ItemStack item : generatedLoot) {
                if (isSmeltable(player.level(), item)) {
                    ItemStack itemToAdd = getSmelt(player.level(), new SimpleContainer(item));
                    itemToAdd.setCount(item.getCount());
                    modified.add(itemToAdd);

                    // Fire chance could scale with attribute value if you want
                    if (player.getRandom().nextFloat() < 0.06 * burnValue) {
                        player.setSecondsOnFire(4);
                    }
                } else {
                    modified.add(item); // keep non-smeltable items
                }
            }
            return modified;
        }

        // Default: just return original loot
        return generatedLoot;
    }

    public static boolean isSmeltable(Level level, ItemStack stack) {
            if (stack.isEmpty()) return false;

            RecipeManager manager = level.getRecipeManager();
            return manager.getRecipesFor(RecipeType.SMELTING, new SimpleContainer(stack), level)
                    .stream()
                    .anyMatch(r -> r.getIngredients().get(0).test(stack));
        }

        public static ItemStack getSmelt(Level level, Container container) {
            SmeltingRecipe recipe = level.getRecipeManager()
                    .getRecipeFor(RecipeType.SMELTING, container, level)
                    .orElse(null);

            if (recipe != null) {
                return recipe.getResultItem(RegistryAccess.EMPTY);
            }
            return ItemStack.EMPTY;
        }
    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
