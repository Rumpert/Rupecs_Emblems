package net.hubert.rupecs_emblems.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.block.ModBlocks;
import net.hubert.rupecs_emblems.recipe.EntherealSelectorRecipe;
import net.hubert.rupecs_emblems.recipe.IngredientWithNbt;
import net.hubert.rupecs_emblems.recipe.NbtCondition;
import net.hubert.rupecs_emblems.recipe.RealityManipulatorRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EntherealSelectionCategory implements IRecipeCategory<EntherealSelectorRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Rupecs_Emblems.MOD_ID, "enthereal_selection");
    public static final ResourceLocation TEXTURE = new ResourceLocation(Rupecs_Emblems.MOD_ID,
            "textures/gui/enthereal_selector_gui.png");

    public static final RecipeType<EntherealSelectorRecipe> ENTHEREAL_SELECTOR_TYPE =
            new RecipeType<>(UID, EntherealSelectorRecipe.class);


    private final IDrawable background;
    private final IDrawable icon;

    public EntherealSelectionCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 5, 5, 166, 75);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ENTHEREAL_SELECTOR.get()));
    }


    @Override
    public RecipeType<EntherealSelectorRecipe> getRecipeType() {
        return ENTHEREAL_SELECTOR_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.rupecs_emblems.enthereal_selector");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }
    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, EntherealSelectorRecipe recipe, IFocusGroup focuses) {
        List<IngredientWithNbt> ingredients = recipe.getIngredientsWithNbt();

        // Create display stacks for first ingredient (slot 1)
        List<ItemStack> displayStacks1 = createDisplayStacks(ingredients.get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 28, 19)
                .addItemStacks(displayStacks1);

        // Create display stacks for second ingredient (slot 2)
        List<ItemStack> displayStacks2 = createDisplayStacks(ingredients.get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 28, 39)
                .addItemStacks(displayStacks2);

        // Output slot
        builder.addSlot(RecipeIngredientRole.OUTPUT, 118, 28)
                .addItemStack(recipe.getResultItem(null));
    }

    private List<ItemStack> createDisplayStacks(IngredientWithNbt ingredient) {
        ItemStack[] originalStacks = ingredient.getIngredient().getItems();
        List<ItemStack> displayStacks = new ArrayList<>();

        for (ItemStack stack : originalStacks) {
            ItemStack displayStack = stack.copy();
            displayStack.setCount(ingredient.getCount());

            // Apply NBT conditions to the display stack
            List<NbtCondition> conditions = ingredient.getNbtConditions();
            if (conditions != null && !conditions.isEmpty()) {
                for (NbtCondition condition : conditions) {
                    switch (condition.getType()) {
                        case INT_EQUAL:
                            displayStack.getOrCreateTag().putInt(condition.getKey(), condition.getIntValue());
                            break;
                        case INT_LOWER:
                            // Show a value lower than the requirement
                            displayStack.getOrCreateTag().putInt(condition.getKey(), condition.getIntValue() - 1);
                            break;
                        case INT_HIGHER:
                            // Show a value higher than the requirement
                            displayStack.getOrCreateTag().putInt(condition.getKey(), condition.getIntValue() + 1);
                            break;
                        case TAG_EXISTS:
                            if (condition.isExists()) {
                                // Tag must exist - add it
                                displayStack.getOrCreateTag().putBoolean(condition.getKey(), true);
                            } else {
                                // Tag must NOT exist - mark for special rendering
                                displayStack.getOrCreateTag().putBoolean("jei_missing_tag", true);
                            }
                            break;
                    }
                }
            }

            displayStacks.add(displayStack);
        }

        return displayStacks;
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return this.background;
    }

}
