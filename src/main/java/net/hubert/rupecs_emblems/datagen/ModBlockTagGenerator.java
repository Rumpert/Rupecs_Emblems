package net.hubert.rupecs_emblems.datagen;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.block.ModBlocks;
import net.hubert.rupecs_emblems.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Rupecs_Emblems.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.COUNTED_AS_A_PLANT_BLOCK)
                .add(
                        Blocks.GRASS_BLOCK,
                        Blocks.MOSS_BLOCK,
                        Blocks.MOSS_CARPET
                );

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
                ModBlocks.REALITY_MANIPULATOR.get()
        );
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.REALITY_MANIPULATOR.get()
        );

    }
}
