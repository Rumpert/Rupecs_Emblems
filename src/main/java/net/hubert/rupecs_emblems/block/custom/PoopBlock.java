package net.hubert.rupecs_emblems.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.Collections;
import java.util.List;

public class PoopBlock extends Block{
    // Define a block state property for lifetime. Adjust the max value as needed.
    public static final IntegerProperty LIFETIME = IntegerProperty.create("lifetime", 0, 100);

    public PoopBlock(Properties properties, int initialLifetime) {
        super(Properties.copy(Blocks.MUD));
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
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        } else {
            // Decrement lifetime and update block state.
            level.setBlock(pos, state.setValue(LIFETIME, lifetime - 1), 3);
            // Schedule next tick in 20 ticks (1 second).
            level.scheduleTick(pos, this, 20);
        }
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

    // Particle effects are only run on the client side.
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (level.isClientSide) {
            for (int i = 0; i < 5; i++) {
                double x = pos.getX() + 0.5 + (random.nextGaussian() * 0.2);
                double y = pos.getY() + 1.1 + (random.nextDouble() * 0.3);
                double z = pos.getZ() + 0.5 + (random.nextGaussian() * 0.2);
                level.addParticle(ParticleTypes.SMOKE, x, y, z, 0, 0.02, 0);
            }
        }
    }
}
