package net.hubert.rupecs_emblems.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.Collections;
import java.util.List;

public class DryMagmaBlock extends Block{
    // Define a block state property for lifetime. Adjust the max value as needed.
    public static final IntegerProperty LIFETIME = IntegerProperty.create("lifetime", 0, 100);

    public DryMagmaBlock(Properties properties, int initialLifetime) {
        super(Properties.copy(Blocks.NETHERRACK));
        // Set the default block state with the initial lifetime.
        this.registerDefaultState(this.stateDefinition.any().setValue(LIFETIME, initialLifetime));
    }
    @Override
    public List<ItemStack> getDrops(BlockState pState, LootParams.Builder pParams) {
        return Collections.emptyList();
    }



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIFETIME);
    }

    // This tick method is executed on the server side.
    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int lifetime = state.getValue(LIFETIME);
        if (lifetime <= 0) {
            level.setBlock(pos, Blocks.LAVA.defaultBlockState(), 3);
        } else {
            // Decrement lifetime and update block state.
            if (level.random.nextFloat() < 0.5f) {
                level.setBlock(pos, state.setValue(LIFETIME, lifetime - 1), 3);
            }
            // Schedule next tick in 20 ticks (1 second).
            level.scheduleTick(pos, this, 20);
        }
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
        if (!player.isCrouching()) {
            level.setBlock(pos, Blocks.LAVA.defaultBlockState(), 3);
        }
        return true;
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    // When the block is placed, schedule the first tick update.
    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            level.scheduleTick(pos, this, 20);
        }
    }

}
