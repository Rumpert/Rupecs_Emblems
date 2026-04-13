package net.hubert.rupecs_emblems.datagen;

import com.mojang.serialization.Codec;
import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.datagen.loot.*;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Rupecs_Emblems.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> FORTUNE_FIST =
            LOOT_MODIFIERS.register("fortune_fist", () -> FortuneFistLootModifier.CODEC
            );

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> BLOCK_BURNING =
            LOOT_MODIFIERS.register("block_burning", () -> BurnerLootModifier.CODEC
            );
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> SMELTING =
            LOOT_MODIFIERS.register("smelting", () -> SmeltingLootModifier.CODEC
            );
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> RICH_SOIL =
            LOOT_MODIFIERS.register("rich_soil", () -> RichHarvestLootModifier.CODEC
            );
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> FORTUNE_FEVER =
            LOOT_MODIFIERS.register("fortune_fever", () -> FortuneFeverLootModifier.CODEC
            );
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> LEAF_LUCK =
            LOOT_MODIFIERS.register("leaf_luck", () -> LeafLuckLootModifier.CODEC
            );



}
