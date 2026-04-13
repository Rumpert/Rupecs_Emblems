package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FrostWalkerHandler {

    private static final int MAX_VERTICAL_RANGE = 1;


    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            handleFrostWalking(player);
        }
    }

    private static void handleFrostWalking(ServerPlayer player) {
        if (shouldActivateFrostWalker(player)) {
            freezeNearbyWater(player);
        }
    }

    private static boolean shouldActivateFrostWalker(Player player) {
        // Check if the custom attribute is present and positive
        return player.getAttribute(ModAttributes.FROST_WALKER.get()) != null &&
                player.getAttributeValue(ModAttributes.FROST_WALKER.get()) > 0 &&
                player.onGround() &&
                !player.isInWater() &&
                !player.isUnderWater() &&
                !player.isCrouching();
    }

    private static void freezeNearbyWater(ServerPlayer player) {
        // Use the custom attribute value to calculate the radius
        // The effective radius is a base plus the attribute level
        int radius = (int) player.getAttributeValue(ModAttributes.FROST_WALKER.get());
        BlockPos center = player.blockPosition();

        BlockPos.betweenClosedStream(center.offset(-radius, -MAX_VERTICAL_RANGE, -radius),
                        center.offset(radius, 1, radius))
                .forEach(pos -> {
                    if (isValidWaterPosition(player.serverLevel(), pos)) {
                        freezeWaterBlock(player.serverLevel(), pos);
                    }
                });
    }

    private static void freezeWaterBlock(ServerLevel level, BlockPos pos) {
        // Only create new ice if it doesn't exist or is about to melt
        level.setBlockAndUpdate(pos, Blocks.FROSTED_ICE.defaultBlockState());

    }


    private static boolean isValidWaterPosition(ServerLevel level, BlockPos pos) {
        return level.getBlockState(pos).getBlock() == Blocks.WATER &&
                level.getBlockState(pos).getFluidState().isSource();
    }

}
