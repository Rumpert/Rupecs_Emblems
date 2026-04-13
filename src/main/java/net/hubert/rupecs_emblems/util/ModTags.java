package net.hubert.rupecs_emblems.util;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.ForgeRegistry;


public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> COUNTED_AS_A_PLANT_BLOCK = tag("counted_as_a_plant_block");
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Rupecs_Emblems.MOD_ID, name));
        }
    }
    public static class Structures{
        public static final TagKey<Structure> BASTION = create("bastion_locator");

        private static TagKey<Structure> create(String name) {
            return TagKey.create(Registries.STRUCTURE, new ResourceLocation("rupecs_emblems", name));
        }
    }


    public static class Items {
        public static final TagKey<Item> F_TIER_EMBLEMS = tag("f_tier_emblems");
        public static final TagKey<Item> E_TIER_EMBLEMS = tag("e_tier_emblems");
        public static final TagKey<Item> D_TIER_EMBLEMS = tag("d_tier_emblems");
        public static final TagKey<Item> C_TIER_EMBLEMS = tag("c_tier_emblems");
        public static final TagKey<Item> B_TIER_EMBLEMS = tag("b_tier_emblems");
        public static final TagKey<Item> A_TIER_EMBLEMS = tag("a_tier_emblems");
        public static final TagKey<Item> S_TIER_EMBLEMS = tag("s_tier_emblems");
        public static final TagKey<Item> SS_TIER_EMBLEMS = tag("ss_tier_emblems");
        public static final TagKey<Item> EMBLEMS = tag("emblems");
        public static final TagKey<Item> TIER_ONE_ITEMIUMS = tag("tier_one_itemiums");
        public static final TagKey<Item> TIER_TWO_ITEMIUMS = tag("tier_two_itemiums");
        public static final TagKey<Item> TIER_THREE_ITEMIUMS = tag("tier_three_itemiums");
        public static final TagKey<Item> TIER_FOUR_ITEMIUMS = tag("tier_four_itemiums");
        public static final TagKey<Item> TIER_FIVE_ITEMIUMS = tag("tier_five_itemiums");



        public static final TagKey<Item> MEAT = tag("meat");
        public static final TagKey<Item> FOOD = tag("food");


        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Rupecs_Emblems.MOD_ID, name));
        }
    }
}
