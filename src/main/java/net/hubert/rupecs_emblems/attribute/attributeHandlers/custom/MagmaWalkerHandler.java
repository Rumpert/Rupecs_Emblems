package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MagmaWalkerHandler {

    private static final int MAX_VERTICAL_RANGE = 1;


    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            handleMagmaWalking(player);
        }
    }

    private static void handleMagmaWalking(ServerPlayer player) {
        if (shouldActivateMagmaWalker(player)) {
            freezeNearbyLava(player);
        }
    }

    private static boolean shouldActivateMagmaWalker(Player player) {
        // Check if the custom attribute is present and positive
        return player.getAttribute(ModAttributes.MAGMA_WALKER.get()) != null &&
                player.getAttributeValue(ModAttributes.MAGMA_WALKER.get()) > 0 &&
                player.onGround() &&
                !player.isInLava() &&
                !player.isUnderWater() &&
                !player.isCrouching();
    }

    private static void freezeNearbyLava(ServerPlayer player) {
        // Use the custom attribute value to calculate the radius
        // The effective radius is a base plus the attribute level
        int radius = (int) player.getAttributeValue(ModAttributes.MAGMA_WALKER.get());
        BlockPos center = player.blockPosition();

        BlockPos.betweenClosedStream(center.offset(-radius, -MAX_VERTICAL_RANGE, -radius),
                        center.offset(radius, 1, radius))
                .forEach(pos -> {
                    if (isValidLavaPosition(player.serverLevel(), pos)) {
                        freezeLavaBlock(player.serverLevel(), pos);
                    }
                });
    }

    private static void freezeLavaBlock(ServerLevel level, BlockPos pos) {
        // Only create new ice if it doesn't exist or is about to melt
        level.setBlockAndUpdate(pos, ModBlocks.DRY_MAGMA_BLOCK.get().defaultBlockState());

    }


    private static boolean isValidLavaPosition(ServerLevel level, BlockPos pos) {
        return (level.getBlockState(pos).getBlock() == Blocks.LAVA &&
                level.getBlockState(pos).getFluidState().isSource()) || level.getBlockState(pos).getBlock() == ModBlocks.DRY_MAGMA_BLOCK.get();
    }

}
