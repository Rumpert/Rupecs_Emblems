package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.providers.EmblemEffectProvider;
import net.hubert.rupecs_emblems.util.AttributeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlantGrowthHandler {
    // Slower configuration
    private static int CHECK_RADIUS = 5;          // Smaller radius
    private static int VERTICAL_CHECK = 5;        // Only 1 block above/below
    private static final int BASE_TICK_RATE = 1;        // Start with just 1 extra tick
    private static int CHECK_INTERVAL = 40;       // Check every 2 seconds (40 ticks)

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        CHECK_INTERVAL = 40;
        if (event.phase != TickEvent.Phase.END || event.side.isClient()) return;

        Player player = event.player;
        if (player.level().isClientSide || player.isSpectator() || (player.level().getDayTime() % 24000 >= 13000 && player.level().getDayTime() % 24000 <= 23000 && !(EmblemEffectProvider.IS_FORCED_DAY || player.getAttributeValue(ModAttributes.NIGHT_CROPS.get())>0))) return;

        // Check attribute
        if (player.getAttribute(ModAttributes.FARMERS_BLESSING.get()) == null ||
                player.getAttributeValue(ModAttributes.FARMERS_BLESSING.get()) <= 0) {
            return;
        }
        if (!(player.getAttribute(ModAttributes.SPRINKLERS.get()) == null ||
                player.getAttributeValue(ModAttributes.SPRINKLERS.get()) <= 0)) {
            CHECK_RADIUS = (int) Math.max(5,5+AttributeHelper.getValueIfNotNull(player,ModAttributes.SPRINKLERS.get()));
            VERTICAL_CHECK = (int) Math.max(5,5+AttributeHelper.getValueIfNotNull(player,ModAttributes.SPRINKLERS.get()));
        }

        CHECK_INTERVAL -= (int) Math.min(CHECK_INTERVAL - 1, (player.getAttribute(ModAttributes.FARMERS_BLESSING.get()).getValue() * 5));

        // Slower checking interval
        if (player.level().getGameTime() % CHECK_INTERVAL != 0) return;

        // More gradual scaling - square root of attribute value
        double growthFactor = Math.sqrt(player.getAttributeValue(ModAttributes.FARMERS_BLESSING.get()));
        int tickRateMultiplier = BASE_TICK_RATE + (int)growthFactor;

        acceleratePlantGrowth(player, tickRateMultiplier);
    }

    private static void acceleratePlantGrowth(Player player, int tickRateMultiplier) {
        ServerLevel level = (ServerLevel) player.level();
        BlockPos centerPos = player.blockPosition();

        // Get plants in smaller area
        List<BlockPos> plantPositions = findGrowablePlants(level, centerPos);

        // Only process a fraction of plants each time
        int plantsToProcess = Math.max(1, plantPositions.size() / 4);

        for (int i = 0; i < plantsToProcess; i++) {
            if (plantPositions.isEmpty()) break;

            BlockPos pos = plantPositions.remove(level.random.nextInt(plantPositions.size()));
            BlockState state = level.getBlockState(pos);

            // Apply ticks with decreasing probability based on multiplier
            for (int t = 0; t < tickRateMultiplier; t++) {
                if (level.random.nextFloat() < 0.33f) {  // Only 33% chance per tick
                    state.randomTick(level, pos, level.random);
                }
            }

            // Rare visual feedback
            if (tickRateMultiplier > 3 && level.random.nextInt(20) == 0) {
                level.levelEvent(2005, pos, 0);
            }
        }
    }

    private static List<BlockPos> findGrowablePlants(ServerLevel level, BlockPos center) {
        List<BlockPos> positions = new ArrayList<>();

        for (int x = -CHECK_RADIUS; x <= CHECK_RADIUS; x++) {
            for (int y = -VERTICAL_CHECK; y <= VERTICAL_CHECK; y++) {
                for (int z = -CHECK_RADIUS; z <= CHECK_RADIUS; z++) {
                    BlockPos pos = center.offset(x, y, z);

                    if (!level.isLoaded(pos)) continue;

                    BlockState state = level.getBlockState(pos);

                    if (state.isRandomlyTicking() && isGrowablePlant(state)) {
                        positions.add(pos);
                    }
                }
            }
        }
        return positions;
    }

    private static boolean isGrowablePlant(BlockState state) {
        Block block = state.getBlock();
        return isPlant(block) ||
                state.is(BlockTags.SAPLINGS) ||
                state.is(BlockTags.CROPS);
    }

    private static boolean isPlant(Block block) {
        return
                block instanceof StemBlock ||
                block instanceof CocoaBlock ||
                block instanceof CropBlock ||
                block instanceof GrowingPlantBlock ||
                block instanceof SugarCaneBlock ||
                block instanceof NetherWartBlock;
    }
}