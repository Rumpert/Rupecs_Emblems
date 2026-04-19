package net.hubert.rupecs_emblems.providers;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.attribute.attributeHandlers.custom.RandomHandlerCommon;
import net.hubert.rupecs_emblems.command.gamerule.ModGameRules;
import net.hubert.rupecs_emblems.entity.ModEntities;
import net.hubert.rupecs_emblems.entity.custom.CometShardProjectile;
import net.hubert.rupecs_emblems.entity.custom.DamagingNoteProjectile;
import net.hubert.rupecs_emblems.entity.custom.HealingNoteProjectile;
import net.hubert.rupecs_emblems.item.custom.ModEmblemItem;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.RandomPacket;
import net.hubert.rupecs_emblems.network.packet.RinkPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.ModList;
import net.rumpertt.rupecs_elytras.item.custom.ModElytraItem;
import net.rumpertt.rupecs_elytras.playerData.FlightTimeDataProvider;
import net.rumpertt.rupecs_elytras.playerData.PlayerFlightTimeEntry;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.*;

public class EmblemEffectProvider {
    // Constants for emblem bonuses
    private static final int WOOD_EMBLEM_HP_BONUS = 2;
    private static final int HEART_EMBLEM_HP_BONUS = 4;
    private static final double STONE_EMBLEM_DAMAGE_BONUS = 0.5;
    private static final double MOON_EMBLEM_DAMAGE_BONUS = 1;
    private static final double GROUND_EMBLEM_ATTACK_SPEED_BONUS = 0.5;
    private static final double FOSSIL_EMBLEM_MINING_SPEED_BONUS = 0.5;
    private static final double REMAINS_EMBLEM_MINING_SPEED_BONUS = 1;
    private static final double LIFELESS_EMBLEM_MINING_SPEED_BONUS = 1.4;
    private static final double PLANT_EMBLEM_NATURE_HEALING_BONUS = 1;
    private static final double SUN_EMBLEM_PHOTOSYNTHESIS_BONUS = 2;
    private static final double FIRE_EMBLEM_FIRE_FIST_BONUS = 3;
    private static final double CRYSTAL_EMBLEM_FORTUNE_FIST_BONUS = 1;
    private static final double VEGE_EMBLEM_VEGE_BONUS = 1;
    private static final double VEGE_EMBLEM_ANIMAL_TEMPTATION_BONUS = 1;
    private static final double VEGE_EMBLEM_UNDERFED_BONUS = 1;
    private static final double FARMER_EMBLEM_FARMERS_BLESSING_BONUS = 1;
    private static final double BUILDER_EMBLEM_BLOCK_REACH_BONUS = 2;
    private static final double FLOWER_EMBLEM_PHOTOSYNTHESIS_BONUS = 1;
    private static final double BONE_EMBLEM_ARMOR_BONUS = 4;
    private static final double REMAINS_EMBLEM_ARMOR_BONUS = 6;
    private static final double INFERNO_EMBLEM_INFERNO_BONUS = 2;
    private static final double SUPERNOVA_EMBLEM_INFERNO_BONUS = 3;
    private static final double SUPERNOVA_EMBLEM_PHOTOSYNTHESIS_BONUS = 3;
    private static final double SUPERNOVA_EMBLEM_FIRE_FIST_BONUS = 9;
    private static final double ASTEROID_EMBLEM_ASTEROID_BONUS = 1;
    private static final double POOP_EMBLEM_POOP_BONUS = 1;
    private static final double MERCHANT_EMBLEM_MERCHANT_BONUS = 1;
    private static final double BARTERER_EMBLEM_BARTERER_BONUS = 1;
    private static final double TREE_EMBLEM_NATURE_HEALING_BONUS = 1;
    private static final double TREE_EMBLEM_ATTACK_SPEED_BONUS = 0.5;
    private static final double TREE_EMBLEM_PHOTOSYNTHESIS_BONUS = 1;
    private static final double TREE_EMBLEM_MAX_HEALTH_BONUS = 3;
    private static final double CAVE_EMBLEM_MOVEMENT_SPEED_BONUS = 0.01;
    private static final double CAVE_EMBLEM_FORTUNE_FIST_BONUS = 2;
    private static final double CAVE_EMBLEM_ARMOR_BONUS = 5;
    private static final double CAVE_EMBLEM_DAMAGE_BONUS = 0.75;
    private static final double RADIANT_CAVE_EMBLEM_MOVEMENT_SPEED_BONUS = 0.01;
    private static final double RADIANT_CAVE_EMBLEM_FORTUNE_FIST_BONUS = 3;
    private static final double RADIANT_CAVE_EMBLEM_ARMOR_BONUS = 10;
    private static final double RADIANT_CAVE_EMBLEM_DAMAGE_BONUS = 1.5;
    private static final double LIFELESS_CAVE_EMBLEM_MOVEMENT_SPEED_BONUS = 0.01;
    private static final double LIFELESS_CAVE_EMBLEM_FORTUNE_FIST_BONUS = 3;
    private static final double LIFELESS_CAVE_EMBLEM_ARMOR_BONUS = 15;
    private static final double LIFELESS_CAVE_EMBLEM_DAMAGE_BONUS = 1.5;
    private static final double SPADE_EMBLEM_DAMAGE_BONUS = 1;
    private static final double CLOVER_EMBLEM_MOVEMENT_SPEED_BONUS = 0.02;
    private static final double WATER_EMBLEM_RESPIRATION_BONUS = 1;
    private static final double WATER_EMBLEM_SWIM_SPEED_BONUS = 1;
    private static final double WATER_EMBLEM_MINING_SPEED_BONUS = 1;
    private static final double BLIZZARD_EMBLEM_BLIZZARD_BONUS = 3;
    private static final double ICE_EMBLEM_FROST_FIST_BONUS = 2;
    private static final double FROST_EMBLEM_FROST_WALKER_BONUS = 1;
    private static final double SNOWSTORM_EMBLEM_BLIZZARD_BONUS = 4;
    private static final double SNOWSTORM_EMBLEM_FROST_FIST_BONUS = 3;
    private static final double SPARK_EMBLEM_STATIC_BONUS = 2;
    private static final double CONDUCTOR_EMBLEM_CONDUCTOR_BONUS = 7;
    private static final double COILED_EMBLEM_COILED_BONUS = 1;
    private static final double RETURN_EMBLEM_RETURN_BONUS = 1;
    private static final double WARRIOR_EMBLEM_ARMOR_BONUS = 2;
    private static final double ROTTEN_EMBLEM_ROTTEN_BONUS = 1;
    private static final double GOLDEN_EMBLEM_GOLDEN_FAVOR_BONUS = 1;
    private static final double ELECTRO_EMBLEM_ELECTRO_FIST_BONUS = 3;
    private static final double GAMBLER_EMBLEM_GAMBLER_BONUS = 1;
    private static final double SMOKE_EMBLEM_VANISH_BONUS = 1;
    private static final double EASTER_EGGBLEM_EGGING_BONUS = 1;
    private static final double STEAM_EMBLEM_STEAM_BONUS = 3;
    private static final double AURIC_EMBLEM_STEAM_BONUS = 4;
    private static final double AURIC_EMBLEM_COILED_BONUS = 2;
    private static final double AURIC_EMBLEM_BLIZZARD_BONUS = 4;
    private static final double AURIC_EMBLEM_INFERNO_BONUS = 3;
    private static final double INDUCTION_EMBLEM_STATIC_BONUS = 2;
    private static final double INDUCTION_EMBLEM_CONDUCTOR_BONUS = 7;
    private static final double OVERCHARGED_EMBLEM_COILED_BONUS = 1;
    private static final double OVERCHARGED_EMBLEM_ELECTRO_FIST_BONUS = 4;
    private static final double THUNDER_EMBLEM_STATIC_BONUS = 3;
    private static final double THUNDER_EMBLEM_CONDUCTOR_BONUS = 10;
    private static final double THUNDER_EMBLEM_COILED_BONUS = 2;
    private static final double THUNDER_EMBLEM_ELECTRO_FIST_BONUS = 5;
    private static final double THUNDER_EMBLEM_THUNDERCLAP_BONUS = 1;
    private static final double MOON_EMBLEM_MOON_FEET_BONUS = 1;
    private static final double BUBBLE_EMBLEM_BUBBLE_FIST_BONUS = 3;
    private static final double HOURGLASS_EMBLEM_TIME_CONTROL_BONUS = 1;
    private static final double MIRROR_EMBLEM_MIRRORING_BONUS = 1;
    private static final double MIRROR_EMBLEM_DAMAGE_REFLECTION_BONUS = 0.6;
    private static final double TANK_EMBLEM_MAX_HEALTH_BONUS = 6;
    private static final double TANK_EMBLEM_ARMOR_BONUS = 6;
    private static final double RUTHLESS_GUARDIAN_EMBLEM_MAX_HEALTH_BONUS = 14;
    private static final double RUTHLESS_GUARDIAN_EMBLEM_ARMOR_BONUS = 10;
    private static final double ABSORBER_EMBLEM_MAX_HEALTH_BONUS = 20;
    private static final double ABSORBER_EMBLEM_ARMOR_BONUS = 16;
    private static final double THE_TANK_EMBLEM_MAX_HEALTH_BONUS = 36;
    private static final double PYRO_EMBLEM_MAGMA_WALKER_BONUS = 1;
    private static final double SOUL_EMBLEM_SOUL_CONNECTOR_BONUS = 1;
    private static final double FULL_MOON_EMBLEM_MOON_WRATH_BONUS = 2;
    private static final double MOON_WRATH_EMBLEM_MOON_WRATH_BONUS = 1;
    private static final double BLOOD_MOON_EMBLEM_MOON_WRATH_BONUS = 2;
    private static final double BLOODSTAR_EMBLEM_MOON_WRATH_BONUS = 3;
    private static final double ORCHID_EMBLEM_PHOTOSYNTHESIS_BONUS = 2;
    private static final double ORCHID_EMBLEM_FARMERS_BLESSING_BONUS = 2;
    private static final double CACTUS_EMBLEM_THORNS_BONUS = 1;
    private static final double BAMBOO_EMBLEM_ENTITY_REACH_BONUS = 1;
    private static final double IVY_EMBLEM_SNEAKY_DEFENCE_BONUS = 1;
    private static final double ROSE_EMBLEM_BLEEDING_FIST_BONUS = 1;
    private static final double IBARA_EMBLEM_BLEEDING_FIST_BONUS = 1;
    private static final double IBARA_EMBLEM_THORNS_BONUS = 1;
    private static final double BLACK_ROSE_EMBLEM_WITHER_FIST_BONUS = 1;
    private static final double DRIED_ROSE_EMBLEM_WITHERER_BONUS = 1;
    private static final double WITHER_ROSE_EMBLEM_WITHER_FIST_BONUS = 1;
    private static final double WITHER_ROSE_EMBLEM_WITHERER_BONUS = 1;
    private static final double BOUQUET_EMBLEM_BLEEDING_FIST_BONUS = 2;
    private static final double BOUQUET_EMBLEM_FARMERS_BLESSING_BONUS = 2;
    private static final double BOUQUET_EMBLEM_PHOTOSYNTHESIS_BONUS = 3;
    private static final double VINE_EMBLEM_SNEAKY_DEFENCE_BONUS = 2;
    private static final double VINE_EMBLEM_ENTITY_REACH_BONUS = 2;
    private static final double RADIANT_CAVE_EMBLEM_SNEAKY_DEFENCE_BONUS = 3;
    private static final double RADIANT_CAVE_EMBLEM_ENTITY_REACH_BONUS = 3;
    private static final double PRICKLE_VINE_EMBLEM_ENTITY_REACH_BONUS = 3;
    private static final double PRICKLE_VINE_EMBLEM_SNEAKY_DEFENCE_BONUS = 2;
    private static final double PRICKLE_VINE_EMBLEM_THORNS_BONUS = 2;
    private static final double PRICKLE_VINE_EMBLEM_BLEEDING_FIST_BONUS = 2;
    private static final double FLOWERFIELD_EMBLEM_BLEEDING_FIST_BONUS = 2;
    private static final double FLOWERFIELD_EMBLEM_THORNS_BONUS = 2;
    private static final double FLOWERFIELD_EMBLEM_SNEAKY_DEFENCE_BONUS = 3;
    private static final double FLOWERFIELD_EMBLEM_PHOTOSYNTHESIS_BONUS = 3;
    private static final double FLOWERFIELD_EMBLEM_ENTITY_REACH_BONUS = 4;
    private static final double FLOWERFIELD_EMBLEM_FARMERS_BLESSING_BONUS = 3;
    private static final double RAINFOREST_EMBLEM_NATURE_HEALING_BONUS = 3;
    private static final double RAINFOREST_EMBLEM_ATTACK_SPEED_BONUS = 1;
    private static final double RAINFOREST_EMBLEM_PHOTOSYNTHESIS_BONUS = 3;
    private static final double RAINFOREST_EMBLEM_MAX_HEALTH_BONUS = 6;
    private static final double DEADFOREST_EMBLEM_NATURE_HEALING_BONUS = 3;
    private static final double DEADFOREST_EMBLEM_ATTACK_SPEED_BONUS = 1;
    private static final double DEADFOREST_EMBLEM_PHOTOSYNTHESIS_BONUS = 3;
    private static final double DEADFOREST_EMBLEM_MAX_HEALTH_BONUS = 6;
    private static final double DEADFOREST_EMBLEM_WITHER_FIST_BONUS = 1;
    private static final double DEADFOREST_EMBLEM_WITHERER_BONUS = 1;
    private static final double FLOWERFOREST_EMBLEM_NATURE_HEALING_BONUS = 4;
    private static final double FLOWERFOREST_EMBLEM_ATTACK_SPEED_BONUS = 1.5;
    private static final double FLOWERFOREST_EMBLEM_PHOTOSYNTHESIS_BONUS = 4;
    private static final double FLOWERFOREST_EMBLEM_MAX_HEALTH_BONUS = 10;
    private static final double FLOWERFOREST_EMBLEM_WITHER_FIST_BONUS = 2;
    private static final double FLOWERFOREST_EMBLEM_WITHERER_BONUS = 2;
    private static final double FLOWERFOREST_EMBLEM_BLEEDING_FIST_BONUS = 2;
    private static final double FLOWERFOREST_EMBLEM_THORNS_BONUS = 2;
    private static final double FLOWERFOREST_EMBLEM_SNEAKY_DEFENCE_BONUS = 4;
    private static final double FLOWERFOREST_EMBLEM_ENTITY_REACH_BONUS = 5;
    private static final double FLOWERFOREST_EMBLEM_FARMERS_BLESSING_BONUS = 4;
    private static final double DANDELION_EMBLEM_DANDELION_FALL_BONUS = 1;
    private static final double BLOODSTAR_EMBLEM_MOVEMENT_SPEED_BONUS = 0.05;
    private static final double BLOODSTAR_EMBLEM_ATTACK_DAMAGE_BONUS = 0.65;
    private static final double MIST_EMBLEM_MIST_BONUS = 3;
    private static final double TIDE_EMBLEM_TIDAL_FIST_BONUS = 1;
    private static final double RAIN_EMBLEM_MOVEMENT_SPEED_BONUS = 0.03;
    private static final double SPRINKLER_EMBLEM_SPRINKLERS_BONUS = 5;
    private static final double TEAR_EMBLEM_SCHADENFREUDE_BONUS = 1;
    private static final double PUDDLE_EMBLEM_PUFF_BONUS = 2;
    private static final double MUD_EMBLEM_MUDDY_FIST_BONUS = 1;
    private static final double CLAY_EMBLEM_MUDDY_FIST_BONUS = 1;
    private static final double CLAY_EMBLEM_ATTACK_DAMAGE_BONUS = 0.5;
    private static final double MARSH_EMBLEM_MUDDY_FIST_BONUS = 2;
    private static final double MARSH_EMBLEM_STEAM_BONUS = 4;
    private static final double BOG_EMBLEM_MIST_BONUS = 3;
    private static final double BOG_EMBLEM_PUFF_BONUS = 3;
    private static final double WAVE_EMBLEM_MOVEMENT_SPEED_BONUS = 0.03;
    private static final double WAVE_EMBLEM_RESPIRATION_BONUS = 1;
    private static final double WAVE_EMBLEM_SWIM_SPEED_BONUS = 1.2;
    private static final double WAVE_EMBLEM_MINING_SPEED_BONUS = 1;
    private static final double WHIRL_EMBLEM_TIDAL_FIST_BONUS = 3;
    private static final double WHIRL_EMBLEM_BUBBLE_FIST_BONUS = 3;
    private static final double VORTEX_EMBLEM_TIDAL_FIST_BONUS = 3;
    private static final double VORTEX_EMBLEM_BUBBLE_FIST_BONUS = 3;
    private static final double VORTEX_EMBLEM_SCHADENFREUDE_BONUS = 1;
    private static final double VORTEX_EMBLEM_MOON_FAVOR_BONUS = 0.04;
    private static final double VORTEX_EMBLEM_DAMAGE_BONUS = 1;
    private static final double SWAMP_EMBLEM_MUDDY_FIST_BONUS = 4;
    private static final double SWAMP_EMBLEM_STEAM_BONUS = 4;
    private static final double SWAMP_EMBLEM_MIST_BONUS = 4;
    private static final double SWAMP_EMBLEM_PUFF_BONUS = 4;
    private static final double CURRENT_EMBLEM_MOVEMENT_SPEED_BONUS = 0.035;
    private static final double CURRENT_EMBLEM_RESPIRATION_BONUS = 1;
    private static final double CURRENT_EMBLEM_SWIM_SPEED_BONUS = 1.3;
    private static final double CURRENT_EMBLEM_MINING_SPEED_BONUS = 1.2;
    private static final double CURRENT_EMBLEM_SPRINKLERS_BONUS = 5;
    private static final double MUCKMIRE_EMBLEM_MUDDY_FIST_BONUS = 4;
    private static final double MUCKMIRE_EMBLEM_STEAM_BONUS = 5;
    private static final double MUCKMIRE_EMBLEM_MIST_BONUS = 5;
    private static final double MUCKMIRE_EMBLEM_PUFF_BONUS = 5;
    private static final double MUCKMIRE_EMBLEM_MOVEMENT_SPEED_BONUS = 0.035;
    private static final double MUCKMIRE_EMBLEM_RESPIRATION_BONUS = 2;
    private static final double MUCKMIRE_EMBLEM_SWIM_SPEED_BONUS = 1.4;
    private static final double MUCKMIRE_EMBLEM_MINING_SPEED_BONUS = 1.5;
    private static final double MUCKMIRE_EMBLEM_SPRINKLERS_BONUS = 7;
    private static final double MAELSTROM_EMBLEM_TIDAL_FIST_BONUS = 5;
    private static final double MAELSTROM_EMBLEM_BUBBLE_FIST_BONUS = 5;
    private static final double MAELSTROM_EMBLEM_SCHADENFREUDE_BONUS = 1;
    private static final double MAELSTROM_EMBLEM_MOON_FAVOR_BONUS = 0.07;
    private static final double MAELSTROM_EMBLEM_DAMAGE_BONUS = 2;
    private static final double FORBIDDEN_OASIS_EMBLEM_MUDDY_FIST_BONUS = 5;
    private static final double FORBIDDEN_OASIS_EMBLEM_STEAM_BONUS = 7;
    private static final double FORBIDDEN_OASIS_EMBLEM_MIST_BONUS = 7;
    private static final double FORBIDDEN_OASIS_EMBLEM_PUFF_BONUS = 10;
    private static final double FORBIDDEN_OASIS_EMBLEM_MOVEMENT_SPEED_BONUS = 0.04;
    private static final double FORBIDDEN_OASIS_EMBLEM_RESPIRATION_BONUS = 3;
    private static final double FORBIDDEN_OASIS_EMBLEM_SWIM_SPEED_BONUS = 1.6;
    private static final double FORBIDDEN_OASIS_EMBLEM_MINING_SPEED_BONUS = 2;
    private static final double FORBIDDEN_OASIS_EMBLEM_SPRINKLERS_BONUS = 10;
    private static final double FORBIDDEN_OASIS_EMBLEM_TIDAL_FIST_BONUS = 10;
    private static final double FORBIDDEN_OASIS_EMBLEM_BUBBLE_FIST_BONUS = 10;
    private static final double FORBIDDEN_OASIS_EMBLEM_SCHADENFREUDE_BONUS = 2;
    private static final double FORBIDDEN_OASIS_EMBLEM_MOON_FAVOR_BONUS = 0.09;
    private static final double FORBIDDEN_OASIS_EMBLEM_DAMAGE_BONUS = 2;
    private static final double FLORIST_EMBLEM_FLORIST_BONUS = 1;
    private static final double KNIGHT_EMBLEM_ATTACK_SPEED_BONUS = 0.5;
    private static final double GLADIATOR_EMBLEM_ATTACK_SPEED_BONUS = 1;
    private static final double GLADIATOR_EMBLEM_ATTACK_DAMAGE_BONUS = 0.5;
    private static final double ASSASSIN_EMBLEM_ATTACK_DAMAGE_BONUS = 2;
    private static final double ASSASSIN_EMBLEM_ATTACK_SPEED_BONUS = 2;
    private static final double BRANCH_EMBLEM_NATURE_HEALING_BONUS = 1;
    private static final double BRANCH_EMBLEM_MAX_HEALTH_BONUS = 3;
    private static final double BUTCHER_EMBLEM_BUTCHER_BLESSING_BONUS = 1;
    private static final double SKIN_TRANSPLANT_EMBLEM_SKIN_TRANSPLANTER_BONUS = 1;
    private static final double ARCHER_EMBLEM_ARROW_RETRIEVE_BONUS = 0.15;
    private static final double SMELTER_EMBLEM_SMELTING_BONUS = 1;
    private static final double BURNER_EMBLEM_BLOCK_BURNING_BONUS = 1;
    private static final double SMOKING_EMBLEM_SMOKING_BONUS = 1;
    private static final double BOULDER_EMBLEM_DAMAGE_BONUS = 1.5;
    private static final double DOWNHILL_EMBLEM_SPEED_BONUS = 0.002;
    private static final double DUNE_EMBLEM_SPEED_BONUS = 0.005;
    private static final double HILL_EMBLEM_SPEED_BONUS = 0.003;
    private static final double HILL_EMBLEM_ARMOR_BONUS = 1;
    private static final double MOUNTAIN_EMBLEM_SPEED_BONUS = 0.004;
    private static final double MOUNTAIN_EMBLEM_ARMOR_BONUS = 1;
    private static final double MOUNTAIN_RANGE_EMBLEM_SPEED_FER_HUNGER_BONUS = 0.005;
    private static final double MOUNTAIN_RANGE_EMBLEM_SPEED_BONUS = 0.04;
    private static final double MOUNTAIN_RANGE_EMBLEM_ARMOR_BONUS = 1;
    private static final double MOUNTAIN_CAVE_EMBLEM_SPEED_BONUS = 0.003;
    private static final double MOUNTAIN_CAVE_EMBLEM_ARMOR_BONUS = 1;
    private static final double RADIANT_CAVE_EMBLEM_SPEED_BONUS = 0.003;
    private static final double RADIANT_CAVE_EMBLEM_ON_HEIGHT_ARMOR_BONUS = 1;
    private static final double SISYPHUS_EMBLEM_DAMAGE_BONUS = 0.05;
    private static final double SISYPHUS_EMBLEM_MAX_HEALTH_BONUS = -1;
    private static final double IRON_EMBLEM_ATTACK_SPEED_BONUS = 1.0;
    private static final double UNTUNED_EMBLEM_UNTUNED_BONUS = 1.0;
    private static final double RHYTHM_EMBLEM_TUNED_BONUS = 1.0;
    private static final double SONG_EMBLEM_SONG_BONUS = 1.0;
    private static final double ANTHEM_EMBLEM_ANTHEM_BONUS = 2.0;
    private static final double INDEPENDENT_EMBLEM_I_ANTHEM_BONUS = 2.0;
    private static final double FALSE_NOTE_EMBLEM_UNTUNED_BONUS = 2.0;
    private static final double LULLABY_EMBLEM_LULLABY_BONUS = 5.0;
    private static final double BEAT_EMBLEM_BEAT_BONUS = 1;
    private static final double PAPER_EMBLEM_PAPER_BONUS = 1;
    private static final double CLOTH_EMBLEM_KNOCKBACK_RESISTANCE_BONUS = 0.1;
    private static final double SOIL_EMBLEM_RICH_SOIL_BONUS = 1;
    private static final double RICH_SOIL_EMBLEM_RICH_SOIL_BONUS = 2;
    private static final double NUTRITIOUS_EMBLEM_NUTRITIOUS_CROPS_BONUS = 1;
    private static final double ITEMIUM_EMBLEM_LUCKY_BONUS = 0.05;
    private static final double FINDERS_EMBLEM_LUCKY_BONUS = 0.15;
    private static final double COIN_EMBLEM_LUCKY_BONUS = 0.25;
    private static final double HAPPY_EMBLEM_LUCKY_BONUS = 0.33;
    private static final double LUCKY_EMBLEM_LUCKY_BONUS = 0.66;
    private static final double PRAYERS_EMBLEM_LUCKY_BONUS = 0.5;
    private static final double LEPRECHAUNS_EMBLEM_LUCKY_BONUS = 0.75;
    private static final double LOTTERY_EMBLEM_LUCKY_BONUS = 0.9;
    private static final double TRAINED_EMBLEM_LUCKY_BONUS = 1.1;
    private static final double EXPERT_EMBLEM_LUCKY_BONUS = 1.4;
    private static final double MASTER_EMBLEM_LUCKY_BONUS = 1.7;
    private static final double CHAMPION_EMBLEM_LUCKY_BONUS = 2.0;
    private static final double WINNER_EMBLEM_LUCKY_BONUS = 2.3;
    private static final double RULE_MASTER_EMBLEM_LUCKY_BONUS = 2.6;
    private static final double FORESEEN_EMBLEM_LUCKY_BONUS = 3.0;
    private static final double THE_TRUTH_EMBLEM_LUCKY_BONUS = 3.7;
    private static final double CREATOR_BLESSED_EMBLEM_LUCKY_BONUS = 4.5;
    private static final double ANVIL_EMBLEM_ANVIL_BONUS = 0.05;
    private static final double BLACKSMITH_EMBLEM_ANVIL_BONUS = 0.15;
    private static final double FORGER_EMBLEM_ANVIL_BONUS = 0.3;
    private static final double GOD_FORGE_EMBLEM_ANVIL_BONUS = 0.55;
    private static final double HEPHAESTUS_EMBLEM_ANVIL_BONUS = 0.85;
    private static final double FARMER_APPRENTICE_EMBLEM_FARMING_BONUS = 1;
    private static final double SNOW_EMBLEM_SNOW_WALK_BONUS = 1;
    private static final double ICICLE_EMBLEM_ICICLE_BONUS = 1;
    private static final double RINK_EMBLEM_RINK_BONUS = 1;
    private static final double YUKI_EMBLEM_YUKI_BONUS = 1;
    private static final double MAGNETITE_EMBLEM_MAGNET_BONUS = 1;
    private static final double ELECTROMAGNET_EMBLEM_MAGNET_BONUS = 2;
    private static final double PULSE_EMBLEM_PULSE_BONUS = 1;
    private static final double LIGHTNING_EMBLEM_LIGHTNING_ASPECT_BONUS = 1;
    private static final double UNDERWORLD_EMBLEM_FORTUNE_FEVER_BONUS = 1;
    private static final double APPLE_EMBLEM_LEAF_LUCK_BONUS = 1;
    private static final double MOONDEW_EMBLEM_FARMERS_BLESSING_BONUS = 3;
    private static final double MOONDEW_EMBLEM_NIGHT_CROPS_BONUS = 1;
    private static final double ECLIPSE_EMBLEM_PHOTOSYNTHESIS_BONUS = 3;
    private static final double ECLIPSE_EMBLEM_LUNARSYNTHESIS_BONUS = 1;
    private static final double LUAU_EMBLEM_LUAU_BONUS = 1;
    private static final double HERMES_EMBLEM_LUNG_CAPACITY_BONUS = 30;
    private static final double VOLCANO_EMBLEM_VOLCANIC_BONUS = 1;

