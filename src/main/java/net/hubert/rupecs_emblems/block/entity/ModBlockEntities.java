package net.hubert.rupecs_emblems.block.entity;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES=
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Rupecs_Emblems.MOD_ID);

    public static final RegistryObject<BlockEntityType<RealityManipulatorBlockEntity>> REALITY_MANIPULATOR =
            BLOCK_ENTITIES.register("reality_manipulator", () ->
                    BlockEntityType.Builder.of(RealityManipulatorBlockEntity::new,
                            ModBlocks.REALITY_MANIPULATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<EntherealSelectorBlockEntity>> ENTHEREAL_SELECTOR =
            BLOCK_ENTITIES.register("enthereal_selector", () ->
                    BlockEntityType.Builder.of(EntherealSelectorBlockEntity::new,
                            ModBlocks.ENTHEREAL_SELECTOR.get()).build(null));


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
