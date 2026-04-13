package net.hubert.rupecs_emblems.config;

import net.minecraftforge.common.ForgeConfigSpec;
public class RupecsEmblemsClientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Configs for Rupec's Emblems");
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