    private static final double ATOMIC_CAVE_EMBLEM_MOVEMENT_SPEED_BONUS = 0.04;
    private static final double ATOMIC_CAVE_EMBLEM_FORTUNE_FIST_BONUS = 5;
    private static final double ATOMIC_CAVE_EMBLEM_ARMOR_BONUS = 25;
    private static final double ATOMIC_CAVE_EMBLEM_DAMAGE_BONUS = 2.5;
    private static final double ATOMIC_CAVE_EMBLEM_SPEED_BONUS = 0.003;
    private static final double ATOMIC_CAVE_EMBLEM_ON_HEIGHT_ARMOR_BONUS = 1;
    private static final double ATOMIC_CAVE_EMBLEM_MINING_SPEED_BONUS = 2;
    private static final double ATOMIC_CAVE_EMBLEM_SPEED_FER_HUNGER_BONUS = 0.005;
    private static final double ATOMIC_CAVE_EMBLEM_SNEAKY_DEFENCE_BONUS = 4;
    private static final double ATOMIC_CAVE_EMBLEM_ENTITY_REACH_BONUS = 4;


    private static final double MOTHER_EARTH_EMBLEM_MOTHER_EARTH_BLESSING_BONUS = 1;








    //Rupec's Elytras
    private static final float GLIDER_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS = 0.2f;
    private static final float FEATHER_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS = 0.1f;
    private static final float WIND_CATCHER_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS = 0.3f;
    private static final float PHOENIX_EMBLEM_PHOENIX_BONUS = 1f;
    private static final float HUMMING_BIRD_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS = 0.5f;
    private static final float MOUNTAIN_WIND_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS = 0.7f;
    private static final float WEIGHTLESS_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS = 1.0f;
    private static final float WING_POWER_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS = 2.0f;












    // Moon phases
    private static final int MOON_CYCLE_DAYS = 8;




    public static boolean IS_FORCED_DAY = false;
    public static boolean IS_FORCED_NIGHT = false;
    public static boolean IS_FORCED_RAIN = false;

    // Main effect methods

