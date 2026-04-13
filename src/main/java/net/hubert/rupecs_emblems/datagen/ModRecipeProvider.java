package net.hubert.rupecs_emblems.datagen;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.block.ModBlocks;
import net.hubert.rupecs_emblems.item.EmblemTypes;
import net.hubert.rupecs_emblems.item.ModItems;
import net.hubert.rupecs_emblems.item.custom.ItemiumItem;
import net.hubert.rupecs_emblems.util.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OVERWORLD_EMBLEMIUM.get(), 1)
                .pattern("LCQ")
                .pattern("IDG")
                .pattern("QRL")
                .define('I', Items.IRON_BLOCK)
                .define('C', Items.COPPER_BLOCK)
                .define('G', Items.GOLD_BLOCK)
                .define('R', Items.REDSTONE_BLOCK)
                .define('L', ModItems.BONSAI.get())
                .define('Q', ModItems.VERDANT_CORE.get())
                .define('D', Items.DIAMOND_BLOCK)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NETHER_EMBLEMIUM.get(), 1)
                .pattern("LCQ")
                .pattern("DID")
                .pattern("QCL")
                .define('I', Items.NETHERITE_INGOT)
                .define('C', Items.QUARTZ_BLOCK)
                .define('L', Items.GOLD_BLOCK)
                .define('Q', ModItems.BURNING_CLOTH.get())
                .define('D', Items.DIAMOND_BLOCK)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.END_EMBLEMIUM.get(), 1)
                .pattern("LIL")
                .pattern("IDI")
                .pattern("LIL")
                .define('I', Items.NETHERITE_INGOT)
                .define('L', Items.DRAGON_BREATH)
                .define('D', Items.DRAGON_EGG)
                .unlockedBy(getHasName(Items.DRAGON_EGG), has(Items.DRAGON_EGG))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAR_EMBLEMIUM.get(), 1)
                .pattern("DID")
                .pattern("IDI")
                .pattern("DID")
                .define('I', Items.NETHERITE_INGOT)
                .define('D', Items.NETHER_STAR)
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOODEN_EMBLEMIUM.get(), 1)
                .pattern("QLQ")
                .pattern("LIL")
                .pattern("QLQ")
                .define('L', Ingredient.of(ItemTags.LOGS))
                .define('Q', Ingredient.of(ModTags.Items.TIER_ONE_ITEMIUMS))
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CLOUD_EMBLEMIUM.get(), 1)
                .pattern("QGL")
                .pattern("QIQ")
                .pattern("LGQ")
                .define('L', Ingredient.of(ItemTags.LEAVES))
                .define('Q', ModItems.GLASS_LUNG.get())
                .define('I', Items.GOLD_INGOT)
                .define('G', Items.GLASS)
                .unlockedBy(getHasName(Items.GOLD_BLOCK), has(Items.GOLD_BLOCK))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BLESSED_EMBLEMIUM.get(), 1)
                .pattern("LGL")
                .pattern("GBG")
                .pattern("LGL")
                .define('L', Items.GOLD_BLOCK)
                .define('G', Ingredient.of(ModTags.Items.TIER_TWO_ITEMIUMS))
                .define('B', ModItems.BREAD_PIECE.get())
                .unlockedBy(getHasName(Items.GOLD_BLOCK), has(Items.GOLD_BLOCK))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PROFANED_EMBLEMIUM.get(), 1)
                .pattern("LGL")
                .pattern("QBQ")
                .pattern("QGQ")
                .define('L', Items.IRON_BLOCK)
                .define('G', Items.DIAMOND)
                .define('Q', Ingredient.of(ModTags.Items.TIER_TWO_ITEMIUMS))
                .define('B', Ingredient.of(ModTags.Items.D_TIER_EMBLEMS))
                .unlockedBy(getHasName(Items.IRON_BLOCK), has(Items.IRON_BLOCK))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ANCIENT_EMBLEMIUM.get(), 1)
                .pattern("QGQ")
                .pattern("LBL")
                .pattern("QGQ")
                .define('L', Items.DIAMOND)
                .define('G', Items.LAPIS_BLOCK)
                .define('Q', Ingredient.of(ModTags.Items.TIER_THREE_ITEMIUMS))
                .define('B', Ingredient.of(ModTags.Items.C_TIER_EMBLEMS))
                .unlockedBy(getHasName(Items.LAPIS_BLOCK), has(Items.LAPIS_BLOCK))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CURSED_EMBLEMIUM.get(), 1)
                .pattern("QGQ")
                .pattern("LBL")
                .pattern("QGQ")
                .define('L', Items.NETHERITE_SCRAP)
                .define('Q', Ingredient.of(ModTags.Items.TIER_THREE_ITEMIUMS))
                .define('G', Ingredient.of(ModTags.Items.C_TIER_EMBLEMS))
                .define('B', Ingredient.of(ModTags.Items.B_TIER_EMBLEMS))
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GLOBAL_EMBLEMIUM.get(), 1)
                .pattern("LGL")
                .pattern("DBD")
                .pattern("DGD")
                .define('L', Items.NETHERITE_INGOT)
                .define('G', Ingredient.of(ModTags.Items.B_TIER_EMBLEMS))
                .define('D', Ingredient.of(ModTags.Items.TIER_THREE_ITEMIUMS))
                .define('B', Ingredient.of(ModTags.Items.TIER_FOUR_ITEMIUMS))
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COSMIC_EMBLEMIUM.get(), 1)
                .pattern("LGL")
                .pattern("DBD")
                .pattern("LGL")
                .define('L', Ingredient.of(ModTags.Items.TIER_THREE_ITEMIUMS))
                .define('G', Ingredient.of(ModTags.Items.B_TIER_EMBLEMS))
                .define('B', Ingredient.of(ModTags.Items.A_TIER_EMBLEMS))
                .define('D', Ingredient.of(ModTags.Items.TIER_FIVE_ITEMIUMS))
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pWriter);



        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MANIPULATION_CORE.get(), 1)
                .pattern("GGG")
                .pattern("GDG")
                .pattern("GGG")
                .define('G', ModItems.ENTHEREAL_MASS.get())
                .define('D', Ingredient.of(ModTags.Items.TIER_THREE_ITEMIUMS))
                .unlockedBy(getHasName(Items.SMOOTH_STONE), has(Items.SMOOTH_STONE))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.REALITY_MANIPULATOR.get(), 1)
                .pattern("GGG")
                .pattern("GDG")
                .pattern("IOI")
                .define('G', Ingredient.of(Tags.Items.GLASS))
                .define('D', ModItems.MANIPULATION_CORE.get())
                .define('I', Items.SMOOTH_STONE)
                .define('O', Items.OBSIDIAN)
                .unlockedBy(getHasName(Items.SMOOTH_STONE), has(Items.SMOOTH_STONE))
                .save(pWriter);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ENTHEREAL_SELECTOR.get(), 1)
                .pattern("BIB")
                .pattern("GDG")
                .pattern("WNW")
                .define('G', Ingredient.of(ModTags.Items.TIER_FOUR_ITEMIUMS))
                .define('D', ModItems.ENTHEREAL_VOID.get())
                .define('I', Items.SMOOTH_STONE)
                .define('B', Items.IRON_BLOCK)
                .define('N', Items.NETHERITE_INGOT)
                .define('W', Ingredient.of(ItemTags.PLANKS))
                .unlockedBy(getHasName(Items.SMOOTH_STONE), has(Items.SMOOTH_STONE))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ENTHEREAL_VOID.get(), 2)
                .pattern("DDD")
                .pattern("LGR")
                .pattern("QQQ")
                .define('G', ModItems.ENTHEREAL_CONJUGATION.get())
                .define('D', Items.PRISMARINE_SHARD)
                .define('Q', Items.BLAZE_POWDER)
                .define('L', Items.ENDER_EYE)
                .define('R', Items.PHANTOM_MEMBRANE)
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD))
                .save(pWriter);



        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EMBLEMIUM_SIGIL.get(), 3)
                .pattern("GAG")
                .pattern("ADA")
                .pattern("GAG")
                .define('G', Items.GLOWSTONE_DUST)
                .define('D', Items.DEEPSLATE)
                .define('A', Items.AMETHYST_SHARD)
                .unlockedBy(getHasName(Items.DEEPSLATE), has(Items.DEEPSLATE))
                .save(pWriter);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EMBLEMIUM_INSIGNIA.get(), 1)
                .pattern("RLR")
                .pattern("SSS")
                .pattern("RLR")
                .define('R', Items.REDSTONE_BLOCK)
                .define('S', ModItems.EMBLEMIUM_SIGIL.get())
                .define('L', Items.LAPIS_BLOCK)
                .unlockedBy(getHasName(ModItems.EMBLEMIUM_SIGIL.get()), has(ModItems.EMBLEMIUM_SIGIL.get()))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.NEPTUNES_GIFT.get(), 1)
                .requires(ModItems.BULBULIUM.get(), 3)
                .requires(ModItems.SUNLIGHT_SHARD.get(), 3)
                .requires(ModItems.ENTHEREAL_MASS.get(), 3)
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SEED_POUCH.get(), 1)
                .requires(ModItems.VERDANT_CORE.get(), 3)
                .requires(ModItems.BONSAI.get(), 3)
                .requires(ModItems.ENTHEREAL_MASS.get(), 3)
                .unlockedBy(getHasName(ModItems.VERDANT_CORE.get()), has(ModItems.VERDANT_CORE.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.COSMOS_ESSENCE.get(), 1)
                .requires(ModItems.MOON_FRAGMENT.get(), 3)
                .requires(ModItems.SUNLIGHT_SHARD.get(), 3)
                .requires(ModItems.ENTHEREAL_MASS.get(), 3)
                .unlockedBy(getHasName(ModItems.SUNLIGHT_SHARD.get()), has(ModItems.SUNLIGHT_SHARD.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PHOENIX_FEATHER.get(), 1)
                .requires(ModItems.BURNING_CLOTH.get(), 3)
                .requires(ModItems.BENT_FIN.get(), 3)
                .requires(ModItems.ENTHEREAL_MASS.get(), 3)
                .unlockedBy(getHasName(ModItems.BURNING_CLOTH.get()), has(ModItems.BURNING_CLOTH.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DOVE_WING.get(), 1)
                .requires(ModItems.GLASS_LUNG.get(), 3)
                .requires(ModItems.BENT_FIN.get(), 3)
                .requires(ModItems.ENTHEREAL_MASS.get(), 3)
                .unlockedBy(getHasName(ModItems.GLASS_LUNG.get()), has(ModItems.GLASS_LUNG.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TESLA_BAR.get(), 1)
                .requires(ModItems.IRON_PLATING.get(), 3)
                .requires(ModItems.BATTERY.get(), 3)
                .requires(ModItems.ENTHEREAL_MASS.get(), 3)
                .unlockedBy(getHasName(ModItems.IRON_PLATING.get()), has(ModItems.IRON_PLATING.get()))
                .save(pWriter, "tesla_bar_from_plating_battery");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TESLA_BAR.get(), 1)
                .requires(ModItems.DISCAPTURED_ENERGY.get())
                .unlockedBy(getHasName(ModItems.DISCAPTURED_ENERGY.get()), has(ModItems.DISCAPTURED_ENERGY.get()))
                .save(pWriter, "tesla_bar_from_discaptured_energy");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RADIANT_GEODE.get(), 1)
                .requires(ModItems.SHIELDED_FOSSIL.get(), 3)
                .requires(ModItems.BATTERY.get(), 3)
                .requires(ModItems.ENTHEREAL_MASS.get(), 3)
                .unlockedBy(getHasName(ModItems.IRON_PLATING.get()), has(ModItems.IRON_PLATING.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ANCIENT_REMAINS.get(), 1)
                .requires(ModItems.IRON_PLATING.get(), 3)
                .requires(ModItems.SHIELDED_FOSSIL.get(), 3)
                .requires(ModItems.ENTHEREAL_MASS.get(), 3)
                .unlockedBy(getHasName(ModItems.IRON_PLATING.get()), has(ModItems.IRON_PLATING.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PROMETHEUS_GIFT.get(), 1)
                .requires(ModItems.THE_EARTH.get(), 3)
                .requires(ModItems.THE_TORCH.get(), 3)
                .requires(ModItems.ENTHEREAL_MASS.get(), 3)
                .unlockedBy(getHasName(ModItems.THE_EARTH.get()), has(ModItems.THE_EARTH.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WINE_CUP.get(), 1)
                .requires(ModItems.BREAD_PIECE.get(), 3)
                .requires(ModItems.REFERENCIUM.get(), 3)
                .requires(ModItems.ENTHEREAL_MASS.get(), 3)
                .unlockedBy(getHasName(ModItems.BREAD_PIECE.get()), has(ModItems.BREAD_PIECE.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DISCAPTURED_ENERGY.get(), 1)
                .requires(ModItems.TESLA_BAR.get())
                .unlockedBy(getHasName(ModItems.TESLA_BAR.get()), has(ModItems.TESLA_BAR.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WISTERIA_FLOWER.get(), 1)
                .requires(ModItems.VERDANT_CORE.get(), 3)
                .requires(ModItems.THE_EARTH.get(), 3)
                .requires(ModItems.ENTHEREAL_MASS.get(), 3)
                .unlockedBy(getHasName(ModItems.VERDANT_CORE.get()), has(ModItems.VERDANT_CORE.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.CORAL_CROWN.get(), 1)
                .requires(ModItems.BULBULIUM.get(), 3)
                .requires(ModItems.VERDANT_CORE.get(), 3)
                .requires(ModItems.ENTHEREAL_MASS.get(), 3)
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SALT_ORE.get(), 1)
                .requires(ModItems.SHIELDED_FOSSIL.get(), 3)
                .requires(ModItems.THE_EARTH.get(), 3)
                .requires(ModItems.ENTHEREAL_MASS.get(), 3)
                .unlockedBy(getHasName(ModItems.SHIELDED_FOSSIL.get()), has(ModItems.SHIELDED_FOSSIL.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BUBBLIUM.get(), 1)
                .requires(ModItems.BULBULIUM.get(), 3)
                .requires(ModItems.THE_EARTH.get(), 3)
                .requires(ModItems.ENTHEREAL_MASS.get(), 3)
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.EAGLE_BEAK.get(), 1)
                .requires(ModItems.DOVE_WING.get(), 2)
                .requires(ModItems.THE_EARTH.get(), 4)
                .requires(ModItems.ENTHEREAL_CONJUGATION.get(), 2)
                .unlockedBy(getHasName(ModItems.DOVE_WING.get()), has(ModItems.DOVE_WING.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.TINY_BUSH.get(), 1)
                .requires(ModItems.SEED_POUCH.get(), 2)
                .requires(ModItems.BULBULIUM.get(), 4)
                .requires(ModItems.ENTHEREAL_CONJUGATION.get(), 2)
                .unlockedBy(getHasName(ModItems.SEED_POUCH.get()), has(ModItems.SEED_POUCH.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SHATTERED_VOID.get(), 1)
                .requires(ModItems.COSMOS_ESSENCE.get(), 2)
                .requires(ModItems.THE_EARTH.get(), 4)
                .requires(ModItems.ENTHEREAL_CONJUGATION.get(), 2)
                .unlockedBy(getHasName(ModItems.COSMOS_ESSENCE.get()), has(ModItems.COSMOS_ESSENCE.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.STINGER.get(), 1)
                .requires(ModItems.DOVE_WING.get(), 2)
                .requires(ModItems.VERDANT_CORE.get(), 4)
                .requires(ModItems.ENTHEREAL_CONJUGATION.get(), 2)
                .unlockedBy(getHasName(ModItems.DOVE_WING.get()), has(ModItems.DOVE_WING.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PAW_FOSSIL.get(), 1)
                .requires(ModItems.WISTERIA_FLOWER.get(), 2)
                .requires(ModItems.BENT_FIN.get(), 4)
                .requires(ModItems.ENTHEREAL_CONJUGATION.get(), 2)
                .unlockedBy(getHasName(ModItems.WISTERIA_FLOWER.get()), has(ModItems.WISTERIA_FLOWER.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MOONGLOW.get(), 1)
                .requires(ModItems.WISTERIA_FLOWER.get(), 2)
                .requires(ModItems.MOON_FRAGMENT.get(), 4)
                .requires(ModItems.ENTHEREAL_CONJUGATION.get(), 2)
                .unlockedBy(getHasName(ModItems.WISTERIA_FLOWER.get()), has(ModItems.WISTERIA_FLOWER.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SUNRAY_BEAM.get(), 1)
                .requires(ModItems.COSMOS_ESSENCE.get(), 2)
                .requires(ModItems.VERDANT_CORE.get(), 4)
                .requires(ModItems.ENTHEREAL_CONJUGATION.get(), 2)
                .unlockedBy(getHasName(ModItems.COSMOS_ESSENCE.get()), has(ModItems.COSMOS_ESSENCE.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.STAR_SAPLING.get(), 1)
                .requires(ModItems.SEED_POUCH.get(), 2)
                .requires(ModItems.THE_EARTH.get(), 4)
                .requires(ModItems.ENTHEREAL_CONJUGATION.get(), 2)
                .unlockedBy(getHasName(ModItems.SEED_POUCH.get()), has(ModItems.SEED_POUCH.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.REEFS_GLORY.get(), 1)
                .requires(ModItems.CORAL_CROWN.get(), 2)
                .requires(ModItems.THE_EARTH.get(), 4)
                .requires(ModItems.ENTHEREAL_CONJUGATION.get(), 2)
                .unlockedBy(getHasName(ModItems.CORAL_CROWN.get()), has(ModItems.CORAL_CROWN.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.JADE_EARRING.get(), 1)
                .requires(ModItems.SALT_ORE.get(), 2)
                .requires(ModItems.BONSAI.get(), 4)
                .requires(ModItems.ENTHEREAL_CONJUGATION.get(), 2)
                .unlockedBy(getHasName(ModItems.SALT_ORE.get()), has(ModItems.SALT_ORE.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BRITTLE_FOSSIL.get(), 1)
                .requires(ModItems.SALT_ORE.get(), 2)
                .requires(ModItems.VERDANT_CORE.get(), 4)
                .requires(ModItems.ENTHEREAL_CONJUGATION.get(), 2)
                .unlockedBy(getHasName(ModItems.SALT_ORE.get()), has(ModItems.SALT_ORE.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SAPPHIRE_NECKLACE.get(), 1)
                .requires(ModItems.SALT_ORE.get(), 2)
                .requires(ModItems.BULBULIUM.get(), 4)
                .requires(ModItems.ENTHEREAL_CONJUGATION.get(), 2)
                .unlockedBy(getHasName(ModItems.SALT_ORE.get()), has(ModItems.SALT_ORE.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.OWSHUN.get(), 1)
                .requires(ModItems.BUBBLIUM.get(), 2)
                .requires(ModItems.BONSAI.get(), 4)
                .requires(ModItems.ENTHEREAL_CONJUGATION.get(), 2)
                .unlockedBy(getHasName(ModItems.BUBBLIUM.get()), has(ModItems.BUBBLIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BONE_CROWN.get(), 1)
                .requires(ModItems.CORAL_CROWN.get(), 2)
                .requires(ModItems.SHIELDED_FOSSIL.get(), 4)
                .requires(ModItems.ENTHEREAL_CONJUGATION.get(), 2)
                .unlockedBy(getHasName(ModItems.CORAL_CROWN.get()), has(ModItems.CORAL_CROWN.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.EARF.get(), 1)
                .requires(ModItems.SEED_POUCH.get(), 2)
                .requires(ModItems.SHIELDED_FOSSIL.get(), 4)
                .requires(ModItems.ENTHEREAL_CONJUGATION.get(), 2)
                .unlockedBy(getHasName(ModItems.SEED_POUCH.get()), has(ModItems.SEED_POUCH.get()))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.LIVING_MASS.get(), 1)
                .requires(ModItems.SHATTERED_VOID.get(), 4)
                .requires(ModItems.MOONGLOW.get(), 2)
                .requires(ModItems.SUNRAY_BEAM.get(), 2)
                .requires(ModItems.ENTHEREAL_VOID.get())
                .unlockedBy(getHasName(ModItems.SHATTERED_VOID.get()), has(ModItems.SHATTERED_VOID.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.EDENS_VINES.get(), 1)
                .requires(ModItems.TINY_BUSH.get(), 4)
                .requires(ModItems.STAR_SAPLING.get(), 2)
                .requires(ModItems.REEFS_GLORY.get(), 2)
                .requires(ModItems.ENTHEREAL_VOID.get())
                .unlockedBy(getHasName(ModItems.TINY_BUSH.get()), has(ModItems.TINY_BUSH.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.TANGLED_TAILS.get(), 1)
                .requires(ModItems.EAGLE_BEAK.get(), 4)
                .requires(ModItems.PAW_FOSSIL.get(), 2)
                .requires(ModItems.STINGER.get(), 2)
                .requires(ModItems.ENTHEREAL_VOID.get())
                .unlockedBy(getHasName(ModItems.EAGLE_BEAK.get()), has(ModItems.EAGLE_BEAK.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SERPENT_SEED.get(), 1)
                .requires(ModItems.STAR_SAPLING.get(), 4)
                .requires(ModItems.JADE_EARRING.get(), 2)
                .requires(ModItems.BRITTLE_FOSSIL.get(), 2)
                .requires(ModItems.ENTHEREAL_VOID.get())
                .unlockedBy(getHasName(ModItems.STAR_SAPLING.get()), has(ModItems.STAR_SAPLING.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.GRAND_ANCHOR.get(), 1)
                .requires(ModItems.JADE_EARRING.get(), 4)
                .requires(ModItems.SAPPHIRE_NECKLACE.get(), 2)
                .requires(ModItems.OWSHUN.get(), 2)
                .requires(ModItems.ENTHEREAL_VOID.get())
                .unlockedBy(getHasName(ModItems.JADE_EARRING.get()), has(ModItems.JADE_EARRING.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BLACK_PEARL.get(), 1)
                .requires(ModItems.BRITTLE_FOSSIL.get(), 4)
                .requires(ModItems.SAPPHIRE_NECKLACE.get(), 2)
                .requires(ModItems.REEFS_GLORY.get(), 2)
                .requires(ModItems.ENTHEREAL_VOID.get())
                .unlockedBy(getHasName(ModItems.BRITTLE_FOSSIL.get()), has(ModItems.BRITTLE_FOSSIL.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ROOTED_ROCK.get(), 1)
                .requires(ModItems.SAPPHIRE_NECKLACE.get(), 4)
                .requires(ModItems.EARF.get(), 2)
                .requires(ModItems.BONE_CROWN.get(), 2)
                .requires(ModItems.ENTHEREAL_VOID.get())
                .unlockedBy(getHasName(ModItems.SAPPHIRE_NECKLACE.get()), has(ModItems.SAPPHIRE_NECKLACE.get()))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.EVES_APPLE.get(), 1)
                .requires(ModItems.EDENS_VINES.get(), 1)
                .requires(ModItems.SERPENT_SEED.get(), 1)
                .requires(ModItems.ROOTED_ROCK.get(), 1)
                .requires(ModItems.GRAND_ANCHOR.get(), 1)
                .requires(ModItems.BLACK_PEARL.get(), 1)
                .requires(ModItems.ENTHEREAL_VOID.get(),4)
                .unlockedBy(getHasName(ModItems.EDENS_VINES.get()), has(ModItems.EDENS_VINES.get()))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MOON_EMBLEM.get(), 1)
                .requires(Items.DIAMOND)
                .requires(ModItems.SHIELDED_FOSSIL.get())
                .requires(ModItems.MOON_FRAGMENT.get(), 2)
                .requires(ModItems.SUNLIGHT_SHARD.get(), 2)
                .requires(ModItems.STONE_EMBLEM.get())
                .requires(ModItems.SKY_EMBLEM.get())
                .requires(ModItems.NIGHT_EMBLEM.get())
                .unlockedBy(getHasName(ModItems.STONE_EMBLEM.get()), has(ModItems.STONE_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FOSSIL_EMBLEM.get(), 1)
                .requires(Items.GOLD_BLOCK, 3)
                .requires(Items.SANDSTONE)
                .requires(ModItems.SHIELDED_FOSSIL.get(), 3)
                .requires(ModItems.SAND_EMBLEM.get())
                .requires(ModItems.BONE_EMBLEM.get())
                .unlockedBy(getHasName(ModItems.SAND_EMBLEM.get()), has(ModItems.SAND_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WOOD_EMBLEM.get(), 1)
                .requires(Items.WOODEN_AXE)
                .requires(Items.WOODEN_SHOVEL)
                .requires(Items.WOODEN_SWORD)
                .requires(Items.WOODEN_PICKAXE)
                .requires(Items.WOODEN_HOE)
                .requires(ItemTags.LOGS)
                .requires(ItemTags.LOGS)
                .requires(ItemTags.LOGS)
                .unlockedBy(getHasName(Items.WOODEN_PICKAXE), has(ItemTags.LOGS))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BRANCH_EMBLEM.get(), 1)
                .requires(ModItems.WOOD_EMBLEM.get())
                .requires(ModItems.PLANT_EMBLEM.get())
                .requires(Ingredient.of(ItemTags.LOGS), 3)
                .requires(Ingredient.of(ItemTags.LEAVES), 3)
                .unlockedBy(getHasName(Items.WOODEN_PICKAXE), has(ItemTags.LOGS))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FERTILIZED_EMBLEM.get(), 1)
                .requires(ModItems.FLOWER_EMBLEM.get())
                .requires(ModItems.GROUND_EMBLEM.get())
                .requires(Ingredient.of(ItemTags.DIRT), 3)
                .requires(Ingredient.of(ItemTags.FLOWERS), 3)
                .unlockedBy(getHasName(Items.WOODEN_PICKAXE), has(ItemTags.FLOWERS))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.STONE_EMBLEM.get(), 1)
                .requires(Items.STONE_AXE)
                .requires(Items.STONE_SHOVEL)
                .requires(Items.STONE_SWORD)
                .requires(Items.STONE_PICKAXE)
                .requires(Items.STONE_HOE)
                .requires(ItemTags.STONE_TOOL_MATERIALS)
                .requires(ItemTags.STONE_TOOL_MATERIALS)
                .requires(ItemTags.STONE_TOOL_MATERIALS)
                .unlockedBy(getHasName(Items.STONE_PICKAXE), has(ItemTags.STONE_TOOL_MATERIALS))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.IRON_EMBLEM.get(), 1)
                .requires(Items.IRON_AXE)
                .requires(Items.IRON_SHOVEL)
                .requires(Items.IRON_SWORD)
                .requires(Items.IRON_PICKAXE)
                .requires(Items.IRON_HOE)
                .requires(Items.IRON_INGOT, 3)
                .unlockedBy(getHasName(Items.IRON_PICKAXE), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SAND_EMBLEM.get(), 1)
                .requires(Items.GOLDEN_AXE)
                .requires(Items.GOLDEN_SWORD)
                .requires(Items.GOLDEN_PICKAXE)
                .requires(Items.GOLD_INGOT)
                .requires(Items.SAND, 4)
                .requires(ModItems.DUST_EMBLEM.get())
                .unlockedBy(getHasName(Items.SAND), has(Items.SAND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.GROUND_EMBLEM.get(), 1)
                .requires(Items.STONE_SHOVEL)
                .requires(Items.WOODEN_SHOVEL)
                .requires(Items.STONE_PICKAXE)
                .requires(Items.WOODEN_PICKAXE)
                .requires(Items.DIRT)
                .requires(Items.DIRT)
                .requires(Items.DIRT)
                .requires(Items.DIRT)
                .requires(Items.DIRT)
                .unlockedBy(getHasName(Items.DIRT), has(Items.DIRT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PLANT_EMBLEM.get(), 1)
                .requires(Items.SHEARS)
                .requires(Ingredient.of(ItemTags.SAPLINGS), 3)
                .requires(Ingredient.of(ItemTags.LEAVES),4)
                .requires(ModItems.BONSAI.get())
                .unlockedBy(getHasName(ModItems.BONSAI.get()), has(ModItems.BONSAI.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FIRE_EMBLEM.get(), 1)
                .requires(Items.FLINT_AND_STEEL)
                .requires(Items.FLINT, 3)
                .requires(Items.NETHERRACK)
                .requires(ModItems.BURNING_CLOTH.get(), 2)
                .requires(Ingredient.of(ItemTags.COALS),2)
                .unlockedBy(getHasName(Items.NETHERRACK), has(Items.NETHERRACK))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.CRYSTAL_EMBLEM.get(), 1)
                .requires(Items.AMETHYST_BLOCK)
                .requires(Items.AMETHYST_SHARD, 3)
                .requires(Items.IRON_INGOT,2)
                .requires(ModItems.STONE_EMBLEM.get())
                .requires(ModItems.SHIELDED_FOSSIL.get(), 2)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SKY_EMBLEM.get(), 1)
                .requires(Items.GLASS_BOTTLE, 3)
                .requires(Items.IRON_INGOT)
                .requires(Items.FEATHER, 3)
                .requires(ModItems.GLASS_LUNG.get(), 2)
                .unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.NIGHT_EMBLEM.get(), 1)
                .requires(Items.TORCH)
                .requires(Items.OBSIDIAN, 3)
                .requires(Items.IRON_INGOT)
                .requires(Items.COAL_BLOCK, 4)
                .unlockedBy(getHasName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DAY_EMBLEM.get(), 1)
                .requires(Items.SOUL_TORCH)
                .requires(Items.GOLD_INGOT, 2)
                .requires(Items.GLOWSTONE_DUST, 4)
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ICE_EMBLEM.get(), 1)
                .requires(Items.SNOWBALL, 4)
                .requires(Items.SNOW_BLOCK, 2)
                .requires(Items.IRON_INGOT, 2)
                .requires(ModItems.FRACTURED_ICICLE.get())
                .unlockedBy(getHasName(Items.ICE), has(Items.ICE))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SUN_EMBLEM.get(), 1)
                .requires(ModItems.SUNLIGHT_SHARD.get(), 4)
                .requires(ModItems.THE_TORCH.get(), 1)
                .requires(Items.GLOW_BERRIES)
                .requires(Items.DIAMOND)
                .requires(Items.ENDER_EYE)
                .requires(ModItems.FLOWER_EMBLEM.get())
                .unlockedBy(getHasName(Items.SUNFLOWER), has(Items.SUNFLOWER))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.HEART_EMBLEM.get(), 1)
                .requires(Items.RED_DYE, 4)
                .requires(Items.BLAZE_POWDER)
                .requires(Items.GOLD_INGOT, 4)
                .unlockedBy(getHasName(Items.FERMENTED_SPIDER_EYE), has(Items.FERMENTED_SPIDER_EYE))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DIAMONDS_EMBLEM.get(), 1)
                .requires(Items.RED_DYE, 4)
                .requires(Items.REDSTONE_BLOCK)
                .requires(Items.IRON_INGOT, 4)
                .unlockedBy(getHasName(Items.RABBIT_FOOT), has(Items.RABBIT_FOOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SPADE_EMBLEM.get(), 1)
                .requires(Items.BLACK_DYE, 4)
                .requires(Items.WOODEN_SWORD)
                .requires(Items.STONE_SWORD)
                .requires(Items.IRON_SWORD)
                .requires(Items.GOLDEN_SWORD)
                .requires(Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_SWORD), has(Items.IRON_SWORD))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.CLOVER_EMBLEM.get(), 1)
                .requires(Items.BLACK_DYE, 4)
                .requires(Items.SUGAR, 5)
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FULL_MOON_EMBLEM.get(), 1)
                .requires(ModItems.COSMOS_ESSENCE.get(),4)
                .requires(ModItems.MOON_EMBLEM.get())
                .requires(ModItems.MOON_WRATH_EMBLEM.get())
                .requires(Items.BLAZE_POWDER, 3)
                .unlockedBy(getHasName(ModItems.MOON_EMBLEM.get()), has(ModItems.MOON_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FLUORESCENT_EMBLEM.get(), 1)
                .requires(Items.GLOW_INK_SAC, 4)
                .requires(Items.DIAMOND)
                .requires(ModItems.SUNRAY_BEAM.get())
                .requires(Items.MUD, 3)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DUST_EMBLEM.get(), 1)
                .requires(Items.DIRT, 4)
                .requires(Items.STONE_SHOVEL)
                .requires(Items.GRAVEL, 4)
                .unlockedBy(getHasName(Items.STONE_SHOVEL), has(Items.STONE_SHOVEL))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.VEGE_EMBLEM.get(), 1)
                .requires(Items.HAY_BLOCK, 4)
                .requires(Items.DIAMOND)
                .requires(ModItems.SEED_POUCH.get(), 4)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MAGMA_EMBLEM.get(), 1)
                .requires(Items.MAGMA_BLOCK, 4)
                .requires(ModItems.BURNING_CLOTH.get())
                .requires(Items.OBSIDIAN, 4)
                .unlockedBy(getHasName(ModItems.BURNING_CLOTH.get()), has(ModItems.BURNING_CLOTH.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FARMER_EMBLEM.get(), 1)
                .requires(Items.WOODEN_HOE)
                .requires(Items.STONE_HOE)
                .requires(Items.IRON_HOE)
                .requires(ModItems.COIN_NECKLACE.get(), 2)
                .requires(ModItems.VERDANT_CORE.get(), 2)
                .requires(Items.BONE_MEAL, 2)
                .unlockedBy(getHasName(Items.STONE_HOE), has(Items.STONE_HOE))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BUILDER_EMBLEM.get(), 1)
                .requires(Items.BRICKS, 4)
                .requires(Items.IRON_INGOT)
                .requires(Items.STONE_BRICKS, 4)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FLOWER_EMBLEM.get(), 1)
                .requires(Ingredient.of(ItemTags.FLOWERS), 4)
                .requires(Items.GOLD_BLOCK)
                .requires(Ingredient.of(ItemTags.FLOWERS), 4)
                .unlockedBy(getHasName(Items.GOLD_BLOCK), has(Items.GOLD_BLOCK))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BONE_EMBLEM.get(), 1)
                .requires(Items.BONE_BLOCK, 2)
                .requires(ModItems.ANCIENT_REMAINS.get())
                .requires(ModItems.SHIELDED_FOSSIL.get(), 2)
                .requires(Items.BONE, 4)
                .unlockedBy(getHasName(Items.BONE), has(Items.BONE))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.INFERNO_EMBLEM.get(), 1)
                .requires(Items.MAGMA_BLOCK)
                .requires(Items.NETHERRACK)
                .requires(Items.MAGMA_CREAM)
                .requires(Items.COAL_BLOCK)
                .requires(Items.GOLD_BLOCK)
                .requires(Items.FLINT_AND_STEEL)
                .requires(Items.FIRE_CHARGE)
                .requires(ModItems.BURNING_CLOTH.get(), 2)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SUPERNOVA_EMBLEM.get(), 1)
                .requires(ModItems.SUN_EMBLEM.get())
                .requires(ModItems.INFERNO_EMBLEM.get())
                .requires(ModItems.MAGMA_EMBLEM.get())
                .requires(ModItems.FIRE_EMBLEM.get())
                .requires(Items.NETHERITE_INGOT)
                .requires(ModItems.BURNING_CLOTH.get(), 2)
                .requires(ModItems.SUNLIGHT_SHARD.get(), 2)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MOON_JUMPER_EMBLEM.get(), 1)
                .requires(Items.PHANTOM_MEMBRANE,2)
                .requires(Items.FEATHER,2)
                .requires(ModItems.MOON_FRAGMENT.get(), 5)
                .unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.COMET_EMBLEM.get(), 1)
                .requires(Items.COBBLESTONE, 2)
                .requires(ModItems.SHIELDED_FOSSIL.get(), 2)
                .unlockedBy(getHasName(Items.SOUL_SAND), has(Items.SOUL_SAND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BLOOD_MOON_EMBLEM.get(), 1)
                .requires(ModItems.FULL_MOON_EMBLEM.get())
                .requires(ModItems.MOON_JUMPER_EMBLEM.get())
                .requires(ModItems.COMET_EMBLEM.get())
                .requires(ModItems.COSMOS_ESSENCE.get(), 2)
                .requires(ModItems.MOON_FRAGMENT.get(), 3)
                .requires(Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BLOODSTAR_EMBLEM.get(), 1)
                .requires(ModItems.BLOOD_MOON_EMBLEM.get())
                .requires(ModItems.SUPERNOVA_EMBLEM.get())
                .requires(ModItems.SUNFLOWER_EMBLEM.get())
                .requires(ModItems.NIGHTBLOOM_EMBLEM.get())
                .requires(ModItems.SHATTERED_VOID.get(), 2)
                .requires(Items.NETHERITE_INGOT, 1)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.POOP_EMBLEM.get(), 1)
                .requires(Items.MUD, 8)
                .requires(Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MERCHANT_EMBLEM.get(), 1)
                .requires(ModItems.COIN_NECKLACE.get())
                .requires(Items.FLETCHING_TABLE)
                .requires(ModItems.COIN_NECKLACE.get())
                .requires(Items.SMITHING_TABLE)
                .requires(ModItems.COIN_NECKLACE.get())
                .requires(Items.CARTOGRAPHY_TABLE)
                .requires(ModItems.COIN_NECKLACE.get())
                .requires(Items.LECTERN)
                .requires(ModItems.COIN_NECKLACE.get())
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.TREE_EMBLEM.get(), 1)
                .requires(ModItems.BRANCH_EMBLEM.get())
                .requires(ModItems.FERTILIZED_EMBLEM.get())
                .requires(Items.DIAMOND)
                .requires(ModItems.SEED_POUCH.get(), 2)
                .requires(ModItems.BONSAI.get())
                .requires(ModItems.VERDANT_CORE.get())
                .unlockedBy(getHasName(ModItems.WOOD_EMBLEM.get()), has(ModItems.WOOD_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.CAVE_EMBLEM.get(), 1)
                .requires(ModItems.STONE_EMBLEM.get())
                .requires(ModItems.CRYSTAL_EMBLEM.get())
                .requires(ModItems.DUST_EMBLEM.get())
                .requires(ModItems.BONE_EMBLEM.get())
                .requires(Items.DIAMOND)
                .requires(ModItems.SHIELDED_FOSSIL.get(), 4)
                .unlockedBy(getHasName(ModItems.STONE_EMBLEM.get()), has(ModItems.STONE_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WATER_EMBLEM.get(), 1)
                .requires(Items.CLAY, 2)
                .requires(Items.WATER_BUCKET)
                .requires(Items.KELP)
                .requires(Items.DIAMOND)
                .requires(ModItems.BULBULIUM.get(), 4)
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BLIZZARD_EMBLEM.get(), 1)
                .requires(Items.SNOWBALL, 3)
                .requires(Ingredient.of(ItemTags.SHOVELS))
                .requires(Items.IRON_INGOT)
                .requires(Items.SNOW_BLOCK, 2)
                .requires(ModItems.FRACTURED_ICICLE.get(), 2)
                .unlockedBy(getHasName(Items.SNOW), has(Items.SNOW))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FROST_EMBLEM.get(), 1)
                .requires(Items.SNOW_BLOCK, 3)
                .requires(Items.POWDER_SNOW_BUCKET)
                .requires(Items.IRON_BLOCK)
                .requires(Items.SNOWBALL, 2)
                .requires(ModItems.FRACTURED_ICICLE.get(), 2)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SNOWSTORM_EMBLEM.get(), 1)
                .requires(ModItems.ICE_EMBLEM.get())
                .requires(ModItems.BLIZZARD_EMBLEM.get())
                .requires(Items.DIAMOND)
                .requires(Items.SNOW_BLOCK, 2)
                .requires(ModItems.FRACTURED_ICICLE.get(), 4)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.COILED_EMBLEM.get(), 1)
                .requires(Items.IRON_INGOT, 3)
                .requires(Items.COPPER_INGOT, 3)
                .requires(Items.LIGHTNING_ROD)
                .requires(ModItems.IRON_PLATING.get(), 2)
                .unlockedBy(getHasName(Items.LIGHTNING_ROD), has(Items.LIGHTNING_ROD))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SPARK_EMBLEM.get(), 1)
                .requires(Items.IRON_INGOT, 2)
                .requires(Items.IRON_NUGGET, 2)
                .requires(Items.COPPER_INGOT, 2)
                .requires(Items.LIGHTNING_ROD)
                .requires(ModItems.IRON_PLATING.get(), 2)
                .unlockedBy(getHasName(Items.LIGHTNING_ROD), has(Items.LIGHTNING_ROD))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.CONDUCTOR_EMBLEM.get(), 1)
                .requires(Items.IRON_BLOCK)
                .requires(Items.IRON_INGOT, 3)
                .requires(Items.COPPER_BLOCK)
                .requires(Items.LIGHTNING_ROD)
                .requires(ModItems.IRON_PLATING.get(), 3)
                .unlockedBy(getHasName(Items.LIGHTNING_ROD), has(Items.LIGHTNING_ROD))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.HEAVEN_EMBLEM.get(), 1)
                .requires(ModItems.DAY_EMBLEM.get())
                .requires(ModItems.NIGHT_EMBLEM.get())
                .requires(ModItems.SKY_EMBLEM.get())
                .requires(Items.DIAMOND)
                .requires(ModItems.GLASS_LUNG.get(), 3)
                .requires(ModItems.SUNLIGHT_SHARD.get(), 2)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RETURN_EMBLEM.get(), 1)
                .requires(ModItems.HEAVEN_EMBLEM.get())
                .requires(ModItems.CAVE_EMBLEM.get())
                .requires(ModItems.TREE_EMBLEM.get())
                .requires(Items.DIAMOND_BLOCK, 1)
                .requires(ModItems.BREAD_PIECE.get(), 2)
                .requires(ModItems.THE_TORCH.get(), 3)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WARRIOR_EMBLEM.get(), 1)
                .requires(Items.STONE, 4)
                .requires(Items.SHIELD)
                .requires(Items.STONE, 4)
                .unlockedBy(getHasName(Items.SHIELD), has(Items.SHIELD))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.KNIGHT_EMBLEM.get(), 1)
                .requires(Items.STONE, 2)
                .requires(Items.IRON_SWORD)
                .requires(Items.STONE_SWORD)
                .unlockedBy(getHasName(Items.STONE_SWORD), has(Items.STONE_SWORD))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ROTTEN_EMBLEM.get(), 1)
                .requires(Items.ROTTEN_FLESH, 4)
                .requires(Items.IRON_INGOT)
                .requires(Items.ROTTEN_FLESH, 4)
                .unlockedBy(getHasName(Items.ROTTEN_FLESH), has(Items.ROTTEN_FLESH))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.GOLDEN_EMBLEM.get(), 1)
                .requires(Items.GOLD_BLOCK, 4)
                .requires(Items.COMPASS)
                .requires(Items.SOUL_SOIL, 2)
                .requires(Items.GILDED_BLACKSTONE, 2)
                .unlockedBy(getHasName(Items.COMPASS), has(Items.COMPASS))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ELECTRO_EMBLEM.get(), 1)
                .requires(Items.COPPER_BLOCK)
                .requires(Items.LIGHTNING_ROD)
                .requires(Items.DIAMOND, 2)
                .requires(Items.IRON_BLOCK, 2)
                .requires(ModItems.IRON_PLATING.get(), 2)
                .requires(ModItems.BATTERY.get())
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.GAMBLER_EMBLEM.get(), 1)
                .requires(ModItems.SPADE_EMBLEM.get())
                .requires(ModItems.CLOVER_EMBLEM.get())
                .requires(ModItems.HEART_EMBLEM.get())
                .requires(ModItems.DIAMONDS_EMBLEM.get())
                .requires(Items.DIAMOND, 3)
                .requires(ModItems.JACK_OF_SPADES.get(), 2)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SMOKE_EMBLEM.get(), 1)
                .requires(Items.CAMPFIRE, 3)
                .requires(Items.COAL_BLOCK, 3)
                .requires(Items.HAY_BLOCK, 3)
                .unlockedBy(getHasName(Items.COAL_BLOCK), has(Items.COAL_BLOCK))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.EASTER_EGGBLEM.get(), 1)
                .requires(Items.BREAD)
                .requires(Items.RABBIT_STEW)
                .requires(Items.WHITE_WOOL)
                .requires(Ingredient.of(ItemTags.WOOL))
                .requires(Items.EGG, 3)
                .requires(ModItems.THE_TORCH.get(), 2)
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.STEAM_EMBLEM.get(), 1)
                .requires(Items.WATER_BUCKET)
                .requires(Items.NETHERRACK, 2)
                .requires(ModItems.BULBULIUM.get(), 1)
                .requires(ModItems.BURNING_CLOTH.get(), 1)
                .requires(Items.SNOWBALL,3)
                .unlockedBy(getHasName(Items.ICE), has(Items.ICE))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.AURIC_EMBLEM.get(), 1)
                .requires(ModItems.INFERNO_EMBLEM.get())
                .requires(ModItems.BLIZZARD_EMBLEM.get())
                .requires(ModItems.COILED_EMBLEM.get())
                .requires(ModItems.STEAM_EMBLEM.get())
                .requires(ModItems.THE_EARTH.get(), 2)
                .requires(ModItems.COSMOS_ESSENCE.get())
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.INDUCTION_EMBLEM.get(), 1)
                .requires(ModItems.SPARK_EMBLEM.get())
                .requires(ModItems.CONDUCTOR_EMBLEM.get())
                .requires(Items.IRON_BLOCK)
                .requires(Items.COPPER_BLOCK)
                .requires(Items.DIAMOND)
                .requires(Items.LIGHTNING_ROD, 2)
                .requires(ModItems.IRON_PLATING.get(), 2)
                .unlockedBy(getHasName(Items.LIGHTNING_ROD), has(Items.LIGHTNING_ROD))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.OVERCHARGED_EMBLEM.get(), 1)
                .requires(ModItems.ELECTRO_EMBLEM.get())
                .requires(ModItems.COILED_EMBLEM.get())
                .requires(Items.COPPER_BLOCK)
                .requires(Items.LIGHTNING_ROD, 2)
                .requires(Items.GOLD_BLOCK, 2)
                .requires(ModItems.IRON_PLATING.get(), 2)
                .unlockedBy(getHasName(Items.LIGHTNING_ROD), has(Items.LIGHTNING_ROD))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.THUNDER_EMBLEM.get(), 1)
                .requires(ModItems.OVERCHARGED_EMBLEM.get())
                .requires(ModItems.INDUCTION_EMBLEM.get())
                .requires(ModItems.BATTERY.get(), 4)
                .requires(ModItems.TESLA_BAR.get(), 2)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BUBBLE_EMBLEM.get(), 1)
                .requires(Items.MAGMA_BLOCK)
                .requires(ModItems.BULBULIUM.get(), 3)
                .requires(Items.WATER_BUCKET)
                .requires(Items.SEAGRASS,4)
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BARTERER_EMBLEM.get(), 1)
                .requires(Items.SMOKER)
                .requires(ModItems.COIN_NECKLACE.get())
                .requires(Items.ANVIL)
                .requires(Items.CAULDRON)
                .requires(ModItems.MERCHANT_EMBLEM.get())
                .requires(Items.BLAST_FURNACE)
                .requires(Items.COMPOSTER)
                .requires(Items.DIAMOND_BLOCK)
                .requires(Items.STONECUTTER)
                .unlockedBy(getHasName(ModItems.MERCHANT_EMBLEM.get()), has(ModItems.MERCHANT_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.HOURGLASS_EMBLEM.get(), 1)
                .requires(Items.SAND, 1)
                .requires(Items.GLASS, 1)
                .requires(ModItems.DAY_EMBLEM.get())
                .requires(ModItems.HEAVEN_EMBLEM.get())
                .requires(ModItems.NIGHT_EMBLEM.get())
                .requires(ModItems.COSMOS_ESSENCE.get(), 2)
                .requires(ModItems.BREAD_PIECE.get(), 2)
                .unlockedBy(getHasName(ModItems.HEAVEN_EMBLEM.get()), has(ModItems.HEAVEN_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MIRROR_EMBLEM.get(), 1)
                .requires(Items.ENDER_PEARL)
                .requires(ModItems.REFERENCIUM.get())
                .requires(Items.GLASS, 3)
                .requires(Items.IRON_INGOT, 3)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.TANK_EMBLEM.get(), 1)
                .requires(Items.IRON_BLOCK, 2)
                .requires(Items.DIAMOND)
                .requires(ModItems.BONE_EMBLEM.get())
                .requires(ModItems.WOOD_EMBLEM.get(), 2)
                .requires(ModItems.WARRIOR_EMBLEM.get())
                .requires(ModItems.IRON_PLATING.get(), 2)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.GLADIATOR_EMBLEM.get(), 1)
                .requires(Items.IRON_INGOT, 2)
                .requires(ModItems.STONE_EMBLEM.get())
                .requires(ModItems.GROUND_EMBLEM.get(), 2)
                .requires(ModItems.KNIGHT_EMBLEM.get())
                .requires(ModItems.IRON_PLATING.get(), 2)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RUTHLESS_GUARDIAN_EMBLEM.get(), 1)
                .requires(Items.DIAMOND, 2)
                .requires(Items.OBSIDIAN, 2)
                .requires(Items.CRYING_OBSIDIAN)
                .requires(ModItems.TANK_EMBLEM.get())
                .requires(ModItems.ANCIENT_REMAINS.get(), 2)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ASSASSIN_EMBLEM.get(), 1)
                .requires(Items.DIAMOND, 2)
                .requires(Items.QUARTZ_BLOCK, 2)
                .requires(Items.SMOOTH_QUARTZ)
                .requires(ModItems.GLADIATOR_EMBLEM.get())
                .requires(ModItems.ANCIENT_REMAINS.get(), 2)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PYRO_EMBLEM.get(), 1)
                .requires(Items.MAGMA_BLOCK, 2)
                .requires(Items.IRON_BOOTS)
                .requires(Items.OBSIDIAN, 4)
                .requires(ModItems.BURNING_CLOTH.get(), 2)
                .unlockedBy(getHasName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SOUL_EMBLEM.get(), 1)
                .requires(Items.SOUL_SOIL, 2)
                .requires(Items.IRON_BLOCK)
                .requires(ModItems.BREAD_PIECE.get(), 2)
                .requires(Items.SOUL_SAND, 2)
                .requires(Items.DIAMOND, 2)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MOON_WRATH_EMBLEM.get(), 1)
                .requires(Items.TNT, 2)
                .requires(ModItems.MOON_FRAGMENT.get(), 2)
                .unlockedBy(getHasName(Items.TNT), has(Items.TNT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ORCHID_EMBLEM.get(), 1)
                .requires(ModItems.FLOWER_EMBLEM.get())
                .requires(ModItems.FARMER_EMBLEM.get())
                .requires(ModItems.SEED_POUCH.get())
                .requires(ModItems.VERDANT_CORE.get())
                .requires(Items.DIAMOND)
                .requires(Items.BLUE_ORCHID, 3)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.CACTUS_EMBLEM.get(), 1)
                .requires(Items.CACTUS, 4)
                .requires(Items.IRON_INGOT)
                .requires(Items.CACTUS, 4)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BAMBOO_EMBLEM.get(), 1)
                .requires(Items.BAMBOO, 4)
                .requires(Items.GOLD_INGOT)
                .requires(Items.COPPER_INGOT, 4)
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.IVY_EMBLEM.get(), 1)
                .requires(Items.VINE, 4)
                .requires(Items.IRON_INGOT)
                .requires(Items.GLOW_LICHEN, 4)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ROSE_EMBLEM.get(), 1)
                .requires(Items.ROSE_BUSH, 4)
                .requires(Items.IRON_INGOT)
                .requires(Items.IRON_NUGGET, 4)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.IBARA_EMBLEM.get(), 1)
                .requires(ModItems.ROSE_EMBLEM.get())
                .requires(ModItems.CACTUS_EMBLEM.get())
                .requires(ModItems.VERDANT_CORE.get())
                .requires(Items.IRON_NUGGET)
                .requires(Items.IRON_BLOCK)
                .requires(Items.CACTUS, 2)
                .requires(Items.ROSE_BUSH, 2)
                .unlockedBy(getHasName(Items.ROSE_BUSH), has(Items.ROSE_BUSH))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SUNFLOWER_EMBLEM.get(), 1)
                .requires(ModItems.FLOWER_EMBLEM.get())
                .requires(Items.SUNFLOWER, 7)
                .requires(Items.DIAMOND)
                .unlockedBy(getHasName(Items.SUNFLOWER), has(Items.SUNFLOWER))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.NIGHTBLOOM_EMBLEM.get(), 1)
                .requires(ModItems.FLOWER_EMBLEM.get())
                .requires(Items.AZURE_BLUET, 7)
                .requires(Items.DIAMOND)
                .unlockedBy(getHasName(Items.AZURE_BLUET), has(Items.AZURE_BLUET))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BLACK_ROSE_EMBLEM.get(), 1)
                .requires(ModItems.ROSE_EMBLEM.get())
                .requires(ModItems.SEED_POUCH.get())
                .requires(Items.WITHER_ROSE)
                .requires(Items.ROSE_BUSH, 6)
                .unlockedBy(getHasName(Items.WITHER_ROSE), has(Items.WITHER_ROSE))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DRIED_ROSE_EMBLEM.get(), 1)
                .requires(ModItems.ROSE_EMBLEM.get())
                .requires(ModItems.STEAM_EMBLEM.get())
                .requires(ModItems.SEED_POUCH.get())
                .requires(Items.DEAD_BUSH, 6)
                .unlockedBy(getHasName(Items.DEAD_BUSH), has(Items.DEAD_BUSH))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WITHER_ROSE_EMBLEM.get(), 1)
                .requires(ModItems.BLACK_ROSE_EMBLEM.get())
                .requires(ModItems.DRIED_ROSE_EMBLEM.get())
                .requires(ModItems.SEED_POUCH.get(), 2)
                .requires(ModItems.BONSAI.get())
                .requires(Items.WITHER_ROSE, 1)
                .requires(Items.DEAD_BUSH, 3)
                .unlockedBy(getHasName(Items.WITHER_ROSE), has(Items.WITHER_ROSE))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BOUQUET_EMBLEM.get(), 1)
                .requires(ModItems.ROSE_EMBLEM.get())
                .requires(ModItems.SUNFLOWER_EMBLEM.get())
                .requires(ModItems.NIGHTBLOOM_EMBLEM.get())
                .requires(ModItems.FLOWER_EMBLEM.get())
                .requires(ModItems.ORCHID_EMBLEM.get())
                .requires(ModItems.TINY_BUSH.get())
                .requires(ModItems.BONSAI.get())
                .requires(Items.DIAMOND, 2)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.VINE_EMBLEM.get(), 1)
                .requires(ModItems.BAMBOO_EMBLEM.get())
                .requires(ModItems.IVY_EMBLEM.get())
                .requires(ModItems.VERDANT_CORE.get(), 2)
                .requires(ModItems.SEED_POUCH.get())
                .requires(Items.TWISTING_VINES, 2)
                .requires(Items.WEEPING_VINES, 2)
                .unlockedBy(getHasName(Items.TWISTING_VINES), has(Items.TWISTING_VINES))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PRICKLE_VINE_EMBLEM.get(), 1)
                .requires(ModItems.VINE_EMBLEM.get())
                .requires(ModItems.IBARA_EMBLEM.get())
                .requires(ModItems.VERDANT_CORE.get())
                .requires(ModItems.SEED_POUCH.get(), 2)
                .requires(Items.DIAMOND, 2)
                .requires(Items.IRON_BLOCK, 2)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FLOWERFIELD_EMBLEM.get(), 1)
                .requires(ModItems.PRICKLE_VINE_EMBLEM.get())
                .requires(ModItems.BOUQUET_EMBLEM.get())
                .requires(ModItems.GROUND_EMBLEM.get())
                .requires(ModItems.TINY_BUSH.get())
                .requires(ModItems.VERDANT_CORE.get(), 3)
                .requires(Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RAINFOREST_EMBLEM.get(), 1)
                .requires(ModItems.TREE_EMBLEM.get(), 2)
                .requires(ModItems.PLANT_EMBLEM.get())
                .requires(ModItems.TINY_BUSH.get())
                .requires(ModItems.SEED_POUCH.get(), 3)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DEADFOREST_EMBLEM.get(), 1)
                .requires(ModItems.RAINFOREST_EMBLEM.get())
                .requires(ModItems.WITHER_ROSE_EMBLEM.get())
                .requires(ModItems.TINY_BUSH.get(),2)
                .requires(Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FLOWERFOREST_EMBLEM.get(), 1)
                .requires(ModItems.DEADFOREST_EMBLEM.get())
                .requires(ModItems.FLOWERFIELD_EMBLEM.get())
                .requires(ModItems.SUNFLOWER_EMBLEM.get())
                .requires(ModItems.NIGHTBLOOM_EMBLEM.get())
                .requires(ModItems.TINY_BUSH.get(), 3)
                .requires(Items.NETHERITE_INGOT)
                .requires(Items.DIAMOND_BLOCK)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DANDELION_EMBLEM.get(), 1)
                .requires(ModItems.FLOWER_EMBLEM.get())
                .requires(Items.DANDELION, 8)
                .unlockedBy(getHasName(Items.DANDELION), has(Items.DANDELION))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RED_SPIDER_LILY_EMBLEM.get(), 1)
                .requires(ModItems.FLOWER_EMBLEM.get())
                .requires(Items.PHANTOM_MEMBRANE, 8)
                .unlockedBy(getHasName(Items.PHANTOM_MEMBRANE), has(Items.PHANTOM_MEMBRANE))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BLUE_SPIDER_LILY_EMBLEM.get(), 1)
                .requires(ModItems.FLOWER_EMBLEM.get())
                .requires(Items.PRISMARINE_SHARD, 8)
                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_SHARD))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MIST_EMBLEM.get(), 1)
                .requires(Items.WATER_BUCKET)
                .requires(ModItems.BULBULIUM.get(), 4)
                .requires(Items.SNOW_BLOCK, 3)
                .unlockedBy(getHasName(Items.ICE), has(Items.ICE))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.TIDE_EMBLEM.get(), 1)
                .requires(Items.WATER_BUCKET)
                .requires(Items.KELP, 2)
                .requires(ModItems.BULBULIUM.get(), 3)
                .requires(Items.IRON_INGOT, 3)
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RAIN_EMBLEM.get(), 1)
                .requires(Items.WATER_BUCKET)
                .requires(ModItems.BULBULIUM.get(), 1)
                .requires(Items.IRON_INGOT, 3)
                .requires(Items.GLASS_BOTTLE, 3)
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SPRINKLER_EMBLEM.get(), 1)
                .requires(Items.WATER_BUCKET)
                .requires(Items.IRON_INGOT, 3)
                .requires(Items.COPPER_INGOT, 3)
                .requires(ModItems.IRON_PLATING.get(), 2)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.TEAR_EMBLEM.get(), 1)
                .requires(ModItems.BULBULIUM.get(), 4)
                .requires(Items.IRON_INGOT, 5)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PUDDLE_EMBLEM.get(), 1)
                .requires(ModItems.BULBULIUM.get())
                .requires(Items.PUFFERFISH_BUCKET)
                .requires(ModItems.BENT_FIN.get(), 2)
                .unlockedBy(getHasName(Items.PUFFERFISH_BUCKET), has(Items.PUFFERFISH_BUCKET))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MUD_EMBLEM.get(), 1)
                .requires(ModItems.BULBULIUM.get())
                .requires(Items.MUD, 2)
                .requires(Items.DIAMOND)
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MARSH_EMBLEM.get(), 1)
                .requires(ModItems.BULBULIUM.get(), 2)
                .requires(ModItems.MUD_EMBLEM.get())
                .requires(ModItems.STEAM_EMBLEM.get())
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BOG_EMBLEM.get(), 1)
                .requires(ModItems.BULBULIUM.get(), 2)
                .requires(ModItems.PUDDLE_EMBLEM.get())
                .requires(ModItems.MIST_EMBLEM.get())
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WAVE_EMBLEM.get(), 1)
                .requires(ModItems.BULBULIUM.get(), 3)
                .requires(ModItems.RAIN_EMBLEM.get())
                .requires(ModItems.WATER_EMBLEM.get())
                .requires(Items.DIAMOND)
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WHIRL_EMBLEM.get(), 1)
                .requires(ModItems.BULBULIUM.get(), 5)
                .requires(ModItems.TIDE_EMBLEM.get())
                .requires(ModItems.BUBBLE_EMBLEM.get())
                .requires(Items.DIAMOND)
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.VORTEX_EMBLEM.get(), 1)
                .requires(ModItems.NEPTUNES_GIFT.get(), 2)
                .requires(ModItems.WHIRL_EMBLEM.get())
                .requires(ModItems.TEAR_EMBLEM.get())
                .requires(ModItems.MOON_EMBLEM.get())
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SWAMP_EMBLEM.get(), 1)
                .requires(ModItems.NEPTUNES_GIFT.get())
                .requires(ModItems.BULBULIUM.get(), 3)
                .requires(ModItems.BOG_EMBLEM.get())
                .requires(ModItems.MARSH_EMBLEM.get())
                .requires(Items.DIAMOND)
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.CURRENT_EMBLEM.get(), 1)
                .requires(ModItems.NEPTUNES_GIFT.get())
                .requires(ModItems.BULBULIUM.get(), 3)
                .requires(ModItems.WAVE_EMBLEM.get())
                .requires(ModItems.SPRINKLER_EMBLEM.get())
                .requires(Items.DIAMOND, 2)
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MUCKMIRE_EMBLEM.get(), 1)
                .requires(ModItems.NEPTUNES_GIFT.get(), 6)
                .requires(ModItems.CURRENT_EMBLEM.get())
                .requires(ModItems.SWAMP_EMBLEM.get())
                .requires(Items.DIAMOND_BLOCK)
                .unlockedBy(getHasName(ModItems.BULBULIUM.get()), has(ModItems.BULBULIUM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MAELSTROM_EMBLEM.get(), 1)
                .requires(ModItems.NEPTUNES_GIFT.get(), 5)
                .requires(ModItems.VORTEX_EMBLEM.get())
                .requires(ModItems.FULL_MOON_EMBLEM.get())
                .requires(Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(ModItems.NEPTUNES_GIFT.get()), has(ModItems.NEPTUNES_GIFT.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FORBIDDEN_OASIS_EMBLEM.get(), 1)
                .requires(ModItems.NEPTUNES_GIFT.get(), 3)
                .requires(ModItems.MAELSTROM_EMBLEM.get())
                .requires(ModItems.MUCKMIRE_EMBLEM.get())
                .requires(ModItems.SPRINKLER_EMBLEM.get(),2)
                .requires(Items.NETHERITE_INGOT, 2)
                .unlockedBy(getHasName(ModItems.NEPTUNES_GIFT.get()), has(ModItems.NEPTUNES_GIFT.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FLORIST_EMBLEM.get(), 1)
                .requires(ModItems.SEED_POUCH.get(), 4)
                .requires(ModItems.FLOWER_EMBLEM.get())
                .requires(ModItems.COIN_NECKLACE.get(), 4)
                .unlockedBy(getHasName(ModItems.SEED_POUCH.get()), has(ModItems.SEED_POUCH.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RANDOM_EMBLEM.get(), 1)
                .requires(ModItems.COSMOS_ESSENCE.get(), 4)
                .requires(Items.DIAMOND_BLOCK)
                .requires(ModItems.COSMOS_ESSENCE.get(), 4)
                .unlockedBy(getHasName(ModItems.COSMOS_ESSENCE.get()), has(ModItems.COSMOS_ESSENCE.get()))
                .save(pWriter);



        //Rupec's Elytras
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.GLIDER_EMBLEM.get(), 1)
                .requires(ModItems.GLASS_LUNG.get(), 3)
                .requires(ModItems.FEATHER_EMBLEM.get())
                .requires(Items.FEATHER, 3)
                .requires(Items.PHANTOM_MEMBRANE, 2)
                .unlockedBy(getHasName(ModItems.GLASS_LUNG.get()), has(ModItems.GLASS_LUNG.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FEATHER_EMBLEM.get(), 1)
                .requires(Items.FEATHER, 8)
                .requires(Items.IRON_INGOT, 1)
                .unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WIND_CATCHER_EMBLEM.get(), 1)
                .requires(ModItems.GLASS_LUNG.get(), 8)
                .requires(ModItems.GLIDER_EMBLEM.get(), 1)
                .unlockedBy(getHasName(ModItems.GLIDER_EMBLEM.get()), has(ModItems.GLIDER_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DASHER_EMBLEM.get(), 1)
                .requires(ModItems.GLASS_LUNG.get(), 3)
                .requires(ModItems.FEATHER_EMBLEM.get(), 1)
                .requires(Items.FEATHER, 5)
                .unlockedBy(getHasName(ModItems.FEATHER_EMBLEM.get()), has(ModItems.FEATHER_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PHOENIX_EMBLEM.get(), 1)
                .requires(ModItems.PHOENIX_FEATHER.get(), 3)
                .requires(ModItems.FEATHER_EMBLEM.get(), 1)
                .requires(Items.GUNPOWDER, 5)
                .unlockedBy(getHasName(ModItems.FEATHER_EMBLEM.get()), has(ModItems.FEATHER_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PHOENIX_WING_EMBLEM.get(), 1)
                .requires(ModItems.PHOENIX_FEATHER.get(), 3)
                .requires(ModItems.GLASS_LUNG.get(), 3)
                .requires(ModItems.FEATHER_EMBLEM.get(), 1)
                .requires(Items.FEATHER, 2)
                .unlockedBy(getHasName(ModItems.FEATHER_EMBLEM.get()), has(ModItems.FEATHER_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.TRUE_PHOENIX_EMBLEM.get(), 1)
                .requires(ModItems.PHOENIX_FEATHER.get(), 6)
                .requires(ModItems.PHOENIX_EMBLEM.get(), 1)
                .requires(ModItems.PHOENIX_WING_EMBLEM.get(), 1)
                .requires(ModItems.INFERNO_EMBLEM.get(), 1)
                .unlockedBy(getHasName(ModItems.PHOENIX_EMBLEM.get()), has(ModItems.PHOENIX_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.HUMMING_BIRD_EMBLEM.get(), 1)
                .requires(ModItems.DOVE_WING.get(), 3)
                .requires(ModItems.WIND_CATCHER_EMBLEM.get(), 1)
                .requires(Items.PHANTOM_MEMBRANE, 5)
                .unlockedBy(getHasName(ModItems.WIND_CATCHER_EMBLEM.get()), has(ModItems.WIND_CATCHER_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MOUNTAIN_WIND_EMBLEM.get(), 1)
                .requires(ModItems.DOVE_WING.get(), 5)
                .requires(ModItems.HUMMING_BIRD_EMBLEM.get(), 1)
                .requires(ModItems.THE_EARTH.get(), 3)
                .unlockedBy(getHasName(ModItems.HUMMING_BIRD_EMBLEM.get()), has(ModItems.HUMMING_BIRD_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WEIGHTLESS_EMBLEM.get(), 1)
                .requires(ModItems.EAGLE_BEAK.get(), 3)
                .requires(ModItems.MOUNTAIN_WIND_EMBLEM.get(), 1)
                .requires(ModItems.DOVE_WING.get(), 3)
                .unlockedBy(getHasName(ModItems.MOUNTAIN_WIND_EMBLEM.get()), has(ModItems.MOUNTAIN_WIND_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WING_POWER_EMBLEM.get(), 1)
                .requires(ModItems.EAGLE_BEAK.get(), 3)
                .requires(ModItems.WEIGHTLESS_EMBLEM.get(), 1)
                .requires(ModItems.COSMOS_ESSENCE.get(), 5)
                .unlockedBy(getHasName(ModItems.WEIGHTLESS_EMBLEM.get()), has(ModItems.WEIGHTLESS_EMBLEM.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SKY_WANDERER_EMBLEM.get(), 1)
                .requires(ModItems.EAGLE_BEAK.get())
                .requires(ModItems.TANGLED_TAILS.get())
                .requires(ModItems.WING_POWER_EMBLEM.get(), 2)
                .requires(ModItems.TANGLED_TAILS.get())
                .requires(ModItems.EAGLE_BEAK.get())
                .unlockedBy(getHasName(ModItems.WING_POWER_EMBLEM.get()), has(ModItems.WING_POWER_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MOTHER_EARTH_EMBLEM.get(), 1)
                .requires(ModItems.EDENS_VINES.get())
                .requires(ModItems.PLANT_SIGIL_1.get())
                .requires(ModItems.FLOWERFOREST_EMBLEM.get(), 2)
                .requires(ModItems.EDENS_VINES.get())
                .requires(ModItems.PLANT_SIGIL_2.get())
                .unlockedBy(getHasName(ModItems.FLOWERFOREST_EMBLEM.get()), has(ModItems.FLOWERFOREST_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.UNDERWORLD_EMBLEM.get(), 1)
                .requires(ModItems.BLACK_PEARL.get())
                .requires(ModItems.BOULDER_EMBLEM.get())
                .requires(ModItems.BRITTLE_FOSSIL.get())
                .requires(ModItems.ATOMIC_CAVE_EMBLEM.get(), 2)
                .requires(ModItems.BLACK_PEARL.get())
                .requires(ModItems.CLAY_EMBLEM.get())
                .requires(ModItems.BRITTLE_FOSSIL.get())
                .unlockedBy(getHasName(ModItems.ATOMIC_CAVE_EMBLEM.get()), has(ModItems.ATOMIC_CAVE_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BUTCHER_EMBLEM.get(), 1)
                .requires(ModItems.BENT_FIN.get(), 4)
                .requires(Ingredient.of(ModTags.Items.MEAT), 5)
                .unlockedBy(getHasName(ModItems.BENT_FIN.get()), has(ModItems.BENT_FIN.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SKIN_TRANSPLANT_EMBLEM.get(), 1)
                .requires(ModItems.BENT_FIN.get(), 4)
                .requires(ModItems.COSMOS_ESSENCE.get())
                .requires(ModItems.BENT_FIN.get(), 4)
                .unlockedBy(getHasName(ModItems.BENT_FIN.get()), has(ModItems.BENT_FIN.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ARCHER_EMBLEM.get(), 1)
                .requires(ModItems.BONSAI.get(), 2)
                .requires(Items.BOW)
                .requires(ModItems.COIN_NECKLACE.get())
                .requires(Items.ARROW, 5)
                .unlockedBy(getHasName(ModItems.BONSAI.get()), has(ModItems.BONSAI.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SMELTER_EMBLEM.get(), 1)
                .requires(ModItems.SHIELDED_FOSSIL.get(), 2)
                .requires(ModItems.ANCIENT_REMAINS.get())
                .requires(Ingredient.of(ItemTags.PICKAXES))
                .requires(Ingredient.of(Items.BLAST_FURNACE))
                .requires(Ingredient.of(Items.LAVA_BUCKET))
                .requires(ModItems.BURNING_CLOTH.get(), 2)
                .requires(ModItems.COIN_NECKLACE.get())
                .unlockedBy(getHasName(ModItems.COIN_NECKLACE.get()), has(ModItems.COIN_NECKLACE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BURNER_EMBLEM.get(), 1)
                .requires(Ingredient.of(ItemTags.COALS), 3)
                .requires(Ingredient.of(ItemTags.PICKAXES))
                .requires(Ingredient.of(Items.FURNACE))
                .requires(ModItems.ANCIENT_REMAINS.get())
                .requires(ModItems.BURNING_CLOTH.get(), 2)
                .requires(ModItems.COIN_NECKLACE.get())
                .unlockedBy(getHasName(ModItems.COIN_NECKLACE.get()), has(ModItems.COIN_NECKLACE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SMOKING_EMBLEM.get(), 1)
                .requires(Ingredient.of(ItemTags.COALS), 3)
                .requires(Ingredient.of(ModTags.Items.MEAT))
                .requires(Ingredient.of(Items.SMOKER))
                .requires(ModItems.BENT_FIN.get(), 3)
                .requires(ModItems.COIN_NECKLACE.get())
                .unlockedBy(getHasName(ModItems.COIN_NECKLACE.get()), has(ModItems.COIN_NECKLACE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.CLAY_EMBLEM.get(), 1)
                .requires(Ingredient.of(Items.CLAY), 6)
                .requires(ModItems.SHIELDED_FOSSIL.get(), 2)
                .requires(ModItems.MUD_EMBLEM.get())
                .unlockedBy(getHasName(ModItems.MUD_EMBLEM.get()), has(ModItems.MUD_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BOULDER_EMBLEM.get(), 1)
                .requires(Ingredient.of(Tags.Items.STONE), 4)
                .requires(ModItems.SHIELDED_FOSSIL.get(), 4)
                .requires(ModItems.STONE_EMBLEM.get())
                .unlockedBy(getHasName(ModItems.STONE_EMBLEM.get()), has(ModItems.STONE_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.STALAGMITE_EMBLEM.get(), 1)
                .requires(Ingredient.of(Items.POINTED_DRIPSTONE), 4)
                .requires(ModItems.SHIELDED_FOSSIL.get(), 4)
                .requires(ModItems.BONE_EMBLEM.get())
                .unlockedBy(getHasName(ModItems.BONE_EMBLEM.get()), has(ModItems.BONE_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.STALACTITE_EMBLEM.get(), 1)
                .requires(Ingredient.of(Items.POINTED_DRIPSTONE), 4)
                .requires(ModItems.SHIELDED_FOSSIL.get(), 4)
                .requires(ModItems.STONE_EMBLEM.get())
                .unlockedBy(getHasName(ModItems.STONE_EMBLEM.get()), has(ModItems.STONE_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.STALAGNATE_EMBLEM.get(), 1)
                .requires(Ingredient.of(Items.DRIPSTONE_BLOCK), 5)
                .requires(ModItems.ANCIENT_REMAINS.get(), 2)
                .requires(ModItems.STALAGMITE_EMBLEM.get())
                .requires(ModItems.STALACTITE_EMBLEM.get())
                .unlockedBy(getHasName(ModItems.STALACTITE_EMBLEM.get()), has(ModItems.STALACTITE_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.REMAINS_EMBLEM.get(), 1)
                .requires(ModItems.ANCIENT_REMAINS.get(), 3)
                .requires(ModItems.FOSSIL_EMBLEM.get())
                .unlockedBy(getHasName(ModItems.ANCIENT_REMAINS.get()), has(ModItems.ANCIENT_REMAINS.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.LUSH_CAVE_EMBLEM.get(), 1)
                .requires(ModItems.ANCIENT_REMAINS.get(), 3)
                .requires(ModItems.SEED_POUCH.get(), 3)
                .requires(ModItems.CAVE_EMBLEM.get())
                .requires(ModItems.VINE_EMBLEM.get())
                .requires(Items.DIAMOND)
                .unlockedBy(getHasName(ModItems.CAVE_EMBLEM.get()), has(ModItems.CAVE_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.STALACTITE_CAVE_EMBLEM.get(), 1)
                .requires(ModItems.ANCIENT_REMAINS.get(), 6)
                .requires(ModItems.CAVE_EMBLEM.get())
                .requires(ModItems.STALAGNATE_EMBLEM.get())
                .requires(Items.DIAMOND)
                .unlockedBy(getHasName(ModItems.CAVE_EMBLEM.get()), has(ModItems.CAVE_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DESERT_CAVE_EMBLEM.get(), 1)
                .requires(ModItems.ANCIENT_REMAINS.get(), 6)
                .requires(ModItems.CAVE_EMBLEM.get())
                .requires(ModItems.REMAINS_EMBLEM.get())
                .requires(Items.DIAMOND)
                .unlockedBy(getHasName(ModItems.CAVE_EMBLEM.get()), has(ModItems.CAVE_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MOUNTAIN_CAVE_EMBLEM.get(), 1)
                .requires(ModItems.ANCIENT_REMAINS.get(), 6)
                .requires(ModItems.CAVE_EMBLEM.get())
                .requires(ModItems.MOUNTAIN_EMBLEM.get())
                .requires(Items.DIAMOND)
                .unlockedBy(getHasName(ModItems.CAVE_EMBLEM.get()), has(ModItems.CAVE_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DOWNHILL_EMBLEM.get(), 1)
                .requires(Ingredient.of(ModTags.Items.FOOD), 3)
                .requires(Ingredient.of(ItemTags.DIRT), 3)
                .requires(Ingredient.of(Tags.Items.FEATHERS), 3)
                .unlockedBy(getHasName(ModItems.WOOD_EMBLEM.get()), has(ModItems.WOOD_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.DUNE_EMBLEM.get(), 1)
                .requires(ModItems.DOWNHILL_EMBLEM.get())
                .requires(ModItems.SAND_EMBLEM.get())
                .requires(ModItems.SHIELDED_FOSSIL.get(), 2)
                .unlockedBy(getHasName(ModItems.SHIELDED_FOSSIL.get()), has(ModItems.SHIELDED_FOSSIL.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.HILL_EMBLEM.get(), 1)
                .requires(ModItems.DUNE_EMBLEM.get())
                .requires(ModItems.BONE_EMBLEM.get())
                .requires(ModItems.SHIELDED_FOSSIL.get(), 2)
                .unlockedBy(getHasName(ModItems.SHIELDED_FOSSIL.get()), has(ModItems.SHIELDED_FOSSIL.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MOUNTAIN_EMBLEM.get(), 1)
                .requires(ModItems.HILL_EMBLEM.get())
                .requires(ModItems.ANCIENT_REMAINS.get(), 4)
                .requires(ModItems.SHIELDED_FOSSIL.get(), 4)
                .unlockedBy(getHasName(ModItems.SHIELDED_FOSSIL.get()), has(ModItems.SHIELDED_FOSSIL.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SISYPHUS_EMBLEM.get(), 1)
                .requires(ModItems.HILL_EMBLEM.get())
                .requires(ModItems.BOULDER_EMBLEM.get())
                .requires(ModItems.SHIELDED_FOSSIL.get(), 2)
                .requires(ModItems.BREAD_PIECE.get(), 2)
                .unlockedBy(getHasName(ModItems.SHIELDED_FOSSIL.get()), has(ModItems.SHIELDED_FOSSIL.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MOUNTAIN_RANGE_EMBLEM.get(), 1)
                .requires(ModItems.MOUNTAIN_EMBLEM.get(), 2)
                .requires(ModItems.ANCIENT_REMAINS.get())
                .requires(ModItems.RADIANT_GEODE.get())
                .unlockedBy(getHasName(ModItems.ANCIENT_REMAINS.get()), has(ModItems.ANCIENT_REMAINS.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RADIANT_CAVE_EMBLEM.get(), 1)
                .requires(ModItems.MOUNTAIN_CAVE_EMBLEM.get())
                .requires(ModItems.LUSH_CAVE_EMBLEM.get())
                .requires(ModItems.RADIANT_GEODE.get(), 4)
                .unlockedBy(getHasName(ModItems.RADIANT_GEODE.get()), has(ModItems.RADIANT_GEODE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.LIFELESS_CAVE_EMBLEM.get(), 1)
                .requires(ModItems.DESERT_CAVE_EMBLEM.get())
                .requires(ModItems.STALACTITE_CAVE_EMBLEM.get())
                .requires(ModItems.ANCIENT_REMAINS.get(), 4)
                .unlockedBy(getHasName(ModItems.ANCIENT_REMAINS.get()), has(ModItems.ANCIENT_REMAINS.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ATOMIC_CAVE_EMBLEM.get(), 1)
                .requires(ModItems.LIFELESS_CAVE_EMBLEM.get())
                .requires(ModItems.RADIANT_CAVE_EMBLEM.get())
                .requires(ModItems.MOUNTAIN_RANGE_EMBLEM.get())
                .requires(ModItems.ANCIENT_REMAINS.get())
                .requires(ModItems.RADIANT_GEODE.get())
                .requires(Items.REDSTONE_BLOCK)
                .requires(Items.DIAMOND, 3)
                .unlockedBy(getHasName(ModItems.ANCIENT_REMAINS.get()), has(ModItems.ANCIENT_REMAINS.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PETRIFIED_DANDELION_EMBLEM.get(), 1)
                .requires(ModItems.DANDELION_EMBLEM.get())
                .requires(ModItems.SHIELDED_FOSSIL.get())
                .unlockedBy(getHasName(ModItems.SHIELDED_FOSSIL.get()), has(ModItems.SHIELDED_FOSSIL.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.NOTE_EMBLEM.get(), 1)
                .requires(Items.NOTE_BLOCK)
                .requires(Items.IRON_NUGGET, 8)
                .unlockedBy(getHasName(Items.NOTE_BLOCK), has(Items.NOTE_BLOCK))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.CHORD_EMBLEM.get(), 1)
                .requires(ModItems.NOTE_EMBLEM.get())
                .requires(Items.IRON_INGOT, 4)
                .requires(Items.COAL, 4)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.UNTUNED_EMBLEM.get(), 1)
                .requires(Items.NOTE_BLOCK)
                .requires(Items.GLASS_BOTTLE, 3)
                .requires(Items.IRON_NUGGET, 2)
                .requires(Items.COAL, 2)
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FALSE_NOTE_EMBLEM.get(), 1)
                .requires(ModItems.UNTUNED_EMBLEM.get())
                .requires(Items.GLASS, 2)
                .requires(Items.PAPER, 2)
                .requires(Items.GOLD_INGOT, 2)
                .requires(Items.IRON_BLOCK)
                .unlockedBy(getHasName(Items.IRON_BLOCK), has(Items.IRON_BLOCK))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.LULLABY_EMBLEM.get(), 1)
                .requires(ModItems.NOTE_EMBLEM.get())
                .requires(Ingredient.of(ItemTags.WOOL), 6)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BEAT_EMBLEM.get(), 1)
                .requires(ModItems.NOTE_EMBLEM.get())
                .requires(Ingredient.of(Items.IRON_NUGGET), 6)
                .requires(Ingredient.of(ItemTags.WOOL), 2)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RHYTHM_EMBLEM.get(), 1)
                .requires(ModItems.FALSE_NOTE_EMBLEM.get())
                .requires(ModItems.CHORD_EMBLEM.get())
                .requires(ModItems.HALF_NOTE.get(), 2)
                .unlockedBy(getHasName(ModItems.HALF_NOTE.get()), has(ModItems.HALF_NOTE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SONG_EMBLEM.get(), 1)
                .requires(ModItems.BEAT_EMBLEM.get())
                .requires(ModItems.CHORD_EMBLEM.get())
                .requires(ModItems.HALF_NOTE.get(), 2)
                .unlockedBy(getHasName(ModItems.HALF_NOTE.get()), has(ModItems.HALF_NOTE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ANTHEM_EMBLEM.get(), 1)
                .requires(ModItems.CHORD_EMBLEM.get())
                .requires(ModItems.HALF_NOTE.get(), 3)
                .unlockedBy(getHasName(ModItems.HALF_NOTE.get()), has(ModItems.HALF_NOTE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.INDEPENDENT_EMBLEM.get(), 1)
                .requires(ModItems.ANTHEM_EMBLEM.get())
                .requires(ModItems.HALF_NOTE.get(), 3)
                .unlockedBy(getHasName(ModItems.HALF_NOTE.get()), has(ModItems.HALF_NOTE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PAPER_EMBLEM.get(), 1)
                .requires(Items.PAPER, 8)
                .requires(Items.DIAMOND, 1)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.CLOTH_EMBLEM.get(), 1)
                .requires(Items.LEATHER, 4)
                .requires(Items.RABBIT_HIDE, 4)
                .requires(Items.DIAMOND, 1)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SOIL_EMBLEM.get(), 1)
                .requires(Items.DIRT, 2)
                .requires(Items.COARSE_DIRT, 2)
                .requires(Items.CLAY, 2)
                .requires(Items.MUD, 2)
                .requires(ModItems.GROUND_EMBLEM.get(), 1)
                .unlockedBy(getHasName(Items.MUD), has(Items.MUD))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RICH_SOIL_EMBLEM.get(), 1)
                .requires(ModItems.VERDANT_CORE.get(), 3)
                .requires(ModItems.BONSAI.get(), 1)
                .requires(Items.IRON_INGOT, 2)
                .requires(ModItems.SOIL_EMBLEM.get(), 1)
                .requires(ModItems.FERTILIZED_EMBLEM.get(), 1)
                .unlockedBy(getHasName(Items.MUD), has(Items.MUD))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.NUTRITIOUS_EMBLEM.get(), 1)
                .requires(ModItems.VERDANT_CORE.get(), 3)
                .requires(Items.COMPOSTER, 1)
                .unlockedBy(getHasName(Items.COMPOSTER), has(Items.COMPOSTER))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ITEMIUM_EMBLEM.get(), 1)
                .requires(Ingredient.of(ModTags.Items.TIER_ONE_ITEMIUMS))
                .requires(Items.STICK, 7)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FINDERS_EMBLEM.get(), 1)
                .requires(ModItems.ITEMIUM_EMBLEM.get())
                .requires(ModItems.BURNING_CLOTH.get() )
                .requires(ModItems.SHIELDED_FOSSIL.get() )
                .requires(Items.IRON_NUGGET, 2)
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.COIN_EMBLEM.get(), 1)
                .requires(ModItems.FINDERS_EMBLEM.get())
                .requires(ModItems.COIN_NECKLACE.get(), 2)
                .requires(Items.IRON_INGOT, 2)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.HAPPY_EMBLEM.get(), 1)
                .requires(ModItems.COIN_EMBLEM.get())
                .requires(Ingredient.of(ModTags.Items.TIER_TWO_ITEMIUMS), 2)
                .requires(Items.GOLD_NUGGET, 2)
                .unlockedBy(getHasName(Items.GOLD_NUGGET), has(Items.GOLD_NUGGET))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PRAYERS_EMBLEM.get(), 1)
                .requires(ModItems.HAPPY_EMBLEM.get())
                .requires(Ingredient.of(ModTags.Items.TIER_TWO_ITEMIUMS), 2)
                .requires(ModItems.BREAD_PIECE.get(), 2)
                .requires(Items.GOLD_INGOT, 2)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.LUCKY_EMBLEM.get(), 1)
                .requires(ModItems.PRAYERS_EMBLEM.get(), 2)
                .requires(ModItems.REFERENCIUM.get(), 2)
                .requires(Items.GOLD_BLOCK, 2)
                .unlockedBy(getHasName(Items.GOLD_BLOCK), has(Items.GOLD_BLOCK))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.LEPRECHAUN_EMBLEM.get(), 1)
                .requires(ModItems.LUCKY_EMBLEM.get(), 2)
                .requires(ModItems.WINE_CUP.get(), 2)
                .requires(Items.GOLD_BLOCK, 5)
                .unlockedBy(getHasName(Items.GOLD_BLOCK), has(Items.GOLD_BLOCK))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.LOTTERY_EMBLEM.get(), 1)
                .requires(ModItems.LEPRECHAUN_EMBLEM.get())
                .requires(Ingredient.of(ModTags.Items.TIER_TWO_ITEMIUMS), 4)
                .requires(Items.DIAMOND, 3)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.TRAINED_EMBLEM.get(), 1)
                .requires(ModItems.LOTTERY_EMBLEM.get())
                .requires(Ingredient.of(ModTags.Items.TIER_THREE_ITEMIUMS), 2)
                .requires(Items.DIAMOND, 5)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.EXPERT_EMBLEM.get(), 1)
                .requires(ModItems.TRAINED_EMBLEM.get())
                .requires(Ingredient.of(ModTags.Items.TIER_THREE_ITEMIUMS), 2)
                .requires(Ingredient.of(ModTags.Items.TIER_TWO_ITEMIUMS), 2)
                .requires(Items.DIAMOND, 4)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MASTER_EMBLEM.get(), 1)
                .requires(ModItems.EXPERT_EMBLEM.get())
                .requires(Ingredient.of(ModTags.Items.TIER_THREE_ITEMIUMS), 4)
                .requires(Items.NETHERITE_SCRAP, 2)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.CHAMPION_EMBLEM.get(),  1)
                .requires(ModItems.MASTER_EMBLEM.get())
                .requires(Ingredient.of(ModTags.Items.TIER_FOUR_ITEMIUMS), 1)
                .requires(Items.NETHERITE_SCRAP, 6)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.WINNER_EMBLEM.get(), 1)
                .requires(ModItems.CHAMPION_EMBLEM.get())
                .requires(Ingredient.of(ModTags.Items.TIER_FOUR_ITEMIUMS), 1)
                .requires(Ingredient.of(ModTags.Items.TIER_THREE_ITEMIUMS), 1)
                .requires(Ingredient.of(ModTags.Items.TIER_TWO_ITEMIUMS), 1)
                .requires(Ingredient.of(ModTags.Items.TIER_ONE_ITEMIUMS), 1)
                .requires(Items.NETHERITE_INGOT, 1)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RULE_MASTER_EMBLEM.get(), 1)
                .requires(ModItems.WINNER_EMBLEM.get(), 2)
                .requires(Ingredient.of(ModTags.Items.TIER_FOUR_ITEMIUMS), 2)
                .requires(ModItems.JACK_OF_SPADES.get(), 2)
                .requires(Items.NETHERITE_INGOT, 2)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FORESEEN_EMBLEM.get(), 1)
                .requires(ModItems.RULE_MASTER_EMBLEM.get())
                .requires(ModItems.SUNRAY_BEAM.get(), 3)
                .requires(Items.NETHERITE_INGOT, 2)
                .requires(Items.DIAMOND_BLOCK, 2)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.THE_TRUTH_EMBLEM.get(), 1)
                .requires(ModItems.FORESEEN_EMBLEM.get())
                .requires(ModItems.WINE_CUP.get())
                .requires(Ingredient.of(ModTags.Items.TIER_FOUR_ITEMIUMS), 2)
                .requires(ModItems.SUNRAY_BEAM.get(), 2)
                .requires(Items.DIAMOND_BLOCK, 3)
                .unlockedBy(getHasName(Items.DIAMOND_BLOCK), has(Items.DIAMOND_BLOCK))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.CREATOR_BLESSED_EMBLEM.get(), 1)
                .requires(ModItems.THE_TRUTH_EMBLEM.get())
                .requires(Ingredient.of(ModTags.Items.TIER_FIVE_ITEMIUMS))
                .requires(Items.NETHERITE_INGOT, 2)
                .requires(Items.DIAMOND_BLOCK, 2)
                .requires(Items.NETHER_STAR, 1)
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ANVIL_EMBLEM.get(), 1)
                .requires(Items.IRON_INGOT, 8)
                .requires(Items.ANVIL)
                .unlockedBy(getHasName(Items.ANVIL), has(Items.ANVIL))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BLACKSMITH_EMBLEM.get(), 1)
                .requires(ModItems.ANVIL_EMBLEM.get())
                .requires(Items.LAVA_BUCKET)
                .requires(Items.IRON_BLOCK,2)
                .unlockedBy(getHasName(ModItems.ANVIL_EMBLEM.get()), has(ModItems.ANVIL_EMBLEM.get()))
                .save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FORGER_EMBLEM.get(), 1)
                .requires(ModItems.BLACKSMITH_EMBLEM.get())
                .requires(Items.DIAMOND_BLOCK)
                .requires(ModItems.THE_EARTH.get(),2)
                .unlockedBy(getHasName(ModItems.BLACKSMITH_EMBLEM.get()), has(ModItems.BLACKSMITH_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.GOD_FORGE_EMBLEM.get(), 1)
                .requires(ModItems.FORGER_EMBLEM.get())
                .requires(Items.NETHERITE_SCRAP)
                .requires(ModItems.PROMETHEUS_GIFT.get(),2)
                .unlockedBy(getHasName(ModItems.BLACKSMITH_EMBLEM.get()), has(ModItems.BLACKSMITH_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.HEPHAESTUS_EMBLEM.get(), 1)
                .requires(ModItems.GOD_FORGE_EMBLEM.get())
                .requires(Items.NETHERITE_INGOT)
                .requires(ModItems.SHATTERED_VOID.get(),2)
                .unlockedBy(getHasName(ModItems.GOD_FORGE_EMBLEM.get()), has(ModItems.GOD_FORGE_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.FARMER_APPRENTICE_EMBLEM.get(), 1)
                .requires(ModItems.COIN_NECKLACE.get(),2)
                .requires(Ingredient.of(Tags.Items.SEEDS),4)
                .requires(ItemTags.HOES)
                .unlockedBy(getHasName(ModItems.COIN_NECKLACE.get()), has(ModItems.COIN_NECKLACE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SNOW_EMBLEM.get(), 1)
                .requires(Items.SNOW_BLOCK,4)
                .unlockedBy(getHasName(Items.SNOW_BLOCK), has(Items.SNOW_BLOCK))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ICICLE_EMBLEM.get(), 1)
                .requires(Items.ICE,2)
                .requires(ModItems.FRACTURED_ICICLE.get())
                .unlockedBy(getHasName(ModItems.FRACTURED_ICICLE.get()), has(ModItems.FRACTURED_ICICLE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.RINK_EMBLEM.get(), 1)
                .requires(Items.BLUE_ICE,2)
                .requires(ModItems.FRACTURED_ICICLE.get(),2)
                .unlockedBy(getHasName(ModItems.FRACTURED_ICICLE.get()), has(ModItems.FRACTURED_ICICLE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.YUKI_EMBLEM.get(), 1)
                .requires(Items.BLUE_ICE,4)
                .requires(Items.GOLD_BLOCK)
                .requires(ModItems.FRACTURED_ICICLE.get(),4)
                .unlockedBy(getHasName(ModItems.FRACTURED_ICICLE.get()), has(ModItems.FRACTURED_ICICLE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MAGNETITE_EMBLEM.get(), 1)
                .requires(Items.IRON_INGOT,4)
                .requires(ModItems.SHIELDED_FOSSIL.get(),2)
                .requires(ModItems.IRON_PLATING.get(),2)
                .unlockedBy(getHasName(ModItems.IRON_PLATING.get()), has(ModItems.IRON_PLATING.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ELECTROMAGNET_EMBLEM.get(), 1)
                .requires(Items.IRON_INGOT,2)
                .requires(Items.COPPER_BLOCK,2)
                .requires(ModItems.MAGNETITE_EMBLEM.get())
                .requires(ModItems.BATTERY.get(),4)
                .unlockedBy(getHasName(ModItems.MAGNETITE_EMBLEM.get()), has(ModItems.MAGNETITE_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PULSE_EMBLEM.get(), 1)
                .requires(Items.IRON_BLOCK)
                .requires(Items.COPPER_INGOT,2)
                .requires(ModItems.MAGNETITE_EMBLEM.get())
                .requires(ModItems.COILED_EMBLEM.get())
                .requires(ModItems.BATTERY.get(),4)
                .unlockedBy(getHasName(ModItems.MAGNETITE_EMBLEM.get()), has(ModItems.MAGNETITE_EMBLEM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.LIGHTNING_EMBLEM.get(), 1)
                .requires(Items.LIGHTNING_ROD,2)
                .requires(Items.IRON_BLOCK)
                .requires(ModItems.SPARK_EMBLEM.get(),2)
                .requires(ModItems.DISCAPTURED_ENERGY.get(),2)
                .requires(ModItems.BATTERY.get(),2)
                .unlockedBy(getHasName(ModItems.DISCAPTURED_ENERGY.get()), has(ModItems.DISCAPTURED_ENERGY.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.STAFF_EMBLEM.get(), 1)
                .requires(ModItems.UNTUNED_EMBLEM.get())
                .requires(Items.PAPER, 2)
                .requires(Ingredient.of(Tags.Items.COBBLESTONE), 6)
                .unlockedBy(getHasName(Items.COBBLESTONE), has(Items.COBBLESTONE))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.APPLE_EMBLEM.get(), 1)
                .requires(Ingredient.of(ItemTags.LEAVES), 4)
                .requires(Items.APPLE)
                .requires(Ingredient.of(ItemTags.LEAVES), 4)
                .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.MOONDEW_EMBLEM.get(), 1)
                .requires(ModItems.MOON_EMBLEM.get())
                .requires(ModItems.ORCHID_EMBLEM.get())
                .requires(ModItems.MOONGLOW.get(), 3)
                .unlockedBy(getHasName(ModItems.MOONGLOW.get()), has(ModItems.MOONGLOW.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ECLIPSE_EMBLEM.get(), 1)
                .requires(ModItems.MOON_EMBLEM.get())
                .requires(ModItems.SUN_EMBLEM.get())
                .requires(ModItems.SHATTERED_VOID.get(), 3)
                .unlockedBy(getHasName(ModItems.SHATTERED_VOID.get()), has(ModItems.SHATTERED_VOID.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.LUAU_EMBLEM.get(), 1)
                .requires(ModItems.SUNFLOWER_EMBLEM.get())
                .requires(ModItems.EASTER_EGGBLEM.get())
                .requires(ModItems.REEFS_GLORY.get(), 3)
                .unlockedBy(getHasName(ModItems.REEFS_GLORY.get()), has(ModItems.REEFS_GLORY.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PETRIFIED_VEGE_EMBLEM.get(), 1)
                .requires(ModItems.VEGE_EMBLEM.get())
                .requires(ModItems.POOP_EMBLEM.get(), 3)
                .requires(ModItems.EARF.get(), 3)
                .unlockedBy(getHasName(ModItems.EARF.get()), has(ModItems.EARF.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ABSORBER_EMBLEM.get(), 1)
                .requires(ModItems.RUTHLESS_GUARDIAN_EMBLEM.get())
                .requires(ModItems.TANK_EMBLEM.get(), 2)
                .requires(ModItems.BRITTLE_FOSSIL.get(), 3)
                .unlockedBy(getHasName(ModItems.BRITTLE_FOSSIL.get()), has(ModItems.BRITTLE_FOSSIL.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.THE_TANK_EMBLEM.get(), 1)
                .requires(ModItems.RUTHLESS_GUARDIAN_EMBLEM.get())
                .requires(ModItems.WOOD_EMBLEM.get(), 5)
                .requires(ModItems.STAR_SAPLING.get(), 3)
                .unlockedBy(getHasName(ModItems.STAR_SAPLING.get()), has(ModItems.STAR_SAPLING.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SKY_SHREDDING_EMBLEM.get(), 1)
                .requires(ModItems.DASHER_EMBLEM.get())
                .requires(ModItems.WIND_CATCHER_EMBLEM.get())
                .requires(ModItems.EAGLE_BEAK.get(), 3)
                .unlockedBy(getHasName(ModItems.EAGLE_BEAK.get()), has(ModItems.EAGLE_BEAK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.HERMES_EMBLEM.get(), 1)
                .requires(ModItems.GLASS_LUNG.get(), 2)
                .requires(ModItems.BREAD_PIECE.get())
                .unlockedBy(getHasName(ModItems.GLASS_LUNG.get()), has(ModItems.GLASS_LUNG.get()))
                .save(pWriter);



        RealityManipulationRecipeBuilder.realityManipulation(ModItems.VERDANT_CORE.get(), 1)
                .requires(ItemTags.FLOWERS)
                .requires(ItemTags.SAPLINGS)
                .requires(Items.DIRT)
                .withSuccessChance(0.17f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.VERDANT_CORE.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.BULBULIUM.get(), 1)
                .requires(Items.SEAGRASS)
                .requires(Items.SEAGRASS)
                .requires(Items.KELP)
                .withSuccessChance(0.17f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.BULBULIUM.get()+ "_from_reality_manipulation" );


        RealityManipulationRecipeBuilder.realityManipulation(ModItems.BURNING_CLOTH.get(), 1)
                .requires(Items.LEATHER)
                .requires(Items.COAL)
                .requires(Items.NETHERRACK)
                .withSuccessChance(0.2f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.BURNING_CLOTH.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.SUNLIGHT_SHARD.get(), 1)
                .requires(Items.GLASS)
                .requires(Items.SANDSTONE)
                .requires(Items.DEAD_BUSH)
                .withSuccessChance(0.1f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.SUNLIGHT_SHARD.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.SHIELDED_FOSSIL.get(), 1)
                .requires(Items.COBBLED_DEEPSLATE)
                .requires(Items.COBBLESTONE)
                .requires(Tags.Items.STONE)
                .withSuccessChance(0.03f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.SHIELDED_FOSSIL.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.BONSAI.get(), 1)
                .requires(ItemTags.LOGS)
                .requires(ItemTags.LEAVES)
                .requires(ItemTags.PLANKS)
                .withSuccessChance(0.04f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.BONSAI.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.REFERENCIUM.get(), 1)
                .requires(ModTags.Items.MEAT)
                .requires(Tags.Items.FEATHERS)
                .requires(Tags.Items.FEATHERS)
                .withSuccessChance(0.02f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.REFERENCIUM.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.FRACTURED_ICICLE.get(), 1)
                .requires(Items.ICE)
                .requires(Items.ICE)
                .requires(Items.ICE)
                .withSuccessChance(0.1f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.FRACTURED_ICICLE.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.GLASS_LUNG.get(), 1)
                .requires(Items.GLASS)
                .requires(Items.KELP)
                .requires(Items.SEAGRASS)
                .withSuccessChance(0.1f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.GLASS_LUNG.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.BATTERY.get(), 1)
                .requires(Items.IRON_NUGGET)
                .requires(Items.COPPER_INGOT)
                .requires(Items.COAL)
                .withSuccessChance(0.13f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.BATTERY.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.THE_EARTH.get(), 1)
                .requires(Items.QUARTZ)
                .requires(Items.COPPER_INGOT)
                .requires(Items.CHARCOAL)
                .withSuccessChance(0.13f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.THE_EARTH.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.THE_TORCH.get(), 1)
                .requires(Items.TORCH)
                .requires(Items.DIORITE)
                .requires(Items.IRON_NUGGET)
                .withSuccessChance(0.15f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.THE_TORCH.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.JACK_OF_SPADES.get(), 1)
                .requires(Items.PAPER)
                .requires(Items.INK_SAC)
                .requires(Items.PAPER)
                .withSuccessChance(0.2f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.JACK_OF_SPADES.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.BENT_FIN.get(), 1)
                .requires(ModTags.Items.MEAT)
                .requires(Items.IRON_NUGGET)
                .requires(Items.SEAGRASS)
                .withSuccessChance(0.12f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.BENT_FIN.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.MOON_FRAGMENT.get(), 1)
                .requires(ModTags.Items.FOOD)
                .requires(Items.COPPER_INGOT)
                .requires(Items.GOLD_NUGGET)
                .withSuccessChance(0.04f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.MOON_FRAGMENT.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.IRON_PLATING.get(), 1)
                .requires(Items.IRON_INGOT)
                .requires(Items.COPPER_INGOT)
                .requires(Items.GOLD_NUGGET)
                .withSuccessChance(0.2f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.IRON_PLATING.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.COIN_NECKLACE.get(), 1)
                .requires(Items.COMPOSTER)
                .requires(Items.BARREL)
                .requires(Items.POPPY)
                .withSuccessChance(0.24f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.COIN_NECKLACE.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.BREAD_PIECE.get(), 1)
                .requires(Items.BREAD)
                .requires(ItemTags.BOATS)
                .requires(Items.BREAD)
                .withSuccessChance(0.14f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.BREAD_PIECE.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.HALF_NOTE.get(), 2)
                .requires(Items.AMETHYST_SHARD)
                .requires(Items.AMETHYST_BLOCK)
                .requires(Items.AMETHYST_SHARD)
                .withSuccessChance(0.60f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.HALF_NOTE.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.PLANT_SIGIL_1.get(), 1)
                .requires(ModItems.BLUE_SPIDER_LILY_EMBLEM.get())
                .requires(ModItems.TINY_BUSH.get())
                .requires(ModItems.RED_SPIDER_LILY_EMBLEM.get())
                .withSuccessChance(1f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.PLANT_SIGIL_1.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.PLANT_SIGIL_2.get(), 1)
                .requires(ModItems.DANDELION_EMBLEM.get())
                .requires(ModItems.TINY_BUSH.get())
                .requires(ModItems.DANDELION_EMBLEM.get())
                .withSuccessChance(1f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.PLANT_SIGIL_2.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.OVERWORLD_EMBLEMIUM.get(), 1)
                .requires(Items.DIAMOND)
                .requires(ModItems.EMBLEMIUM_SIGIL.get())
                .requires(Items.LAPIS_LAZULI)
                .withSuccessChance(0.01f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.OVERWORLD_EMBLEMIUM.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.NETHER_EMBLEMIUM.get(), 1)
                .requires(Items.DIAMOND)
                .requires(ModItems.EMBLEMIUM_SIGIL.get())
                .requires(Items.QUARTZ)
                .withSuccessChance(0.02f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.NETHER_EMBLEMIUM.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.NETHER_EMBLEMIUM.get(), 1)
                .requires(Items.NETHERITE_SCRAP)
                .requires(ModItems.OVERWORLD_EMBLEMIUM.get())
                .requires(Items.QUARTZ)
                .withSuccessChance(0.1f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.NETHER_EMBLEMIUM.get()+ "_from_reality_manipulation_2" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.END_EMBLEMIUM.get(), 1)
                .requires(Items.NETHERITE_SCRAP)
                .requires(ModItems.EMBLEMIUM_SIGIL.get())
                .requires(Items.DRAGON_BREATH)
                .withSuccessChance(0.05f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.END_EMBLEMIUM.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.END_EMBLEMIUM.get(), 1)
                .requires(Items.NETHERITE_SCRAP)
                .requires(ModItems.NETHER_EMBLEMIUM.get())
                .requires(Items.DRAGON_BREATH)
                .withSuccessChance(0.3f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.END_EMBLEMIUM.get()+ "_from_reality_manipulation_2" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.WOODEN_EMBLEMIUM.get(), 1)
                .requires(ModItems.EMBLEMIUM_SIGIL.get())
                .requires(ModItems.WOODEN_EMBLEMIUM.get(), tag -> tag.hasIntValue("slots",2, true))
                .requires(ModTags.Items.TIER_THREE_ITEMIUMS)
                .withIntTag("slots",3)
                .withSuccessChance(1.0f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.WOODEN_EMBLEMIUM.get()+ "-e3_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.CLOUD_EMBLEMIUM.get(), 1)
                .requires(ModItems.EMBLEMIUM_SIGIL.get())
                .requires(ModItems.CLOUD_EMBLEMIUM.get(), tag -> tag.hasIntValue("slots",2, true))
                .requires(ModTags.Items.TIER_THREE_ITEMIUMS)
                .withIntTag("slots",3)
                .withSuccessChance(1.0f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.CLOUD_EMBLEMIUM.get()+ "-e3_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.BLESSED_EMBLEMIUM.get(), 1)
                .requires(ModItems.EMBLEMIUM_SIGIL.get())
                .requires(ModItems.BLESSED_EMBLEMIUM.get(), tag -> tag.hasIntValue("slots",1, true))
                .requires(ModTags.Items.TIER_THREE_ITEMIUMS)
                .withIntTag("slots",2)
                .withSuccessChance(1.0f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.BLESSED_EMBLEMIUM.get()+ "-e2_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.PROFANED_EMBLEMIUM.get(), 1)
                .requires(ModItems.EMBLEMIUM_SIGIL.get())
                .requires(ModItems.PROFANED_EMBLEMIUM.get(), tag -> tag.hasIntValue("slots",1, true))
                .requires(ModTags.Items.TIER_THREE_ITEMIUMS)
                .withIntTag("slots",2)
                .withSuccessChance(1.0f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.PROFANED_EMBLEMIUM.get()+ "-e2_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.ANCIENT_EMBLEMIUM.get(), 1)
                .requires(ModItems.EMBLEMIUM_SIGIL.get())
                .requires(ModItems.ANCIENT_EMBLEMIUM.get(), tag -> tag.hasIntValue("slots",1, true))
                .requires(ModTags.Items.TIER_THREE_ITEMIUMS)
                .withIntTag("slots",2)
                .withSuccessChance(1.0f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.ANCIENT_EMBLEMIUM.get()+ "-e2_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.CURSED_EMBLEMIUM.get(), 1)
                .requires(ModItems.EMBLEMIUM_SIGIL.get())
                .requires(ModItems.CURSED_EMBLEMIUM.get(), tag -> tag.hasIntValue("slots",1, true))
                .requires(ModTags.Items.TIER_FOUR_ITEMIUMS)
                .withIntTag("slots",2)
                .withSuccessChance(1.0f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.CURSED_EMBLEMIUM.get()+ "-e2_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.GLOBAL_EMBLEMIUM.get(), 1)
                .requires(ModItems.EMBLEMIUM_SIGIL.get())
                .requires(ModItems.GLOBAL_EMBLEMIUM.get(), tag -> tag.hasIntValue("slots",1, true))
                .requires(ModTags.Items.TIER_FOUR_ITEMIUMS)
                .withIntTag("slots",2)
                .withSuccessChance(1.0f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.GLOBAL_EMBLEMIUM.get()+ "-e2_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.COSMIC_EMBLEMIUM.get(), 1)
                .requires(ModItems.EMBLEMIUM_SIGIL.get())
                .requires(ModItems.COSMIC_EMBLEMIUM.get(), tag -> tag.hasIntValue("slots",1, true))
                .requires(ModTags.Items.TIER_FOUR_ITEMIUMS)
                .withIntTag("slots",2)
                .withSuccessChance(1.0f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.COSMIC_EMBLEMIUM.get()+ "-e2_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.BLESSED_EMBLEMIUM.get(), 1)
                .requires(ModItems.EMBLEMIUM_INSIGNIA.get())
                .requires(ModItems.BLESSED_EMBLEMIUM.get(), tag -> tag.hasIntValue("slots",2, true))
                .requires(ModTags.Items.TIER_FOUR_ITEMIUMS)
                .withIntTag("slots",3)
                .withSuccessChance(1.0f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.BLESSED_EMBLEMIUM.get()+ "-e3_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.PROFANED_EMBLEMIUM.get(), 1)
                .requires(ModItems.EMBLEMIUM_INSIGNIA.get())
                .requires(ModItems.PROFANED_EMBLEMIUM.get(), tag -> tag.hasIntValue("slots",2, true))
                .requires(ModTags.Items.TIER_FOUR_ITEMIUMS)
                .withIntTag("slots",3)
                .withSuccessChance(1.0f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.PROFANED_EMBLEMIUM.get()+ "-e3_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.ANCIENT_EMBLEMIUM.get(), 1)
                .requires(ModItems.EMBLEMIUM_INSIGNIA.get())
                .requires(ModItems.ANCIENT_EMBLEMIUM.get(), tag -> tag.hasIntValue("slots",2, true))
                .requires(ModTags.Items.TIER_FOUR_ITEMIUMS)
                .withIntTag("slots",3)
                .withSuccessChance(1.0f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.ANCIENT_EMBLEMIUM.get()+ "-e3_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.CURSED_EMBLEMIUM.get(), 1)
                .requires(ModItems.EMBLEMIUM_INSIGNIA.get())
                .requires(ModItems.CURSED_EMBLEMIUM.get(), tag -> tag.hasIntValue("slots",2, true))
                .requires(ModTags.Items.TIER_FIVE_ITEMIUMS)
                .withIntTag("slots",3)
                .withSuccessChance(1.0f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.CURSED_EMBLEMIUM.get()+ "-e3_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.GLOBAL_EMBLEMIUM.get(), 1)
                .requires(ModItems.EMBLEMIUM_INSIGNIA.get())
                .requires(ModItems.GLOBAL_EMBLEMIUM.get(), tag -> tag.hasIntValue("slots",2, true))
                .requires(ModTags.Items.TIER_FIVE_ITEMIUMS)
                .withIntTag("slots",3)
                .withSuccessChance(1.0f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.GLOBAL_EMBLEMIUM.get()+ "-e3_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.COSMIC_EMBLEMIUM.get(), 1)
                .requires(ModItems.EMBLEMIUM_INSIGNIA.get())
                .requires(ModItems.COSMIC_EMBLEMIUM.get(), tag -> tag.hasIntValue("slots",2, true))
                .requires(ModTags.Items.TIER_FIVE_ITEMIUMS)
                .withIntTag("slots",3)
                .withSuccessChance(1.0f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.COSMIC_EMBLEMIUM.get()+ "-e3_from_reality_manipulation" );


        RealityManipulationRecipeBuilder.realityManipulation(ModItems.ENTHEREAL_CONJUGATION.get(), 1)
                .requires(ModItems.ENTHEREAL_MASS.get())
                .requires(ModItems.ENTHEREAL_MASS.get())
                .requires(ModItems.ENTHEREAL_MASS.get())
                .withSuccessChance(0.5f)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.COSMIC_EMBLEMIUM.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.EMBLEMENTAL_ENTHERA.get(), 1)
                .requires(ModTags.Items.EMBLEMS)
                .requires(ModItems.ENTHEREAL_MASS.get())
                .requires(ModItems.EMBLEMIUM_SIGIL.get())
                .withSuccessChance(1.0f)
                .withIntTag("enther", 0)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.EMBLEMENTAL_ENTHERA.get()+ "_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.EMBLEMENTAL_ENTHERA.get(), 1)
                .requires(ModItems.ENTHEREAL_MASS.get())
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntValue("enther", 0, true))
                .requires(ModItems.ENTHEREAL_MASS.get())
                .withSuccessChance(0.9f)
                .withIntTag("enther", 1)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.EMBLEMENTAL_ENTHERA.get()+ "-e1_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.EMBLEMENTAL_ENTHERA.get(), 1)
                .requires(ModItems.ENTHEREAL_MASS.get())
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntValue("enther", 1, true))
                .requires(ModItems.ENTHEREAL_MASS.get())
                .withSuccessChance(0.8f)
                .withIntTag("enther", 2)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.EMBLEMENTAL_ENTHERA.get()+ "-e2_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.EMBLEMENTAL_ENTHERA.get(), 1)
                .requires(ModItems.ENTHEREAL_MASS.get())
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntValue("enther", 2, true))
                .requires(ModItems.ENTHEREAL_MASS.get())
                .withSuccessChance(0.65f)
                .withIntTag("enther", 3)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.EMBLEMENTAL_ENTHERA.get()+ "-e3_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.EMBLEMENTAL_ENTHERA.get(), 1)
                .requires(ModItems.ENTHEREAL_MASS.get())
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntValue("enther", 3, true))
                .requires(ModItems.ENTHEREAL_MASS.get())
                .withSuccessChance(0.5f)
                .withIntTag("enther", 4)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.EMBLEMENTAL_ENTHERA.get()+ "-e4_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.EMBLEMENTAL_ENTHERA.get(), 1)
                .requires(ModItems.ENTHEREAL_MASS.get())
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntValue("enther", 4, true))
                .requires(ModItems.ENTHEREAL_MASS.get())
                .withSuccessChance(0.3f)
                .withIntTag("enther", 5)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.EMBLEMENTAL_ENTHERA.get()+ "-e5_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.EMBLEMENTAL_ENTHERA.get(), 1)
                .requires(ModItems.ENTHEREAL_MASS.get())
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntValue("enther", 5, true))
                .requires(ModItems.ENTHEREAL_MASS.get())
                .withSuccessChance(0.2f)
                .withIntTag("enther", 6)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.EMBLEMENTAL_ENTHERA.get()+ "-e6_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.EMBLEMENTAL_ENTHERA.get(), 1)
                .requires(ModItems.ENTHEREAL_MASS.get())
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntValue("enther", 6, true))
                .requires(ModItems.ENTHEREAL_MASS.get())
                .withSuccessChance(0.1f)
                .withIntTag("enther", 7)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.EMBLEMENTAL_ENTHERA.get()+ "-e7_from_reality_manipulation" );

        RealityManipulationRecipeBuilder.realityManipulation(ModItems.EMBLEMENTAL_ENTHERA.get(), 1)
                .requires(ModItems.ENTHEREAL_MASS.get())
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntValue("enther", 7, true))
                .requires(ModItems.ENTHEREAL_MASS.get())
                .withSuccessChance(0.03f)
                .withIntTag("enther", 8)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.EMBLEMENTAL_ENTHERA.get()+ "-e8_from_reality_manipulation" );

        EntherealSelectorRecipeBuilder.etherealSelection(ModItems.ENTHEREAL_MASS.get(), 5)
                .requires(ModItems.ENTHEREAL_MASS.get())
                .requires(ModItems.ENTHEREAL_CONJUGATION.get())
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.ENTHEREAL_MASS.get()+ "_from_ethereal_selection-conjugation" );

        EntherealSelectorRecipeBuilder.etherealSelection(ModItems.WOOD_EMBLEM.get(), 1)
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntHigher("enther", 4))
                .requires(ModItems.WOOD_EMBLEM.get())
                .withIntTag("ascended", 1)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.WOOD_EMBLEM.get()+ "_ascended_from_ethereal_selection" );

        EntherealSelectorRecipeBuilder.etherealSelection(ModItems.COMET_EMBLEM.get(), 1)
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntHigher("enther", 4))
                .requires(ModItems.COMET_EMBLEM.get())
                .withIntTag("ascended", 1)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.COMET_EMBLEM.get()+ "_ascended_from_ethereal_selection" );

        EntherealSelectorRecipeBuilder.etherealSelection(ModItems.APPLE_EMBLEM.get(), 1)
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntHigher("enther", 4))
                .requires(ModItems.APPLE_EMBLEM.get())
                .withIntTag("ascended", 1)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.APPLE_EMBLEM.get()+ "_ascended_from_ethereal_selection" );

        EntherealSelectorRecipeBuilder.etherealSelection(ModItems.INFERNO_EMBLEM.get(), 1)
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntHigher("enther", 4))
                .requires(ModItems.INFERNO_EMBLEM.get())
                .withIntTag("ascended", 1)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.INFERNO_EMBLEM.get()+ "_ascended_from_ethereal_selection" );

        EntherealSelectorRecipeBuilder.etherealSelection(ModItems.HERMES_EMBLEM.get(), 1)
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntHigher("enther", 4))
                .requires(ModItems.HERMES_EMBLEM.get())
                .withIntTag("ascended", 1)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.HERMES_EMBLEM.get()+ "_ascended_from_ethereal_selection" );

        EntherealSelectorRecipeBuilder.etherealSelection(ModItems.PLANT_EMBLEM.get(), 1)
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntHigher("enther", 5))
                .requires(ModItems.PLANT_EMBLEM.get())
                .withIntTag("ascended", 1)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.PLANT_EMBLEM.get()+ "_ascended_from_ethereal_selection" );

        EntherealSelectorRecipeBuilder.etherealSelection(ModItems.FARMER_EMBLEM.get(), 1)
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntHigher("enther", 5))
                .requires(ModItems.FARMER_EMBLEM.get())
                .withIntTag("ascended", 1)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.FARMER_EMBLEM.get()+ "_ascended_from_ethereal_selection" );

        EntherealSelectorRecipeBuilder.etherealSelection(ModItems.ORCHID_EMBLEM.get(), 1)
                .requires(ModItems.EMBLEMENTAL_ENTHERA.get(), tag -> tag.hasIntHigher("enther", 6))
                .requires(ModItems.ORCHID_EMBLEM.get())
                .withIntTag("ascended", 1)
                .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ ModItems.ORCHID_EMBLEM.get()+ "_ascended_from_ethereal_selection" );

        for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()){
            if (item.get() instanceof ItemiumItem itemiumItem){
                if (itemiumItem.getWorkingEmblemTypes().size() == 1){
                    EntherealSelectorRecipeBuilder.etherealSelection(item.get(), 2)
                            .requires(item.get())
                            .requires(ModItems.ENTHEREAL_CONJUGATION.get())
                            .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ item.get()+ "_from_ethereal_selection-conjugation" );
                }
            }
        }
        for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()){
            if (item.get() instanceof ItemiumItem itemiumItem){
                if (itemiumItem.getWorkingEmblemTypes().size() == 1){
                    EntherealSelectorRecipeBuilder.etherealSelection(getNextItemiumItem(itemiumItem), 1)
                            .requires(item.get())
                            .requires(ModItems.ENTHEREAL_VOID.get())
                            .save(pWriter, Rupecs_Emblems.MOD_ID +":"+ getNextItemiumItem(itemiumItem)+ "_from_ethereal_selection-void" );
                }
            }
        }

    }

    private ItemiumItem getNextItemiumItem(ItemiumItem itemiumItem){
        if (itemiumItem.getWorkingEmblemTypes().size() > 1){
            return null;
        }
        EmblemTypes emblemType = itemiumItem.getWorkingEmblemTypes().get(0);
        EmblemTypes nextType = EmblemTypes.values()[(emblemType.ordinal()+1)%EmblemTypes.values().length];
        for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()){
            if (item.get() instanceof ItemiumItem selectedItemium && selectedItemium.getWorkingEmblemTypes().size() == 1 && selectedItemium.getWorkingEmblemTypes().get(0)==nextType){
                return selectedItemium;
            }
        }
        return null;
    }


    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, Rupecs_Emblems.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }

}
