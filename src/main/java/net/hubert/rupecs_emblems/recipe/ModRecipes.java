package net.hubert.rupecs_emblems.recipe;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Rupecs_Emblems.MOD_ID);

    public static final RegistryObject<RecipeSerializer<RealityManipulatorRecipe>> REALITY_MANIPULATION_SERIALIZER =
            SERIALIZERS.register("reality_manipulation", () -> RealityManipulatorRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<EntherealSelectorRecipe>> ENTHEREAL_SELECTOR_SERIALIZER =
            SERIALIZERS.register("enthereal_selector", () -> EntherealSelectorRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