    public static void provideConstantEmblemEffect(ModEmblemItem emblemItem, LivingEntity entity) {
        long timeOfDay = entity.level().getDayTime() % 24000;
        boolean isNight = timeOfDay >= 13000 && timeOfDay <= 23000;
        boolean isFullMoon = (entity.level().getDayTime() / 24000) % MOON_CYCLE_DAYS == 0;
        double lightLevel = entity.level().getLightEngine().getRawBrightness(entity.blockPosition(), 1);
        switch (emblemItem.toString()) {
            case "moon_emblem":
                handleMoonEmblemEffects(emblemItem, entity, isNight, isFullMoon, false, "Moon");
                entity.resetFallDistance();
                break;
            case "full_moon_emblem":
                entity.resetFallDistance();
                handleMoonEmblemEffects(emblemItem, entity, isNight, isFullMoon, true, "Full Moon");
                break;
            case "sand_emblem":
                handleSandEmblemEffects(emblemItem, entity, "Sand", "OnSandBonus");
                break;
            case "dune_emblem":
                handleSandEmblemEffects(emblemItem, entity, "Dune","OnSandBonus");
                handleHungerBasedEmblemEffects(emblemItem, entity, "Dune", (float) DUNE_EMBLEM_SPEED_BONUS, 2, Attributes.MOVEMENT_SPEED);
                break;
            case "hill_emblem":
                handleSandEmblemEffects(emblemItem, entity, "Hill", "OnSandBonus");
                handleHungerBasedEmblemEffects(emblemItem, entity, "Hill", (float) HILL_EMBLEM_SPEED_BONUS, 1, Attributes.MOVEMENT_SPEED);
                handleHeightBasedEmblemEffects(emblemItem, entity, "Hill", (float) HILL_EMBLEM_ARMOR_BONUS, 20, Attributes.ARMOR, false);

                break;
            case "mountain_emblem":
                handleSandEmblemEffects(emblemItem, entity, "Mountain", "OnSandBonus");
                handleHungerBasedEmblemEffects(emblemItem, entity, "Mountain", (float) MOUNTAIN_EMBLEM_SPEED_BONUS, 1, Attributes.MOVEMENT_SPEED);
                handleHeightBasedEmblemEffects(emblemItem, entity, "Mountain", (float) MOUNTAIN_EMBLEM_ARMOR_BONUS, 10, Attributes.ARMOR, false);

                break;
            case "mountain_range_emblem":
                handleConditionalEmblemEffects(emblemItem, entity, "Mountain Range", "OnSandBonus", Attributes.MOVEMENT_SPEED, MOUNTAIN_RANGE_EMBLEM_SPEED_BONUS, MOUNTAIN_RANGE_EMBLEM_SPEED_BONUS * 2,
                        isOnAny(entity, BlockTags.SAND, BlockTags.SNOW));
                handleHungerBasedEmblemEffects(emblemItem, entity, "Mountain Range", (float) MOUNTAIN_RANGE_EMBLEM_SPEED_FER_HUNGER_BONUS, 1, Attributes.MOVEMENT_SPEED);
                handleHeightBasedEmblemEffects(emblemItem, entity, "Mountain Range", (float) MOUNTAIN_RANGE_EMBLEM_ARMOR_BONUS, 5, Attributes.ARMOR, false);

                break;
            case "mountain_cave_emblem":
                handleSandEmblemEffects(emblemItem, entity, "Mountain Cave","OnSandBonus");
                handleHungerBasedEmblemEffects(emblemItem, entity, "Mountain Cave", (float) MOUNTAIN_CAVE_EMBLEM_SPEED_BONUS, 1, Attributes.MOVEMENT_SPEED);
                handleHeightBasedEmblemEffects(emblemItem, entity, "Mountain Cave", (float) MOUNTAIN_CAVE_EMBLEM_ARMOR_BONUS, 10, Attributes.ARMOR, true, 2);

                break;
            case "radiant_cave_emblem":
                handleSandEmblemEffects(emblemItem, entity, "Radiant Cave","OnSandBonus");
                handleHungerBasedEmblemEffects(emblemItem, entity, "Radiant Cave", (float) RADIANT_CAVE_EMBLEM_SPEED_BONUS, 1, Attributes.MOVEMENT_SPEED);
                handleHeightBasedEmblemEffects(emblemItem, entity, "Radiant Cave", (float) RADIANT_CAVE_EMBLEM_ON_HEIGHT_ARMOR_BONUS, 10, Attributes.ARMOR, true, 2);

                break;
            case "atomic_cave_emblem":
                handleSandEmblemEffects(emblemItem, entity, "Atomic Cave","OnSandBonus");
                handleHungerBasedEmblemEffects(emblemItem, entity, "Atomic Cave", (float) ATOMIC_CAVE_EMBLEM_SPEED_BONUS, 1, Attributes.MOVEMENT_SPEED);
                handleHeightBasedEmblemEffects(emblemItem, entity, "Atomic Cave", (float) ATOMIC_CAVE_EMBLEM_ON_HEIGHT_ARMOR_BONUS, 3, Attributes.ARMOR, true, 1);
                handleConditionalEmblemEffects(emblemItem, entity, "Atomic Range", "OnConditionalBonus", Attributes.MOVEMENT_SPEED, ATOMIC_CAVE_EMBLEM_SPEED_BONUS, ATOMIC_CAVE_EMBLEM_SPEED_BONUS * 2,
                        isOnAny(entity, BlockTags.SAND, BlockTags.SNOW));
                handleHungerBasedEmblemEffects(emblemItem, entity, "Atomic Range", (float) ATOMIC_CAVE_EMBLEM_SPEED_FER_HUNGER_BONUS, 1, Attributes.MOVEMENT_SPEED);
                handleStalactiteEmblemEffects(emblemItem, entity, "Atomic Cave");
                handleStalagmiteEmblemEffects(emblemItem, entity, "Atomic Cave");
                handleRemainsEmblemEffects(emblemItem, entity, "Atomic Cave");
                break;
            case "stalagmite_emblem":
                handleStalagmiteEmblemEffects(emblemItem, entity, "Stalagmite");
                break;
            case "stalactite_emblem":
                handleStalactiteEmblemEffects(emblemItem, entity, "Stalactite");
                break;
            case "stalagnate_emblem":
                handleStalactiteEmblemEffects(emblemItem, entity, "Stalagnate");
                handleStalagmiteEmblemEffects(emblemItem, entity, "Stalagnate");
                break;
            case "stalactite_cave_emblem":
                handleStalactiteEmblemEffects(emblemItem, entity, "Stalactite Cave");
                handleStalagmiteEmblemEffects(emblemItem, entity, "Stalactite Cave");
                break;
            case "lifeless_cave_emblem":
                handleStalactiteEmblemEffects(emblemItem, entity, "Lifeless Cave");
                handleStalagmiteEmblemEffects(emblemItem, entity, "Lifeless Cave");
                handleRemainsEmblemEffects(emblemItem, entity, "Lifeless Cave");
                break;
            case "sisyphus_emblem":
                handleHeightBasedEmblemEffects(emblemItem, entity, "Sisyphus", (float) SISYPHUS_EMBLEM_DAMAGE_BONUS, 5, Attributes.ATTACK_DAMAGE,true, (float) (SISYPHUS_EMBLEM_DAMAGE_BONUS * -1));
                handleHeightBasedEmblemEffects(emblemItem, entity, "Sisyphus", (float) SISYPHUS_EMBLEM_MAX_HEALTH_BONUS, 20, Attributes.MAX_HEALTH,true, (float) (SISYPHUS_EMBLEM_MAX_HEALTH_BONUS * -1));

                break;
            case "fossil_emblem":
                handleSandEmblemEffects(emblemItem, entity, "Fossil", "OnSandBonus");
                break;
            case "remains_emblem":
                handleRemainsEmblemEffects(emblemItem, entity, "Remains");
                break;
            case "desert_cave_emblem":
                handleRemainsEmblemEffects(emblemItem, entity, "Desert Cave");
                break;
            case "downhill_emblem":
                handleHungerBasedEmblemEffects(emblemItem, entity, "Downhill", (float) DOWNHILL_EMBLEM_SPEED_BONUS, 1, Attributes.MOVEMENT_SPEED);
                break;
            case "heart_emblem":
                handleHeartEmblemEffects(emblemItem, entity, "Heart");
                break;
            case "night_emblem":
                handleNightEmblemEffects(emblemItem, entity, isNight, isFullMoon, "Night");
                break;
            case "sun_emblem":
                handleSunEmblemEffects(emblemItem, entity, isNight, false, "Sun");
                break;
            case "supernova_emblem":
                handleSunEmblemEffects(emblemItem, entity, isNight, true, "Supernova");
                if (!entity.hasEffect(MobEffects.FIRE_RESISTANCE)){
                    entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, MobEffectInstance.INFINITE_DURATION, 0, false,false,false));
                }
                break;
            case "fluorescent_emblem":
                entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, MobEffectInstance.INFINITE_DURATION, 0, false,false,false));
                break;
            case "dust_emblem":
                handleDustEmblemEffects(emblemItem, entity, "Dust");
                break;
            case "comet_emblem":
                handleCometEmblemEffects(emblemItem, entity, 3);
                break;
            case "blood_moon_emblem":
                handleCometEmblemEffects(emblemItem, entity, 5);
                entity.resetFallDistance();
                handleMoonEmblemEffects(emblemItem, entity, isNight, true, true, "Blood Moon");
                break;
            case "bloodstar_emblem":
                handleCometEmblemEffects(emblemItem, entity, 9);
                entity.resetFallDistance();
                if (!entity.hasEffect(MobEffects.FIRE_RESISTANCE)){
                    entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, MobEffectInstance.INFINITE_DURATION, 0, false,false,false));
                }
                break;

            case "magma_emblem":
                if (!entity.hasEffect(MobEffects.FIRE_RESISTANCE)){
                    entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, MobEffectInstance.INFINITE_DURATION, 0, false,false,false));
                }
                break;

            case "petrified_dandelion_emblem":
                if (!entity.hasEffect(MobEffects.SLOW_FALLING)){
                    entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, MobEffectInstance.INFINITE_DURATION, 0, false,false,false));
                }
                break;

            case "petrified_vege_emblem":
                if (!entity.hasEffect(MobEffects.SATURATION)){
                    entity.addEffect(new MobEffectInstance(MobEffects.SATURATION, MobEffectInstance.INFINITE_DURATION, 0, false,false,false));
                }
                break;

            case "diamonds_emblem":
                if (!entity.hasEffect(MobEffects.JUMP)){
                    entity.addEffect(new MobEffectInstance(MobEffects.JUMP, MobEffectInstance.INFINITE_DURATION, 1, false,false,false));
                }
                break;
            case "water_emblem":
                handleWaterEmblemEffects(emblemItem, entity, "Water", WATER_EMBLEM_MINING_SPEED_BONUS);
                break;
            case "day_emblem":
                handleDayEmblemEffects(emblemItem, entity, isNight, "Day");
                break;
            case "heaven_emblem":
                handleDayEmblemEffects(emblemItem, entity, isNight, "Heaven");
                handleNightEmblemEffects(emblemItem, entity, isNight, isFullMoon, "Heaven");
                break;
            case "sunflower_emblem", "mother_earth_emblem":
                if (!IS_FORCED_DAY) {
                    IS_FORCED_DAY = true;
                }
                break;
            case "nightbloom_emblem":
                if (!IS_FORCED_NIGHT) {
                    IS_FORCED_NIGHT = true;

                }
                break;
            case "flowerforest_emblem":
                if (!IS_FORCED_NIGHT) {
                    IS_FORCED_NIGHT = true;
                }
                if (!IS_FORCED_DAY) {
                    IS_FORCED_DAY = true;
                }
                break;
            case "red_spider_lily_emblem":
                handleSpiderLillyEmblemEffects(emblemItem, entity, isNight, true, "Red Spider Lily");
                break;
            case "blue_spider_lily_emblem":
                handleSpiderLillyEmblemEffects(emblemItem, entity, isNight, false, "Blue Spider Lily");
                break;
            case "rain_emblem":
                handleRainEmblemEffects(emblemItem, entity, "Rain", RAIN_EMBLEM_MOVEMENT_SPEED_BONUS);
                break;
            case "sprinkler_emblem":
                if (!IS_FORCED_RAIN) {
                    IS_FORCED_RAIN = true;

                }
                break;
            case "wave_emblem":
                handleWaterEmblemEffects(emblemItem, entity, "Wave", WAVE_EMBLEM_MINING_SPEED_BONUS);
                handleRainEmblemEffects(emblemItem, entity, "Wave", WAVE_EMBLEM_MOVEMENT_SPEED_BONUS);
                break;
            case "vortex_emblem":
                handleMoonEmblemEffects(emblemItem, entity, isNight, isFullMoon, false, "Vortex");
                break;
            case "maelstrom_emblem":
                handleMoonEmblemEffects(emblemItem, entity, isNight, isFullMoon, true, "Maelstrom");
                break;
            case "current_emblem":
                handleWaterEmblemEffects(emblemItem, entity, "Current", CURRENT_EMBLEM_MINING_SPEED_BONUS);
                break;
            case "muckmire_emblem":
                handleWaterEmblemEffects(emblemItem, entity, "Muckmire", MUCKMIRE_EMBLEM_MINING_SPEED_BONUS);
                break;
            case "forbidden_oasis_emblem":
                handleWaterEmblemEffects(emblemItem, entity, "Forbidden Oasis", FORBIDDEN_OASIS_EMBLEM_MINING_SPEED_BONUS);
                handleMoonEmblemEffects(emblemItem, entity, isNight, isFullMoon, true, "Forbidden Oasis");
                if (!IS_FORCED_RAIN) {
                    IS_FORCED_RAIN = true;

                }
                break;
            case "note_emblem":
                spawnHealingNote(entity.tickCount, entity, 1, 60, 1);
                break;
            case "chord_emblem":
                spawnHealingNote(entity.tickCount, entity, 3, 100, 1);
                break;
            case "staff_emblem":
                spawnDamagingNote(entity.tickCount, entity, 1, 30, 2);
                break;
            case "glider_emblem":
                if (!ModList.get().isLoaded("rupecs_elytras") || !(entity instanceof Player player)) break;
                player.getCapability(FlightTimeDataProvider.PLAYER_FLIGHT_TIME_DATA).ifPresent(data ->{
                    if (data.canDisplay()) {
                        data.getFlightTimeEntry().addConstantMaxFlightTimeMultiplier(GLIDER_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS, emblemItem.getUniqueID());
                    }
                });
                break;
            case "feather_emblem":
                if (!ModList.get().isLoaded("rupecs_elytras") || !(entity instanceof Player player)) break;
                player.getCapability(FlightTimeDataProvider.PLAYER_FLIGHT_TIME_DATA).ifPresent(data -> {

                    if (data.canDisplay()) {
                        data.getFlightTimeEntry().addConstantMaxFlightTimeMultiplier(FEATHER_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS, emblemItem.getUniqueID());
                    }
                });
                break;
            case "wind_catcher_emblem":
                if (!ModList.get().isLoaded("rupecs_elytras") || !(entity instanceof Player player)) break;
                player.getCapability(FlightTimeDataProvider.PLAYER_FLIGHT_TIME_DATA).ifPresent(data ->{

                    if (data.canDisplay()) {
                    data.getFlightTimeEntry().addConstantMaxFlightTimeMultiplier(WIND_CATCHER_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS, emblemItem.getUniqueID());
                }
                });

                break;
            case "humming_bird_emblem":
                if (!ModList.get().isLoaded("rupecs_elytras") || !(entity instanceof Player player)) break;
                player.getCapability(FlightTimeDataProvider.PLAYER_FLIGHT_TIME_DATA).ifPresent(data ->{

                    if (data.canDisplay()) {
                    data.getFlightTimeEntry().addConstantMaxFlightTimeMultiplier(HUMMING_BIRD_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS, emblemItem.getUniqueID());
                }
                });

                break;
            case "mountain_wind_emblem":
                if (!ModList.get().isLoaded("rupecs_elytras") || !(entity instanceof Player player)) break;
                player.getCapability(FlightTimeDataProvider.PLAYER_FLIGHT_TIME_DATA).ifPresent(data ->{

                    if (data.canDisplay()) {
                    data.getFlightTimeEntry().addConstantMaxFlightTimeMultiplier(MOUNTAIN_WIND_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS, emblemItem.getUniqueID());
                }
                });

                break;
            case "weightless_emblem":
                if (!ModList.get().isLoaded("rupecs_elytras") || !(entity instanceof Player player)) break;
                player.getCapability(FlightTimeDataProvider.PLAYER_FLIGHT_TIME_DATA).ifPresent(data ->{

                    if (data.canDisplay()) {
                    data.getFlightTimeEntry().addConstantMaxFlightTimeMultiplier(WEIGHTLESS_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS, emblemItem.getUniqueID());
                }
                });

                break;
            case "wing_power_emblem":
                if (!ModList.get().isLoaded("rupecs_elytras") || !(entity instanceof Player player)) break;
                player.getCapability(FlightTimeDataProvider.PLAYER_FLIGHT_TIME_DATA).ifPresent(data ->{

                    if (data.canDisplay()) {
                    data.getFlightTimeEntry().addConstantMaxFlightTimeMultiplier(WING_POWER_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS, emblemItem.getUniqueID());
                }
                });

                break;

            case "sky_wanderer_emblem":
                if (!ModList.get().isLoaded("rupecs_elytras") || !(entity instanceof Player player)) break;
                player.getCapability(FlightTimeDataProvider.PLAYER_FLIGHT_TIME_DATA).ifPresent(data ->{

                    if (data.canDisplay()) {
                    data.getFlightTimeEntry().setMaxFlightTime(-2);
                    if (data.getFlightTimeEntry().getFlightTime() > 0) {
                        data.getFlightTimeEntry().setFlightTime(-3);
                    }
                }
                });

                break;


        }
    }

    private static void spawnHealingNote(int tickCount, LivingEntity entity, int amount, int tickDelay, int heal) {
        if (tickCount %tickDelay == 0) {
            for (int i = 0; i < amount; i++) {
                HealingNoteProjectile entityToAdd = new HealingNoteProjectile(ModEntities.HEALING_NOTE.get(), entity.level(), heal, true);
                entityToAdd.setPos(entity.position());
                RandomSource random = entity.getRandom();
                Vec3 randomDir = new Vec3(
                        (random.nextDouble() - 0.5) * 0.3,  // X: random between -0.15 and +0.15
                        0.2 + random.nextDouble() * 0.2,    // Y: random upward between 0.2–0.4
                        (random.nextDouble() - 0.5) * 0.3   // Z: random between -0.15 and +0.15
                );
                entityToAdd.addDeltaMovement(randomDir);
                entityToAdd.setOwner(entity);
                entity.level().addFreshEntity(entityToAdd);
            }
        }
    }
    private static void spawnDamagingNote(int tickCount, LivingEntity entity, int amount, int tickDelay, int damage) {
        if (tickCount %tickDelay == 0) {
            for (int i = 0; i < amount; i++) {
                DamagingNoteProjectile entityToAdd = new DamagingNoteProjectile(ModEntities.DAMAGING_NOTE.get(), entity.level(), damage, false, true);
                entityToAdd.setPos(entity.position().add(0,1,0));
                RandomSource random = entity.getRandom();
                Vec3 randomDir = new Vec3(
                        (random.nextDouble()-0.5) * 0.04,  // X: random between -0.15 and +0.15
                        0,
                        (random.nextDouble()-0.5) * 0.04   // Z: random between -0.15 and +0.15
                );
                entityToAdd.addDeltaMovement(randomDir);

                entityToAdd.setOwner(entity);
                entity.level().addFreshEntity(entityToAdd);
            }
        }
    }
    public static void provideConstantAscendedEmblemEffect(ModEmblemItem emblemItem, LivingEntity entity) {
        long timeOfDay = entity.level().getDayTime() % 24000;
        boolean isNight = timeOfDay >= 13000 && timeOfDay <= 23000;
        boolean isFullMoon = (entity.level().getDayTime() / 24000) % MOON_CYCLE_DAYS == 0;
        double lightLevel = entity.level().getLightEngine().getRawBrightness(entity.blockPosition(), 1);
        switch (emblemItem.toString()) {
            case "comet_emblem":
                handleCometEmblemEffects(emblemItem, entity, 8, 20);
        }
    }
    public static void provideOnEquipAscendedEmblemEffect(ModEmblemItem emblemItem, LivingEntity entity) {
        switch (emblemItem.toString()) {
            case "wood_emblem":

                applyStaticModifier(entity, Attributes.MAX_HEALTH, emblemItem.getUniqueID(),
                        "Wood Emblem Bonus", WOOD_EMBLEM_HP_BONUS * 5, AttributeModifier.Operation.ADDITION);

                break;

            case "plant_emblem":
                applyStaticModifier(entity, ModAttributes.NATURE_HEALING.get(), emblemItem.getUniqueID(),
                        "Plant Emblem Bonus", PLANT_EMBLEM_NATURE_HEALING_BONUS*6, AttributeModifier.Operation.ADDITION);
                break;

            case "apple_emblem":
                applyStaticModifier(entity, ModAttributes.LEAF_LUCK.get(), emblemItem.getUniqueID(),
                        "Apple Emblem Bonus", APPLE_EMBLEM_LEAF_LUCK_BONUS * 5, AttributeModifier.Operation.ADDITION);
                break;

            case "inferno_emblem":
                applyStaticModifier(entity, ModAttributes.INFERNO.get(), emblemItem.getUniqueID(),
                        "Inferno Emblem Bonus", INFERNO_EMBLEM_INFERNO_BONUS+0.2, AttributeModifier.Operation.ADDITION);
                break;

            case "orchid_emblem":
                applyStaticModifier(entity, ModAttributes.PHOTOSYNTHESIS.get(), emblemItem.getUniqueID(),
                        "Orchid Emblem Bonus", ORCHID_EMBLEM_PHOTOSYNTHESIS_BONUS * 2, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.FARMERS_BLESSING.get(), emblemItem.getUniqueID(),
                        "Orchid Emblem Bonus", ORCHID_EMBLEM_FARMERS_BLESSING_BONUS * 3, AttributeModifier.Operation.ADDITION);
                break;

            case "hermes_emblem":
                applyStaticModifier(entity, ModAttributes.LUNG_CAPACITY.get(), emblemItem.getUniqueID(),
                        "Hermes Emblem Bonus", HERMES_EMBLEM_LUNG_CAPACITY_BONUS *7, AttributeModifier.Operation.ADDITION);
                break;
            case "farmer_emblem":
                applyStaticModifier(entity, ModAttributes.FARMERS_BLESSING.get(), emblemItem.getUniqueID(),
                        "Farmer Emblem Bonus", FARMER_EMBLEM_FARMERS_BLESSING_BONUS*3, AttributeModifier.Operation.ADDITION);
                break;
        }

    }
    public static void provideOnEquipEmblemEffect(ModEmblemItem emblemItem, LivingEntity entity) {

        switch (emblemItem.toString()) {
            case "moon_emblem":
                handleMoonEmblemEquip(emblemItem, entity, false, "Moon");
                break;
            case "full_moon_emblem":
                handleMoonEmblemEquip(emblemItem, entity, true, "Full Moon");
                applyStaticModifier(entity, ModAttributes.MOON_WRATH.get(), emblemItem.getUniqueID(),
                        "Full Moon Emblem Bonus", FULL_MOON_EMBLEM_MOON_WRATH_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "blood_moon_emblem":
                applyStaticModifier(entity, ModAttributes.MOON_JUMP.get(), emblemItem.getUniqueID(),
                        "Blood Moon Emblem Bonus", ASTEROID_EMBLEM_ASTEROID_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.MOON_WRATH.get(), emblemItem.getUniqueID(),
                        "Blood Moon Emblem Bonus", BLOOD_MOON_EMBLEM_MOON_WRATH_BONUS, AttributeModifier.Operation.ADDITION);
                handleMoonEmblemEquip(emblemItem, entity, true, "Blood Moon");
                break;
            case "wood_emblem":

                applyStaticModifier(entity, Attributes.MAX_HEALTH, emblemItem.getUniqueID(),
                        "Wood Emblem Bonus", WOOD_EMBLEM_HP_BONUS, AttributeModifier.Operation.ADDITION);

                break;
            case "stone_emblem":
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Stone Emblem Bonus", STONE_EMBLEM_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "ground_emblem":
                applyStaticModifier(entity, Attributes.ATTACK_SPEED, emblemItem.getUniqueID(),
                        "Ground Emblem Bonus", GROUND_EMBLEM_ATTACK_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "plant_emblem":
                applyStaticModifier(entity, ModAttributes.NATURE_HEALING.get(), emblemItem.getUniqueID(),
                        "Plant Emblem Bonus", PLANT_EMBLEM_NATURE_HEALING_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "fossil_emblem":
                applyStaticModifier(entity, ModAttributes.MINING_SPEED.get(), emblemItem.getUniqueID(),
                        "Fossil Emblem Bonus", FOSSIL_EMBLEM_MINING_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ARMOR, emblemItem.getUniqueID(),
                        "Fossil Emblem Bonus", BONE_EMBLEM_ARMOR_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "remains_emblem":
                applyStaticModifier(entity, ModAttributes.MINING_SPEED.get(), emblemItem.getUniqueID(),
                        "Remains Emblem Bonus", REMAINS_EMBLEM_MINING_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ARMOR, emblemItem.getUniqueID(),
                        "Remains Emblem Bonus", REMAINS_EMBLEM_ARMOR_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "fire_emblem":
                applyStaticModifier(entity, ModAttributes.FIRE_FIST.get(), emblemItem.getUniqueID(),
                        "Fire Emblem Bonus", FIRE_EMBLEM_FIRE_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "crystal_emblem":
                handleCrystalEmblemEquip(emblemItem, entity, "Crystal");
                break;
            case "sky_emblem":
                handleSkyEmblemEquip(emblemItem, entity, "Sky");
                break;
            case "heart_emblem":
                applyStaticModifier(entity, Attributes.MAX_HEALTH, emblemItem.getUniqueID(),
                        "Heart Emblem Bonus", HEART_EMBLEM_HP_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "ice_emblem":
                applyStaticModifier(entity, ModAttributes.FROST_FIST.get(), emblemItem.getUniqueID(),
                        "Ice Emblem Bonus", ICE_EMBLEM_FROST_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "vege_emblem":
                applyStaticModifier(entity, ModAttributes.VEGE.get(), emblemItem.getUniqueID(),
                        "Vege Emblem Bonus", VEGE_EMBLEM_VEGE_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.ANIMAL_TEMPTATION.get(), emblemItem.getUniqueID(),
                        "Vege Emblem Bonus", VEGE_EMBLEM_ANIMAL_TEMPTATION_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.UNDERFED.get(), emblemItem.getUniqueID(),
                        "Vege Emblem Bonus", VEGE_EMBLEM_UNDERFED_BONUS, AttributeModifier.Operation.ADDITION);
                break;
                case "farmer_emblem":
                    applyStaticModifier(entity, ModAttributes.FARMERS_BLESSING.get(), emblemItem.getUniqueID(),
                            "Farmer Emblem Bonus", FARMER_EMBLEM_FARMERS_BLESSING_BONUS, AttributeModifier.Operation.ADDITION);
                break;
                case "builder_emblem":
                    applyStaticModifier(entity, ForgeMod.BLOCK_REACH.get(), emblemItem.getUniqueID(),
                            "Builder Emblem Bonus", BUILDER_EMBLEM_BLOCK_REACH_BONUS, AttributeModifier.Operation.ADDITION);
                break;
                case "flower_emblem":
                    applyStaticModifier(entity, ModAttributes.PHOTOSYNTHESIS.get(), emblemItem.getUniqueID(),
                            "Flower Emblem Bonus", FLOWER_EMBLEM_PHOTOSYNTHESIS_BONUS, AttributeModifier.Operation.ADDITION);
                break;
                case "bone_emblem":
                    applyStaticModifier(entity, Attributes.ARMOR, emblemItem.getUniqueID(),
                            "Bone Emblem Bonus", BONE_EMBLEM_ARMOR_BONUS, AttributeModifier.Operation.ADDITION);
                break;
                case "inferno_emblem":
                    applyStaticModifier(entity, ModAttributes.INFERNO.get(), emblemItem.getUniqueID(),
                            "Inferno Emblem Bonus", INFERNO_EMBLEM_INFERNO_BONUS, AttributeModifier.Operation.ADDITION);
                break;
                case "supernova_emblem":
                    applyStaticModifier(entity, ModAttributes.INFERNO.get(), emblemItem.getUniqueID(),
                            "Supernova Emblem Bonus", SUPERNOVA_EMBLEM_INFERNO_BONUS, AttributeModifier.Operation.ADDITION);
                    applyStaticModifier(entity, ModAttributes.FIRE_FIST.get(), emblemItem.getUniqueID(),
                            "Supernova Emblem Bonus", SUPERNOVA_EMBLEM_FIRE_FIST_BONUS, AttributeModifier.Operation.ADDITION);

                break;
            case "moon_jumper_emblem":
                applyStaticModifier(entity, ModAttributes.MOON_JUMP.get(), emblemItem.getUniqueID(),
                        "Moon Jumper Emblem Bonus", ASTEROID_EMBLEM_ASTEROID_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "bloodstar_emblem":

                applyStaticModifier(entity, ModAttributes.PHOTOSYNTHESIS.get(), emblemItem.getUniqueID(),
                        "Bloodstar Emblem Bonus", SUPERNOVA_EMBLEM_PHOTOSYNTHESIS_BONUS,
                        AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MOVEMENT_SPEED, emblemItem.getUniqueID(),
                        "Bloodstar Emblem Bonus", BLOODSTAR_EMBLEM_MOVEMENT_SPEED_BONUS, AttributeModifier.Operation.ADDITION);

                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Bloodstar  Emblem Damage", BLOODSTAR_EMBLEM_ATTACK_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.MOON_JUMP.get(), emblemItem.getUniqueID(),
                        "Bloodstar Emblem Bonus", ASTEROID_EMBLEM_ASTEROID_BONUS, AttributeModifier.Operation.ADDITION);
                handleMoonEmblemEquip(emblemItem, entity, true, "Bloodstar");
                applyStaticModifier(entity, ModAttributes.INFERNO.get(), emblemItem.getUniqueID(),
                        "Bloodstar Emblem Bonus", SUPERNOVA_EMBLEM_INFERNO_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.FIRE_FIST.get(), emblemItem.getUniqueID(),
                        "Bloodstar Emblem Bonus", SUPERNOVA_EMBLEM_FIRE_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.MOON_WRATH.get(), emblemItem.getUniqueID(),
                        "Bloodstar Emblem Bonus", BLOODSTAR_EMBLEM_MOON_WRATH_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "poop_emblem":
                applyStaticModifier(entity, ModAttributes.POOP.get(), emblemItem.getUniqueID(),
                        "Poop Emblem Bonus", POOP_EMBLEM_POOP_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "merchant_emblem":
                applyStaticModifier(entity, ModAttributes.MERCHANT.get(), emblemItem.getUniqueID(),
                        "Merchant Emblem Bonus", MERCHANT_EMBLEM_MERCHANT_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "tree_emblem":
                applyStaticModifier(entity, ModAttributes.PHOTOSYNTHESIS.get(), emblemItem.getUniqueID(),
                        "Tree Emblem Bonus", TREE_EMBLEM_PHOTOSYNTHESIS_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.NATURE_HEALING.get(), emblemItem.getUniqueID(),
                        "Tree Emblem Bonus", TREE_EMBLEM_NATURE_HEALING_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MAX_HEALTH, emblemItem.getUniqueID(),
                        "Tree Emblem Bonus", TREE_EMBLEM_MAX_HEALTH_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_SPEED, emblemItem.getUniqueID(),
                        "Tree Emblem Bonus", TREE_EMBLEM_ATTACK_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "cave_emblem":
                applyStaticModifier(entity, ModAttributes.FORTUNE_FIST.get(), emblemItem.getUniqueID(),
                        "Cave Emblem Bonus", CAVE_EMBLEM_FORTUNE_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MOVEMENT_SPEED, emblemItem.getUniqueID(),
                        "Cave Emblem Bonus", CAVE_EMBLEM_MOVEMENT_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ARMOR, emblemItem.getUniqueID(),
                        "Cave Emblem Bonus", CAVE_EMBLEM_ARMOR_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Cave Emblem Bonus", CAVE_EMBLEM_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "lush_cave_emblem":
                applyStaticModifier(entity, ModAttributes.FORTUNE_FIST.get(), emblemItem.getUniqueID(),
                        "Lush Cave Emblem Bonus", CAVE_EMBLEM_FORTUNE_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MOVEMENT_SPEED, emblemItem.getUniqueID(),
                        "Lush Cave Emblem Bonus", CAVE_EMBLEM_MOVEMENT_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ARMOR, emblemItem.getUniqueID(),
                        "Lush Cave Emblem Bonus", CAVE_EMBLEM_ARMOR_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Lush Cave Emblem Bonus", CAVE_EMBLEM_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ForgeMod.ENTITY_REACH.get(), emblemItem.getUniqueID(),
                        "Lush Cave Emblem Bonus", VINE_EMBLEM_ENTITY_REACH_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.SNEAKY_DEFENCE.get(), emblemItem.getUniqueID(),
                        "Lush Cave Emblem Bonus", VINE_EMBLEM_SNEAKY_DEFENCE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "radiant_cave_emblem":
                applyStaticModifier(entity, ModAttributes.FORTUNE_FIST.get(), emblemItem.getUniqueID(),
                        "Radiant Cave Emblem Bonus", RADIANT_CAVE_EMBLEM_FORTUNE_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MOVEMENT_SPEED, emblemItem.getUniqueID(),
                        "Radiant Cave Emblem Bonus", RADIANT_CAVE_EMBLEM_MOVEMENT_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ARMOR, emblemItem.getUniqueID(),
                        "Radiant Cave Emblem Bonus", RADIANT_CAVE_EMBLEM_ARMOR_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Radiant Cave Emblem Bonus", RADIANT_CAVE_EMBLEM_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ForgeMod.ENTITY_REACH.get(), emblemItem.getUniqueID(),
                        "Radiant Cave Emblem Bonus", RADIANT_CAVE_EMBLEM_ENTITY_REACH_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.SNEAKY_DEFENCE.get(), emblemItem.getUniqueID(),
                        "Radiant Cave Emblem Bonus", RADIANT_CAVE_EMBLEM_SNEAKY_DEFENCE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "atomic_cave_emblem":
                applyStaticModifier(entity, ModAttributes.FORTUNE_FIST.get(), emblemItem.getUniqueID(),
                        "Atomic Cave Emblem Bonus", ATOMIC_CAVE_EMBLEM_FORTUNE_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MOVEMENT_SPEED, emblemItem.getUniqueID(),
                        "Atomic Cave Emblem Bonus", ATOMIC_CAVE_EMBLEM_MOVEMENT_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ARMOR, emblemItem.getUniqueID(),
                        "Atomic Cave Emblem Bonus", ATOMIC_CAVE_EMBLEM_ARMOR_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Atomic Cave Emblem Bonus", ATOMIC_CAVE_EMBLEM_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ForgeMod.ENTITY_REACH.get(), emblemItem.getUniqueID(),
                        "Atomic Cave Emblem Bonus", ATOMIC_CAVE_EMBLEM_ENTITY_REACH_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.SNEAKY_DEFENCE.get(), emblemItem.getUniqueID(),
                        "Atomic Cave Emblem Bonus", ATOMIC_CAVE_EMBLEM_SNEAKY_DEFENCE_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.MINING_SPEED.get(), emblemItem.getUniqueID(),
                        "Atomic Cave Emblem Bonus", ATOMIC_CAVE_EMBLEM_MINING_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "lifeless_cave_emblem":
                applyStaticModifier(entity, ModAttributes.FORTUNE_FIST.get(), emblemItem.getUniqueID(),
                        "Lifeless Cave Emblem Bonus", LIFELESS_CAVE_EMBLEM_FORTUNE_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MOVEMENT_SPEED, emblemItem.getUniqueID(),
                        "Lifeless Cave Emblem Bonus", LIFELESS_CAVE_EMBLEM_MOVEMENT_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ARMOR, emblemItem.getUniqueID(),
                        "Lifeless Cave Emblem Bonus", LIFELESS_CAVE_EMBLEM_ARMOR_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Lifeless Cave Emblem Bonus", LIFELESS_CAVE_EMBLEM_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.MINING_SPEED.get(), emblemItem.getUniqueID(),
                        "Lifeless Cave Emblem Bonus", LIFELESS_EMBLEM_MINING_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "desert_cave_emblem":
                applyStaticModifier(entity, ModAttributes.FORTUNE_FIST.get(), emblemItem.getUniqueID(),
                        "Desert Cave Emblem Bonus", CAVE_EMBLEM_FORTUNE_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MOVEMENT_SPEED, emblemItem.getUniqueID(),
                        "Desert Cave Emblem Bonus", CAVE_EMBLEM_MOVEMENT_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ARMOR, emblemItem.getUniqueID(),
                        "Desert Cave Emblem Bonus", CAVE_EMBLEM_ARMOR_BONUS + REMAINS_EMBLEM_ARMOR_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Desert Cave Emblem Bonus", CAVE_EMBLEM_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.MINING_SPEED.get(), emblemItem.getUniqueID(),
                        "Desert Cave Emblem Bonus", FOSSIL_EMBLEM_MINING_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "stalactite_cave_emblem":
                applyStaticModifier(entity, ModAttributes.FORTUNE_FIST.get(), emblemItem.getUniqueID(),
                        "Stalactite Cave Emblem Bonus", CAVE_EMBLEM_FORTUNE_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MOVEMENT_SPEED, emblemItem.getUniqueID(),
                        "Stalactite Cave Emblem Bonus", CAVE_EMBLEM_MOVEMENT_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ARMOR, emblemItem.getUniqueID(),
                        "Stalactite Cave Emblem Bonus", CAVE_EMBLEM_ARMOR_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Stalactite Cave Emblem Bonus", CAVE_EMBLEM_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "spade_emblem":
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Spade Emblem Bonus", SPADE_EMBLEM_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "clover_emblem":
                applyStaticModifier(entity, Attributes.MOVEMENT_SPEED, emblemItem.getUniqueID(),
                        "Clover Emblem Bonus", CLOVER_EMBLEM_MOVEMENT_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "water_emblem":
                applyStaticModifier(entity, ModAttributes.RESPIRATION.get(), emblemItem.getUniqueID(),
                        "Water Emblem Bonus", WATER_EMBLEM_RESPIRATION_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ForgeMod.SWIM_SPEED.get(), emblemItem.getUniqueID(),
                        "Water Emblem Bonus", WATER_EMBLEM_SWIM_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "blizzard_emblem":
                applyStaticModifier(entity, ModAttributes.BLIZZARD.get(), emblemItem.getUniqueID(),
                        "Blizzard Emblem Bonus", BLIZZARD_EMBLEM_BLIZZARD_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "frost_emblem":
                applyStaticModifier(entity, ModAttributes.FROST_WALKER.get(), emblemItem.getUniqueID(),
                        "Frost Emblem Bonus", FROST_EMBLEM_FROST_WALKER_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "snowstorm_emblem":
                applyStaticModifier(entity, ModAttributes.BLIZZARD.get(), emblemItem.getUniqueID(),
                        "Snowstorm Emblem Bonus", SNOWSTORM_EMBLEM_BLIZZARD_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.FROST_FIST.get(), emblemItem.getUniqueID(),
                        "Snowstorm Emblem Bonus", SNOWSTORM_EMBLEM_FROST_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "spark_emblem":
                applyStaticModifier(entity, ModAttributes.STATIC.get(), emblemItem.getUniqueID(),
                        "Spark Emblem Bonus", SPARK_EMBLEM_STATIC_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "conductor_emblem":
                applyStaticModifier(entity, ModAttributes.CONDUCTOR.get(), emblemItem.getUniqueID(),
                        "Conductor Emblem Bonus", CONDUCTOR_EMBLEM_CONDUCTOR_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "coiled_emblem":
                applyStaticModifier(entity, ModAttributes.COILED.get(), emblemItem.getUniqueID(),
                        "Coiled Emblem Bonus", COILED_EMBLEM_COILED_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "heaven_emblem":
                handleSkyEmblemEquip(emblemItem, entity, "Heaven");
                break;
            case "return_emblem":
                applyStaticModifier(entity, ModAttributes.RETURN.get(), emblemItem.getUniqueID(),
                        "Return Emblem Bonus", RETURN_EMBLEM_RETURN_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "warrior_emblem":
                applyStaticModifier(entity, Attributes.ARMOR, emblemItem.getUniqueID(),
                        "Warrior Emblem Bonus", WARRIOR_EMBLEM_ARMOR_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "rotten_emblem":
                applyStaticModifier(entity, ModAttributes.ROTTEN.get(), emblemItem.getUniqueID(),
                        "Rotten Emblem Bonus", ROTTEN_EMBLEM_ROTTEN_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "golden_emblem":
                applyStaticModifier(entity, ModAttributes.GOLDEN_FAVOR.get(), emblemItem.getUniqueID(),
                        "Golden Emblem Bonus", GOLDEN_EMBLEM_GOLDEN_FAVOR_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "electro_emblem":
                applyStaticModifier(entity, ModAttributes.ELECTRO_FIST.get(), emblemItem.getUniqueID(),
                        "Electro Emblem Bonus", ELECTRO_EMBLEM_ELECTRO_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "gambler_emblem":
                applyStaticModifier(entity, ModAttributes.GAMBLER.get(), emblemItem.getUniqueID(),
                        "Gambler Emblem Bonus", GAMBLER_EMBLEM_GAMBLER_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "smoke_emblem":
                applyStaticModifier(entity, ModAttributes.VANISH.get(), emblemItem.getUniqueID(),
                        "Smoke Emblem Bonus", SMOKE_EMBLEM_VANISH_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "easter_eggblem":
                applyStaticModifier(entity, ModAttributes.EGGING.get(), emblemItem.getUniqueID(),
                        "Easter Eggblem Bonus", EASTER_EGGBLEM_EGGING_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "steam_emblem":
                applyStaticModifier(entity, ModAttributes.STEAM.get(), emblemItem.getUniqueID(),
                        "Steam Emblem Bonus", STEAM_EMBLEM_STEAM_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "auric_emblem":
                applyStaticModifier(entity, ModAttributes.STEAM.get(), emblemItem.getUniqueID(),
                        "Auric Emblem Bonus", AURIC_EMBLEM_STEAM_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.COILED.get(), emblemItem.getUniqueID(),
                        "Auric Emblem Bonus", AURIC_EMBLEM_COILED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.INFERNO.get(), emblemItem.getUniqueID(),
                        "Auric Emblem Bonus", AURIC_EMBLEM_INFERNO_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.BLIZZARD.get(), emblemItem.getUniqueID(),
                        "Auric Emblem Bonus", AURIC_EMBLEM_BLIZZARD_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "induction_emblem":
                applyStaticModifier(entity, ModAttributes.STATIC.get(), emblemItem.getUniqueID(),
                        "Induction Emblem Bonus", INDUCTION_EMBLEM_STATIC_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.CONDUCTOR.get(), emblemItem.getUniqueID(),
                        "Induction Emblem Bonus", INDUCTION_EMBLEM_CONDUCTOR_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "overcharged_emblem":
                applyStaticModifier(entity, ModAttributes.ELECTRO_FIST.get(), emblemItem.getUniqueID(),
                        "Overcharged Emblem Bonus", OVERCHARGED_EMBLEM_ELECTRO_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.COILED.get(), emblemItem.getUniqueID(),
                        "Overcharged Emblem Bonus", OVERCHARGED_EMBLEM_COILED_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "thunder_emblem":
                applyStaticModifier(entity, ModAttributes.ELECTRO_FIST.get(), emblemItem.getUniqueID(),
                        "Thunder Emblem Bonus", THUNDER_EMBLEM_ELECTRO_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.COILED.get(), emblemItem.getUniqueID(),
                        "Thunder Emblem Bonus", THUNDER_EMBLEM_COILED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.STATIC.get(), emblemItem.getUniqueID(),
                        "Thunder Emblem Bonus", THUNDER_EMBLEM_STATIC_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.CONDUCTOR.get(), emblemItem.getUniqueID(),
                        "Thunder Emblem Bonus", THUNDER_EMBLEM_CONDUCTOR_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.THUNDERCLAP.get(), emblemItem.getUniqueID(),
                        "Thunder Emblem Bonus", THUNDER_EMBLEM_THUNDERCLAP_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "bubble_emblem":
                applyStaticModifier(entity, ModAttributes.BUBBLE_FIST.get(), emblemItem.getUniqueID(),
                        "Bubble Emblem Bonus", BUBBLE_EMBLEM_BUBBLE_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "barterer_emblem":
                applyStaticModifier(entity, ModAttributes.BARTERER.get(), emblemItem.getUniqueID(),
                        "Barterer Emblem Bonus", BARTERER_EMBLEM_BARTERER_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "hourglass_emblem":
                applyStaticModifier(entity, ModAttributes.TIME_CONTROL.get(), emblemItem.getUniqueID(),
                        "Hourglass Emblem Bonus", HOURGLASS_EMBLEM_TIME_CONTROL_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "mirror_emblem":
                applyStaticModifier(entity, ModAttributes.MIRRORING.get(), emblemItem.getUniqueID(),
                        "Mirror Emblem Bonus", MIRROR_EMBLEM_MIRRORING_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.DAMAGE_REFLECTION.get(), emblemItem.getUniqueID(),
                        "Mirror Emblem Bonus", MIRROR_EMBLEM_DAMAGE_REFLECTION_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "tank_emblem":
                applyStaticModifier(entity, Attributes.MAX_HEALTH, emblemItem.getUniqueID(),
                        "Tank Emblem Bonus", TANK_EMBLEM_MAX_HEALTH_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ARMOR, emblemItem.getUniqueID(),
                        "Tank Emblem Bonus", TANK_EMBLEM_ARMOR_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "ruthless_guardian_emblem":
                applyStaticModifier(entity, Attributes.MAX_HEALTH, emblemItem.getUniqueID(),
                        "Ruthless Guardian Emblem Bonus", RUTHLESS_GUARDIAN_EMBLEM_MAX_HEALTH_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ARMOR, emblemItem.getUniqueID(),
                        "Ruthless Guardian Emblem Bonus", RUTHLESS_GUARDIAN_EMBLEM_ARMOR_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "absorber_emblem":
                applyStaticModifier(entity, Attributes.MAX_HEALTH, emblemItem.getUniqueID(),
                        "Absorber Guardian Emblem Bonus", ABSORBER_EMBLEM_MAX_HEALTH_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ARMOR, emblemItem.getUniqueID(),
                        "Absorber Guardian Emblem Bonus", ABSORBER_EMBLEM_ARMOR_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "the_tank_emblem":
                applyStaticModifier(entity, Attributes.MAX_HEALTH, emblemItem.getUniqueID(),
                        "The Tank Guardian Emblem Bonus", THE_TANK_EMBLEM_MAX_HEALTH_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "pyro_emblem":
                applyStaticModifier(entity, ModAttributes.MAGMA_WALKER.get(), emblemItem.getUniqueID(),
                        "Pyro Emblem Bonus", PYRO_EMBLEM_MAGMA_WALKER_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "soul_emblem":
                applyStaticModifier(entity, ModAttributes.SOUL_CONNECTOR.get(), emblemItem.getUniqueID(),
                        "Soul Emblem Bonus", SOUL_EMBLEM_SOUL_CONNECTOR_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "moon_wrath_emblem":
                applyStaticModifier(entity, ModAttributes.MOON_WRATH.get(), emblemItem.getUniqueID(),
                        "Moon Wrath Emblem Bonus", MOON_WRATH_EMBLEM_MOON_WRATH_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "orchid_emblem":
                applyStaticModifier(entity, ModAttributes.PHOTOSYNTHESIS.get(), emblemItem.getUniqueID(),
                        "Orchid Emblem Bonus", ORCHID_EMBLEM_PHOTOSYNTHESIS_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.FARMERS_BLESSING.get(), emblemItem.getUniqueID(),
                        "Orchid Emblem Bonus", ORCHID_EMBLEM_FARMERS_BLESSING_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "cactus_emblem":
                applyStaticModifier(entity, ModAttributes.THORNS.get(), emblemItem.getUniqueID(),
                        "Cactus Emblem Bonus", CACTUS_EMBLEM_THORNS_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "bamboo_emblem":
                applyStaticModifier(entity, ForgeMod.ENTITY_REACH.get(), emblemItem.getUniqueID(),
                        "Bamboo Emblem Bonus", BAMBOO_EMBLEM_ENTITY_REACH_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "ivy_emblem":
                applyStaticModifier(entity, ModAttributes.SNEAKY_DEFENCE.get(), emblemItem.getUniqueID(),
                        "Ivy Emblem Bonus", IVY_EMBLEM_SNEAKY_DEFENCE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "rose_emblem":
                applyStaticModifier(entity, ModAttributes.BLEEDING_FIST.get(), emblemItem.getUniqueID(),
                        "Rose Emblem Bonus", ROSE_EMBLEM_BLEEDING_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "ibara_emblem":
                applyStaticModifier(entity, ModAttributes.BLEEDING_FIST.get(), emblemItem.getUniqueID(),
                        "Ibara Emblem Bonus", IBARA_EMBLEM_BLEEDING_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.THORNS.get(), emblemItem.getUniqueID(),
                        "Ibara Emblem Bonus", IBARA_EMBLEM_THORNS_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "black_rose_emblem":
                applyStaticModifier(entity, ModAttributes.WITHER_FIST.get(), emblemItem.getUniqueID(),
                        "Black Rose Emblem Bonus", BLACK_ROSE_EMBLEM_WITHER_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "dried_rose_emblem":
                applyStaticModifier(entity, ModAttributes.WITHERER.get(), emblemItem.getUniqueID(),
                        "Dried Rose Emblem Bonus", DRIED_ROSE_EMBLEM_WITHERER_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "wither_rose_emblem":
                applyStaticModifier(entity, ModAttributes.WITHER_FIST.get(), emblemItem.getUniqueID(),
                        "Wither Rose Emblem Bonus", WITHER_ROSE_EMBLEM_WITHER_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.WITHERER.get(), emblemItem.getUniqueID(),
                        "Wither Rose Emblem Bonus", WITHER_ROSE_EMBLEM_WITHERER_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "bouquet_emblem":
                applyStaticModifier(entity, ModAttributes.FARMERS_BLESSING.get(), emblemItem.getUniqueID(),
                        "Bouquet Emblem Bonus", BOUQUET_EMBLEM_FARMERS_BLESSING_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.BLEEDING_FIST.get(), emblemItem.getUniqueID(),
                        "Bouquet Emblem Bonus", BOUQUET_EMBLEM_BLEEDING_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.PHOTOSYNTHESIS.get(), emblemItem.getUniqueID(),
                        "Bouquet Emblem Bonus", BOUQUET_EMBLEM_PHOTOSYNTHESIS_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "vine_emblem":
                applyStaticModifier(entity, ForgeMod.ENTITY_REACH.get(), emblemItem.getUniqueID(),
                        "Vine Emblem Bonus", VINE_EMBLEM_ENTITY_REACH_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.SNEAKY_DEFENCE.get(), emblemItem.getUniqueID(),
                        "Vine Emblem Bonus", VINE_EMBLEM_SNEAKY_DEFENCE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "prickle_vine_emblem":
                applyStaticModifier(entity, ForgeMod.ENTITY_REACH.get(), emblemItem.getUniqueID(),
                        "Prickle Vine Emblem Bonus", PRICKLE_VINE_EMBLEM_ENTITY_REACH_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.SNEAKY_DEFENCE.get(), emblemItem.getUniqueID(),
                        "Prickle Vine Emblem Bonus", PRICKLE_VINE_EMBLEM_SNEAKY_DEFENCE_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.THORNS.get(), emblemItem.getUniqueID(),
                        "Prickle Vine Emblem Bonus", PRICKLE_VINE_EMBLEM_THORNS_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.BLEEDING_FIST.get(), emblemItem.getUniqueID(),
                        "Prickle Vine Emblem Bonus", PRICKLE_VINE_EMBLEM_BLEEDING_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "flowerfield_emblem":
                applyStaticModifier(entity, ForgeMod.ENTITY_REACH.get(), emblemItem.getUniqueID(),
                        "Flowerfield Emblem Bonus", FLOWERFIELD_EMBLEM_ENTITY_REACH_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.BLEEDING_FIST.get(), emblemItem.getUniqueID(),
                        "Flowerfield Emblem Bonus", FLOWERFIELD_EMBLEM_BLEEDING_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.THORNS.get(), emblemItem.getUniqueID(),
                        "Flowerfield Emblem Bonus", FLOWERFIELD_EMBLEM_THORNS_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.FARMERS_BLESSING.get(), emblemItem.getUniqueID(),
                        "Flowerfield Emblem Bonus", FLOWERFIELD_EMBLEM_FARMERS_BLESSING_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.PHOTOSYNTHESIS.get(), emblemItem.getUniqueID(),
                        "Flowerfield Emblem Bonus", FLOWERFIELD_EMBLEM_PHOTOSYNTHESIS_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.SNEAKY_DEFENCE.get(), emblemItem.getUniqueID(),
                        "Flowerfield Emblem Bonus", FLOWERFIELD_EMBLEM_SNEAKY_DEFENCE_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "rainforest_emblem":
                applyStaticModifier(entity, ModAttributes.PHOTOSYNTHESIS.get(), emblemItem.getUniqueID(),
                        "Rainforest Emblem Bonus", RAINFOREST_EMBLEM_PHOTOSYNTHESIS_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.NATURE_HEALING.get(), emblemItem.getUniqueID(),
                        "Rainforest Emblem Bonus", RAINFOREST_EMBLEM_NATURE_HEALING_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MAX_HEALTH, emblemItem.getUniqueID(),
                        "Rainforest Emblem Bonus", RAINFOREST_EMBLEM_MAX_HEALTH_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_SPEED, emblemItem.getUniqueID(),
                        "Rainforest Emblem Bonus", RAINFOREST_EMBLEM_ATTACK_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "deadforest_emblem":
                applyStaticModifier(entity, ModAttributes.PHOTOSYNTHESIS.get(), emblemItem.getUniqueID(),
                        "Deadforest Emblem Bonus", DEADFOREST_EMBLEM_PHOTOSYNTHESIS_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.NATURE_HEALING.get(), emblemItem.getUniqueID(),
                        "Deadforest Emblem Bonus", DEADFOREST_EMBLEM_NATURE_HEALING_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.WITHER_FIST.get(), emblemItem.getUniqueID(),
                        "Deadforest Emblem Bonus", DEADFOREST_EMBLEM_WITHER_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.WITHERER.get(), emblemItem.getUniqueID(),
                        "Deadforest Emblem Bonus", DEADFOREST_EMBLEM_WITHERER_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MAX_HEALTH, emblemItem.getUniqueID(),
                        "Deadforest Emblem Bonus", DEADFOREST_EMBLEM_MAX_HEALTH_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_SPEED, emblemItem.getUniqueID(),
                        "Deadforest Emblem Bonus", DEADFOREST_EMBLEM_ATTACK_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "flowerforest_emblem":
                applyStaticModifier(entity, ModAttributes.PHOTOSYNTHESIS.get(), emblemItem.getUniqueID(),
                        "Flowerforest Emblem Bonus", FLOWERFOREST_EMBLEM_PHOTOSYNTHESIS_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.NATURE_HEALING.get(), emblemItem.getUniqueID(),
                        "Flowerforest Emblem Bonus", FLOWERFOREST_EMBLEM_NATURE_HEALING_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.WITHER_FIST.get(), emblemItem.getUniqueID(),
                        "Flowerforest Emblem Bonus", FLOWERFOREST_EMBLEM_WITHER_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.WITHERER.get(), emblemItem.getUniqueID(),
                        "Flowerforest Emblem Bonus", FLOWERFOREST_EMBLEM_WITHERER_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MAX_HEALTH, emblemItem.getUniqueID(),
                        "Flowerforest Emblem Bonus", FLOWERFOREST_EMBLEM_MAX_HEALTH_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_SPEED, emblemItem.getUniqueID(),
                        "Flowerforest Emblem Bonus", FLOWERFOREST_EMBLEM_ATTACK_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ForgeMod.ENTITY_REACH.get(), emblemItem.getUniqueID(),
                        "Flowerforest Emblem Bonus", FLOWERFOREST_EMBLEM_ENTITY_REACH_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.BLEEDING_FIST.get(), emblemItem.getUniqueID(),
                        "Flowerforest Emblem Bonus", FLOWERFOREST_EMBLEM_BLEEDING_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.THORNS.get(), emblemItem.getUniqueID(),
                        "Flowerforest Emblem Bonus", FLOWERFOREST_EMBLEM_THORNS_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.FARMERS_BLESSING.get(), emblemItem.getUniqueID(),
                        "Flowerforest Emblem Bonus", FLOWERFOREST_EMBLEM_FARMERS_BLESSING_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.SNEAKY_DEFENCE.get(), emblemItem.getUniqueID(),
                        "Flowerforest Emblem Bonus", FLOWERFOREST_EMBLEM_SNEAKY_DEFENCE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "dandelion_emblem":
                applyStaticModifier(entity, ModAttributes.DANDELION_FALL.get(), emblemItem.getUniqueID(),
                        "Dandelion Emblem Bonus", DANDELION_EMBLEM_DANDELION_FALL_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "mist_emblem":
                applyStaticModifier(entity, ModAttributes.MIST.get(), emblemItem.getUniqueID(),
                        "Mist Emblem Bonus", MIST_EMBLEM_MIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "tide_emblem":
                applyStaticModifier(entity, ModAttributes.TIDAL_FIST.get(), emblemItem.getUniqueID(),
                        "Tide Emblem Bonus", TIDE_EMBLEM_TIDAL_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "sprinkler_emblem":
                applyStaticModifier(entity, ModAttributes.SPRINKLERS.get(), emblemItem.getUniqueID(),
                        "Sprinkler Emblem Bonus", SPRINKLER_EMBLEM_SPRINKLERS_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "tear_emblem":
                applyStaticModifier(entity, ModAttributes.SCHADENFREUDE.get(), emblemItem.getUniqueID(),
                        "Tear Emblem Bonus", TEAR_EMBLEM_SCHADENFREUDE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "puddle_emblem":
                applyStaticModifier(entity, ModAttributes.PUFF.get(), emblemItem.getUniqueID(),
                        "Puddle Emblem Bonus", PUDDLE_EMBLEM_PUFF_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "mud_emblem":
                applyStaticModifier(entity, ModAttributes.MUDDY_FIST.get(), emblemItem.getUniqueID(),
                        "Mud Emblem Bonus", MUD_EMBLEM_MUDDY_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "clay_emblem":
                applyStaticModifier(entity, ModAttributes.MUDDY_FIST.get(), emblemItem.getUniqueID(),
                        "Clay Emblem Bonus", CLAY_EMBLEM_MUDDY_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Clay Emblem Bonus", CLAY_EMBLEM_ATTACK_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "marsh_emblem":
                applyStaticModifier(entity, ModAttributes.MUDDY_FIST.get(), emblemItem.getUniqueID(),
                        "Swamp Emblem Bonus", MARSH_EMBLEM_MUDDY_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.STEAM.get(), emblemItem.getUniqueID(),
                        "Swamp Emblem Bonus", MARSH_EMBLEM_STEAM_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "bog_emblem":
                applyStaticModifier(entity, ModAttributes.MIST.get(), emblemItem.getUniqueID(),
                        "Bog Emblem Bonus", BOG_EMBLEM_MIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.PUFF.get(), emblemItem.getUniqueID(),
                        "Bog Emblem Bonus", BOG_EMBLEM_PUFF_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "wave_emblem":
                applyStaticModifier(entity, ModAttributes.RESPIRATION.get(), emblemItem.getUniqueID(),
                        "Wave Emblem Bonus", WAVE_EMBLEM_RESPIRATION_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ForgeMod.SWIM_SPEED.get(), emblemItem.getUniqueID(),
                        "Wave Emblem Bonus", WAVE_EMBLEM_SWIM_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "whirl_emblem":
                applyStaticModifier(entity, ModAttributes.TIDAL_FIST.get(), emblemItem.getUniqueID(),
                        "Whirl Emblem Bonus", WHIRL_EMBLEM_TIDAL_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.BUBBLE_FIST.get(), emblemItem.getUniqueID(),
                        "Whirl Emblem Bonus", WHIRL_EMBLEM_BUBBLE_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "vortex_emblem":
                applyStaticModifier(entity, ModAttributes.TIDAL_FIST.get(), emblemItem.getUniqueID(),
                        "Vortex Emblem Bonus", VORTEX_EMBLEM_TIDAL_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.BUBBLE_FIST.get(), emblemItem.getUniqueID(),
                        "Vortex Emblem Bonus", VORTEX_EMBLEM_BUBBLE_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.MOON_FAVOR.get(), emblemItem.getUniqueID(),
                        "Vortex Emblem Favor", VORTEX_EMBLEM_MOON_FAVOR_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.SCHADENFREUDE.get(), emblemItem.getUniqueID(),
                        "Vortex Emblem Favor", VORTEX_EMBLEM_SCHADENFREUDE_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Vortex Emblem Damage", VORTEX_EMBLEM_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "swamp_emblem":
                applyStaticModifier(entity, ModAttributes.PUFF.get(), emblemItem.getUniqueID(),
                        "Swamp Emblem Bonus", SWAMP_EMBLEM_PUFF_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.STEAM.get(), emblemItem.getUniqueID(),
                        "Swamp Emblem Bonus", SWAMP_EMBLEM_STEAM_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.MIST.get(), emblemItem.getUniqueID(),
                        "Swamp Emblem Bonus", SWAMP_EMBLEM_MIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.MUDDY_FIST.get(), emblemItem.getUniqueID(),
                        "Swamp Emblem Bonus", SWAMP_EMBLEM_MUDDY_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "current_emblem":
                applyStaticModifier(entity, ModAttributes.RESPIRATION.get(), emblemItem.getUniqueID(),
                        "Current Emblem Bonus", CURRENT_EMBLEM_RESPIRATION_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.SPRINKLERS.get(), emblemItem.getUniqueID(),
                        "Current Emblem Bonus", CURRENT_EMBLEM_SPRINKLERS_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ForgeMod.SWIM_SPEED.get(), emblemItem.getUniqueID(),
                        "Current Emblem Bonus", CURRENT_EMBLEM_SWIM_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MOVEMENT_SPEED, emblemItem.getUniqueID(),
                        "Current Emblem Bonus", CURRENT_EMBLEM_MOVEMENT_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "muckmire_emblem":
                applyStaticModifier(entity, ModAttributes.RESPIRATION.get(), emblemItem.getUniqueID(),
                        "Muckmire Emblem Bonus", MUCKMIRE_EMBLEM_RESPIRATION_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.SPRINKLERS.get(), emblemItem.getUniqueID(),
                        "Muckmire Emblem Bonus", MUCKMIRE_EMBLEM_SPRINKLERS_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ForgeMod.SWIM_SPEED.get(), emblemItem.getUniqueID(),
                        "Muckmire Emblem Bonus", MUCKMIRE_EMBLEM_SWIM_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MOVEMENT_SPEED, emblemItem.getUniqueID(),
                        "Muckmire Emblem Bonus", MUCKMIRE_EMBLEM_MOVEMENT_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.PUFF.get(), emblemItem.getUniqueID(),
                        "Muckmire Emblem Bonus", MUCKMIRE_EMBLEM_PUFF_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.STEAM.get(), emblemItem.getUniqueID(),
                        "Muckmire Emblem Bonus", MUCKMIRE_EMBLEM_STEAM_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.MIST.get(), emblemItem.getUniqueID(),
                        "Muckmire Emblem Bonus", MUCKMIRE_EMBLEM_MIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.MUDDY_FIST.get(), emblemItem.getUniqueID(),
                        "Muckmire Emblem Bonus", MUCKMIRE_EMBLEM_MUDDY_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "maelstrom_emblem":
                applyStaticModifier(entity, ModAttributes.TIDAL_FIST.get(), emblemItem.getUniqueID(),
                        "Maelstrom Emblem Bonus", MAELSTROM_EMBLEM_TIDAL_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.BUBBLE_FIST.get(), emblemItem.getUniqueID(),
                        "Maelstrom Emblem Bonus", MAELSTROM_EMBLEM_BUBBLE_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.MOON_FAVOR.get(), emblemItem.getUniqueID(),
                        "Maelstrom Emblem Favor", MAELSTROM_EMBLEM_MOON_FAVOR_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.SCHADENFREUDE.get(), emblemItem.getUniqueID(),
                        "Maelstrom Emblem Favor", MAELSTROM_EMBLEM_SCHADENFREUDE_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Maelstrom Emblem Damage", MAELSTROM_EMBLEM_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "forbidden_oasis_emblem":
                applyStaticModifier(entity, ModAttributes.TIDAL_FIST.get(), emblemItem.getUniqueID(),
                        "Forbidden Oasis Emblem Bonus", FORBIDDEN_OASIS_EMBLEM_TIDAL_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.BUBBLE_FIST.get(), emblemItem.getUniqueID(),
                        "Forbidden Oasis Emblem Bonus", FORBIDDEN_OASIS_EMBLEM_BUBBLE_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.MOON_FAVOR.get(), emblemItem.getUniqueID(),
                        "Forbidden Oasis Emblem Favor", FORBIDDEN_OASIS_EMBLEM_MOON_FAVOR_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.SCHADENFREUDE.get(), emblemItem.getUniqueID(),
                        "Forbidden Oasis Emblem Favor", FORBIDDEN_OASIS_EMBLEM_SCHADENFREUDE_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Forbidden Oasis Emblem Damage", FORBIDDEN_OASIS_EMBLEM_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.RESPIRATION.get(), emblemItem.getUniqueID(),
                        "Forbidden Oasis Emblem Bonus", FORBIDDEN_OASIS_EMBLEM_RESPIRATION_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.SPRINKLERS.get(), emblemItem.getUniqueID(),
                        "Forbidden Oasis Emblem Bonus", FORBIDDEN_OASIS_EMBLEM_SPRINKLERS_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ForgeMod.SWIM_SPEED.get(), emblemItem.getUniqueID(),
                        "Forbidden Oasis Emblem Bonus", FORBIDDEN_OASIS_EMBLEM_SWIM_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MOVEMENT_SPEED, emblemItem.getUniqueID(),
                        "Forbidden Oasis Emblem Bonus", FORBIDDEN_OASIS_EMBLEM_MOVEMENT_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.PUFF.get(), emblemItem.getUniqueID(),
                        "Forbidden Oasis Emblem Bonus", FORBIDDEN_OASIS_EMBLEM_PUFF_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.STEAM.get(), emblemItem.getUniqueID(),
                        "Forbidden Oasis Emblem Bonus", FORBIDDEN_OASIS_EMBLEM_STEAM_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.MIST.get(), emblemItem.getUniqueID(),
                        "Forbidden Oasis Emblem Bonus", FORBIDDEN_OASIS_EMBLEM_MIST_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.MUDDY_FIST.get(), emblemItem.getUniqueID(),
                        "Forbidden Oasis Emblem Bonus", FORBIDDEN_OASIS_EMBLEM_MUDDY_FIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "florist_emblem":
                applyStaticModifier(entity, ModAttributes.FLORIST.get(), emblemItem.getUniqueID(),
                        "Florist Emblem Bonus", FLORIST_EMBLEM_FLORIST_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "knight_emblem":
                applyStaticModifier(entity, Attributes.ATTACK_SPEED, emblemItem.getUniqueID(),
                        "Knight Emblem Bonus", KNIGHT_EMBLEM_ATTACK_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "gladiator_emblem":
                applyStaticModifier(entity, Attributes.ATTACK_SPEED, emblemItem.getUniqueID(),
                        "Gladiator Emblem Bonus", GLADIATOR_EMBLEM_ATTACK_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Gladiator Emblem Bonus", GLADIATOR_EMBLEM_ATTACK_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "assassin_emblem":
                applyStaticModifier(entity, Attributes.ATTACK_SPEED, emblemItem.getUniqueID(),
                        "Assassin Emblem Bonus", ASSASSIN_EMBLEM_ATTACK_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Assassin Emblem Bonus", ASSASSIN_EMBLEM_ATTACK_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "random_emblem":
                applyStaticModifier(entity, ModAttributes.RANDOM.get(), emblemItem.getUniqueID(),
                        "Random Emblem Bonus", 1, AttributeModifier.Operation.ADDITION);
                break;

            case "dasher_emblem":
                applyStaticModifier(entity, ModAttributes.AIR_DASHER.get(), emblemItem.getUniqueID(),
                        "Dasher Emblem Bonus", 1, AttributeModifier.Operation.ADDITION);
                break;
            case "sky_shredding_emblem":
                applyStaticModifier(entity, ModAttributes.AIR_DASHER.get(), emblemItem.getUniqueID(),
                        "Sky Shredding Emblem Bonus", 2.5, AttributeModifier.Operation.ADDITION);
                break;
            case "phoenix_emblem":
                applyStaticModifier(entity, ModAttributes.PHOENIX.get(), emblemItem.getUniqueID(),
                        "Phoenix Emblem Bonus", 1, AttributeModifier.Operation.ADDITION);
                break;
            case "phoenix_wing_emblem":
                applyStaticModifier(entity, ModAttributes.PHOENIX_FLIGHT.get(), emblemItem.getUniqueID(),
                        "Phoenix Wing Emblem Bonus", 1, AttributeModifier.Operation.ADDITION);
                break;
            case "true_phoenix_emblem":
                applyStaticModifier(entity, ModAttributes.PHOENIX_FLIGHT.get(), emblemItem.getUniqueID(),
                        "True Phoenix Emblem Bonus", 1, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.PHOENIX.get(), emblemItem.getUniqueID(),
                        "True Phoenix Emblem Bonus", 1, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.INFERNO.get(), emblemItem.getUniqueID(),
                        "True Phoenix Emblem Bonus", 2, AttributeModifier.Operation.ADDITION);
                break;
            case "mother_earth_emblem":
                applyStaticModifier(entity, ModAttributes.MOTHER_EARTH_BLESSING.get(), emblemItem.getUniqueID(),
                        "Emblem of the Mother Earth Bonus", MOTHER_EARTH_EMBLEM_MOTHER_EARTH_BLESSING_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "branch_emblem":
                applyStaticModifier(entity, ModAttributes.NATURE_HEALING.get(), emblemItem.getUniqueID(),
                        "Branch Emblem Bonus", BRANCH_EMBLEM_NATURE_HEALING_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, Attributes.MAX_HEALTH, emblemItem.getUniqueID(),
                        "Branch Emblem Bonus", BRANCH_EMBLEM_MAX_HEALTH_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "butcher_emblem":
                applyStaticModifier(entity, ModAttributes.BUTCHER_BLESSING.get(), emblemItem.getUniqueID(),
                        "Butcher Emblem Bonus", BUTCHER_EMBLEM_BUTCHER_BLESSING_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "skin_transplant_emblem":
                applyStaticModifier(entity, ModAttributes.SKIN_TRANSPLANTER.get(), emblemItem.getUniqueID(),
                        "Skin Transplant Emblem Bonus", SKIN_TRANSPLANT_EMBLEM_SKIN_TRANSPLANTER_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "archer_emblem":
                applyStaticModifier(entity, ModAttributes.ARROW_RETRIEVE.get(), emblemItem.getUniqueID(),
                        "Archer Emblem Bonus", ARCHER_EMBLEM_ARROW_RETRIEVE_BONUS, AttributeModifier.Operation.ADDITION);
                break;

            case "smelter_emblem":
                applyStaticModifier(entity, ModAttributes.SMELTING.get(), emblemItem.getUniqueID(),
                        "Smelter Emblem Bonus", SMELTER_EMBLEM_SMELTING_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "burner_emblem":
                applyStaticModifier(entity, ModAttributes.BLOCK_BURNING.get(), emblemItem.getUniqueID(),
                        "Burner Emblem Bonus", BURNER_EMBLEM_BLOCK_BURNING_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "smoking_emblem":
                applyStaticModifier(entity, ModAttributes.SMOKING.get(), emblemItem.getUniqueID(),
                        "Smoking Emblem Bonus", SMOKING_EMBLEM_SMOKING_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "boulder_emblem":
                applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, emblemItem.getUniqueID(),
                        "Boulder Emblem Bonus", BOULDER_EMBLEM_DAMAGE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "iron_emblem":
                applyStaticModifier(entity, Attributes.ATTACK_SPEED, emblemItem.getUniqueID(),
                        "Iron Emblem Bonus", IRON_EMBLEM_ATTACK_SPEED_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "untuned_emblem":
                applyStaticModifier(entity, ModAttributes.UNTUNED.get(), emblemItem.getUniqueID(),
                        "Untuned Emblem Bonus", UNTUNED_EMBLEM_UNTUNED_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "rhythm_emblem":
                applyStaticModifier(entity, ModAttributes.TUNED.get(), emblemItem.getUniqueID(),
                        "Rhythm Emblem Bonus", RHYTHM_EMBLEM_TUNED_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "song_emblem":
                applyStaticModifier(entity, ModAttributes.SONG.get(), emblemItem.getUniqueID(),
                        "Song Emblem Bonus", SONG_EMBLEM_SONG_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "anthem_emblem":
                applyStaticModifier(entity, ModAttributes.ANTHEM.get(), emblemItem.getUniqueID(),
                        "Anthem Emblem Bonus", ANTHEM_EMBLEM_ANTHEM_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "independent_emblem":
                applyStaticModifier(entity, ModAttributes.I_ANTHEM.get(), emblemItem.getUniqueID(),
                        "Independent Emblem Bonus", INDEPENDENT_EMBLEM_I_ANTHEM_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "false_note_emblem":
                applyStaticModifier(entity, ModAttributes.UNTUNED.get(), emblemItem.getUniqueID(),
                        "False Note Emblem Bonus", FALSE_NOTE_EMBLEM_UNTUNED_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "lullaby_emblem":
                applyStaticModifier(entity, ModAttributes.LULLABY.get(), emblemItem.getUniqueID(),
                        "Lullaby Emblem Bonus", LULLABY_EMBLEM_LULLABY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "beat_emblem":
                applyStaticModifier(entity, ModAttributes.BEAT.get(), emblemItem.getUniqueID(),
                        "Beat Emblem Bonus", BEAT_EMBLEM_BEAT_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "paper_emblem":
                applyStaticModifier(entity, ModAttributes.PAPER.get(), emblemItem.getUniqueID(),
                        "Paper Emblem Bonus", PAPER_EMBLEM_PAPER_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "cloth_emblem":
                applyStaticModifier(entity, Attributes.KNOCKBACK_RESISTANCE, emblemItem.getUniqueID(),
                        "Cloth Emblem Bonus", CLOTH_EMBLEM_KNOCKBACK_RESISTANCE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "soil_emblem":
                applyStaticModifier(entity, ModAttributes.RICH_SOIL.get(), emblemItem.getUniqueID(),
                        "Soil Emblem Bonus", SOIL_EMBLEM_RICH_SOIL_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "rich_soil_emblem":
                applyStaticModifier(entity, ModAttributes.RICH_SOIL.get(), emblemItem.getUniqueID(),
                        "Rich Soil Emblem Bonus", RICH_SOIL_EMBLEM_RICH_SOIL_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "nutritious_emblem":
                applyStaticModifier(entity, ModAttributes.NUTRITIOUS_CROPS.get(), emblemItem.getUniqueID(),
                        "Nutritious Emblem Bonus", NUTRITIOUS_EMBLEM_NUTRITIOUS_CROPS_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "itemium_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Itemium Emblem Bonus", ITEMIUM_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "coin_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Coin Emblem Bonus", COIN_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "finders_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Finders Emblem Bonus", FINDERS_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "happy_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Happy Emblem Bonus", HAPPY_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "lucky_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Lucky Emblem Bonus", LUCKY_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "prayers_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Prayers Emblem Bonus", PRAYERS_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "leprechauns_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Leprechauns Emblem Bonus", LEPRECHAUNS_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "lottery_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Lottery Emblem Bonus", LOTTERY_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "trained_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Trained Emblem Bonus", TRAINED_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "expert_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Expert Emblem Bonus", EXPERT_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "master_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Master Emblem Bonus", MASTER_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "champion_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Champion Emblem Bonus", CHAMPION_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "winner_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Winner Emblem Bonus", WINNER_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "rule_master_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Rule Master Emblem Bonus", RULE_MASTER_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "foreseen_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Foreseen Emblem Bonus", FORESEEN_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "the_truth_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Emblem Of The Truth Bonus", THE_TRUTH_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "creator_blessed_emblem":
                applyStaticModifier(entity, ModAttributes.LUCKY.get(), emblemItem.getUniqueID(),
                        "Emblem Of The Creator's Blessing Bonus", CREATOR_BLESSED_EMBLEM_LUCKY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "anvil_emblem":
                applyStaticModifier(entity, ModAttributes.ANVIL.get(), emblemItem.getUniqueID(),
                        "Anvil Emblem Bonus", ANVIL_EMBLEM_ANVIL_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "blacksmith_emblem":
                applyStaticModifier(entity, ModAttributes.ANVIL.get(), emblemItem.getUniqueID(),
                        "Blacksmith Emblem Bonus", BLACKSMITH_EMBLEM_ANVIL_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "forger_emblem":
                applyStaticModifier(entity, ModAttributes.ANVIL.get(), emblemItem.getUniqueID(),
                        "Forger Emblem Bonus", FORGER_EMBLEM_ANVIL_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "god_forge_emblem":
                applyStaticModifier(entity, ModAttributes.ANVIL.get(), emblemItem.getUniqueID(),
                        "God Forge Emblem Bonus", GOD_FORGE_EMBLEM_ANVIL_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "hephaestus_emblem":
                applyStaticModifier(entity, ModAttributes.ANVIL.get(), emblemItem.getUniqueID(),
                        "Hephaestus Emblem Bonus", HEPHAESTUS_EMBLEM_ANVIL_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "farmer_apprentice_emblem":
                applyStaticModifier(entity, ModAttributes.FARMING.get(), emblemItem.getUniqueID(),
                        "Farmer Apprentice Emblem Bonus", FARMER_APPRENTICE_EMBLEM_FARMING_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "snow_emblem":
                applyStaticModifier(entity, ModAttributes.SNOW_WALK.get(), emblemItem.getUniqueID(),
                        "Snow Emblem Bonus", SNOW_EMBLEM_SNOW_WALK_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "icicle_emblem":
                applyStaticModifier(entity, ModAttributes.ICICLE.get(), emblemItem.getUniqueID(),
                        "Icicle Emblem Bonus", ICICLE_EMBLEM_ICICLE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "rink_emblem":
                applyStaticModifier(entity, ModAttributes.RINK.get(), emblemItem.getUniqueID(),
                        "Rink Emblem Bonus", RINK_EMBLEM_RINK_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "yuki_emblem":
                applyStaticModifier(entity, ModAttributes.YUKI.get(), emblemItem.getUniqueID(),
                        "Yuki Emblem Bonus", YUKI_EMBLEM_YUKI_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "magnetite_emblem":
                applyStaticModifier(entity, ModAttributes.MAGNET.get(), emblemItem.getUniqueID(),
                        "Magnetite Emblem Bonus", MAGNETITE_EMBLEM_MAGNET_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "electromagnet_emblem":
                applyStaticModifier(entity, ModAttributes.MAGNET.get(), emblemItem.getUniqueID(),
                        "Electromagnet Emblem Bonus", ELECTROMAGNET_EMBLEM_MAGNET_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "pulse_emblem":
                applyStaticModifier(entity, ModAttributes.PULSE.get(), emblemItem.getUniqueID(),
                        "Pulse Emblem Bonus", PULSE_EMBLEM_PULSE_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "lightning_emblem":
                applyStaticModifier(entity, ModAttributes.LIGHTNING_ASPECT.get(), emblemItem.getUniqueID(),
                        "Lightning Emblem Bonus", LIGHTNING_EMBLEM_LIGHTNING_ASPECT_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "underworld_emblem":
                applyStaticModifier(entity, ModAttributes.FORTUNE_FEVER.get(), emblemItem.getUniqueID(),
                        "Underworld Emblem Bonus", UNDERWORLD_EMBLEM_FORTUNE_FEVER_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "apple_emblem":
                applyStaticModifier(entity, ModAttributes.LEAF_LUCK.get(), emblemItem.getUniqueID(),
                        "Apple Emblem Bonus", APPLE_EMBLEM_LEAF_LUCK_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "moondew_emblem":
                applyStaticModifier(entity, ModAttributes.FARMERS_BLESSING.get(), emblemItem.getUniqueID(),
                        "Moondew Emblem Bonus", MOONDEW_EMBLEM_FARMERS_BLESSING_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.NIGHT_CROPS.get(), emblemItem.getUniqueID(),
                        "Moondew Emblem Bonus", MOONDEW_EMBLEM_NIGHT_CROPS_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "eclipse_emblem":
                applyStaticModifier(entity, ModAttributes.PHOTOSYNTHESIS.get(), emblemItem.getUniqueID(),
                        "Eclipse Emblem Bonus", ECLIPSE_EMBLEM_PHOTOSYNTHESIS_BONUS, AttributeModifier.Operation.ADDITION);
                applyStaticModifier(entity, ModAttributes.LUNARSYNTHESIS.get(), emblemItem.getUniqueID(),
                        "Eclipse Emblem Bonus", ECLIPSE_EMBLEM_LUNARSYNTHESIS_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "luau_emblem":
                applyStaticModifier(entity, ModAttributes.LUAU.get(), emblemItem.getUniqueID(),
                        "Luau Emblem Bonus", LUAU_EMBLEM_LUAU_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "hermes_emblem":
                applyStaticModifier(entity, ModAttributes.LUNG_CAPACITY.get(), emblemItem.getUniqueID(),
                        "Hermes Emblem Bonus", HERMES_EMBLEM_LUNG_CAPACITY_BONUS, AttributeModifier.Operation.ADDITION);
                break;
            case "volcano_emblem":
                applyStaticModifier(entity, ModAttributes.VOLCANIC.get(), emblemItem.getUniqueID(),
                        "Volcano Emblem Bonus", VOLCANO_EMBLEM_VOLCANIC_BONUS, AttributeModifier.Operation.ADDITION);
                break;


        }
    }

    public static void provideOnUnequipEmblemEffect(ModEmblemItem emblemItem, LivingEntity entity) {
        UUID id = emblemItem.getUniqueID();
        for (AttributeInstance attribute : entity.getAttributes().getSyncableAttributes()){
            removeModifierIfPresent(entity, attribute.getAttribute(), id);
            removeModifierIfPresent(entity, attribute.getAttribute(), getEffectUUID(emblemItem, "OnHungerBonus"));
            removeModifierIfPresent(entity, attribute.getAttribute(), getEffectUUID(emblemItem, "OnHeightBonus"));
            removeModifierIfPresent(entity, attribute.getAttribute(), getEffectUUID(emblemItem, "OnStalagmite"));
            removeModifierIfPresent(entity, attribute.getAttribute(), getEffectUUID(emblemItem, "OnStalactite"));
            removeModifierIfPresent(entity, attribute.getAttribute(), getEffectUUID(emblemItem, "OnSandBonus"));

        }

        String name = emblemItem.toString();
        switch (name) {
            case "fluorescent_emblem" -> {
                entity.removeEffect(MobEffects.NIGHT_VISION);
            }
            case "magma_emblem", "supernova_emblem", "bloodstar_emblem" -> {
                entity.removeEffect(MobEffects.FIRE_RESISTANCE);
            }
            case "diamonds_emblem" -> {
                entity.removeEffect(MobEffects.JUMP);
            }
            case "petrified_dandelion_emblem" -> {
                entity.removeEffect(MobEffects.SLOW_FALLING);
            }
            case "petrified_vege_emblem" -> {
                entity.removeEffect(MobEffects.SATURATION);
            }
            case "sunflower_emblem" -> {
                IS_FORCED_DAY = false;
            }
            case "nightbloom_emblem" -> {
                IS_FORCED_NIGHT = false;
            }
            case  "flowerforest_emblem" -> {
                IS_FORCED_NIGHT = false;
                IS_FORCED_DAY = false;
            }
            case "sprinkler_emblem" -> {
                IS_FORCED_RAIN = false;
                removeModifierIfPresent(entity, ModAttributes.SPRINKLERS.get(), id);
            }
            case "forbidden_oasis_emblem" -> {
                IS_FORCED_RAIN = false;
            }
            case "glider_emblem" -> removeFlightBonus(entity, emblemItem, GLIDER_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS);
            case "feather_emblem" -> removeFlightBonus(entity, emblemItem, FEATHER_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS);
            case "wind_catcher_emblem" -> removeFlightBonus(entity, emblemItem, WIND_CATCHER_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS);
            case "humming_bird_emblem" -> removeFlightBonus(entity, emblemItem, HUMMING_BIRD_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS);
            case "mountain_wind_emblem" -> removeFlightBonus(entity, emblemItem, MOUNTAIN_WIND_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS);
            case "weightless_emblem" -> removeFlightBonus(entity, emblemItem, WEIGHTLESS_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS);
            case "wing_power_emblem" -> removeFlightBonus(entity, emblemItem, WING_POWER_EMBLEM_FLIGHT_TIME_MULTIPLIER_BONUS);
            case "sky_wanderer_emblem" -> resetSkyWandererFlight(entity);
            case "rink_emblem"-> PacketHandler.sendToServer(new RinkPacket(entity, true));
            case "random_emblem"-> PacketHandler.sendToServer(new RandomPacket(true));
        }
    }
    private static void removeFlightBonus(LivingEntity entity, ModEmblemItem emblemItem, float bonus) {
        if (!ModList.get().isLoaded("rupecs_elytras") || !(entity instanceof Player player)) return;
        player.getCapability(FlightTimeDataProvider.PLAYER_FLIGHT_TIME_DATA).ifPresent(data -> {
            if (data.canDisplay()) {
                data.getFlightTimeEntry().removeConstantMaxFlightTimeMultiplier(bonus, emblemItem.getUniqueID());
            }
        });
    }

    private static void resetSkyWandererFlight(LivingEntity entity) {
        if (!ModList.get().isLoaded("rupecs_elytras") || !(entity instanceof Player player)) return;
        player.getCapability(FlightTimeDataProvider.PLAYER_FLIGHT_TIME_DATA).ifPresent(data -> {
            if (data.canDisplay()) {
                float max = 0;
                if (entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ModElytraItem elytraItem) {
                    max = elytraItem.maxFlightTimeTicks;
                }
                PlayerFlightTimeEntry entry = data.getPlayerFlightTimeEntry().copy();
                entry.setMaxFlightTime(max);
                data.setPlayerFlightTimeEntry(entry);
            }
        });
    }
/*
        switch (emblemItem.toString()) {
            case "moon_emblem":
            case "full_moon_emblem":
            case "blood_moon_emblem":
                removeModifierIfPresent(entity, ForgeMod.ENTITY_GRAVITY.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MOON_FAVOR.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MOON_WRATH.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MOON_FEET.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                if (emblemItem.toString().equals("blood_moon_emblem")){
                    removeModifierIfPresent(entity, ModAttributes.MOON_JUMP.get(), id);
                }
                break;
            case "wood_emblem":
            case "heart_emblem":
                handleHealthEmblemUnequip(entity, id);
                break;
            case "stone_emblem", "spade_emblem", "red_spider_lilly_emblem", "blue_spider_lilly_emblem",
                 "boulder_emblem":
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                break;
            case "ground_emblem":
            case "knight_emblem":
                removeModifierIfPresent(entity, Attributes.ATTACK_SPEED, id);
                break;
            case "sand_emblem":
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "SandOnSandBonus"));
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                break;
            case "dune_emblem":
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "DuneOnSandBonus"));
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "OnHungerBonus"));
                break;
            case "dust_emblem", "clover_emblem", "rain_emblem", "downhill_emblem":
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "OnHeightBonus"));
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "OnHungerBonus"));
                break;
            case "plant_emblem":
                removeModifierIfPresent(entity, ModAttributes.NATURE_HEALING.get(), id);
                break;
            case "fossil_emblem":
                removeModifierIfPresent(entity, ModAttributes.MINING_SPEED.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                break;
            case "remains_emblem":
                removeModifierIfPresent(entity, ModAttributes.MINING_SPEED.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "RemainsOnSandBonus"));
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                break;
            case "fire_emblem":
                removeModifierIfPresent(entity, ModAttributes.FIRE_FIST.get(), id);
                break;
            case "crystal_emblem":
                removeModifierIfPresent(entity, ModAttributes.FORTUNE_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.FRAGILE_BODY.get(), id);
                break;
            case "sky_emblem":
                removeModifierIfPresent(entity, ForgeMod.ENTITY_GRAVITY.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, ModAttributes.FEATHER_FEET.get(), id);
                break;
            case "night_emblem":
            case "sun_emblem":
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                if (emblemItem.toString().equals("sun_emblem")) {
                    removeModifierIfPresent(entity, ModAttributes.PHOTOSYNTHESIS.get(), id);
                }
                break;
            case "ice_emblem":
                removeModifierIfPresent(entity, ModAttributes.FROST_FIST.get(), id);
                break;
            case "vege_emblem":
                removeModifierIfPresent(entity, ModAttributes.VEGE.get(), id);
                removeModifierIfPresent(entity, ModAttributes.ANIMAL_TEMPTATION.get(), id);
                removeModifierIfPresent(entity, ModAttributes.UNDERFED.get(), id);
                break;
            case "farmer_emblem":
                removeModifierIfPresent(entity, ModAttributes.FARMERS_BLESSING.get(), id);
                break;
            case "builder_emblem":
                removeModifierIfPresent(entity, ForgeMod.BLOCK_REACH.get(), id);
                break;
            case "flower_emblem":
                removeModifierIfPresent(entity, ModAttributes.PHOTOSYNTHESIS.get(), id);
                break;
            case "bone_emblem", "warrior_emblem":
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                break;
            case "inferno_emblem":
                removeModifierIfPresent(entity, ModAttributes.INFERNO.get(), id);
                break;
                removeModifierIfPresent(entity, ModAttributes.INFERNO.get(), id);
                removeModifierIfPresent(entity, ModAttributes.PHOTOSYNTHESIS.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, ModAttributes.FIRE_FIST.get(), id);
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                break;
            case "moon_jumper_emblem":
                removeModifierIfPresent(entity, ModAttributes.MOON_JUMP.get(), id);
                break;

                removeModifierIfPresent(entity, ForgeMod.ENTITY_GRAVITY.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MOON_FAVOR.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MOON_WRATH.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MOON_FEET.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                removeModifierIfPresent(entity, ModAttributes.MOON_JUMP.get(), id);
                removeModifierIfPresent(entity, ModAttributes.FIRE_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.PHOTOSYNTHESIS.get(), id);
                removeModifierIfPresent(entity, ModAttributes.INFERNO.get(), id);
                break;

            case "poop_emblem":
                removeModifierIfPresent(entity, ModAttributes.POOP.get(), id);
                break;
            case "merchant_emblem":
                removeModifierIfPresent(entity, ModAttributes.MERCHANT.get(), id);
                break;
            case "rainforest_emblem":
            case "tree_emblem":
                removeModifierIfPresent(entity, ModAttributes.PHOTOSYNTHESIS.get(), id);
                removeModifierIfPresent(entity, ModAttributes.NATURE_HEALING.get(), id);
                removeModifierIfPresent(entity, Attributes.ATTACK_SPEED, id);
                handleHealthEmblemUnequip(entity, id);

                break;
            case "cave_emblem":
                removeModifierIfPresent(entity, ModAttributes.FRAGILE_BODY.get(), id);
                removeModifierIfPresent(entity, ModAttributes.FORTUNE_FIST.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);


                break;
            case "desert_cave_emblem":
                removeModifierIfPresent(entity, ModAttributes.FRAGILE_BODY.get(), id);
                removeModifierIfPresent(entity, ModAttributes.FORTUNE_FIST.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "RemainsOnSandBonus"));
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                removeModifierIfPresent(entity, ModAttributes.MINING_SPEED.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, id);

                break;
            case "lush_cave_emblem":
                removeModifierIfPresent(entity, ModAttributes.FRAGILE_BODY.get(), id);
                removeModifierIfPresent(entity, ModAttributes.FORTUNE_FIST.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                removeModifierIfPresent(entity, ForgeMod.ENTITY_REACH.get(), id);
                removeModifierIfPresent(entity, ModAttributes.SNEAKY_DEFENCE.get(), id);

                break;

            case "water_emblem":
                removeModifierIfPresent(entity, ModAttributes.RESPIRATION.get(), id);
                removeModifierIfPresent(entity, ForgeMod.SWIM_SPEED.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MINING_SPEED.get(), id);
                break;
            case "blizzard_emblem":
                removeModifierIfPresent(entity, ModAttributes.BLIZZARD.get(), id);
                break;
            case "frost_emblem":
                removeModifierIfPresent(entity, ModAttributes.FROST_WALKER.get(), id);
                break;
            case "snowstorm_emblem":
                removeModifierIfPresent(entity, ModAttributes.BLIZZARD.get(), id);
                removeModifierIfPresent(entity, ModAttributes.FROST_FIST.get(), id);
                break;
            case "spark_emblem":
                removeModifierIfPresent(entity, ModAttributes.STATIC.get(), id);
                break;
            case "conductor_emblem":
                removeModifierIfPresent(entity, ModAttributes.CONDUCTOR.get(), id);
                break;
            case "coiled_emblem":
                removeModifierIfPresent(entity, ModAttributes.COILED.get(), id);
                break;
            case "day_emblem":
                removeModifierIfPresent(entity, ModAttributes.MINING_SPEED.get(), id);
                break;
            case "heaven_emblem":
                removeModifierIfPresent(entity, ForgeMod.ENTITY_GRAVITY.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, ModAttributes.FEATHER_FEET.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, ModAttributes.MINING_SPEED.get(), id);
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                break;
            case "return_emblem":
                removeModifierIfPresent(entity, ModAttributes.RETURN.get(), id);
                break;
            case "rotten_emblem":
                removeModifierIfPresent(entity, ModAttributes.ROTTEN.get(), id);
                break;
            case "golden_emblem":
                removeModifierIfPresent(entity, ModAttributes.GOLDEN_FAVOR.get(), id);
                break;
            case "electro_emblem":
                removeModifierIfPresent(entity, ModAttributes.ELECTRO_FIST.get(), id);
                break;
            case "gambler_emblem":
                removeModifierIfPresent(entity, ModAttributes.GAMBLER.get(), id);
                break;
            case "smoke_emblem":
                removeModifierIfPresent(entity, ModAttributes.VANISH.get(), id);
                break;
            case "easter_eggblem":
                removeModifierIfPresent(entity, ModAttributes.EGGING.get(), id);
                break;
            case "steam_emblem":
                removeModifierIfPresent(entity, ModAttributes.STEAM.get(), id);
                break;
            case "auric_emblem":
                removeModifierIfPresent(entity, ModAttributes.STEAM.get(), id);
                removeModifierIfPresent(entity, ModAttributes.INFERNO.get(), id);
                removeModifierIfPresent(entity, ModAttributes.COILED.get(), id);
                removeModifierIfPresent(entity, ModAttributes.BLIZZARD.get(), id);
                break;
            case "induction_emblem":
                removeModifierIfPresent(entity, ModAttributes.STATIC.get(), id);
                removeModifierIfPresent(entity, ModAttributes.CONDUCTOR.get(), id);
                break;
            case "overcharged_emblem":
                removeModifierIfPresent(entity, ModAttributes.COILED.get(), id);
                removeModifierIfPresent(entity, ModAttributes.ELECTRO_FIST.get(), id);
                break;
            case "thunder_emblem":
                removeModifierIfPresent(entity, ModAttributes.COILED.get(), id);
                removeModifierIfPresent(entity, ModAttributes.ELECTRO_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.STATIC.get(), id);
                removeModifierIfPresent(entity, ModAttributes.CONDUCTOR.get(), id);
                removeModifierIfPresent(entity, ModAttributes.THUNDERCLAP.get(), id);
                break;
            case "bubble_emblem":
                removeModifierIfPresent(entity, ModAttributes.BUBBLE_FIST.get(), id);
                break;
            case "barterer_emblem":
                removeModifierIfPresent(entity, ModAttributes.BARTERER.get(), id);
                break;
            case "hourglass_emblem":
                removeModifierIfPresent(entity, ModAttributes.TIME_CONTROL.get(), id);
                break;
            case "mirror_emblem":
                removeModifierIfPresent(entity, ModAttributes.MIRRORING.get(), id);
                removeModifierIfPresent(entity, ModAttributes.DAMAGE_REFLECTION.get(), id);
                break;
            case "tank_emblem":
            case "ruthless_guardian_emblem":
                handleHealthEmblemUnequip(entity, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                break;
            case "pyro_emblem":
                removeModifierIfPresent(entity, ModAttributes.MAGMA_WALKER.get(), id);
                break;
            case "soul_emblem":
                removeModifierIfPresent(entity, ModAttributes.SOUL_CONNECTOR.get(), id);
                break;
            case "moon_wrath_emblem":
                removeModifierIfPresent(entity, ModAttributes.MOON_WRATH.get(), id);
                break;
            case "orchid_emblem":
                removeModifierIfPresent(entity, ModAttributes.PHOTOSYNTHESIS.get(), id);
                removeModifierIfPresent(entity, ModAttributes.FARMERS_BLESSING.get(), id);
                break;
            case "cactus_emblem":
                removeModifierIfPresent(entity, ModAttributes.THORNS.get(), id);
                break;
            case "bamboo_emblem":
                removeModifierIfPresent(entity, ForgeMod.ENTITY_REACH.get(), id);
                break;
            case "ivy_emblem":
                removeModifierIfPresent(entity, ModAttributes.SNEAKY_DEFENCE.get(), id);
                break;
            case "rose_emblem":
                removeModifierIfPresent(entity, ModAttributes.BLEEDING_FIST.get(), id);
                break;
            case "ibara_emblem":
                removeModifierIfPresent(entity, ModAttributes.BLEEDING_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.THORNS.get(), id);
                break;
            case "black_rose_emblem":
                removeModifierIfPresent(entity, ModAttributes.WITHER_FIST.get(), id);
                break;
            case "dried_rose_emblem":
                removeModifierIfPresent(entity, ModAttributes.WITHERER.get(), id);
                break;
            case "wither_rose_emblem":
                removeModifierIfPresent(entity, ModAttributes.WITHER_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.WITHERER.get(), id);
                break;
            case "bouquet_emblem":
                removeModifierIfPresent(entity, ModAttributes.PHOTOSYNTHESIS.get(), id);
                removeModifierIfPresent(entity, ModAttributes.BLEEDING_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.FARMERS_BLESSING.get(), id);
                break;
            case "vine_emblem":
                removeModifierIfPresent(entity, ForgeMod.ENTITY_REACH.get(), id);
                removeModifierIfPresent(entity, ModAttributes.SNEAKY_DEFENCE.get(), id);
                break;
            case "prickle_vine_emblem":
                removeModifierIfPresent(entity, ForgeMod.ENTITY_REACH.get(), id);
                removeModifierIfPresent(entity, ModAttributes.SNEAKY_DEFENCE.get(), id);
                removeModifierIfPresent(entity, ModAttributes.THORNS.get(), id);
                removeModifierIfPresent(entity, ModAttributes.BLEEDING_FIST.get(), id);
                break;
            case "flowerfield_emblem":
                removeModifierIfPresent(entity, ForgeMod.ENTITY_REACH.get(), id);
                removeModifierIfPresent(entity, ModAttributes.SNEAKY_DEFENCE.get(), id);
                removeModifierIfPresent(entity, ModAttributes.THORNS.get(), id);
                removeModifierIfPresent(entity, ModAttributes.BLEEDING_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.PHOTOSYNTHESIS.get(), id);
                removeModifierIfPresent(entity, ModAttributes.FARMERS_BLESSING.get(), id);
                break;

            case "deadforest_emblem":
                removeModifierIfPresent(entity, ModAttributes.PHOTOSYNTHESIS.get(), id);
                removeModifierIfPresent(entity, ModAttributes.NATURE_HEALING.get(), id);
                removeModifierIfPresent(entity, ModAttributes.WITHER_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.WITHERER.get(), id);
                removeModifierIfPresent(entity, Attributes.ATTACK_SPEED, id);
                handleHealthEmblemUnequip(entity, id);
                break;
            case "flowerforest_emblem":
                removeModifierIfPresent(entity, ModAttributes.PHOTOSYNTHESIS.get(), id);
                removeModifierIfPresent(entity, ModAttributes.NATURE_HEALING.get(), id);
                removeModifierIfPresent(entity, ModAttributes.WITHER_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.WITHERER.get(), id);
                removeModifierIfPresent(entity, Attributes.ATTACK_SPEED, id);
                handleHealthEmblemUnequip(entity, id);
                removeModifierIfPresent(entity, ForgeMod.ENTITY_REACH.get(), id);
                removeModifierIfPresent(entity, ModAttributes.SNEAKY_DEFENCE.get(), id);
                removeModifierIfPresent(entity, ModAttributes.THORNS.get(), id);
                removeModifierIfPresent(entity, ModAttributes.BLEEDING_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.FARMERS_BLESSING.get(), id);
                IS_FORCED_DAY = false;
                IS_FORCED_NIGHT = false;
                break;
            case "dandelion_emblem":
                removeModifierIfPresent(entity, ModAttributes.DANDELION_FALL.get(), id);
                break;
            case "mist_emblem":
                removeModifierIfPresent(entity, ModAttributes.MIST.get(), id);
                break;

            case "tide_emblem":
                removeModifierIfPresent(entity, ModAttributes.TIDAL_FIST.get(), id);
                break;

            case "sprinkler_emblem":
                IS_FORCED_RAIN = false;
                removeModifierIfPresent(entity, ModAttributes.SPRINKLERS.get(), id);
                break;

            case "tear_emblem":
                removeModifierIfPresent(entity, ModAttributes.SCHADENFREUDE.get(), id);
                break;
            case "puddle_emblem":
                removeModifierIfPresent(entity, ModAttributes.PUFF.get(), id);
                break;
            case "mud_emblem":
                removeModifierIfPresent(entity, ModAttributes.MUDDY_FIST.get(), id);
                break;
            case "clay_emblem":
                removeModifierIfPresent(entity, ModAttributes.MUDDY_FIST.get(), id);
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                break;
            case "marsh_emblem":
                removeModifierIfPresent(entity, ModAttributes.MUDDY_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.STEAM.get(), id);
                break;
            case "bog_emblem":
                removeModifierIfPresent(entity, ModAttributes.MIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.PUFF.get(), id);
                break;
            case "wave_emblem":
                removeModifierIfPresent(entity, ModAttributes.RESPIRATION.get(), id);
                removeModifierIfPresent(entity, ForgeMod.SWIM_SPEED.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MINING_SPEED.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                break;
            case "whirl_emblem":
                removeModifierIfPresent(entity, ModAttributes.BUBBLE_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.TIDAL_FIST.get(), id);
                break;
            case "vortex_emblem", "maelstrom_emblem":
                removeModifierIfPresent(entity, ModAttributes.BUBBLE_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.TIDAL_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.SCHADENFREUDE.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MOON_FAVOR.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                break;
            case "swamp_emblem":
                removeModifierIfPresent(entity, ModAttributes.MUDDY_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.STEAM.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.PUFF.get(), id);
                break;
            case "current_emblem":
                removeModifierIfPresent(entity, ModAttributes.RESPIRATION.get(), id);
                removeModifierIfPresent(entity, ModAttributes.SPRINKLERS.get(), id);
                removeModifierIfPresent(entity, ForgeMod.SWIM_SPEED.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MINING_SPEED.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                break;
            case "muckmire_emblem":
                removeModifierIfPresent(entity, ModAttributes.RESPIRATION.get(), id);
                removeModifierIfPresent(entity, ModAttributes.SPRINKLERS.get(), id);
                removeModifierIfPresent(entity, ForgeMod.SWIM_SPEED.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MINING_SPEED.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, ModAttributes.MUDDY_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.STEAM.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.PUFF.get(), id);
                break;
            case "forbidden_oasis_emblem":
                IS_FORCED_RAIN = false;
                removeModifierIfPresent(entity, ModAttributes.RESPIRATION.get(), id);
                removeModifierIfPresent(entity, ModAttributes.SPRINKLERS.get(), id);
                removeModifierIfPresent(entity, ForgeMod.SWIM_SPEED.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MINING_SPEED.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, ModAttributes.MUDDY_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.STEAM.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.PUFF.get(), id);
                removeModifierIfPresent(entity, ModAttributes.BUBBLE_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.TIDAL_FIST.get(), id);
                removeModifierIfPresent(entity, ModAttributes.SCHADENFREUDE.get(), id);
                removeModifierIfPresent(entity, ModAttributes.MOON_FAVOR.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                break;

            case "florist_emblem":
                removeModifierIfPresent(entity, ModAttributes.FLORIST.get(), id);
                break;

            case "gladiator_emblem":
            case "assassin_emblem":
                removeModifierIfPresent(entity, Attributes.ATTACK_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                break;
            case "random_emblem":
                removeModifierIfPresent(entity, ModAttributes.RANDOM.get(), id);
                RandomHandlerCommon.shrinkEmblemList((Player) entity);
                break;


            case "mother_earth_emblem":
                removeModifierIfPresent(entity, ModAttributes.MOTHER_EARTH_BLESSING.get(), id);
                break;


            case "branch_emblem":
                removeModifierIfPresent(entity, ModAttributes.NATURE_HEALING.get(), id);
                removeModifierIfPresent(entity, Attributes.MAX_HEALTH, id);
                break;


            case "butcher_emblem":
                removeModifierIfPresent(entity, ModAttributes.BUTCHER_BLESSING.get(), id);
                break;


            case "skin_transplant_emblem":
                removeModifierIfPresent(entity, ModAttributes.SKIN_TRANSPLANTER.get(), id);
                break;
            case "archer_emblem":
                removeModifierIfPresent(entity, ModAttributes.ARROW_RETRIEVE.get(), id);
                break;
            case "smelter_emblem":
                removeModifierIfPresent(entity, ModAttributes.SMELTING.get(), id);
                break;
            case "burner_emblem":
                removeModifierIfPresent(entity, ModAttributes.BLOCK_BURNING.get(), id);
                break;
            case "smoking_emblem":
                removeModifierIfPresent(entity, ModAttributes.SMOKING.get(), id);
                break;
            case "stalagmite_emblem":
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, getEffectUUID(emblemItem, "OnStalagmite"));

                break;
            case "stalagnate_emblem":
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, getEffectUUID(emblemItem, "OnStalagmite"));
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, getEffectUUID(emblemItem, "OnStalactite"));
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                break;
            case "stalactite_emblem":
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, getEffectUUID(emblemItem, "OnStalactite"));
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                break;
            case "hill_emblem":
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "HillOnSandBonus"));
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, getEffectUUID(emblemItem, "OnHeightBonus"));
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "OnHungerBonus"));
                break;
            case "mountain_emblem":
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "MountainOnSandBonus"));
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, getEffectUUID(emblemItem, "OnHeightBonus"));
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "OnHungerBonus"));
            case "mountain_range_emblem":
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "MountainRangeOnSandBonus"));
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, getEffectUUID(emblemItem, "OnHeightBonus"));
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "OnHungerBonus"));
            case "stalactite_cave_emblem":
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, getEffectUUID(emblemItem, "OnHeightBonus"));
                removeModifierIfPresent(entity, Attributes.ARMOR, getEffectUUID(emblemItem, "OnStalagmite"));
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, getEffectUUID(emblemItem, "OnStalactite"));
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                removeModifierIfPresent(entity, ModAttributes.FRAGILE_BODY.get(), id);
                removeModifierIfPresent(entity, ModAttributes.FORTUNE_FIST.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                break;
            case "lifeless_cave_emblem":
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, getEffectUUID(emblemItem, "OnHeightBonus"));

                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, getEffectUUID(emblemItem, "OnStalactite"));
                removeModifierIfPresent(entity, Attributes.ARMOR, getEffectUUID(emblemItem, "OnStalagmite"));
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                removeModifierIfPresent(entity, ModAttributes.FRAGILE_BODY.get(), id);
                removeModifierIfPresent(entity, ModAttributes.FORTUNE_FIST.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "RemainsOnSandBonus"));
                removeModifierIfPresent(entity, ModAttributes.MINING_SPEED.get(), id);
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                break;
            case "mountain_cave_emblem":
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, getEffectUUID(emblemItem, "OnHeightBonus"));
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                removeModifierIfPresent(entity, ModAttributes.FRAGILE_BODY.get(), id);
                removeModifierIfPresent(entity, ModAttributes.FORTUNE_FIST.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "MountainCaveOnSandBonus"));
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "OnHungerBonus"));
                break;
            case "radiant_cave_emblem":
                removeModifierIfPresent(entity, Attributes.ARMOR, id);
                removeModifierIfPresent(entity, Attributes.ARMOR, getEffectUUID(emblemItem, "OnHeightBonus"));
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                removeModifierIfPresent(entity, ModAttributes.FRAGILE_BODY.get(), id);
                removeModifierIfPresent(entity, ModAttributes.FORTUNE_FIST.get(), id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "RadiantCaveOnSandBonus"));
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
                removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "OnHungerBonus"));
                break;
            case "sisyphus_emblem":
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                removeModifierIfPresent(entity, Attributes.MAX_HEALTH, id);
                removeModifierIfPresent(entity, Attributes.MAX_HEALTH, getEffectUUID(emblemItem, "OnHeightBonus"));
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, getEffectUUID(emblemItem, "OnHeightBonus"));
                break;



            case "dasher_emblem":
                removeModifierIfPresent(entity, ModAttributes.AIR_DASHER.get(), emblemItem.getUniqueID());
                break;
            case "phoenix_emblem":
                removeModifierIfPresent(entity, ModAttributes.PHOENIX.get(), emblemItem.getUniqueID());
                break;
            case "phoenix_wing_emblem":
                removeModifierIfPresent(entity, ModAttributes.PHOENIX_FLIGHT.get(), emblemItem.getUniqueID());
                break;
            case "true_phoenix_emblem":
                removeModifierIfPresent(entity, ModAttributes.PHOENIX_FLIGHT.get(), emblemItem.getUniqueID());
                removeModifierIfPresent(entity, ModAttributes.PHOENIX.get(), emblemItem.getUniqueID());
                removeModifierIfPresent(entity, ModAttributes.INFERNO.get(), emblemItem.getUniqueID());
                break;

        }

 */


    // Specific emblem handlers
    private static void handleMoonEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity,
                                                boolean isNight, boolean isFullMoon, boolean isFullMoonEmblem, String name) {
        UUID id = emblemItem.getUniqueID();

        if (isNight || IS_FORCED_NIGHT) {
            double effectMultiplier = isFullMoonEmblem ? 2 : 1;
            double speedBonus = (isFullMoon ? 0.015 : 0.005) * effectMultiplier;
            double damageBonus = (isFullMoon ? 0.25 : 0.1) * effectMultiplier;

            updateAttributeModifier(entity, Attributes.MOVEMENT_SPEED, id,
                    name + " Emblem Speed", speedBonus, AttributeModifier.Operation.ADDITION);

            updateAttributeModifier(entity, Attributes.ATTACK_DAMAGE, id,
                    name + " Emblem Damage", damageBonus, AttributeModifier.Operation.ADDITION);
        } else {
            removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
            removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
        }
    }

    private static void handleMoonEmblemEquip(ModEmblemItem emblemItem, LivingEntity entity, boolean isFullMoon, String name) {
        UUID id = emblemItem.getUniqueID();
        double gravityMod = isFullMoon ?
                calculateEntityFullMoonEmblemGravity(entity) :
                calculateEntityMoonEmblemGravity(entity);
        double favorBonus = isFullMoon ? 0.1 : 0.09;
        double wrathBonus = isFullMoon ? 2 : 1;

        applyStaticModifier(entity, ForgeMod.ENTITY_GRAVITY.get(), id,
                name+" Emblem Gravity", gravityMod, AttributeModifier.Operation.ADDITION);
        applyStaticModifier(entity, ModAttributes.MOON_FAVOR.get(), id,
                name+" Emblem Favor", favorBonus, AttributeModifier.Operation.ADDITION);
        applyStaticModifier(entity, Attributes.ATTACK_DAMAGE, id,
                name+" Emblem Damage", MOON_EMBLEM_DAMAGE_BONUS * wrathBonus, AttributeModifier.Operation.ADDITION);
        applyStaticModifier(entity, ModAttributes.MOON_FEET.get(), id,
                name+" Emblem Damage", MOON_EMBLEM_MOON_FEET_BONUS * wrathBonus, AttributeModifier.Operation.ADDITION);
    }

    private static void handleSandEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity, String name, String attributeName) {
        updateAttributeModifier(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem,  attributeName),
                name + " Emblem Bonus", calculateEntitySpeedIfOnSand(entity, 0.01f, 3),
                AttributeModifier.Operation.ADDITION);
    }

    private static void handleConditionalEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity, String name, String attributeName, Attribute attribute,double base, double extended, boolean condition) {
        updateAttributeModifier(entity, attribute, getEffectUUID(emblemItem,  attributeName),
                name+" Emblem Bonus", condition ? extended: base,
                AttributeModifier.Operation.ADDITION);
    }

    private static void handleRemainsEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity, String name) {
        updateAttributeModifier(entity, Attributes.MOVEMENT_SPEED, getEffectUUID(emblemItem, "OnSandBonus"),
                name+" Emblem Bonus", calculateEntitySpeedIfOnSand(entity, 0.02f, 2),
                AttributeModifier.Operation.ADDITION);
    }

    private static void handleStalagmiteEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity, String name) {
        updateAttributeModifier(entity, Attributes.ARMOR, getEffectUUID(emblemItem, "OnStalagmite"),
                name+" Emblem Bonus", !entity.level().canSeeSky(entity.blockPosition()) ? 10 : 4,
                AttributeModifier.Operation.ADDITION);
    }
    private static void handleHungerBasedEmblemEffects(
            ModEmblemItem emblemItem,
            LivingEntity entity,
            String name,
            float factor,
            int perPoint,
            Attribute attribute
    ) {
        if (!(entity instanceof Player player)) return;

        int missing = 20 - player.getFoodData().getFoodLevel();
        if (missing < 0 || perPoint == 0)return;
        float bonus = (missing / (float) perPoint) * factor;

        updateAttributeModifier(
                entity,
                attribute,
                getEffectUUID(emblemItem, "OnHungerBonus"),
                name + " Emblem Bonus",
                bonus,
                AttributeModifier.Operation.ADDITION
        );
    }
    private static void handleHeightBasedEmblemEffects(
            ModEmblemItem emblemItem,
            LivingEntity entity,
            String name,
            float factor,
            int perPoint,
            Attribute attribute,
            boolean includeNegative
    ) {handleHeightBasedEmblemEffects(emblemItem, entity, name,factor,perPoint,attribute,includeNegative, -1);}
    private static void handleHeightBasedEmblemEffects(
            ModEmblemItem emblemItem,
            LivingEntity entity,
            String name,
            float factor,
            int perPoint,
            Attribute attribute,
            boolean includeNegative,
            float negativeFactor
    ) {
        if (!(entity instanceof Player player)) return;


        int heightPoints = player.blockPosition().getY();
        if (heightPoints <= 0 && !includeNegative) return;
        if (perPoint == 0) return;
        double bonus = ((includeNegative && heightPoints <= 0) ? (heightPoints / (float) perPoint) * negativeFactor : (heightPoints / (float) perPoint) * factor);
        if (!(attribute == Attributes.MAX_HEALTH)) {



            updateAttributeModifier(
                    entity,
                    attribute,
                    getEffectUUID(emblemItem , "OnHeightBonus"),
                    name + " Emblem Bonus",
                    bonus,
                    AttributeModifier.Operation.ADDITION
            );
        } else {

            updateAttributeModifier(
                    entity,
                    attribute,
                    getEffectUUID(emblemItem , "OnHeightBonus"),
                    name + " Emblem Bonus",
                    Math.round(bonus),
                    AttributeModifier.Operation.ADDITION);
            if (entity.getHealth() > entity.getMaxHealth()) {
                entity.setHealth(entity.getMaxHealth());
            }
        }
    }
    private static void handleStalactiteEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity, String name) {
        boolean isNearCeiling = false;
        for (int i = 2; i < 9; i++){
            if (!entity.level().getBlockState(entity.blockPosition().above(i)).is(Blocks.AIR)){
                isNearCeiling = true;
            }
        }
        updateAttributeModifier(entity, Attributes.ATTACK_DAMAGE, getEffectUUID(emblemItem, "OnStalactite"),
                name+" Emblem Bonus", isNearCeiling ? 5 : 0,
                AttributeModifier.Operation.ADDITION);
    }
    private static void handleDustEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity, String name) {
        updateAttributeModifier(entity, Attributes.MOVEMENT_SPEED, emblemItem.getUniqueID(),
                name+" Emblem Bonus", 0.005,
                AttributeModifier.Operation.ADDITION);
    }
    private static void handleCometEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity, int count) {
        if (entity.tickCount % 60 == 0) {
            for (int i = 0; i < count; i++) {
                CometShardProjectile cometShardProjectile = new CometShardProjectile(ModEntities.COMET_SHARD.get(), entity.level());
                cometShardProjectile.setOwner(entity);
                cometShardProjectile.setPos(entity.position().x, entity.position().y + 1.5, entity.position().z);
                cometShardProjectile.shoot(entity.getRandom().nextInt(), entity.getRandom().nextInt(), entity.getRandom().nextInt(), 0.5f, 0);
                entity.level().addFreshEntity(cometShardProjectile);
            }

        }
    }
    private static void handleCometEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity, int count, int delay) {
        if (entity.tickCount % delay == 0) {
            for (int i = 0; i < count; i++) {
                CometShardProjectile cometShardProjectile = new CometShardProjectile(ModEntities.COMET_SHARD.get(), entity.level());
                cometShardProjectile.setOwner(entity);
                cometShardProjectile.setPos(entity.position().x, entity.position().y + 1.5, entity.position().z);
                cometShardProjectile.shoot(entity.getRandom().nextInt(), entity.getRandom().nextInt(), entity.getRandom().nextInt(), 0.5f, 0);
                entity.level().addFreshEntity(cometShardProjectile);
            }

        }
    }

    private static void handleHeartEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity, String name) {
        float healthRatio = entity.getHealth() / entity.getMaxHealth();
        UUID id = emblemItem.getUniqueID();

        // Speed modifier - always active when below max health
        updateConditionalModifier(entity, Attributes.MOVEMENT_SPEED, id,
                name+" Emblem Speed", 0.01, AttributeModifier.Operation.ADDITION,
                healthRatio < 1.0f);

        // Damage modifier - active below 75% health
        updateConditionalModifier(entity, Attributes.ATTACK_DAMAGE, id,
                name+" Emblem Damage", 1.0, AttributeModifier.Operation.ADDITION,
                healthRatio < 0.75f);

        // Attack speed modifier - active below 50% health
        updateConditionalModifier(entity, Attributes.ATTACK_SPEED, id,
                name+" Emblem Attack Speed", 1.0, AttributeModifier.Operation.ADDITION,
                healthRatio < 0.5f);

        // Healing modifier - active below 25% health
        updateConditionalModifier(entity, ModAttributes.NATURE_HEALING.get(), id,
                name+" Emblem Healing", 3.0, AttributeModifier.Operation.ADDITION,
                healthRatio < 0.25f);
    }

    private static void handleNightEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity,
                                                 boolean isNight, boolean isFullMoon, String name) {
        UUID id = emblemItem.getUniqueID();

        if (isNight ||IS_FORCED_NIGHT) {
            double speedBonus = isFullMoon ? 0.03 : 0.01;
            double damageBonus = isFullMoon ? 0.5 : 0.2;

            updateAttributeModifier(entity, Attributes.MOVEMENT_SPEED, id,
                    name+" Emblem Speed", speedBonus, AttributeModifier.Operation.ADDITION);

            updateAttributeModifier(entity, Attributes.ATTACK_DAMAGE, id,
                    name+" Emblem Damage", damageBonus, AttributeModifier.Operation.ADDITION);
        } else {
            removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
            removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
        }
    }

    private static void handleSunEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity, boolean isNight, boolean isSupernova, String name) {
        UUID id = emblemItem.getUniqueID();
        double multiplier = isSupernova ? 2 : 1;

        if (!isNight || IS_FORCED_DAY) {

            updateAttributeModifier(entity, Attributes.MOVEMENT_SPEED, id,
                    name+" Emblem Speed", 0.01 * multiplier, AttributeModifier.Operation.ADDITION);

            updateAttributeModifier(entity, Attributes.ATTACK_DAMAGE, id,
                    name+" Emblem Damage", 0.2 * multiplier, AttributeModifier.Operation.ADDITION);

            if (!isSupernova) {
                updateAttributeModifier(entity, ModAttributes.PHOTOSYNTHESIS.get(), id,
                        name+" Emblem Photosynthesis", SUN_EMBLEM_PHOTOSYNTHESIS_BONUS,
                        AttributeModifier.Operation.ADDITION);
            } else {
                updateAttributeModifier(entity, ModAttributes.PHOTOSYNTHESIS.get(), id,
                        name+" Emblem Photosynthesis", SUPERNOVA_EMBLEM_PHOTOSYNTHESIS_BONUS,
                        AttributeModifier.Operation.ADDITION);
            }
        } else {
            removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
            removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
            removeModifierIfPresent(entity, ModAttributes.PHOTOSYNTHESIS.get(), id);
        }
    }
    private static void handleSpiderLillyEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity, boolean isNight, boolean isRed, String name) {
        UUID id = emblemItem.getUniqueID();

        if ((!isNight || IS_FORCED_DAY) && !IS_FORCED_NIGHT) {
            if (!isRed) {
                updateAttributeModifier(entity, Attributes.ATTACK_DAMAGE, id,
                        name + " Emblem Bonus", 10, AttributeModifier.Operation.ADDITION);
            } else {
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                entity.setSecondsOnFire(3);
            }
        } else if (!IS_FORCED_DAY) {
            if (isRed) {
                updateAttributeModifier(entity, Attributes.ATTACK_DAMAGE, id,
                        name + " Emblem Bonus", 10, AttributeModifier.Operation.ADDITION);
            } else {
                removeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, id);
                entity.setTicksFrozen(300);
            }
        } else {
            updateAttributeModifier(entity, Attributes.ATTACK_DAMAGE, id,
                    name + " Emblem Bonus", 5, AttributeModifier.Operation.ADDITION);
            entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, false,false,false));
            if (entity.getTicksFrozen() > 0) {
                entity.setTicksFrozen(0);
            }
        }
    }
    private static void handleDayEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity, boolean isNight, String name) {
        UUID id = emblemItem.getUniqueID();
        if (!isNight || IS_FORCED_DAY) {
            updateAttributeModifier(entity, ModAttributes.MINING_SPEED.get(), id,
                    name+" Emblem Bonus", 0.4, AttributeModifier.Operation.ADDITION);
        } else {
            removeModifierIfPresent(entity, ModAttributes.MINING_SPEED.get(), id);
        }
    }
    private static void handleWaterEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity, String name, double miningSpeedBonus) {
        UUID id = emblemItem.getUniqueID();

        if (entity.isInWater() && entity.isUnderWater()) {
            updateAttributeModifier(entity, ModAttributes.MINING_SPEED.get(), id,
                    name+" Emblem Bonus", miningSpeedBonus, AttributeModifier.Operation.ADDITION);

        } else {
            removeModifierIfPresent(entity, ModAttributes.MINING_SPEED.get(), id);
        }
    }
    private static void handleRainEmblemEffects(ModEmblemItem emblemItem, LivingEntity entity, String name, double movementSpeedBonus) {
        UUID id = emblemItem.getUniqueID();

        if (entity.isInWater() || entity.level().isRaining() || entity.level().isThundering() || IS_FORCED_RAIN) {
            updateAttributeModifier(entity, Attributes.MOVEMENT_SPEED, id,
                    name+" Emblem Bonus", movementSpeedBonus, AttributeModifier.Operation.ADDITION);

        } else {
            removeModifierIfPresent(entity, Attributes.MOVEMENT_SPEED, id);
        }
    }

    private static void handleCrystalEmblemEquip(ModEmblemItem emblemItem, LivingEntity entity, String name) {
        UUID id = emblemItem.getUniqueID();

        applyStaticModifier(entity, ModAttributes.FORTUNE_FIST.get(), id,
                name+" Emblem Fortune", CRYSTAL_EMBLEM_FORTUNE_FIST_BONUS, AttributeModifier.Operation.ADDITION);
    }

    private static void handleSkyEmblemEquip(ModEmblemItem emblemItem, LivingEntity entity, String name) {
        UUID id = emblemItem.getUniqueID();

        applyStaticModifier(entity, ForgeMod.ENTITY_GRAVITY.get(), id,
                name+" Emblem Gravity", calculateEntitySkyEmblemGravity(entity), AttributeModifier.Operation.ADDITION);
        applyStaticModifier(entity, Attributes.MOVEMENT_SPEED, id,
                name+" Emblem Speed", 0.01, AttributeModifier.Operation.ADDITION);
        applyStaticModifier(entity, ModAttributes.FEATHER_FEET.get(), id,
                name+" Emblem Feather Feet", -0.2, AttributeModifier.Operation.ADDITION);
    }

    private static void handleHealthEmblemUnequip(LivingEntity entity, UUID id) {
        AttributeInstance healthAttribute = entity.getAttribute(Attributes.MAX_HEALTH);
        if (healthAttribute != null && healthAttribute.getModifier(id) != null) {
            healthAttribute.removeModifier(id);
            if (entity.getHealth() > entity.getMaxHealth()) {
                entity.hurt(entity.damageSources().generic(), entity.getHealth() - entity.getMaxHealth());
                entity.hurtMarked = false;
            }
        }
    }

    // Helper methods
    private static void applyStaticModifier(LivingEntity entity, Attribute attribute,
                                            UUID modifierId, String name, double amount,
                                            AttributeModifier.Operation operation) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance == null || instance.getModifier(modifierId) != null) return;
        if(attribute == Attributes.MOVEMENT_SPEED && !entity.level().getGameRules().getRule(ModGameRules.ALLOW_EMBLEM_SPEED_BONUS).get())return;
        AttributeModifier modifier = new AttributeModifier(
                modifierId, name, amount, operation);
        instance.addTransientModifier(modifier);
    }
    private static UUID getEffectUUID(ModEmblemItem emblemItem, String effectName) {
        return UUID.nameUUIDFromBytes((emblemItem.getUniqueID().toString() + effectName).getBytes());
    }

    private static void updateAttributeModifier(LivingEntity entity, Attribute attribute,
                                                UUID modifierId, String name, double amount,
                                                AttributeModifier.Operation operation) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance == null) return;
        if(attribute == Attributes.MOVEMENT_SPEED && !entity.level().getGameRules().getRule(ModGameRules.ALLOW_EMBLEM_SPEED_BONUS).get())return;
        AttributeModifier existing = instance.getModifier(modifierId);
        if (existing != null) {
            if (existing.getAmount() != amount || existing.getOperation() != operation) {
                instance.removeModifier(modifierId);
                existing = null;
            }
        }

        if (existing == null) {
            AttributeModifier modifier = new AttributeModifier(
                    modifierId, name, amount, operation);
            instance.addTransientModifier(modifier);
        }
    }

    private static void updateConditionalModifier(LivingEntity entity, Attribute attribute,
                                                  UUID modifierId, String name, double amount,
                                                  AttributeModifier.Operation operation,
                                                  boolean shouldApply) {
        if (shouldApply) {
            updateAttributeModifier(entity, attribute, modifierId, name, amount, operation);
        } else {
            removeModifierIfPresent(entity, attribute, modifierId);
        }
    }

    private static void removeModifierIfPresent(LivingEntity entity, Attribute attribute, UUID modifierId) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance != null && instance.getModifier(modifierId) != null) {
            instance.removeModifier(modifierId);
        }
    }

    // Calculation methods
    private static double calculateEntitySpeedIfOnSand(LivingEntity entity, float base, float multiplier) {
        boolean isOnSand = false;
        for (int i = 0; i < 3; i++){
            if (entity.level().getBlockState(entity.blockPosition().below(i)).is(Tags.Blocks.SAND)){
                if (i == 0 || i == 1) {
                    isOnSand = true;
                } else {
                    if (entity.level().getBlockState(entity.blockPosition().below(1)).is(Blocks.AIR)){
                        isOnSand = true;
                    }
                }
            }
        }
        return isOnSand ? base*multiplier : base;
    }

    private static boolean isEntityOn(LivingEntity entity, List<TagKey<Block>> blockTags) {
        BlockPos feetPos = entity.blockPosition().below(); // block directly under feet

        for (int i = 0; i < 2; i++) { // feet block and one deeper
            BlockPos checkPos = feetPos.below(i);
            BlockState state = entity.level().getBlockState(checkPos);

            if (blockTags.stream().anyMatch(state::is)) {
                return true;
            }
        }
        return false;
    }

    @SafeVarargs
    private static boolean isOnAny(LivingEntity entity, TagKey<Block>... tags) {
        return isEntityOn(entity, List.of(tags));
    }

    private static double calculateEntityMoonEmblemGravity(LivingEntity entity) {
        return -entity.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).getBaseValue() * 0.25;
    }

    private static double calculateEntityFullMoonEmblemGravity(LivingEntity entity) {
        return -entity.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).getBaseValue() * 0.4;
    }

    private static double calculateEntitySkyEmblemGravity(LivingEntity entity) {
        return -entity.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).getValue() * 0.2;
    }
}