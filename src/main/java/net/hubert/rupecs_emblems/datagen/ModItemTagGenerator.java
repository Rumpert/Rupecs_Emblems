package net.hubert.rupecs_emblems.datagen;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.item.EmblemTiers;
import net.hubert.rupecs_emblems.item.ModItems;
import net.hubert.rupecs_emblems.item.custom.ItemiumItem;
import net.hubert.rupecs_emblems.item.custom.ModEmblemItem;
import net.hubert.rupecs_emblems.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Rupecs_Emblems.MOD_ID, existingFileHelper);
    }
    // Define tier order from lowest to highest
    private static final List<String> TIER_ORDER = Arrays.asList(
            "f", "e", "d", "c", "b", "a", "s", "ss"
    );
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        var curiosEmblemTag = ItemTags.create(new ResourceLocation(CuriosApi.MODID, "emblem"));

        for (Item item : ForgeRegistries.ITEMS) {
            if (item instanceof ModEmblemItem emblemItem) {
                String currentTier = emblemItem.getEmblemTier().toString().toLowerCase();

                // Add to main emblem tag
                this.tag(curiosEmblemTag).add(item);
                this.tag(ModTags.Items.EMBLEMS).add(item);
                String baseTier = currentTier;
                if (currentTier.startsWith("x")) {
                    baseTier = currentTier.substring(1); // Remove the 'x' prefix
                }
                this.tag(ItemTags.create(new ResourceLocation(CuriosApi.MODID, baseTier + "_tier_emblem"))).add(item);
                this.tag(ItemTags.create(new ResourceLocation(Rupecs_Emblems.MOD_ID, baseTier + "_tier_emblems"))).add(item);

            }
                else if (item instanceof ItemiumItem itemiumItem) {
                switch (itemiumItem.getWorkingEmblemTypes().size()) {
                    case 1:
                        this.tag(ModTags.Items.TIER_ONE_ITEMIUMS)
                                .add(
                                        item
                                );
                        break;
                    case 2:
                        this.tag(ModTags.Items.TIER_TWO_ITEMIUMS)
                                .add(
                                        item
                                );
                        break;
                    case 3:
                        this.tag(ModTags.Items.TIER_THREE_ITEMIUMS)
                                .add(
                                        item
                                );
                        break;
                    case 4:
                        this.tag(ModTags.Items.TIER_FOUR_ITEMIUMS)
                                .add(
                                        item
                                );
                        break;
                    case 5:
                        this.tag(ModTags.Items.TIER_FIVE_ITEMIUMS)
                                .add(
                                        item
                                );
                        break;
                }

            } else if (item.getDefaultInstance().getFoodProperties(null) != null) {
                if (item.getDefaultInstance().getFoodProperties(null).isMeat()) {
                    this.tag(ModTags.Items.MEAT).add(
                            item
                    );
                }
                this.tag(ModTags.Items.FOOD).add(
                        item
                );
            }
        }





    }
}
