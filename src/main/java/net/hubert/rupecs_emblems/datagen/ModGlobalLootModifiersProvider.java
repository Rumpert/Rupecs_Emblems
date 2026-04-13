package net.hubert.rupecs_emblems.datagen;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.datagen.loot.*;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, Rupecs_Emblems.MOD_ID);
    }

    @Override
    protected void start() {
        // Registers the JSON automatically
        add("fortune_fist", new FortuneFistLootModifier(new LootItemCondition[]{}));
        add("block_burner", new BurnerLootModifier(new LootItemCondition[]{}));
        add("smelting", new SmeltingLootModifier(new LootItemCondition[]{}));
        add("rich_soil", new RichHarvestLootModifier(new LootItemCondition[]{}));
        add("fortune_fever", new FortuneFeverLootModifier(new LootItemCondition[]{}));
        add("leaf_luck", new LeafLuckLootModifier(new LootItemCondition[]{}));
    }
}
