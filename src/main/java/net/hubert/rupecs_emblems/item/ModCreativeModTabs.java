package net.hubert.rupecs_emblems.item;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.block.ModBlocks;
import net.hubert.rupecs_emblems.item.custom.ModEmblemItem;
import net.hubert.rupecs_emblems.recipe.EntherealSelectorRecipe;
import net.hubert.rupecs_emblems.recipe.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Rupecs_Emblems.MOD_ID);

    public static final RegistryObject<CreativeModeTab> emblems = CREATIVE_MODE_TABS.register("rupecs_emblems",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MOON_EMBLEM.get()))
                    .title(Component.translatable("creativetab.rupecs_emblems"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.OVERWORLD_EMBLEMIUM.get());
                        pOutput.accept(ModItems.NETHER_EMBLEMIUM.get());
                        pOutput.accept(ModItems.END_EMBLEMIUM.get());
                        pOutput.accept(ModItems.STAR_EMBLEMIUM.get());


                        pOutput.accept(ModItems.WOODEN_EMBLEMIUM.get());


                        pOutput.accept(ModItems.CLOUD_EMBLEMIUM.get());


                        pOutput.accept(ModItems.BLESSED_EMBLEMIUM.get());


                        pOutput.accept(ModItems.PROFANED_EMBLEMIUM.get());


                        pOutput.accept(ModItems.ANCIENT_EMBLEMIUM.get());


                        pOutput.accept(ModItems.CURSED_EMBLEMIUM.get());


                        pOutput.accept(ModItems.GLOBAL_EMBLEMIUM.get());


                        pOutput.accept(ModItems.COSMIC_EMBLEMIUM.get());



                        pOutput.accept(ModItems.PLANT_SIGIL_1.get());
                        pOutput.accept(ModItems.PLANT_SIGIL_2.get());
                        pOutput.accept(ModItems.EMBLEMIUM_SIGIL.get());
                        pOutput.accept(ModItems.EMBLEMIUM_INSIGNIA.get());

                        boolean hasMore = true;
                        int currVal = 0;
                        while (hasMore) {
                            if (hasEntherRecipe(ModItems.EMBLEMENTAL_ENTHERA.get(), currVal)){
                                pOutput.accept(getEntherVersion(ModItems.EMBLEMENTAL_ENTHERA.get(),currVal));
                                currVal++;
                            }
                            else if (currVal == 0){
                                pOutput.accept(ModItems.EMBLEMENTAL_ENTHERA.get());
                                currVal++;
                            }
                            else {
                                hasMore=false;
                            }
                        }

                        pOutput.accept(ModItems.ENTHEREAL_MASS.get());
                        pOutput.accept(ModItems.ENTHEREAL_CONJUGATION.get());
                        pOutput.accept(ModItems.ENTHEREAL_VOID.get());

                        pOutput.accept(ModItems.MANIPULATION_CORE.get());


                        pOutput.accept(ModBlocks.REALITY_MANIPULATOR.get());
                        pOutput.accept(ModBlocks.ENTHEREAL_SELECTOR.get());


                        pOutput.accept(ModItems.BULBULIUM.get());
                        pOutput.accept(ModItems.BURNING_CLOTH.get());
                        pOutput.accept(ModItems.SUNLIGHT_SHARD.get());
                        pOutput.accept(ModItems.SHIELDED_FOSSIL.get());
                        pOutput.accept(ModItems.VERDANT_CORE.get());
                        pOutput.accept(ModItems.BONSAI.get());
                        pOutput.accept(ModItems.REFERENCIUM.get());
                        pOutput.accept(ModItems.FRACTURED_ICICLE.get());
                        pOutput.accept(ModItems.GLASS_LUNG.get());
                        pOutput.accept(ModItems.BATTERY.get());
                        pOutput.accept(ModItems.THE_EARTH.get());
                        pOutput.accept(ModItems.THE_TORCH.get());
                        pOutput.accept(ModItems.JACK_OF_SPADES.get());
                        pOutput.accept(ModItems.BENT_FIN.get());
                        pOutput.accept(ModItems.MOON_FRAGMENT.get());
                        pOutput.accept(ModItems.IRON_PLATING.get());
                        pOutput.accept(ModItems.COIN_NECKLACE.get());
                        pOutput.accept(ModItems.BREAD_PIECE.get());
                        pOutput.accept(ModItems.HALF_NOTE.get());



                        pOutput.accept(ModItems.NEPTUNES_GIFT.get());
                        pOutput.accept(ModItems.SEED_POUCH.get());
                        pOutput.accept(ModItems.COSMOS_ESSENCE.get());
                        pOutput.accept(ModItems.PHOENIX_FEATHER.get());
                        pOutput.accept(ModItems.DOVE_WING.get());
                        pOutput.accept(ModItems.ANCIENT_REMAINS.get());
                        pOutput.accept(ModItems.TESLA_BAR.get());
                        pOutput.accept(ModItems.RADIANT_GEODE.get());
                        pOutput.accept(ModItems.PROMETHEUS_GIFT.get());
                        pOutput.accept(ModItems.WINE_CUP.get());
                        pOutput.accept(ModItems.DISCAPTURED_ENERGY.get());
                        pOutput.accept(ModItems.WISTERIA_FLOWER.get());
                        pOutput.accept(ModItems.CORAL_CROWN.get());
                        pOutput.accept(ModItems.SALT_ORE.get());
                        pOutput.accept(ModItems.BUBBLIUM.get());



                        pOutput.accept(ModItems.EAGLE_BEAK.get());
                        pOutput.accept(ModItems.TINY_BUSH.get());
                        pOutput.accept(ModItems.SHATTERED_VOID.get());
                        pOutput.accept(ModItems.STINGER.get());
                        pOutput.accept(ModItems.PAW_FOSSIL.get());
                        pOutput.accept(ModItems.MOONGLOW.get());
                        pOutput.accept(ModItems.SUNRAY_BEAM.get());
                        pOutput.accept(ModItems.STAR_SAPLING.get());
                        pOutput.accept(ModItems.REEFS_GLORY.get());
                        pOutput.accept(ModItems.BRITTLE_FOSSIL.get());
                        pOutput.accept(ModItems.JADE_EARRING.get());
                        pOutput.accept(ModItems.SAPPHIRE_NECKLACE.get());
                        pOutput.accept(ModItems.OWSHUN.get());
                        pOutput.accept(ModItems.BONE_CROWN.get());
                        pOutput.accept(ModItems.EARF.get());


                        pOutput.accept(ModItems.LIVING_MASS.get());
                        pOutput.accept(ModItems.EDENS_VINES.get());
                        pOutput.accept(ModItems.TANGLED_TAILS.get());
                        pOutput.accept(ModItems.SERPENT_SEED.get());
                        pOutput.accept(ModItems.ROOTED_ROCK.get());
                        pOutput.accept(ModItems.GRAND_ANCHOR.get());
                        pOutput.accept(ModItems.BLACK_PEARL.get());


                        pOutput.accept(ModItems.EVES_APPLE.get());








                        for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()){
                            if (item.get() instanceof ModEmblemItem){
                                pOutput.accept(item.get());
                                if (hasAscendedRecipe(item.get())) {
                                    // Get or create the ascended version
                                    ItemStack ascendedItem = getAscendedVersion(item.get());
                                    pOutput.accept(ascendedItem);
                                }
                            }
                        }

                        //Rupec's Elytras
                        if (ModList.get().isLoaded("rupecs_elytras")) {
                            pOutput.accept(ModItems.GLIDER_EMBLEM.get());
                            pOutput.accept(ModItems.FEATHER_EMBLEM.get());
                            pOutput.accept(ModItems.WIND_CATCHER_EMBLEM.get());
                            pOutput.accept(ModItems.DASHER_EMBLEM.get());
                            pOutput.accept(ModItems.PHOENIX_EMBLEM.get());
                            pOutput.accept(ModItems.PHOENIX_WING_EMBLEM.get());
                            pOutput.accept(ModItems.TRUE_PHOENIX_EMBLEM.get());
                            pOutput.accept(ModItems.HUMMING_BIRD_EMBLEM.get());
                            pOutput.accept(ModItems.MOUNTAIN_WIND_EMBLEM.get());
                            pOutput.accept(ModItems.WEIGHTLESS_EMBLEM.get());
                            pOutput.accept(ModItems.WING_POWER_EMBLEM.get());
                            pOutput.accept(ModItems.SKY_WANDERER_EMBLEM.get());
                        }





                    }).build());

    private static ItemStack getAscendedVersion(@NotNull Item item) {
        ItemStack stack = item.getDefaultInstance();
        stack.getOrCreateTag().putInt("ascended", 1);
        return stack;
    }
    private static ItemStack getEntherVersion(@NotNull Item item, int entherLevel) {
        ItemStack stack = item.getDefaultInstance();
        stack.getOrCreateTag().putInt("enther", entherLevel);
        return stack;
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
    private static boolean hasAscendedRecipe(Item baseItem) {
        // Get registry name from ForgeRegistries
        ResourceLocation registryName = net.minecraftforge.registries.ForgeRegistries.ITEMS.getKey(baseItem);
        if (registryName == null) return false;

        // Build recipe ID (e.g., "rupecs_emblems:plant_emblem_ascended_from_ethereal_selection")
        ResourceLocation recipeId = new ResourceLocation(
                Rupecs_Emblems.MOD_ID,
                registryName.getPath() + "_ascended_from_ethereal_selection"
        );

        // Check if recipe exists by ID
        return Minecraft.getInstance().level != null &&
                Minecraft.getInstance().level.getRecipeManager()
                        .byKey(recipeId)
                        .isPresent();
    }
    private static boolean hasEntherRecipe(Item baseItem, int entherLevel) {
        // Get registry name from ForgeRegistries
        ResourceLocation registryName = net.minecraftforge.registries.ForgeRegistries.ITEMS.getKey(baseItem);
        if (registryName == null) return false;

        // Build recipe ID (e.g., "rupecs_emblems:plant_emblem_ascended_from_ethereal_selection")
        ResourceLocation recipeId = new ResourceLocation(
                Rupecs_Emblems.MOD_ID,
                registryName.getPath() + "-e"+entherLevel+"_from_reality_manipulation"
        );
        System.out.println(recipeId);

        // Check if recipe exists by ID
        return Minecraft.getInstance().level != null &&
                Minecraft.getInstance().level.getRecipeManager()
                        .byKey(recipeId)
                        .isPresent();
    }
}
