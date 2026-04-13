package net.hubert.rupecs_emblems.datagen;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.item.ModItems;
import net.hubert.rupecs_emblems.util.ModTags;
import net.minecraft.advancements.Advancement;

import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider {

    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, List.of(new ModAdvancements()));
    }

    private static class ModAdvancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider provider, Consumer<Advancement> consumer) {
            Advancement root = Advancement.Builder.advancement()
                    .display(
                            ModItems.WOOD_EMBLEM.get(), // icon
                            net.minecraft.network.chat.Component.literal("Emblems"), // title
                            net.minecraft.network.chat.Component.literal("Get your first emblem"), // description
                            new ResourceLocation("minecraft", "textures/block/stone_bricks.png"),
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("got_first_emblem", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModTags.Items.EMBLEMS).build()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "root").toString());
            Advancement fTier = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            ModItems.WOOD_EMBLEM.get(),
                            Component.literal("Not so good"),
                            Component.literal("Obtain F Tier Emblem"),
                            null,
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("got_an_f",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModTags.Items.F_TIER_EMBLEMS).build()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "got_an_f").toString());
            Advancement eTier = Advancement.Builder.advancement()
                    .parent(fTier)
                    .display(
                            ModItems.SAND_EMBLEM.get(),
                            Component.literal("Is that better?"),
                            Component.literal("Obtain E Tier Emblem"),
                            null,
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("got_an_e",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModTags.Items.E_TIER_EMBLEMS).build()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "got_an_e").toString());
            Advancement dTier = Advancement.Builder.advancement()
                    .parent(eTier)
                    .display(
                            ModItems.GOLDEN_EMBLEM.get(),
                            Component.literal("That's Better"),
                            Component.literal("Obtain D Tier Emblem"),
                            null,
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("got_a_d",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModTags.Items.D_TIER_EMBLEMS).build()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "got_a_d").toString());
            Advancement cTier = Advancement.Builder.advancement()
                    .parent(dTier)
                    .display(
                            ModItems.MIRROR_EMBLEM.get(),
                            Component.literal("Finally something good"),
                            Component.literal("Obtain C Tier Emblem"),
                            null,
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("got_a_c",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModTags.Items.C_TIER_EMBLEMS).build()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "got_a_c").toString());
            Advancement bTier = Advancement.Builder.advancement()
                    .parent(cTier)
                    .display(
                            ModItems.SUPERNOVA_EMBLEM.get(),
                            Component.literal("Can it get any better?"),
                            Component.literal("Obtain B Tier Emblem"),
                            null,
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("got_a_b",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModTags.Items.B_TIER_EMBLEMS).build()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "got_a_b").toString());
            Advancement aTier = Advancement.Builder.advancement()
                    .parent(bTier)
                    .display(
                            ModItems.DEADFOREST_EMBLEM.get(),
                            Component.literal("truly \"A\"mazing"),
                            Component.literal("Obtain A Tier Emblem"),
                            null,
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("got_an_a",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModTags.Items.A_TIER_EMBLEMS).build()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "got_an_a").toString());
            Advancement sTier = Advancement.Builder.advancement()
                    .parent(aTier)
                    .display(
                            ModItems.FLOWERFOREST_EMBLEM.get(),
                            Component.literal("Perfection"),
                            Component.literal("Obtain S Tier Emblem"),
                            null,
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("got_an_s",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModTags.Items.S_TIER_EMBLEMS).build()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "got_an_s").toString());
            Advancement ssTier = Advancement.Builder.advancement()
                    .parent(sTier)
                    .display(
                            ModItems.MOTHER_EARTH_EMBLEM.get(),
                            Component.literal("High as gods and above"),
                            Component.literal("Obtain SS Tier Emblem"),
                            null,
                            FrameType.CHALLENGE,
                            true, true, false
                    )
                    .addCriterion("got_an_ss",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModTags.Items.SS_TIER_EMBLEMS).build()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "got_an_ss").toString());
            Advancement moonKnight = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            ModItems.MOON_EMBLEM.get(),
                            Component.literal("Get delusional"),
                            Component.literal("Obtain Moon and Knight emblems"),
                            null,
                            FrameType.GOAL,
                            true, true, true
                    )
                    .addCriterion("got_delusional",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MOON_EMBLEM.get(), ModItems.KNIGHT_EMBLEM.get()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "get_delusional").toString());
            Advancement itemiums = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            ModItems.BONSAI.get(),
                            Component.literal("Itemiums"),
                            Component.literal("Obtain any tier one itemium"),
                            null,
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("obtained_itemium",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModTags.Items.TIER_ONE_ITEMIUMS).build()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "obtained_itemium").toString());
            Advancement itemiumsPlus = Advancement.Builder.advancement()
                    .parent(itemiums)
                    .display(
                            ModItems.NEPTUNES_GIFT.get(),
                            Component.literal("Itemiums+"),
                            Component.literal("Obtain any tier two itemium"),
                            null,
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("obtained_tier_two_itemium",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModTags.Items.TIER_TWO_ITEMIUMS).build()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "obtained_tier_two_itemium").toString());
            Advancement itemiumsPro = Advancement.Builder.advancement()
                    .parent(itemiumsPlus)
                    .display(
                            ModItems.EAGLE_BEAK.get(),
                            Component.literal("There's More..."),
                            Component.literal("Obtain any tier three itemium"),
                            null,
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("obtained_tier_three_itemium",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModTags.Items.TIER_THREE_ITEMIUMS).build()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "obtained_tier_three_itemium").toString());
            Advancement itemiumsExt = Advancement.Builder.advancement()
                    .parent(itemiumsPro)
                    .display(
                            ModItems.LIVING_MASS.get(),
                            Component.literal("A four?"),
                            Component.literal("Obtain any tier four itemium"),
                            null,
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("obtained_tier_four_itemium",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModTags.Items.TIER_FOUR_ITEMIUMS).build()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "obtained_tier_four_itemium").toString());
            Advancement itemiumsUlt = Advancement.Builder.advancement()
                    .parent(itemiumsExt)
                    .display(
                            ModItems.EVES_APPLE.get(),
                            Component.literal("Final form"),
                            Component.literal("Obtain any tier five itemium"),
                            null,
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("obtained_tier_five_itemium",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModTags.Items.TIER_FIVE_ITEMIUMS).build()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "obtained_tier_five_itemium").toString());
            Advancement rouxlsKaard = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            ModItems.RULE_MASTER_EMBLEM.get(),
                            Component.literal("One makes the rules"),
                            Component.literal("Confront Rouxls"),
                            null,
                            FrameType.GOAL,
                            true, true, true
                    )
                    .addCriterion("obtained_rule_master_emblem",
                            InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RULE_MASTER_EMBLEM.get()))
                    .save(consumer, new ResourceLocation(Rupecs_Emblems.MOD_ID, "obtained_rule_master_emblem").toString());
        }
    }
}
