package net.hubert.rupecs_emblems.block;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.block.custom.DryMagmaBlock;
import net.hubert.rupecs_emblems.block.custom.EntherealSelector;
import net.hubert.rupecs_emblems.block.custom.PoopBlock;
import net.hubert.rupecs_emblems.block.custom.RealityManipulator;
import net.hubert.rupecs_emblems.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Rupecs_Emblems.MOD_ID);


    public static final RegistryObject<Block> POOP_BLOCK = registerBlock("poop_block",
            () -> new PoopBlock(BlockBehaviour.Properties.copy(Blocks.MUD).noLootTable(), 2));
    public static final RegistryObject<Block> DRY_MAGMA_BLOCK = registerBlock("dry_magma_block",
            () -> new DryMagmaBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK).noLootTable(), 3));
    public static final RegistryObject<Block> REALITY_MANIPULATOR = registerBlock("reality_manipulator",
            () -> new RealityManipulator(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().destroyTime(4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENTHEREAL_SELECTOR = registerBlock("enthereal_selector",
            () -> new EntherealSelector(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().destroyTime(4).requiresCorrectToolForDrops()));






    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
