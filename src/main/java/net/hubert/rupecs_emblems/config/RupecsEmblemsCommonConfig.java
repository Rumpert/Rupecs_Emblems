package net.hubert.rupecs_emblems.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class RupecsEmblemsCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> MOON_WRATH_SPAWNING_ASTEROIDS;

    static {
        BUILDER.push("Configs for Rupec's Emblems");
        MOON_WRATH_SPAWNING_ASTEROIDS = BUILDER.comment("Not In Use!").define("Is Spawning", true);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
