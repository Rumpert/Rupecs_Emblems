package net.hubert.rupecs_emblems.datagen;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Rupecs_Emblems.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.POOP_BLOCK);
        blockWithItem(ModBlocks.DRY_MAGMA_BLOCK);
        blockWith3DItem(ModBlocks.REALITY_MANIPULATOR.get(), "reality_manipulator");
        blockWith3DItem(ModBlocks.ENTHEREAL_SELECTOR.get(), "enthereal_selector");



    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    private void blockWith3DItem(Block block, String modelName) {
        // Generate blockstate with given model
        simpleBlock(block, new ModelFile.UncheckedModelFile(modLoc("block/" + modelName)));

        // Generate item model with display transforms (fixes flat + full-block size)
        itemModels().withExistingParent(modelName, modLoc("block/" + modelName))
                .transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(75, 45, 0)
                .translation(0, 2.5f, 0)
                .scale(0.375f).end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                .rotation(75, 45, 0)
                .translation(0, 2.5f, 0)
                .scale(0.375f).end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(0, 45, 0)
                .scale(0.4f).end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                .rotation(0, 225, 0)
                .scale(0.4f).end()
                .transform(ItemDisplayContext.GUI)
                .rotation(30, 225, 0)
                .scale(0.625f).end()
                .transform(ItemDisplayContext.GROUND) // 👈 dropped item entity size
                .translation(0, 3, 0)
                .scale(0.25f).end()
                .transform(ItemDisplayContext.FIXED) // 👈 item frame size
                .scale(0.5f).end()
                .end();
    }

}
