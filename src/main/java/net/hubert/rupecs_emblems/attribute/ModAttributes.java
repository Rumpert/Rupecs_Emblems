package net.hubert.rupecs_emblems.attribute;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Rupecs_Emblems.MOD_ID);

    public static final RegistryObject<Attribute> MINING_SPEED =
            ATTRIBUTES.register("mining_speed",
                    () -> new RangedAttribute("attribute.rupecs_emblems.mining_speed", 1.0, 0.0, 1024.0)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> NATURE_HEALING =
            ATTRIBUTES.register("nature_healing",
                    () -> new RangedAttribute("attribute.rupecs_emblems.nature_healing", 0.0, 0.0, 1024.0)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> MOON_FAVOR =
            ATTRIBUTES.register("moon_favor",
                    () -> new RangedAttribute("attribute.rupecs_emblems.moon_favor", 0.0, 0.0, 1024.0)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> FIRE_FIST =
            ATTRIBUTES.register("fire_fist",
                    () -> new RangedAttribute("attribute.rupecs_emblems.fire_fist", 0.0, 0.0, 1024.0)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> FORTUNE_FIST =
            ATTRIBUTES.register("fortune_fist",
                    () -> new RangedAttribute("attribute.rupecs_emblems.fortune_fist", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> FRAGILE_BODY =
            ATTRIBUTES.register("fragile_body",
                    () -> new RangedAttribute("attribute.rupecs_emblems.fragile_body", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> FEATHER_FEET =
            ATTRIBUTES.register("feather_feet",
                    () -> new RangedAttribute("attribute.rupecs_emblems.feather_feet", 1, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> PHOTOSYNTHESIS =
            ATTRIBUTES.register("photosynthesis",
                    () -> new RangedAttribute("attribute.rupecs_emblems.photosynthesis", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> MOON_WRATH =
            ATTRIBUTES.register("moon_wrath",
                    () -> new RangedAttribute("attribute.rupecs_emblems.moon_wrath", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> VEGE =
            ATTRIBUTES.register("vege",
                    () -> new RangedAttribute("attribute.rupecs_emblems.vege", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> ANIMAL_TEMPTATION =
            ATTRIBUTES.register("animal_temptation",
                    () -> new RangedAttribute("attribute.rupecs_emblems.animal_temptation", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> UNDERFED =
            ATTRIBUTES.register("underfed",
                    () -> new RangedAttribute("attribute.rupecs_emblems.underfed", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> FARMERS_BLESSING =
            ATTRIBUTES.register("farmers_blessing",
                    () -> new RangedAttribute("attribute.rupecs_emblems.farmers_blessing", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> MOTHER_EARTH_BLESSING =
            ATTRIBUTES.register("mother_earth_blessing",
                    () -> new RangedAttribute("attribute.rupecs_emblems.mother_earth_blessing", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> INFERNO =
            ATTRIBUTES.register("inferno",
                    () -> new RangedAttribute("attribute.rupecs_emblems.inferno", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> MOON_JUMP =
            ATTRIBUTES.register("moon_jump",
                    () -> new RangedAttribute("attribute.rupecs_emblems.moon_jump", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> POOP =
            ATTRIBUTES.register("poop",
                    () -> new RangedAttribute("attribute.rupecs_emblems.poop", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> MERCHANT =
            ATTRIBUTES.register("merchant",
                    () -> new RangedAttribute("attribute.rupecs_emblems.merchant", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> FROST_FIST =
            ATTRIBUTES.register("frost_fist",
                    () -> new RangedAttribute("attribute.rupecs_emblems.frost_fist", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> RESPIRATION =
            ATTRIBUTES.register("respiration",
                    () -> new RangedAttribute("attribute.rupecs_emblems.respiration", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> BLIZZARD =
            ATTRIBUTES.register("blizzard",
                    () -> new RangedAttribute("attribute.rupecs_emblems.blizzard", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> FROST_WALKER =
            ATTRIBUTES.register("frost_walker",
                    () -> new RangedAttribute("attribute.rupecs_emblems.frost_walker", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> STATIC =
            ATTRIBUTES.register("static",
                    () -> new RangedAttribute("attribute.rupecs_emblems.static", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> CONDUCTOR =
            ATTRIBUTES.register("conductor",
                    () -> new RangedAttribute("attribute.rupecs_emblems.conductor", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> COILED =
            ATTRIBUTES.register("coiled",
                    () -> new RangedAttribute("attribute.rupecs_emblems.coiled", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> RETURN =
            ATTRIBUTES.register("return",
                    () -> new RangedAttribute("attribute.rupecs_emblems.return", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> ROTTEN =
            ATTRIBUTES.register("rotten",
                    () -> new RangedAttribute("attribute.rupecs_emblems.rotten", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> GOLDEN_FAVOR =
            ATTRIBUTES.register("golden_favor",
                    () -> new RangedAttribute("attribute.rupecs_emblems.golden_favor", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> ELECTRO_FIST =
            ATTRIBUTES.register("electro_fist",
                    () -> new RangedAttribute("attribute.rupecs_emblems.electro_fist", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> GAMBLER =
            ATTRIBUTES.register("gambler",
                    () -> new RangedAttribute("attribute.rupecs_emblems.gambler", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> VANISH =
            ATTRIBUTES.register("vanish",
                    () -> new RangedAttribute("attribute.rupecs_emblems.vanish", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> EGGING =
            ATTRIBUTES.register("egging",
                    () -> new RangedAttribute("attribute.rupecs_emblems.egging", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> STEAM =
            ATTRIBUTES.register("steam",
                    () -> new RangedAttribute("attribute.rupecs_emblems.steam", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> THUNDERCLAP =
            ATTRIBUTES.register("thunderclap",
                    () -> new RangedAttribute("attribute.rupecs_emblems.thunderclap", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> MOON_FEET =
            ATTRIBUTES.register("moon_feet",
                    () -> new RangedAttribute("attribute.rupecs_emblems.moon_feet", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> BUBBLE_FIST =
            ATTRIBUTES.register("bubble_fist",
                    () -> new RangedAttribute("attribute.rupecs_emblems.bubble_fist", 0, 0, 1024)
                            .setSyncable(true));

    public static final RegistryObject<Attribute> BARTERER =
            ATTRIBUTES.register("barterer",
                    () -> new RangedAttribute("attribute.rupecs_emblems.barterer", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> TIME_CONTROL =
            ATTRIBUTES.register("time_control",
                    () -> new RangedAttribute("attribute.rupecs_emblems.time_control", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> DAMAGE_REFLECTION =
            ATTRIBUTES.register("damage_reflection",
                    () -> new RangedAttribute("attribute.rupecs_emblems.damage_reflection", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> MIRRORING =
            ATTRIBUTES.register("mirroring",
                    () -> new RangedAttribute("attribute.rupecs_emblems.mirroring", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> MAGMA_WALKER =
            ATTRIBUTES.register("magma_walker",
                    () -> new RangedAttribute("attribute.rupecs_emblems.magma_walker", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> SOUL_CONNECTOR =
            ATTRIBUTES.register("soul_connector",
                    () -> new RangedAttribute("attribute.rupecs_emblems.soul_connector", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> THORNS =
            ATTRIBUTES.register("thorns",
                    () -> new RangedAttribute("attribute.rupecs_emblems.thorns", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> SNEAKY_DEFENCE =
            ATTRIBUTES.register("sneaky_defence",
                    () -> new RangedAttribute("attribute.rupecs_emblems.sneaky_defence", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> BLEEDING_FIST =
            ATTRIBUTES.register("bleeding_fist",
                    () -> new RangedAttribute("attribute.rupecs_emblems.bleeding_fist", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> WITHER_FIST =
            ATTRIBUTES.register("wither_fist",
                    () -> new RangedAttribute("attribute.rupecs_emblems.wither_fist", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> WITHERER =
            ATTRIBUTES.register("witherer",
                    () -> new RangedAttribute("attribute.rupecs_emblems.witherer", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> DANDELION_FALL =
            ATTRIBUTES.register("dandelion_fall",
                    () -> new RangedAttribute("attribute.rupecs_emblems.dandelion_fall", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> MIST =
            ATTRIBUTES.register("mist",
                    () -> new RangedAttribute("attribute.rupecs_emblems.mist", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> TIDAL_FIST =
            ATTRIBUTES.register("tidal_fist",
                    () -> new RangedAttribute("attribute.rupecs_emblems.tidal_fist", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> SPRINKLERS =
            ATTRIBUTES.register("sprinklers",
                    () -> new RangedAttribute("attribute.rupecs_emblems.sprinklers", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> SCHADENFREUDE =
            ATTRIBUTES.register("schadenfreude",
                    () -> new RangedAttribute("attribute.rupecs_emblems.schadenfreude", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> PUFF =
            ATTRIBUTES.register("puff",
                    () -> new RangedAttribute("attribute.rupecs_emblems.puff", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> MUDDY_FIST =
            ATTRIBUTES.register("muddy_fist",
                    () -> new RangedAttribute("attribute.rupecs_emblems.muddy_fist", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> FLORIST =
            ATTRIBUTES.register("florist",
                    () -> new RangedAttribute("attribute.rupecs_emblems.florist", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> RANDOM =
            ATTRIBUTES.register("random",
                    () -> new RangedAttribute("attribute.rupecs_emblems.random", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> AIR_DASHER =
            ATTRIBUTES.register("air_dasher",
                    () -> new RangedAttribute("attribute.rupecs_emblems.air_dasher", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> PHOENIX =
            ATTRIBUTES.register("phoenix",
                    () -> new RangedAttribute("attribute.rupecs_emblems.phoenix", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> PHOENIX_FLIGHT =
            ATTRIBUTES.register("phoenix_flight",
                    () -> new RangedAttribute("attribute.rupecs_emblems.phoenix_flight", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> BUTCHER_BLESSING =
            ATTRIBUTES.register("butcher_blessing",
                    () -> new RangedAttribute("attribute.rupecs_emblems.butcher_blessing", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> SKIN_TRANSPLANTER =
            ATTRIBUTES.register("skin_transplanter",
                    () -> new RangedAttribute("attribute.rupecs_emblems.skin_transplanter", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> ARROW_RETRIEVE =
            ATTRIBUTES.register("arrow_retrieve",
                    () -> new RangedAttribute("attribute.rupecs_emblems.arrow_retrieve", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> SMELTING =
            ATTRIBUTES.register("smelting",
                    () -> new RangedAttribute("attribute.rupecs_emblems.smelting", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> BLOCK_BURNING =
            ATTRIBUTES.register("block_burning",
                    () -> new RangedAttribute("attribute.rupecs_emblems.block_burning", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> SMOKING =
            ATTRIBUTES.register("smoking",
                    () -> new RangedAttribute("attribute.rupecs_emblems.smoking", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> UNTUNED =
            ATTRIBUTES.register("untuned",
                    () -> new RangedAttribute("attribute.rupecs_emblems.untuned", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> LULLABY =
            ATTRIBUTES.register("lullaby",
                    () -> new RangedAttribute("attribute.rupecs_emblems.lullaby", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> BEAT =
            ATTRIBUTES.register("beat",
                    () -> new RangedAttribute("attribute.rupecs_emblems.beat", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> TUNED =
            ATTRIBUTES.register("tuned",
                    () -> new RangedAttribute("attribute.rupecs_emblems.tuned", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> SONG =
            ATTRIBUTES.register("song",
                    () -> new RangedAttribute("attribute.rupecs_emblems.song", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> ANTHEM =
            ATTRIBUTES.register("anthem",
                    () -> new RangedAttribute("attribute.rupecs_emblems.anthem", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> I_ANTHEM =
            ATTRIBUTES.register("i_anthem",
                    () -> new RangedAttribute("attribute.rupecs_emblems.i_anthem", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> PAPER =
            ATTRIBUTES.register("paper",
                    () -> new RangedAttribute("attribute.rupecs_emblems.paper", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> RICH_SOIL =
            ATTRIBUTES.register("rich_soil",
                    () -> new RangedAttribute("attribute.rupecs_emblems.rich_soil", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> NUTRITIOUS_CROPS =
            ATTRIBUTES.register("nutritious_crops",
                    () -> new RangedAttribute("attribute.rupecs_emblems.nutritious_crops", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> LUCKY =
            ATTRIBUTES.register("lucky",
                    () -> new RangedAttribute("attribute.rupecs_emblems.lucky", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> ANVIL =
            ATTRIBUTES.register("anvil",
                    () -> new RangedAttribute("attribute.rupecs_emblems.anvil", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> FARMING =
            ATTRIBUTES.register("farming",
                    () -> new RangedAttribute("attribute.rupecs_emblems.farming", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> SNOW_WALK =
            ATTRIBUTES.register("snow_walk",
                    () -> new RangedAttribute("attribute.rupecs_emblems.snow_walk", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> ICICLE =
            ATTRIBUTES.register("icicle",
                    () -> new RangedAttribute("attribute.rupecs_emblems.icicle", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> RINK =
            ATTRIBUTES.register("rink",
                    () -> new RangedAttribute("attribute.rupecs_emblems.rink", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> YUKI =
            ATTRIBUTES.register("yuki",
                    () -> new RangedAttribute("attribute.rupecs_emblems.yuki", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> MAGNET =
            ATTRIBUTES.register("magnet",
                    () -> new RangedAttribute("attribute.rupecs_emblems.magnet", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> PULSE =
            ATTRIBUTES.register("pulse",
                    () -> new RangedAttribute("attribute.rupecs_emblems.pulse", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> LIGHTNING_ASPECT =
            ATTRIBUTES.register("lightning_aspect",
                    () -> new RangedAttribute("attribute.rupecs_emblems.lightning_aspect", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> FORTUNE_FEVER =
            ATTRIBUTES.register("fortune_fever",
                    () -> new RangedAttribute("attribute.rupecs_emblems.fortune_fever", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> LEAF_LUCK =
            ATTRIBUTES.register("leaf_luck",
                    () -> new RangedAttribute("attribute.rupecs_emblems.leaf_luck", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> NIGHT_CROPS =
            ATTRIBUTES.register("night_crops",
                    () -> new RangedAttribute("attribute.rupecs_emblems.night_crops", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> LUNARSYNTHESIS =
            ATTRIBUTES.register("lunarsynthesis",
                    () -> new RangedAttribute("attribute.rupecs_emblems.lunarsynthesis", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> LUAU =
            ATTRIBUTES.register("luau",
                    () -> new RangedAttribute("attribute.rupecs_emblems.luau", 0, 0, 1024)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> LUNG_CAPACITY =
            ATTRIBUTES.register("lung_capacity",
                    () -> new RangedAttribute("attribute.rupecs_emblems.lung_capacity", 300.0,0.0,1024.0)
                            .setSyncable(true));
    public static final RegistryObject<Attribute> VOLCANIC =
            ATTRIBUTES.register("volcanic",
                    () -> new RangedAttribute("attribute.rupecs_emblems.volcanic", 0,0.0,1024.0)
                            .setSyncable(true));


    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }
}
