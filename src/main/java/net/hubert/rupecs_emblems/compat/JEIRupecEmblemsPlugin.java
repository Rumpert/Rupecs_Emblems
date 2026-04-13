package net.hubert.rupecs_emblems.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.recipe.EntherealSelectorRecipe;
import net.hubert.rupecs_emblems.recipe.RealityManipulatorRecipe;
import net.hubert.rupecs_emblems.screen.EntherealSelectorScreen;
import net.hubert.rupecs_emblems.screen.RealityManipulatorScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIRupecEmblemsPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Rupecs_Emblems.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new RealityManipulationCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new EntherealSelectionCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(RealityManipulatorScreen.class, 0,0, 70, 8,
                RealityManipulationCategory.REALITY_MANIPULATOR_TYPE);
        registration.addRecipeClickArea(EntherealSelectorScreen.class, 0,0, 70, 8,
                EntherealSelectionCategory.ENTHEREAL_SELECTOR_TYPE);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<RealityManipulatorRecipe> realityManipulatorRecipes = recipeManager.getAllRecipesFor(RealityManipulatorRecipe.Type.INSTANCE);
        registration.addRecipes(RealityManipulationCategory.REALITY_MANIPULATOR_TYPE, realityManipulatorRecipes);
        List<EntherealSelectorRecipe> entherealSelectionRecipes = recipeManager.getAllRecipesFor(EntherealSelectorRecipe.Type.INSTANCE);
        registration.addRecipes(EntherealSelectionCategory.ENTHEREAL_SELECTOR_TYPE, entherealSelectionRecipes);


    }
}
