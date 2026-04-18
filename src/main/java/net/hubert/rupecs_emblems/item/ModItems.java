package net.hubert.rupecs_emblems.item;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.item.custom.*;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Rupecs_Emblems.MOD_ID);



    public static final RegistryObject<Item> MOON_EMBLEM = ITEMS.register("moon_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.ASTRAL, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE,1, EmblemClasses.OFFENCE, 1),
                    "Lower Gravity, No fall damage, Send thy enemies over luna, Damage Up, Night boosts"));

    public static final RegistryObject<Item> WOOD_EMBLEM = ITEMS.register("wood_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XF, List.of(EmblemTypes.NATURAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.DEFENCE,1),
                    "+2 Max Health", "+10 Max Health"));

    public static final RegistryObject<Item> STONE_EMBLEM = ITEMS.register("stone_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.NATURAL, EmblemTypes.ELEMENTAL, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "+0.5 Damage"));

    public static final RegistryObject<Item> SAND_EMBLEM = ITEMS.register("sand_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.NATURAL, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Speed up, Bonus speed on sand"));

    public static final RegistryObject<Item> GROUND_EMBLEM = ITEMS.register("ground_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.NATURAL, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "+0.5 Attack Speed"));

    public static final RegistryObject<Item> PLANT_EMBLEM = ITEMS.register("plant_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.NATURAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "+1 Nature Regen","+6 Nature Regen"));

    public static final RegistryObject<Item> FOSSIL_EMBLEM = ITEMS.register("fossil_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.NATURAL, EmblemTypes.ANIMAL, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE,1, EmblemClasses.DEFENCE, 1),
                    "+0.5 Mining Speed, Speed up, Bonus speed on sand, +4 Armor"));

    public static final RegistryObject<Item> FIRE_EMBLEM = ITEMS.register("fire_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.ELEMENTAL, EmblemTypes.FIRE),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Set thy enemies on fire"));

    public static final RegistryObject<Item> CRYSTAL_EMBLEM = ITEMS.register("crystal_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.ELEMENTAL, EmblemTypes.NATURAL, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Chance to multiply ore drops"));

    public static final RegistryObject<Item> SKY_EMBLEM = ITEMS.register("sky_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XF, List.of(EmblemTypes.ASTRAL, EmblemTypes.AIR),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Lower Gravity, Less fall damage, Speed up"));

    public static final RegistryObject<Item> HEART_EMBLEM = ITEMS.register("heart_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.CARD),
                    Map.of(EmblemClasses.PASSIVE,1,EmblemClasses.OFFENCE,1),
                    "Less health -> more boosts"));

    public static final RegistryObject<Item> NIGHT_EMBLEM = ITEMS.register("night_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XF, List.of(EmblemTypes.SPECIAL, EmblemTypes.NATURAL, EmblemTypes.AIR),
                    Map.of(EmblemClasses.PASSIVE,1,EmblemClasses.OFFENCE,1),
                    "Night boosts"));

    public static final RegistryObject<Item> ICE_EMBLEM = ITEMS.register("ice_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.ELEMENTAL, EmblemTypes.ICE),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Frost Fist"));

    public static final RegistryObject<Item> SUN_EMBLEM = ITEMS.register("sun_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.ASTRAL, EmblemTypes.FIRE),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Sunlight boosts, +2 Photosynthesis"));

    public static final RegistryObject<Item> FULL_MOON_EMBLEM = ITEMS.register("full_moon_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.C, List.of(EmblemTypes.ASTRAL, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE,1, EmblemClasses.OFFENCE, 1, EmblemClasses.ACTIVE, 1),
                    "Lower Gravity, No fall damage, Send thy enemies over luna, " +
                            "Press Secondary Emblem Ability to Spawn Moon Asteroids, Damage Up, Night boosts"));

    public static final RegistryObject<Item> FLUORESCENT_EMBLEM = ITEMS.register("fluorescent_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.NATURAL),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Night vision"));

    public static final RegistryObject<Item> DUST_EMBLEM = ITEMS.register("dust_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.NATURAL, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Speed up"));

    public static final RegistryObject<Item> VEGE_EMBLEM = ITEMS.register("vege_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Cant eat meat, Animals follow you, Hunger drowns faster"));

    public static final RegistryObject<Item> MAGMA_EMBLEM = ITEMS.register("magma_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.NATURAL, EmblemTypes.FIRE),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Fire Resistance"));

    public static final RegistryObject<Item> FARMER_EMBLEM = ITEMS.register("farmer_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Plants grow near you"));

    public static final RegistryObject<Item> BUILDER_EMBLEM = ITEMS.register("builder_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.SPECIAL, EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "+2 Block place range"));

    public static final RegistryObject<Item> FLOWER_EMBLEM = ITEMS.register("flower_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.NATURAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "+1 Photosynthesis"));

    public static final RegistryObject<Item> BONE_EMBLEM = ITEMS.register("bone_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.NATURAL, EmblemTypes.ANIMAL, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.DEFENCE,1),
                    "+4 Armor"));

    public static final RegistryObject<Item> INFERNO_EMBLEM = ITEMS.register("inferno_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XF, List.of(EmblemTypes.ELEMENTAL, EmblemTypes.FIRE),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Fire Ring"));

    public static final RegistryObject<Item> SUPERNOVA_EMBLEM = ITEMS.register("supernova_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.B, List.of(EmblemTypes.ASTRAL, EmblemTypes.ELEMENTAL, EmblemTypes.FIRE),
                    Map.of(EmblemClasses.PASSIVE,1,EmblemClasses.OFFENCE,1),
                    "Fire Punch, Fire Ring, +3 Photosynthesis, Sunlight boosts, Fire Resistance"));

    public static final RegistryObject<Item> MOON_JUMPER_EMBLEM = ITEMS.register("moon_jumper_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.ASTRAL, EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.ACTIVE,1),
                    "Activate Primary Emblem Ability to perform moon jump"));

    public static final RegistryObject<Item> COMET_EMBLEM = ITEMS.register("comet_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XF, List.of(EmblemTypes.ASTRAL, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Comet shards shoot from you"));

    public static final RegistryObject<Item> BLOOD_MOON_EMBLEM = ITEMS.register("blood_moon_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.B, List.of(EmblemTypes.ASTRAL, EmblemTypes.SPECIAL, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.OFFENCE,1,EmblemClasses.PASSIVE,1,EmblemClasses.ACTIVE,2),
                    "Comet shards shoot from you, Activate Primary Emblem Ability to perform moon jump, Lower Gravity, " +
                            "No fall damage, Send thy enemies over luna, Press Secondary Emblem Ability to Spawn Moon Asteroids, Damage Up, Night boosts"));

    public static final RegistryObject<Item> BLOODSTAR_EMBLEM = ITEMS.register("bloodstar_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.S, List.of(EmblemTypes.CELESTIAL, EmblemTypes.ELEMENTAL, EmblemTypes.SPECIAL, EmblemTypes.FIRE, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.OFFENCE,2,EmblemClasses.PASSIVE,2, EmblemClasses.ACTIVE, 2),
                    "Comet shards shoot from you, Activate Primary Emblem Ability to perform moon jump, Lower Gravity, " +
                            "No fall damage, Send thy enemies over luna, Press Secondary Emblem Ability to Spawn Moon Asteroids, Damage Up, " +
                            "Fire Punch, Fire Ring, +3 Photosynthesis, Celestial boosts, Fire Resistance"));

    public static final RegistryObject<Item> POOP_EMBLEM = ITEMS.register("poop_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.SEASONAL),
                    Map.of(EmblemClasses.ACTIVE,1),
                    "Activate Secondary Emblem Ability to Poop"));

    public static final RegistryObject<Item> MERCHANT_EMBLEM = ITEMS.register("merchant_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Cheaper emerald prices"));

    public static final RegistryObject<Item> TREE_EMBLEM = ITEMS.register("tree_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.NATURAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.OFFENCE,1,EmblemClasses.DEFENCE,1,EmblemClasses.PASSIVE,1),
                    "+3 Max Health, +1 Photosynthesis, +1 Nature Regen, +0.5 Attack Speed"));

    public static final RegistryObject<Item> CAVE_EMBLEM = ITEMS.register("cave_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.NATURAL, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.OFFENCE,1,EmblemClasses.DEFENCE,1,EmblemClasses.PASSIVE,1),
                    "+5 Armor, Chance to multiply ore drops, Speed up, +0.75 Damage"));

    public static final RegistryObject<Item> SPADE_EMBLEM = ITEMS.register("spade_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.CARD),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "+1 Damage"));

    public static final RegistryObject<Item> CLOVER_EMBLEM = ITEMS.register("clover_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.CARD),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Speed up"));

    public static final RegistryObject<Item> DIAMONDS_EMBLEM = ITEMS.register("diamonds_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.CARD),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Jump boost"));

    public static final RegistryObject<Item> WATER_EMBLEM = ITEMS.register("water_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.NATURAL, EmblemTypes.ELEMENTAL, EmblemTypes.WATER),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Respiration, Swim Speed up, +1 Underwater Mining Speed"));

    public static final RegistryObject<Item> BLIZZARD_EMBLEM = ITEMS.register("blizzard_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XF, List.of(EmblemTypes.NATURAL, EmblemTypes.ICE),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Frost Ring"));

    public static final RegistryObject<Item> FROST_EMBLEM = ITEMS.register("frost_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.NATURAL, EmblemTypes.ICE),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Frost Walker"));

    public static final RegistryObject<Item> SNOWSTORM_EMBLEM = ITEMS.register("snowstorm_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.ICE),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Frost Ring, Frost Fist"));

    public static final RegistryObject<Item> SPARK_EMBLEM = ITEMS.register("spark_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.TECH),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Zap thy enemies"));

    public static final RegistryObject<Item> CONDUCTOR_EMBLEM = ITEMS.register("conductor_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.TECH),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Zap those who dare to hurt you"));

    public static final RegistryObject<Item> COILED_EMBLEM = ITEMS.register("coiled_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.TECH),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Zap Ring"));

    public static final RegistryObject<Item> DAY_EMBLEM = ITEMS.register("day_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.NATURAL, EmblemTypes.AIR),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "+0.4 Mining Speed during day"));

    public static final RegistryObject<Item> HEAVEN_EMBLEM = ITEMS.register("heaven_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.ASTRAL, EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "+0.4 Mining Speed during day, Lower Gravity, Less fall damage, Speed up, Night boosts"));

    public static final RegistryObject<Item> RETURN_EMBLEM = ITEMS.register("return_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XC, List.of(EmblemTypes.SPECIAL, EmblemTypes.DIVINE),
                    Map.of(EmblemClasses.ACTIVE,3),
                    "Hold Shift + Primary Emblem Ability to return to spawn"));

    public static final RegistryObject<Item> WARRIOR_EMBLEM = ITEMS.register("warrior_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.DEFENCE,1),
                    "+2 Armor"));

    public static final RegistryObject<Item> ROTTEN_EMBLEM = ITEMS.register("rotten_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Can eat unhealthy food"));

    public static final RegistryObject<Item> GOLDEN_EMBLEM = ITEMS.register("golden_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.SPECIAL, EmblemTypes.NATURAL, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Piglins are passive and guide you"));

    public static final RegistryObject<Item> ELECTRO_EMBLEM = ITEMS.register("electro_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.TECH),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Electrocute thy enemies"));

    public static final RegistryObject<Item> GAMBLER_EMBLEM = ITEMS.register("gambler_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.SPECIAL, EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.ACTIVE,2),
                    "Press Shift + Secondary Emblem Ability to gamble item in hand"));

    public static final RegistryObject<Item> SMOKE_EMBLEM = ITEMS.register("smoke_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E,List.of(EmblemTypes.SPECIAL, EmblemTypes.SEASONAL),
                    Map.of(EmblemClasses.ACTIVE,1),
                    "Press Ctrl + Secondary Emblem Ability to vanish"));

    public static final RegistryObject<Item> EASTER_EGGBLEM = ITEMS.register("easter_eggblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XF, List.of(EmblemTypes.SEASONAL),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Egg"));

    public static final RegistryObject<Item> STEAM_EMBLEM = ITEMS.register("steam_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.NATURAL, EmblemTypes.WATER),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Steam Ring"));

    public static final RegistryObject<Item> AURIC_EMBLEM = ITEMS.register("auric_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.SPECIAL, EmblemTypes.CELESTIAL),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Elemental Ring"));

    public static final RegistryObject<Item> INDUCTION_EMBLEM = ITEMS.register("induction_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.SPECIAL, EmblemTypes.TECH),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Zap thy enemies and those who dare to hurt you"));

    public static final RegistryObject<Item> OVERCHARGED_EMBLEM = ITEMS.register("overcharged_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.SPECIAL, EmblemTypes.TECH),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Zap Ring, Electrocute thy enemies"));

    public static final RegistryObject<Item> THUNDER_EMBLEM = ITEMS.register("thunder_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XC, List.of(EmblemTypes.SPECIAL, EmblemTypes.TECH, EmblemTypes.NATURAL, EmblemTypes.ELECTRICITY),
                    Map.of(EmblemClasses.OFFENCE,1,EmblemClasses.ACTIVE,1),
                    "Zap Ring, Electrocute thy enemies, Zap thy enemies and those who dare to hurt you, " +
                            "Press Primary Emblem Ability to strike with lightning"));

    public static final RegistryObject<Item> BUBBLE_EMBLEM = ITEMS.register("bubble_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.NATURAL, EmblemTypes.WATER),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Drown thy enemies"));

    public static final RegistryObject<Item> BARTERER_EMBLEM = ITEMS.register("barterer_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Cheaper prices"));

    public static final RegistryObject<Item> HOURGLASS_EMBLEM = ITEMS.register("hourglass_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.SPECIAL, EmblemTypes.CELESTIAL),
                    Map.of(EmblemClasses.ACTIVE,2),
                    "Hold Primary Emblem Ability to Fast Forward Time"));

    public static final RegistryObject<Item> MIRROR_EMBLEM = ITEMS.register("mirror_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.SPECIAL, EmblemTypes.DIVINE),
                    Map.of(EmblemClasses.DEFENCE,1,EmblemClasses.ACTIVE,1),
                    "+0.6 Damage Reflection, Press Primary Emblem Ability to swap places with target"));

    public static final RegistryObject<Item> TANK_EMBLEM = ITEMS.register("tank_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.SPECIAL, EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.DEFENCE,1),
                    "+6 Armor, +6 Max Health"));

    public static final RegistryObject<Item> RUTHLESS_GUARDIAN_EMBLEM = ITEMS.register("ruthless_guardian_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.C, List.of(EmblemTypes.SPECIAL, EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.DEFENCE,2),
                    "+10 Armor, +14 Max Health"));

    public static final RegistryObject<Item> ABSORBER_EMBLEM = ITEMS.register("absorber_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.B, List.of(EmblemTypes.SPECIAL, EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.DEFENCE,2),
                    "+16 Armor, +20 Max Health"));

    public static final RegistryObject<Item> THE_TANK_EMBLEM = ITEMS.register("the_tank_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.B, List.of(EmblemTypes.SPECIAL, EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.DEFENCE,2),
                    "+36 Max Health"));

    public static final RegistryObject<Item> PYRO_EMBLEM = ITEMS.register("pyro_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.NATURAL, EmblemTypes.FIRE),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Magma Walker"));

    public static final RegistryObject<Item> SOUL_EMBLEM = ITEMS.register("soul_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.SPECIAL, EmblemTypes.DIVINE),
                    Map.of(EmblemClasses.ACTIVE,2),
                    "Press Primary Emblem Ability to connect thy soul with target"));

    public static final RegistryObject<Item> MOON_WRATH_EMBLEM = ITEMS.register("moon_wrath_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.ASTRAL),
                    Map.of(EmblemClasses.ACTIVE,1),
                    "Press Secondary Emblem Ability to Spawn Moon Asteroids"));

    public static final RegistryObject<Item> ORCHID_EMBLEM = ITEMS.register("orchid_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "+2 Photosynthesis, Plants grow near you", "+4 Photosynthesis, Plants grow near you"));

    public static final RegistryObject<Item> CACTUS_EMBLEM = ITEMS.register("cactus_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.NATURAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.OFFENCE,1,EmblemClasses.DEFENCE,1),
                    "Thorns"));

    public static final RegistryObject<Item> BAMBOO_EMBLEM = ITEMS.register("bamboo_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.NATURAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "+1 Entity Reach"));

    public static final RegistryObject<Item> IVY_EMBLEM = ITEMS.register("ivy_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.NATURAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.DEFENCE,1),
                    "10% Damage Reduction while Sneaking"));

    public static final RegistryObject<Item> ROSE_EMBLEM = ITEMS.register("rose_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.NATURAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Inflict Bleeding on attack"));

    public static final RegistryObject<Item> IBARA_EMBLEM = ITEMS.register("ibara_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Inflict Bleeding on attack, Thorns"));

    public static final RegistryObject<Item> SUNFLOWER_EMBLEM = ITEMS.register("sunflower_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Sun always shines for your sunny emblems"));

    public static final RegistryObject<Item> NIGHTBLOOM_EMBLEM = ITEMS.register("nightbloom_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Moonlight for your lunar emblems"));

    public static final RegistryObject<Item> BLACK_ROSE_EMBLEM = ITEMS.register("black_rose_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.NATURAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Wither thy enemies"));

    public static final RegistryObject<Item> DRIED_ROSE_EMBLEM = ITEMS.register("dried_rose_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.NATURAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Wither Ring"));

    public static final RegistryObject<Item> WITHER_ROSE_EMBLEM = ITEMS.register("wither_rose_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.C, List.of(EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Wither thy enemies, Wither Ring"));

    public static final RegistryObject<Item> BOUQUET_EMBLEM = ITEMS.register("bouquet_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.C, List.of(EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.PASSIVE,1,EmblemClasses.OFFENCE,1),
                    "+3 Photosynthesis, Plants grow near you, Inflict Bleeding on attack"));

    public static final RegistryObject<Item> VINE_EMBLEM = ITEMS.register("vine_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.NATURAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.DEFENCE,1,EmblemClasses.OFFENCE,1),
                    "+2 Entity Reach, 20% Damage Reduction while Sneaking"));

    public static final RegistryObject<Item> PRICKLE_VINE_EMBLEM = ITEMS.register("prickle_vine_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.DEFENCE,1,EmblemClasses.OFFENCE,1),
                    "+3 Entity Reach, 20% Damage Reduction while Sneaking, Inflict Bleeding on attack, Thorns"));

    public static final RegistryObject<Item> FLOWERFIELD_EMBLEM = ITEMS.register("flowerfield_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.B, List.of(EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.PASSIVE,1,EmblemClasses.DEFENCE,1,EmblemClasses.OFFENCE,1),
                    "+4 Entity Reach, 30% Damage Reduction while Sneaking, Inflict Bleeding on attack, Thorns," +
                            " +3 Photosynthesis, Plants grow near you"));

    public static final RegistryObject<Item> RAINFOREST_EMBLEM = ITEMS.register("rainforest_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XC, List.of(EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.PASSIVE,1,EmblemClasses.DEFENCE,1,EmblemClasses.OFFENCE,1),
                    "+6 Max Health, +3 Photosynthesis, +3 Nature Regen, +1 Attack Speed"));

    public static final RegistryObject<Item> DEADFOREST_EMBLEM = ITEMS.register("deadforest_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.A, List.of(EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.PASSIVE,1,EmblemClasses.DEFENCE,1,EmblemClasses.OFFENCE,1),
                    "+6 Max Health, +3 Photosynthesis, +3 Nature Regen, +1 Attack Speed, Wither thy enemies, Wither Ring"));

    public static final RegistryObject<Item> FLOWERFOREST_EMBLEM = ITEMS.register("flowerforest_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XS, List.of(EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.PASSIVE,3,EmblemClasses.DEFENCE,1,EmblemClasses.OFFENCE,1),
                    "+5 Entity Reach, 40% Damage Reduction while Sneaking, Inflict Bleeding on attack, Thorns, " +
                            "+4 Photosynthesis, Plants grow near you, " +
                            "+10 Max Health, +4 Nature Regen, +1.5 Attack Speed, Wither thy enemies, Wither Ring, " +
                            "Moonlight for your lunar emblems, Sun always shines for your sunny emblems"));

    public static final RegistryObject<Item> DANDELION_EMBLEM = ITEMS.register("dandelion_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.ACTIVE,1),
                    "Press Primary Emblem Ability to gain slow falling"));

    public static final RegistryObject<Item> RED_SPIDER_LILY_EMBLEM = ITEMS.register("red_spider_lily_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.OFFENCE,2),
                    "+10 Damage during night, you burn during day"));

    public static final RegistryObject<Item> BLUE_SPIDER_LILY_EMBLEM = ITEMS.register("blue_spider_lily_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.OFFENCE,2),
                    "+10 Damage during day, you freeze at night"));

    public static final RegistryObject<Item> MIST_EMBLEM = ITEMS.register("mist_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XF, List.of(EmblemTypes.NATURAL, EmblemTypes.WATER),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Drowning Ring"));

    public static final RegistryObject<Item> TIDE_EMBLEM = ITEMS.register("tide_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.NATURAL, EmblemTypes.WATER),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Tidal Fist"));

    public static final RegistryObject<Item> RAIN_EMBLEM = ITEMS.register("rain_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.NATURAL, EmblemTypes.WATER),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Speed up during rain or thunder"));

    public static final RegistryObject<Item> SPRINKLER_EMBLEM = ITEMS.register("sprinkler_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.TECH, EmblemTypes.WATER),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Rains in you, best support for farmers"));

    public static final RegistryObject<Item> TEAR_EMBLEM = ITEMS.register("tear_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.WATER, EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Cry over your killed foes"));

    public static final RegistryObject<Item> PUDDLE_EMBLEM = ITEMS.register("puddle_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.WATER, EmblemTypes.SPECIAL, EmblemTypes.NATURAL, EmblemTypes.ANIMAL),
                    Map.of(EmblemClasses.ACTIVE,1),
                    "Press Secondary Emblem Ability to summon a friendly pufferfish"));

    public static final RegistryObject<Item> MUD_EMBLEM = ITEMS.register("mud_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.WATER, EmblemTypes.NATURAL, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Slow thy enemies"));

    public static final RegistryObject<Item> MARSH_EMBLEM = ITEMS.register("marsh_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.WATER, EmblemTypes.NATURAL),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Slow thy enemies, Steam ring"));

    public static final RegistryObject<Item> BOG_EMBLEM = ITEMS.register("bog_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.WATER, EmblemTypes.NATURAL, EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.OFFENCE,1, EmblemClasses.ACTIVE, 1),
                    "Drowning ring, Press Secondary Emblem Ability to summon a friendly pufferfish"));

    public static final RegistryObject<Item> WAVE_EMBLEM = ITEMS.register("wave_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.WATER, EmblemTypes.NATURAL),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Speed up during rain or thunder, Respiration, Swim Speed up, +1 Underwater Mining Speed"));

    public static final RegistryObject<Item> WHIRL_EMBLEM = ITEMS.register("whirl_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.WATER, EmblemTypes.NATURAL),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Tidal Fist, Drown thy enemies"));

    public static final RegistryObject<Item> VORTEX_EMBLEM = ITEMS.register("vortex_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XC, List.of(EmblemTypes.WATER, EmblemTypes.NATURAL, EmblemTypes.ASTRAL, EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "Tidal Fist, Drown thy enemies, Cry over your killed foes, Send thy enemies over luna, Damage Up, Night boosts"));

    public static final RegistryObject<Item> SWAMP_EMBLEM = ITEMS.register("swamp_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.WATER, EmblemTypes.NATURAL, EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.OFFENCE,1,EmblemClasses.ACTIVE,1,EmblemClasses.PASSIVE,1),
                    "Drowning ring, Press Secondary Emblem Ability to summon a friendly pufferfish, Slow thy enemies, Steam ring"));

    public static final RegistryObject<Item> CURRENT_EMBLEM = ITEMS.register("current_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.WATER, EmblemTypes.NATURAL, EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Speed up, Respiration, Swim Speed up, +1.2 Underwater Mining Speed, best support for farmers"));

    public static final RegistryObject<Item> MUCKMIRE_EMBLEM = ITEMS.register("muckmire_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.A, List.of(EmblemTypes.WATER, EmblemTypes.NATURAL, EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.OFFENCE,2, EmblemClasses.ACTIVE, 1, EmblemClasses.PASSIVE, 1),
                    "Speed up, Respiration, Swim Speed up, +1.5 Underwater Mining Speed, best support for farmers, " +
                            "Drowning ring, Press Secondary Emblem Ability to summon a friendly pufferfish, Slow thy enemies, Steam ring"));

    public static final RegistryObject<Item> MAELSTROM_EMBLEM = ITEMS.register("maelstrom_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.A, List.of(EmblemTypes.WATER, EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.CELESTIAL, EmblemTypes.ASTRAL),
                    Map.of(EmblemClasses.OFFENCE,2),
                    "Tidal Fist, Drown thy enemies, Cry over your killed foes, Send thy enemies over luna, Damage Up, Night boosts"));

    public static final RegistryObject<Item> FORBIDDEN_OASIS_EMBLEM = ITEMS.register("forbidden_oasis_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.S, List.of(EmblemTypes.WATER, EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.CELESTIAL, EmblemTypes.ASTRAL, EmblemTypes.DIVINE),
                    Map.of(EmblemClasses.OFFENCE,3, EmblemClasses.ACTIVE, 1, EmblemClasses.PASSIVE, 1),
                    "Tidal Fist, Drown thy enemies, Cry over your killed foes, Send thy enemies over luna, Damage Up, Night boosts, " +
                            "Speed up, Respiration, Swim Speed up, +2 Underwater Mining Speed, Rains in you, best support for farmers, " +
                            "Drowning ring, Press Secondary Emblem Ability to summon a friendly pufferfish, Slow thy enemies, Steam ring"));

    public static final RegistryObject<Item> FLORIST_EMBLEM = ITEMS.register("florist_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XF, List.of(EmblemTypes.NATURAL, EmblemTypes.PROFESSION, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.ACTIVE,1),
                    "Press Primary Emblem Ability to summon flowers"));

    public static final RegistryObject<Item> KNIGHT_EMBLEM = ITEMS.register("knight_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.SPECIAL, EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "+0.5 Attack Speed"));

    public static final RegistryObject<Item> GLADIATOR_EMBLEM = ITEMS.register("gladiator_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.SPECIAL, EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "+1 Attack Speed, +0.5 Attack Damage"));

    public static final RegistryObject<Item> ASSASSIN_EMBLEM = ITEMS.register("assassin_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.SPECIAL, EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.OFFENCE,1),
                    "+2 Attack Speed, +2 Attack Damage"));

    public static final RegistryObject<Item> RANDOM_EMBLEM = ITEMS.register("random_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XC, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE,2),
                    "Random"));

    public static final RegistryObject<Item> MOTHER_EARTH_EMBLEM = ITEMS.register("mother_earth_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.SS, List.of(EmblemTypes.PLANT, EmblemTypes.PLANT, EmblemTypes.NATURAL, EmblemTypes.DIVINE),
                    Map.of(EmblemClasses.PASSIVE,3),
                    "Let it Grow"));

    public static final RegistryObject<Item> BRANCH_EMBLEM = ITEMS.register("branch_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.PLANT, EmblemTypes.NATURAL),
                    Map.of(EmblemClasses.PASSIVE,1, EmblemClasses.DEFENCE, 1),
                    "+1 Nature Regen, +3 Max Health"));

    public static final RegistryObject<Item> FERTILIZED_EMBLEM = ITEMS.register("fertilized_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.PLANT, EmblemTypes.NATURAL),
                    Map.of(EmblemClasses.PASSIVE,1, EmblemClasses.OFFENCE, 1),
                    "+0.5 Attack Speed, +1 Photosynthesis"));

    public static final RegistryObject<Item> BUTCHER_EMBLEM = ITEMS.register("butcher_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Chance to get double animal loot"));

    public static final RegistryObject<Item> SKIN_TRANSPLANT_EMBLEM = ITEMS.register("skin_transplant_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Switches loot for rabbit and cow"));

    public static final RegistryObject<Item> ARCHER_EMBLEM = ITEMS.register("archer_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Chance to not consume arrow"));

    public static final RegistryObject<Item> SMELTER_EMBLEM = ITEMS.register("smelter_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.PROFESSION,EmblemTypes.FIRE),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Auto smelts ores"));

    public static final RegistryObject<Item> BURNER_EMBLEM = ITEMS.register("burner_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XF, List.of(EmblemTypes.PROFESSION,EmblemTypes.FIRE),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Auto smelts blocks"));

    public static final RegistryObject<Item> SMOKING_EMBLEM = ITEMS.register("smoking_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.PROFESSION,EmblemTypes.FIRE),
                    Map.of(EmblemClasses.PASSIVE,1),
                    "Auto smelts loot"));

    public static final RegistryObject<Item> CLAY_EMBLEM = ITEMS.register("clay_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 1, EmblemClasses.OFFENCE, 1),
                    "+0.5 Attack Damage, Slow thy enemies"));

    public static final RegistryObject<Item> BOULDER_EMBLEM = ITEMS.register("boulder_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.OFFENCE, 1),
                    "+1.5 Attack Damage"));

    public static final RegistryObject<Item> STALAGMITE_EMBLEM = ITEMS.register("stalagmite_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.DEFENCE, 1),
                    "+10 Armor when underground"));

    public static final RegistryObject<Item> STALACTITE_EMBLEM = ITEMS.register("stalactite_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.OFFENCE, 1),
                    "+5 Damage when near ceiling"));

    public static final RegistryObject<Item> STALAGNATE_EMBLEM = ITEMS.register("stalagnate_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.DEFENCE, 1, EmblemClasses.OFFENCE, 1),
                    "+5 Damage when near ceiling, +10 Armor when underground"));

    public static final RegistryObject<Item> REMAINS_EMBLEM = ITEMS.register("remains_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 1, EmblemClasses.DEFENCE, 1),
                    "+1 Mining Speed, Speed up, Bonus speed on sand, +6 Armor"));

    public static final RegistryObject<Item> LUSH_CAVE_EMBLEM = ITEMS.register("lush_cave_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.C, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND,EmblemTypes.PLANT),
                    Map.of(EmblemClasses.PASSIVE, 1, EmblemClasses.DEFENCE, 1, EmblemClasses.OFFENCE, 1),
                    "+5 Armor, Chance to multiply ore drops, Speed up, +0.75 Damage, +2 Entity Reach, 20% Damage Reduction while Sneaking"));

    public static final RegistryObject<Item> STALACTITE_CAVE_EMBLEM = ITEMS.register("stalactite_cave_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.C, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 1, EmblemClasses.DEFENCE, 1, EmblemClasses.OFFENCE, 1),
                    "Chance to multiply ore drops, Speed up, +0.75 Damage, +5 Damage when near ceiling, +15 Armor when underground"));

    public static final RegistryObject<Item> DESERT_CAVE_EMBLEM = ITEMS.register("desert_cave_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.C, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 1, EmblemClasses.DEFENCE, 1, EmblemClasses.OFFENCE, 1),
                    "+11 Armor, Chance to multiply ore drops, +0.75 Damage, +1 Mining Speed, Speed up, Bonus speed on sand"));

    public static final RegistryObject<Item> DOWNHILL_EMBLEM = ITEMS.register("downhill_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Speed up when hungry"));

    public static final RegistryObject<Item> DUNE_EMBLEM = ITEMS.register("dune_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Speed up, Bonus speed on sand and when hungry"));

    public static final RegistryObject<Item> HILL_EMBLEM = ITEMS.register("hill_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 1, EmblemClasses.DEFENCE, 1),
                    "Speed up, Bonus speed on sand and when hungry, Armor rises with you"));

    public static final RegistryObject<Item> MOUNTAIN_EMBLEM = ITEMS.register("mountain_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 1, EmblemClasses.DEFENCE, 1),
                    "Speed up, Bonus speed on sand and when hungry, Armor rises with you"));

    public static final RegistryObject<Item> MOUNTAIN_CAVE_EMBLEM = ITEMS.register("mountain_cave_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.C, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 1, EmblemClasses.DEFENCE, 1, EmblemClasses.OFFENCE, 1),
                    "+5 Armor, Chance to multiply ore drops, +0.75 Damage, Speed up, Bonus speed on sand and when hungry, Armor rises with you absolutely"));

    public static final RegistryObject<Item> SISYPHUS_EMBLEM = ITEMS.register("sisyphus_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND,EmblemTypes.DIVINE),
                    Map.of(EmblemClasses.OFFENCE, 1),
                    "The higher you are, bigger damage you deal at cost of lower health"));

    public static final RegistryObject<Item> MOUNTAIN_RANGE_EMBLEM = ITEMS.register("mountain_range_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XC, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 1, EmblemClasses.DEFENCE, 1),
                    "Speed up, Bonus speed on sand and snow and when hungry, Armor rises with you"));


    public static final RegistryObject<Item> RADIANT_CAVE_EMBLEM = ITEMS.register("radiant_cave_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.B, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 2, EmblemClasses.DEFENCE, 1, EmblemClasses.OFFENCE, 1),
                    "+10 Armor, Chance to multiply ore drops, +1.5 Damage, Speed up, Bonus speed on sand and when hungry, Armor rises with you absolutely, " +
                            "+3 Entity Reach, 30% Damage Reduction while Sneaking"));


    public static final RegistryObject<Item> LIFELESS_CAVE_EMBLEM = ITEMS.register("lifeless_cave_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.B, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 2, EmblemClasses.DEFENCE, 2),
                    "Chance to multiply ore drops, Speed up, +1.5 Damage, +5 Damage when near ceiling, +26 Armor when underground, "+
                            "+1.4 Mining Speed, Bonus speed on sand"));


    public static final RegistryObject<Item> ATOMIC_CAVE_EMBLEM = ITEMS.register("atomic_cave_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.S, List.of(EmblemTypes.NATURAL,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 3, EmblemClasses.DEFENCE, 3, EmblemClasses.OFFENCE, 1),
                    "Chance to multiply ore drops, Speed up, +2.5 Damage, +5 Damage when near ceiling, +36 Armor when underground, "+
                            "+2 Mining Speed, "+
                            "Bonus speed on sand and snow and when hungry, Armor rises with you absolutely, " +
                            "+4 Entity Reach, 40% Damage Reduction while Sneaking"));


    public static final RegistryObject<Item> PETRIFIED_DANDELION_EMBLEM = ITEMS.register("petrified_dandelion_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.NATURAL,EmblemTypes.PLANT,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Slow Falling"));

    public static final RegistryObject<Item> IRON_EMBLEM = ITEMS.register("iron_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.FIRE,EmblemTypes.GROUND),
                    Map.of(EmblemClasses.OFFENCE, 1),
                    "+1 Attack Speed"));

    public static final RegistryObject<Item> NOTE_EMBLEM = ITEMS.register("note_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.MUSIC),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Sometimes spawns a healing note"));

    public static final RegistryObject<Item> CHORD_EMBLEM = ITEMS.register("chord_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.MUSIC),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Sometimes spawns healing notes"));

    public static final RegistryObject<Item> UNTUNED_EMBLEM = ITEMS.register("untuned_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.MUSIC),
                    Map.of(EmblemClasses.OFFENCE, 1),
                    "Spawns damaging note on attack"));

    public static final RegistryObject<Item> FALSE_NOTE_EMBLEM = ITEMS.register("false_note_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.MUSIC),
                    Map.of(EmblemClasses.OFFENCE, 1),
                    "Spawns damaging note on attack"));

    public static final RegistryObject<Item> STAFF_EMBLEM = ITEMS.register("staff_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XF, List.of(EmblemTypes.MUSIC),
                    Map.of(EmblemClasses.OFFENCE, 1),
                    "Spawns stationary damaging notes"));

    public static final RegistryObject<Item> LULLABY_EMBLEM = ITEMS.register("lullaby_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.MUSIC),
                    Map.of(EmblemClasses.ACTIVE, 1),
                    "Press primary emblem ability to heal yourself"));

    public static final RegistryObject<Item> BEAT_EMBLEM = ITEMS.register("beat_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.MUSIC),
                    Map.of(EmblemClasses.OFFENCE, 1),
                    "Crit every few attacks"));

    public static final RegistryObject<Item> RHYTHM_EMBLEM = ITEMS.register("rhythm_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.MUSIC),
                    Map.of(EmblemClasses.DEFENCE, 1),
                    "Spawns healing note on every third attack"));

    public static final RegistryObject<Item> SONG_EMBLEM = ITEMS.register("song_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.MUSIC),
                    Map.of(EmblemClasses.DEFENCE, 1),
                    "Spawns healing note on every second crit attack"));

    public static final RegistryObject<Item> ANTHEM_EMBLEM = ITEMS.register("anthem_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.SEASONAL, EmblemTypes.MUSIC),
                    Map.of(EmblemClasses.DEFENCE, 1),
                    "Spawns three healing notes on every second damage received"));

    public static final RegistryObject<Item> INDEPENDENT_EMBLEM = ITEMS.register("independent_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.SEASONAL),
                    Map.of(EmblemClasses.DEFENCE, 1),
                    "Spawns some healing notes on every second damage received"));

    public static final RegistryObject<Item> PAPER_EMBLEM = ITEMS.register("paper_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.PLANT, EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.DEFENCE, 1),
                    "Thin like paper"));

    public static final RegistryObject<Item> CLOTH_EMBLEM = ITEMS.register("cloth_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.PLANT, EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.DEFENCE, 1),
                    "+0.1 Knockback Resistance"));

    public static final RegistryObject<Item> SOIL_EMBLEM = ITEMS.register("soil_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.PLANT, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Rich Harvest"));

    public static final RegistryObject<Item> RICH_SOIL_EMBLEM = ITEMS.register("rich_soil_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.PLANT, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Richer Harvest"));

    public static final RegistryObject<Item> NUTRITIOUS_EMBLEM = ITEMS.register("nutritious_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.PLANT),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Higher compost chances"));

    public static final RegistryObject<Item> ITEMIUM_EMBLEM = ITEMS.register("itemium_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "+5% itemuium's spawn chances"));

    public static final RegistryObject<Item> FINDERS_EMBLEM = ITEMS.register("finders_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XF, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "+15% itemuium's spawn chances"));

    public static final RegistryObject<Item> COIN_EMBLEM = ITEMS.register("coin_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "+25% itemuium's spawn chances"));

    public static final RegistryObject<Item> HAPPY_EMBLEM = ITEMS.register("happy_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "+33% itemuium's spawn chances"));

    public static final RegistryObject<Item> PRAYERS_EMBLEM = ITEMS.register("prayers_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.SPECIAL, EmblemTypes.DIVINE),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "+50% itemuium's spawn chances"));

    public static final RegistryObject<Item> LUCKY_EMBLEM = ITEMS.register("lucky_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "+66% itemuium's spawn chances"));

    public static final RegistryObject<Item> LEPRECHAUN_EMBLEM = ITEMS.register("leprechauns_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.C, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "+75% itemuium's spawn chances"));

    public static final RegistryObject<Item> LOTTERY_EMBLEM = ITEMS.register("lottery_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XC, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE, 2),
                    "+90% itemuium's spawn chances"));

    public static final RegistryObject<Item> TRAINED_EMBLEM = ITEMS.register("trained_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.B, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE, 2),
                    "+110% itemuium's spawn chances"));

    public static final RegistryObject<Item> EXPERT_EMBLEM = ITEMS.register("expert_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XB, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE, 2),
                    "+140% itemuium's spawn chances"));

    public static final RegistryObject<Item> MASTER_EMBLEM = ITEMS.register("master_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.A, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE, 2),
                    "+170% itemuium's spawn chances"));

    public static final RegistryObject<Item> CHAMPION_EMBLEM = ITEMS.register("champion_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XA, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE, 2),
                    "+200% itemuium's spawn chances"));

    public static final RegistryObject<Item> WINNER_EMBLEM = ITEMS.register("winner_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.S, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE, 3),
                    "+230% itemuium's spawn chances"));

    public static final RegistryObject<Item> RULE_MASTER_EMBLEM = ITEMS.register("rule_master_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XS, List.of(EmblemTypes.SPECIAL,EmblemTypes.CARD),
                    Map.of(EmblemClasses.PASSIVE, 3),
                    "+260% itemuium's spawn chances"));

    public static final RegistryObject<Item> FORESEEN_EMBLEM = ITEMS.register("foreseen_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.SS, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE, 3),
                    "+300% itemuium's spawn chances"));

    public static final RegistryObject<Item> THE_TRUTH_EMBLEM = ITEMS.register("the_truth_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XSS, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE, 3),
                    "+370% itemuium's spawn chances"));

    public static final RegistryObject<Item> CREATOR_BLESSED_EMBLEM = ITEMS.register("creator_blessed_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.U, List.of(EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.PASSIVE, 3),
                    "+450% itemuium's spawn chances"));

    public static final RegistryObject<Item> ANVIL_EMBLEM = ITEMS.register("anvil_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "+5% crafted item durability"));

    public static final RegistryObject<Item> BLACKSMITH_EMBLEM = ITEMS.register("blacksmith_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "+15% crafted item durability"));

    public static final RegistryObject<Item> FORGER_EMBLEM = ITEMS.register("forger_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.C, List.of(EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "+30% crafted item durability"));

    public static final RegistryObject<Item> GOD_FORGE_EMBLEM = ITEMS.register("god_forge_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.B, List.of(EmblemTypes.PROFESSION),
                    Map.of(EmblemClasses.PASSIVE, 2),
                    "+55% crafted item durability"));

    public static final RegistryObject<Item> HEPHAESTUS_EMBLEM = ITEMS.register("hephaestus_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.S, List.of(EmblemTypes.PROFESSION, EmblemTypes.DIVINE),
                    Map.of(EmblemClasses.PASSIVE, 2),
                    "+85% crafted item durability"));

    public static final RegistryObject<Item> FARMER_APPRENTICE_EMBLEM = ITEMS.register("farmer_apprentice_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.PROFESSION, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Experience from farming"));

    public static final RegistryObject<Item> SNOW_EMBLEM = ITEMS.register("snow_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.ICE),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Allows walk on powder snow"));

    public static final RegistryObject<Item> ICICLE_EMBLEM = ITEMS.register("icicle_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.ICE),
                    Map.of(EmblemClasses.OFFENCE, 1),
                    "Press secondary emblem ability to shoot icicles"));

    public static final RegistryObject<Item> RINK_EMBLEM = ITEMS.register("rink_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.ICE),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Press secondary emblem ability to toggle ice skates"));

    public static final RegistryObject<Item> YUKI_EMBLEM = ITEMS.register("yuki_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.ICE),
                    Map.of(EmblemClasses.OFFENCE, 1),
                    "Slowly freeze everything around you"));

    public static final RegistryObject<Item> MAGNETITE_EMBLEM = ITEMS.register("magnetite_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XE, List.of(EmblemTypes.TECH, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Higher pickup range"));

    public static final RegistryObject<Item> ELECTROMAGNET_EMBLEM = ITEMS.register("electromagnet_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.TECH, EmblemTypes.GROUND, EmblemTypes.ELECTRICITY),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Higher pickup range"));

    public static final RegistryObject<Item> PULSE_EMBLEM = ITEMS.register("pulse_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.TECH, EmblemTypes.GROUND, EmblemTypes.ELECTRICITY, EmblemTypes.SPECIAL),
                    Map.of(EmblemClasses.ACTIVE, 1, EmblemClasses.OFFENCE, 1),
                    "Press primary emblem ability to push and zap away mobs around you"));

    public static final RegistryObject<Item> LIGHTNING_EMBLEM = ITEMS.register("lightning_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XD, List.of(EmblemTypes.ELECTRICITY),
                    Map.of(EmblemClasses.OFFENCE, 1),
                    "Lightning aspect"));

    public static final RegistryObject<Item> UNDERWORLD_EMBLEM = ITEMS.register("underworld_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.SS, List.of(EmblemTypes.GROUND, EmblemTypes.GROUND, EmblemTypes.NATURAL,EmblemTypes.CELESTIAL),
                    Map.of(EmblemClasses.PASSIVE, 3),
                    "Fortune Fever"));

    public static final RegistryObject<Item> APPLE_EMBLEM = ITEMS.register("apple_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.XF, List.of(EmblemTypes.PLANT),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Green glory"));

    public static final RegistryObject<Item> MOONDEW_EMBLEM = ITEMS.register("moondew_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.B, List.of(EmblemTypes.PLANT, EmblemTypes.ASTRAL),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Nighttime farming"));

    public static final RegistryObject<Item> ECLIPSE_EMBLEM = ITEMS.register("eclipse_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.B, List.of(EmblemTypes.CELESTIAL, EmblemTypes.ASTRAL),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Lunar-synthesis"));

    public static final RegistryObject<Item> LUAU_EMBLEM = ITEMS.register("luau_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.B, List.of(EmblemTypes.SEASONAL, EmblemTypes.PLANT, EmblemTypes.WATER),
                    Map.of(EmblemClasses.PASSIVE, 2),
                    "Little bit of this and that"));

    public static final RegistryObject<Item> PETRIFIED_VEGE_EMBLEM = ITEMS.register("petrified_vege_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.B, List.of(EmblemTypes.SPECIAL, EmblemTypes.PLANT),
                    Map.of(EmblemClasses.PASSIVE, 2),
                    "Saturation"));

    public static final RegistryObject<Item> HERMES_EMBLEM = ITEMS.register("hermes_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.AIR, EmblemTypes.DIVINE),
                    Map.of(EmblemClasses.PASSIVE, 1),
                    "Higher lung capacity"));

    public static final RegistryObject<Item> VOLCANO_EMBLEM = ITEMS.register("volcano_emblem",
            () -> new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.FIRE, EmblemTypes.GROUND),
                    Map.of(EmblemClasses.ACTIVE, 1),
                    "Press Primary Emblem Ability to spawn volcanic shards"));

















    public static final RegistryObject<Item> BULBULIUM = ITEMS.register("bulbulium",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.WATER)));

    public static final RegistryObject<Item> BURNING_CLOTH = ITEMS.register("burning_cloth",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.FIRE)));

    public static final RegistryObject<Item> SUNLIGHT_SHARD = ITEMS.register("sunlight_shard",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ASTRAL)));

    public static final RegistryObject<Item> SHIELDED_FOSSIL = ITEMS.register("shielded_fossil",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.GROUND)));

    public static final RegistryObject<Item> VERDANT_CORE = ITEMS.register("verdant_core",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.NATURAL)));

    public static final RegistryObject<Item> BONSAI = ITEMS.register("bonsai",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.PLANT)));

    public static final RegistryObject<Item> REFERENCIUM = ITEMS.register("referencium",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.SPECIAL)));

    public static final RegistryObject<Item> FRACTURED_ICICLE = ITEMS.register("fractured_icicle",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ICE)));

    public static final RegistryObject<Item> GLASS_LUNG = ITEMS.register("glass_lung",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.AIR)));

    public static final RegistryObject<Item> BATTERY = ITEMS.register("battery",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ELECTRICITY)));

    public static final RegistryObject<Item> THE_EARTH = ITEMS.register("the_earth",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ELEMENTAL)));

    public static final RegistryObject<Item> THE_TORCH = ITEMS.register("the_torch",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.SEASONAL)));

    public static final RegistryObject<Item> JACK_OF_SPADES = ITEMS.register("jack_of_spades",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.CARD)));

    public static final RegistryObject<Item> BENT_FIN = ITEMS.register("bent_fin",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ANIMAL)));

    public static final RegistryObject<Item> MOON_FRAGMENT = ITEMS.register("moon_fragment",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.CELESTIAL)));

    public static final RegistryObject<Item> IRON_PLATING = ITEMS.register("iron_plating",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.TECH)));

    public static final RegistryObject<Item> COIN_NECKLACE = ITEMS.register("coin_necklace",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.PROFESSION)));

    public static final RegistryObject<Item> BREAD_PIECE = ITEMS.register("bread_piece",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.DIVINE)));

    public static final RegistryObject<Item> HALF_NOTE = ITEMS.register("half_note",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.MUSIC)));



    public static final RegistryObject<Item> NEPTUNES_GIFT = ITEMS.register("neptunes_gift",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.WATER, EmblemTypes.ASTRAL)));

    public static final RegistryObject<Item> SEED_POUCH = ITEMS.register("seed_pouch",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.PLANT, EmblemTypes.NATURAL)));

    public static final RegistryObject<Item> COSMOS_ESSENCE = ITEMS.register("cosmos_essence",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ASTRAL, EmblemTypes.CELESTIAL)));

    public static final RegistryObject<Item> PHOENIX_FEATHER = ITEMS.register("phoenix_feather",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ANIMAL, EmblemTypes.FIRE)));

    public static final RegistryObject<Item> DOVE_WING = ITEMS.register("dove_wing",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ANIMAL, EmblemTypes.AIR)));

    public static final RegistryObject<Item> ANCIENT_REMAINS = ITEMS.register("ancient_remains",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.GROUND, EmblemTypes.TECH)));

    public static final RegistryObject<Item> TESLA_BAR = ITEMS.register("tesla_bar",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ELECTRICITY, EmblemTypes.TECH)));

    public static final RegistryObject<Item> RADIANT_GEODE = ITEMS.register("radiant_geode",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ELECTRICITY, EmblemTypes.GROUND)));

    public static final RegistryObject<Item> PROMETHEUS_GIFT = ITEMS.register("prometheus_gift",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.SEASONAL, EmblemTypes.ELEMENTAL)));

    public static final RegistryObject<Item> WINE_CUP = ITEMS.register("wine_cup",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.DIVINE, EmblemTypes.SPECIAL)));

    public static final RegistryObject<Item> DISCAPTURED_ENERGY = ITEMS.register("discaptured_energy",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ELECTRICITY, EmblemTypes.TECH)));

    public static final RegistryObject<Item> WISTERIA_FLOWER = ITEMS.register("wisteria_flower",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.NATURAL, EmblemTypes.ELEMENTAL)));

    public static final RegistryObject<Item> CORAL_CROWN = ITEMS.register("coral_crown",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.NATURAL, EmblemTypes.WATER)));

    public static final RegistryObject<Item> SALT_ORE = ITEMS.register("salt_ore",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ELEMENTAL, EmblemTypes.GROUND)));

    public static final RegistryObject<Item> BUBBLIUM = ITEMS.register("bubblium",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ELEMENTAL, EmblemTypes.WATER)));



    public static final RegistryObject<Item> EAGLE_BEAK = ITEMS.register("eagle_beak",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ANIMAL, EmblemTypes.AIR, EmblemTypes.ELEMENTAL)));

    public static final RegistryObject<Item> STINGER = ITEMS.register("stinger",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ANIMAL, EmblemTypes.AIR, EmblemTypes.NATURAL)));

    public static final RegistryObject<Item> PAW_FOSSIL = ITEMS.register("paw_fossil",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ANIMAL, EmblemTypes.NATURAL, EmblemTypes.ELEMENTAL)));

    public static final RegistryObject<Item> MOONGLOW = ITEMS.register("moonglow",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.CELESTIAL, EmblemTypes.NATURAL, EmblemTypes.ELEMENTAL)));

    public static final RegistryObject<Item> TINY_BUSH = ITEMS.register("tiny_bush",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.PLANT, EmblemTypes.NATURAL, EmblemTypes.WATER)));

    public static final RegistryObject<Item> SHATTERED_VOID = ITEMS.register("shattered_void",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ASTRAL, EmblemTypes.CELESTIAL, EmblemTypes.ELEMENTAL)));

    public static final RegistryObject<Item> SUNRAY_BEAM = ITEMS.register("sunray_beam",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ASTRAL, EmblemTypes.CELESTIAL, EmblemTypes.NATURAL)));

    public static final RegistryObject<Item> STAR_SAPLING = ITEMS.register("star_sapling",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.PLANT, EmblemTypes.ELEMENTAL, EmblemTypes.NATURAL)));

    public static final RegistryObject<Item> BRITTLE_FOSSIL = ITEMS.register("brittle_fossil",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.GROUND, EmblemTypes.ELEMENTAL, EmblemTypes.NATURAL)));

    public static final RegistryObject<Item> JADE_EARRING = ITEMS.register("jade_earring",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.PLANT, EmblemTypes.ELEMENTAL, EmblemTypes.GROUND)));

    public static final RegistryObject<Item> REEFS_GLORY = ITEMS.register("reefs_glory",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.WATER, EmblemTypes.ELEMENTAL, EmblemTypes.NATURAL)));

    public static final RegistryObject<Item> SAPPHIRE_NECKLACE = ITEMS.register("sapphire_necklace",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.WATER, EmblemTypes.ELEMENTAL, EmblemTypes.GROUND)));

    public static final RegistryObject<Item> OWSHUN = ITEMS.register("owshun",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.WATER, EmblemTypes.ELEMENTAL, EmblemTypes.PLANT)));

    public static final RegistryObject<Item> BONE_CROWN = ITEMS.register("bone_crown",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.WATER, EmblemTypes.NATURAL, EmblemTypes.GROUND)));

    public static final RegistryObject<Item> EARF = ITEMS.register("earf",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.PLANT, EmblemTypes.NATURAL, EmblemTypes.GROUND)));



    public static final RegistryObject<Item> LIVING_MASS = ITEMS.register("living_mass",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ASTRAL, EmblemTypes.CELESTIAL, EmblemTypes.ELEMENTAL, EmblemTypes.NATURAL)));

    public static final RegistryObject<Item> EDENS_VINES = ITEMS.register("edens_vines",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.PLANT, EmblemTypes.NATURAL, EmblemTypes.WATER, EmblemTypes.ELEMENTAL)));

    public static final RegistryObject<Item> SERPENT_SEED = ITEMS.register("serpent_seed",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.PLANT, EmblemTypes.NATURAL, EmblemTypes.GROUND, EmblemTypes.ELEMENTAL)));

    public static final RegistryObject<Item> ROOTED_ROCK = ITEMS.register("rooted_rock",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.PLANT, EmblemTypes.NATURAL, EmblemTypes.WATER, EmblemTypes.GROUND)));

    public static final RegistryObject<Item> GRAND_ANCHOR = ITEMS.register("grand_anchor",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.PLANT, EmblemTypes.WATER, EmblemTypes.ELEMENTAL, EmblemTypes.GROUND)));

    public static final RegistryObject<Item> BLACK_PEARL = ITEMS.register("black_pearl",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.NATURAL, EmblemTypes.WATER, EmblemTypes.ELEMENTAL, EmblemTypes.GROUND)));

    public static final RegistryObject<Item> TANGLED_TAILS = ITEMS.register("tangled_tails",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.ANIMAL, EmblemTypes.AIR, EmblemTypes.ELEMENTAL, EmblemTypes.NATURAL)));



    public static final RegistryObject<Item> EVES_APPLE = ITEMS.register("eves_apple",
            () -> new ItemiumItem(new Item.Properties(), List.of(EmblemTypes.PLANT, EmblemTypes.NATURAL, EmblemTypes.WATER, EmblemTypes.ELEMENTAL, EmblemTypes.GROUND)));







    public static final RegistryObject<Item> PLANT_SIGIL_1 = ITEMS.register("plant_sigil_1",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLANT_SIGIL_2 = ITEMS.register("plant_sigil_2",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EMBLEMIUM_SIGIL = ITEMS.register("emblemium_sigil",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EMBLEMIUM_INSIGNIA = ITEMS.register("emblemium_insignia",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EMBLEMENTAL_ENTHERA = ITEMS.register("emblemental_enthera",
            () -> new EmblementalEntheraItem(new Item.Properties()));


    public static final RegistryObject<Item> ENTHEREAL_MASS = ITEMS.register("enthereal_mass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENTHEREAL_CONJUGATION = ITEMS.register("enthereal_conjugation",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENTHEREAL_VOID = ITEMS.register("enthereal_void",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MANIPULATION_CORE = ITEMS.register("manipulation_core",
            () -> new Item(new Item.Properties()));







    //F TIER EMBLEMIUM
    public static final RegistryObject<Item> WOODEN_EMBLEMIUM = ITEMS.register("wooden_emblemium",
            () -> new EmblemSlotAdder(new Item.Properties(), 2, "f_tier_emblem"));
    //E TIER EMBLEMIUM
    public static final RegistryObject<Item> CLOUD_EMBLEMIUM = ITEMS.register("cloud_emblemium",
            () -> new EmblemSlotAdder(new Item.Properties(), 2, "e_tier_emblem"));
    //D TIER EMBLEMIUM
    public static final RegistryObject<Item> BLESSED_EMBLEMIUM = ITEMS.register("blessed_emblemium",
            () -> new EmblemSlotAdder(new Item.Properties(), 1, "d_tier_emblem"));
    //D TIER EMBLEMIUM
    public static final RegistryObject<Item> PROFANED_EMBLEMIUM = ITEMS.register("profaned_emblemium",
            () -> new EmblemSlotAdder(new Item.Properties(), 1, "c_tier_emblem"));
    //D TIER EMBLEMIUM
    public static final RegistryObject<Item> ANCIENT_EMBLEMIUM = ITEMS.register("ancient_emblemium",
            () -> new EmblemSlotAdder(new Item.Properties(), 1, "b_tier_emblem"));
    //D TIER EMBLEMIUM
    public static final RegistryObject<Item> CURSED_EMBLEMIUM = ITEMS.register("cursed_emblemium",
            () -> new EmblemSlotAdder(new Item.Properties(), 1, "a_tier_emblem"));
    //D TIER EMBLEMIUM
    public static final RegistryObject<Item> GLOBAL_EMBLEMIUM = ITEMS.register("global_emblemium",
            () -> new EmblemSlotAdder(new Item.Properties(), 1, "s_tier_emblem"));
    //D TIER EMBLEMIUM
    public static final RegistryObject<Item> COSMIC_EMBLEMIUM = ITEMS.register("cosmic_emblemium",
            () -> new EmblemSlotAdder(new Item.Properties(), 1, "ss_tier_emblem"));
    //OMNI EMBLEMIUM
    public static final RegistryObject<Item> OVERWORLD_EMBLEMIUM = ITEMS.register("overworld_emblemium",
            () -> new EmblemSlotAdder(new Item.Properties(), 1, "emblem"));
    public static final RegistryObject<Item> NETHER_EMBLEMIUM = ITEMS.register("nether_emblemium",
            () -> new EmblemSlotAdder(new Item.Properties(), 2, "emblem"));
    public static final RegistryObject<Item> END_EMBLEMIUM = ITEMS.register("end_emblemium",
            () -> new EmblemSlotAdder(new Item.Properties(), 3, "emblem"));
    public static final RegistryObject<Item> STAR_EMBLEMIUM = ITEMS.register("star_emblemium",
            () -> new EmblemSlotAdder(new Item.Properties(), 4, "emblem"));




    //Rupec's Elytras
    public static final RegistryObject<Item> GLIDER_EMBLEM = ITEMS.register("glider_emblem",
            () -> {
                if (ModList.get().isLoaded("rupecs_elytras")) {
                    return new ModEmblemItem(new Item.Properties(), EmblemTiers.E, List.of(EmblemTypes.SPECIAL, EmblemTypes.AIR),
                            Map.of(EmblemClasses.PASSIVE,1),
                            "+20% Flight Time");
                }
                return new ProxyEmblemItem(new Item.Properties().stacksTo(1));
            });
    public static final RegistryObject<Item> FEATHER_EMBLEM = ITEMS.register("feather_emblem",
            () -> {
                if (ModList.get().isLoaded("rupecs_elytras")) {
                    return new ModEmblemItem(new Item.Properties(), EmblemTiers.F, List.of(EmblemTypes.SPECIAL, EmblemTypes.AIR),
                            Map.of(EmblemClasses.PASSIVE,1),
                            "+10% Flight Time");
                }
                return new ProxyEmblemItem(new Item.Properties().stacksTo(1));
            });
    public static final RegistryObject<Item> WIND_CATCHER_EMBLEM = ITEMS.register("wind_catcher_emblem",
            () -> {
                if (ModList.get().isLoaded("rupecs_elytras")) {
                    return new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.SPECIAL, EmblemTypes.AIR),
                            Map.of(EmblemClasses.PASSIVE,1),
                            "+30% Flight Time");
                }
                return new ProxyEmblemItem(new Item.Properties().stacksTo(1));
            });
    public static final RegistryObject<Item> DASHER_EMBLEM = ITEMS.register("dasher_emblem",
            () -> {
                if (ModList.get().isLoaded("rupecs_elytras")) {
                    return new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.SPECIAL, EmblemTypes.AIR),
                            Map.of(EmblemClasses.ACTIVE,1),
                            "Press Primary Emblem Ability to dash mid flight");
                }
                return new ProxyEmblemItem(new Item.Properties().stacksTo(1));
            });
    public static final RegistryObject<Item> PHOENIX_EMBLEM = ITEMS.register("phoenix_emblem",
            () -> {
                if (ModList.get().isLoaded("rupecs_elytras")) {
                    return new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.SPECIAL, EmblemTypes.AIR, EmblemTypes.ANIMAL, EmblemTypes.FIRE),
                            Map.of(EmblemClasses.OFFENCE,1),
                            "Burning Takeoff");
                }
                return new ProxyEmblemItem(new Item.Properties().stacksTo(1));
            });
    public static final RegistryObject<Item> PHOENIX_WING_EMBLEM = ITEMS.register("phoenix_wing_emblem",
            () -> {
                if (ModList.get().isLoaded("rupecs_elytras")) {
                    return new ModEmblemItem(new Item.Properties(), EmblemTiers.D, List.of(EmblemTypes.SPECIAL, EmblemTypes.AIR, EmblemTypes.ANIMAL, EmblemTypes.FIRE),
                            Map.of(EmblemClasses.OFFENCE,1),
                            "Burning Flight");
                }
                return new ProxyEmblemItem(new Item.Properties().stacksTo(1));
            });
    public static final RegistryObject<Item> TRUE_PHOENIX_EMBLEM = ITEMS.register("true_phoenix_emblem",
            () -> {
                if (ModList.get().isLoaded("rupecs_elytras")) {
                    return new ModEmblemItem(new Item.Properties(), EmblemTiers.C, List.of(EmblemTypes.SPECIAL, EmblemTypes.AIR, EmblemTypes.ANIMAL, EmblemTypes.FIRE),
                            Map.of(EmblemClasses.OFFENCE,1),
                            "Burning Bird");
                }
                return new ProxyEmblemItem(new Item.Properties().stacksTo(1));
            });
    public static final RegistryObject<Item> HUMMING_BIRD_EMBLEM = ITEMS.register("humming_bird_emblem",
            () -> {
                if (ModList.get().isLoaded("rupecs_elytras")) {
                    return new ModEmblemItem(new Item.Properties(), EmblemTiers.C, List.of(EmblemTypes.AIR, EmblemTypes.ANIMAL),
                            Map.of(EmblemClasses.PASSIVE,1),
                            "+50% Flight Time");
                }
                return new ProxyEmblemItem(new Item.Properties().stacksTo(1));
            });
    public static final RegistryObject<Item> MOUNTAIN_WIND_EMBLEM = ITEMS.register("mountain_wind_emblem",
            () -> {
                if (ModList.get().isLoaded("rupecs_elytras")) {
                    return new ModEmblemItem(new Item.Properties(), EmblemTiers.B, List.of(EmblemTypes.AIR, EmblemTypes.NATURAL),
                            Map.of(EmblemClasses.PASSIVE,1),
                            "+70% Flight Time");
                }
                return new ProxyEmblemItem(new Item.Properties().stacksTo(1));
            });
    public static final RegistryObject<Item> WEIGHTLESS_EMBLEM = ITEMS.register("weightless_emblem",
            () -> {
                if (ModList.get().isLoaded("rupecs_elytras")) {
                    return new ModEmblemItem(new Item.Properties(), EmblemTiers.A, List.of(EmblemTypes.AIR, EmblemTypes.NATURAL, EmblemTypes.SPECIAL),
                            Map.of(EmblemClasses.PASSIVE,1),
                            "+100% Flight Time");
                }
                return new ProxyEmblemItem(new Item.Properties().stacksTo(1));
            });
    public static final RegistryObject<Item> WING_POWER_EMBLEM = ITEMS.register("wing_power_emblem",
            () -> {
                if (ModList.get().isLoaded("rupecs_elytras")) {
                    return new ModEmblemItem(new Item.Properties(), EmblemTiers.S, List.of(EmblemTypes.AIR, EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.ASTRAL),
                            Map.of(EmblemClasses.PASSIVE,2),
                            "+200% Flight Time");
                }
                return new ProxyEmblemItem(new Item.Properties().stacksTo(1));
            });
    public static final RegistryObject<Item> SKY_WANDERER_EMBLEM = ITEMS.register("sky_wanderer_emblem",
            () -> {
                if (ModList.get().isLoaded("rupecs_elytras")) {
                    return new ModEmblemItem(new Item.Properties(), EmblemTiers.SS, List.of(EmblemTypes.AIR, EmblemTypes.AIR, EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.ASTRAL, EmblemTypes.CELESTIAL),
                            Map.of(EmblemClasses.PASSIVE,3),
                            "Endless Flight Time");
                }
                return new ProxyEmblemItem(new Item.Properties().stacksTo(1));
            });
    public static final RegistryObject<Item> SKY_SHREDDING_EMBLEM = ITEMS.register("sky_shredding_emblem",
            () -> {
                if (ModList.get().isLoaded("rupecs_elytras")) {
                    return new ModEmblemItem(new Item.Properties(), EmblemTiers.B, List.of(EmblemTypes.AIR, EmblemTypes.NATURAL, EmblemTypes.SPECIAL, EmblemTypes.ASTRAL),
                            Map.of(EmblemClasses.ACTIVE,2),
                            "Press Primary Emblem Ability to dash mid flight");
                }
                return new ProxyEmblemItem(new Item.Properties().stacksTo(1));
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

    }

}
